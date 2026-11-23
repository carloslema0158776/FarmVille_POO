package farmville;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Permite guardar y cargar la partida del jugador
 * mediante un archivo de texto.
 */
public class Persistencia {

    private static final String NOMBRE_ARCHIVO =
            "partida_farmville.txt";

    /**
     * Verifica si existe una partida guardada.
     *
     * @return true si existe el archivo de partida
     */
    public static boolean existePartida() {
        Path ruta = Path.of(NOMBRE_ARCHIVO);
        return Files.exists(ruta);
    }

    /**
     * Guarda todos los datos importantes del jugador.
     *
     * @param jugador jugador que se desea guardar
     * @throws IOException si ocurre un problema con el archivo
     */
    public static void guardar(Jugador jugador)
            throws IOException {

        Path ruta = Path.of(NOMBRE_ARCHIVO);

        try (BufferedWriter escritor =
                     Files.newBufferedWriter(
                             ruta,
                             StandardCharsets.UTF_8)) {

            escritor.write(
                    "JUGADOR|" + jugador.getNombre()
            );
            escritor.newLine();

            escritor.write(
                    "MONEDAS|" + jugador.verSaldo()
            );
            escritor.newLine();

            for (Parcela parcela
                    : jugador.getGranja().getParcelas()) {

                String codigoCultivo = "VACIA";
                boolean listo = false;

                if (!parcela.estaVacia()) {
                    codigoCultivo =
                            obtenerCodigoCultivo(
                                    parcela.getCultivo());

                    listo =
                            parcela.getCultivo().estaListo();
                }

                escritor.write(
                        "PARCELA|"
                                + parcela.getNumero() + "|"
                                + codigoCultivo + "|"
                                + listo
                );

                escritor.newLine();
            }

            for (Animal animal
                    : jugador.getGranja().getAnimales()) {

                escritor.write(
                        "ANIMAL|"
                                + obtenerCodigoAnimal(animal)
                                + "|"
                                + animal.estaAlimentado()
                );

                escritor.newLine();
            }
        }
    }

    /**
     * Carga una partida guardada.
     *
     * @return jugador recuperado o null si no existe partida
     * @throws IOException si el archivo contiene errores
     */
    public static Jugador cargar()
            throws IOException {

        Path ruta = Path.of(NOMBRE_ARCHIVO);

        if (!Files.exists(ruta)) {
            return null;
        }

        List<String> lineas =
                Files.readAllLines(
                        ruta,
                        StandardCharsets.UTF_8);

        if (lineas.size() < 2) {
            throw new IOException(
                    "El archivo de la partida está incompleto."
            );
        }

        try {
            String[] datosJugador =
                    lineas.get(0).split("\\|", 2);

            String[] datosMonedas =
                    lineas.get(1).split("\\|", 2);

            if (datosJugador.length < 2
                    || datosMonedas.length < 2) {

                throw new IOException(
                        "Los datos principales son inválidos."
                );
            }

            String nombre = datosJugador[1];
            int monedas =
                    Integer.parseInt(datosMonedas[1]);

            Jugador jugador =
                    new Jugador(nombre);

            ajustarMonedas(jugador, monedas);

            /*
             * Se eliminan los elementos iniciales para
             * reconstruir la granja guardada.
             */
            jugador.getGranja()
                    .getParcelas().clear();

            jugador.getGranja()
                    .getAnimales().clear();

            for (int i = 2; i < lineas.size(); i++) {

                String[] datos =
                        lineas.get(i).split("\\|");

                if (datos.length == 0) {
                    continue;
                }

                if (datos[0].equals("PARCELA")) {
                    cargarParcela(jugador, datos);
                }

                if (datos[0].equals("ANIMAL")) {
                    cargarAnimal(jugador, datos);
                }
            }

            /*
             * Si el archivo no tenía parcelas,
             * se crea una parcela inicial.
             */
            if (jugador.getGranja()
                    .getParcelas().isEmpty()) {

                jugador.getGranja()
                        .agregarParcela(
                                new Parcela(1));
            }

            return jugador;

        } catch (NumberFormatException e) {
            throw new IOException(
                    "La partida contiene números inválidos.",
                    e
            );
        }
    }

    /**
     * Reconstruye una parcela desde el archivo.
     *
     * @param jugador jugador cargado
     * @param datos datos de la parcela
     * @throws IOException si los datos están incompletos
     */
    private static void cargarParcela(
            Jugador jugador,
            String[] datos) throws IOException {

        if (datos.length < 4) {
            throw new IOException(
                    "Los datos de una parcela están incompletos."
            );
        }

        int numero =
                Integer.parseInt(datos[1]);

        String codigoCultivo = datos[2];

        boolean listo =
                Boolean.parseBoolean(datos[3]);

        Parcela parcela =
                new Parcela(numero);

        if (!codigoCultivo.equals("VACIA")) {

            Cultivo cultivo =
                    crearCultivo(codigoCultivo);

            if (cultivo == null) {
                throw new IOException(
                        "El cultivo guardado no es válido."
                );
            }

            parcela.sembrar(cultivo);

            if (listo) {
                cultivo.crecer();
            }
        }

        jugador.getGranja()
                .agregarParcela(parcela);
    }

    /**
     * Reconstruye un animal desde el archivo.
     *
     * @param jugador jugador cargado
     * @param datos datos del animal
     * @throws IOException si los datos están incompletos
     */
    private static void cargarAnimal(
            Jugador jugador,
            String[] datos) throws IOException {

        if (datos.length < 3) {
            throw new IOException(
                    "Los datos de un animal están incompletos."
            );
        }

        Animal animal =
                crearAnimal(datos[1]);

        if (animal == null) {
            throw new IOException(
                    "El animal guardado no es válido."
            );
        }

        boolean alimentado =
                Boolean.parseBoolean(datos[2]);

        if (alimentado) {
            animal.alimentar();
        }

        jugador.getGranja()
                .agregarAnimal(animal);
    }

    /**
     * Ajusta el saldo inicial de 100 monedas
     * al saldo almacenado en el archivo.
     *
     * @param jugador jugador cargado
     * @param monedas saldo que debe recuperarse
     */
    private static void ajustarMonedas(
            Jugador jugador,
            int monedas) {

        if (monedas < 0) {
            throw new IllegalArgumentException(
                    "El saldo guardado no puede ser negativo."
            );
        }

        int saldoActual =
                jugador.verSaldo();

        if (monedas > saldoActual) {
            jugador.agregarMonedas(
                    monedas - saldoActual);
        }

        if (monedas < saldoActual) {
            jugador.descontarMonedas(
                    saldoActual - monedas);
        }
    }

    /**
     * Obtiene un código para guardar el tipo de cultivo.
     *
     * @param cultivo cultivo que se desea identificar
     * @return código del cultivo
     */
    private static String obtenerCodigoCultivo(
            Cultivo cultivo) {

        if (cultivo instanceof Trigo) {
            return "TRIGO";
        }

        if (cultivo instanceof Maiz) {
            return "MAIZ";
        }

        if (cultivo instanceof Papa) {
            return "PAPA";
        }

        if (cultivo instanceof Zanahoria) {
            return "ZANAHORIA";
        }

        if (cultivo instanceof Lechuga) {
            return "LECHUGA";
        }

        if (cultivo instanceof Tomate) {
            return "TOMATE";
        }

        if (cultivo instanceof Calabaza) {
            return "CALABAZA";
        }

        return "VACIA";
    }

    /**
     * Crea un cultivo según el código guardado.
     *
     * @param codigo código del cultivo
     * @return cultivo correspondiente
     */
    private static Cultivo crearCultivo(
            String codigo) {

        switch (codigo) {
            case "TRIGO":
                return new Trigo();

            case "MAIZ":
                return new Maiz();

            case "PAPA":
                return new Papa();

            case "ZANAHORIA":
                return new Zanahoria();

            case "LECHUGA":
                return new Lechuga();

            case "TOMATE":
                return new Tomate();

            case "CALABAZA":
                return new Calabaza();

            default:
                return null;
        }
    }

    /**
     * Obtiene un código para guardar el tipo de animal.
     *
     * @param animal animal que se desea identificar
     * @return código del animal
     */
    private static String obtenerCodigoAnimal(
            Animal animal) {

        if (animal instanceof Gallina) {
            return "GALLINA";
        }

        if (animal instanceof Vaca) {
            return "VACA";
        }

        if (animal instanceof Oveja) {
            return "OVEJA";
        }

        return "DESCONOCIDO";
    }

    /**
     * Crea un animal según el código guardado.
     *
     * @param codigo código del animal
     * @return animal correspondiente
     */
    private static Animal crearAnimal(
            String codigo) {

        switch (codigo) {
            case "GALLINA":
                return new Gallina();

            case "VACA":
                return new Vaca();

            case "OVEJA":
                return new Oveja();

            default:
                return null;
        }
    }
}
