package farmville;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal del simulador de granja.
 *
 * Presenta un menú por consola para administrar
 * las parcelas, los cultivos y los animales.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Método principal del programa.
     *
     * @param args argumentos de ejecución
     */
    public static void main(String[] args) {

        mostrarTitulo();

        Jugador jugador = iniciarPartida();

        int opcion;

        do {
            mostrarMenu(jugador);

            opcion = leerEntero(
                    "Seleccione una opción: "
            );

            switch (opcion) {
                case 1:
                    jugador.getGranja().mostrarEstado();
                    break;

                case 2:
                    comprarParcela(jugador);
                    break;

                case 3:
                    sembrarCultivo(jugador);
                    break;

                case 4:
                    cosecharCultivo(jugador);
                    break;

                case 5:
                    comprarAnimal(jugador);
                    break;

                case 6:
                    alimentarAnimal(jugador);
                    break;

                case 7:
                    recolectarProducto(jugador);
                    break;

                case 8:
                    mostrarSaldo(jugador);
                    break;

                case 9:
                    simularAvanceTiempo(jugador);
                    break;

                case 10:
                    guardarPartida(jugador);

                    System.out.println();
                    System.out.println("Gracias por jugar.");
                    System.out.println(
                            "Saliendo de FARMVILLE - CONSOLA..."
                    );
                    break;

                default:
                    System.out.println();
                    System.out.println("Opción inválida.");
                    System.out.println(
                            "Seleccione una opción del 1 al 10."
                    );
            }

        } while (opcion != 10);

        scanner.close();
    }

    /**
     * Muestra el título principal del programa.
     */
    private static void mostrarTitulo() {

        System.out.println("====================================");
        System.out.println("        FARMVILLE - CONSOLA");
        System.out.println("====================================");
    }

    /**
     * Inicia una partida nueva o carga una existente.
     *
     * @return jugador de la partida
     */
    private static Jugador iniciarPartida() {

        if (Persistencia.existePartida()) {

            System.out.println();
            System.out.print(
                    "Se encontró una partida guardada. "
                            + "¿Desea cargarla? (S/N): "
            );

            String respuesta =
                    scanner.nextLine().trim();

            if (respuesta.equalsIgnoreCase("S")) {

                try {
                    Jugador jugador =
                            Persistencia.cargar();

                    if (jugador != null) {
                        System.out.println();
                        System.out.println(
                                "Partida cargada correctamente."
                        );

                        return jugador;
                    }

                } catch (IOException
                         | IllegalArgumentException e) {

                    System.out.println();
                    System.out.println(
                            "No fue posible cargar la partida."
                    );

                    System.out.println(
                            "Motivo: " + e.getMessage()
                    );
                }
            }
        }

        String nombreJugador =
                leerNombreJugador();

        return new Jugador(nombreJugador);
    }

    /**
     * Guarda la partida actual.
     *
     * @param jugador jugador que se desea guardar
     */
    private static void guardarPartida(
            Jugador jugador) {

        try {
            Persistencia.guardar(jugador);

            System.out.println();
            System.out.println(
                    "Partida guardada correctamente."
            );

        } catch (IOException e) {

            System.out.println();
            System.out.println(
                    "No fue posible guardar la partida."
            );

            System.out.println(
                    "Motivo: " + e.getMessage()
            );
        }
    }

    /**
     * Solicita y valida el nombre del jugador.
     *
     * @return nombre válido
     */
    private static String leerNombreJugador() {

        String nombre;

        do {
            System.out.print(
                    "Ingrese el nombre del jugador: "
            );

            nombre =
                    scanner.nextLine().trim();

            if (nombre.isEmpty()) {
                System.out.println(
                        "El nombre no puede estar vacío."
                );
            }

        } while (nombre.isEmpty());

        return nombre;
    }

    /**
     * Muestra el menú principal.
     *
     * @param jugador jugador actual
     */
    private static void mostrarMenu(
            Jugador jugador) {

        System.out.println();
        System.out.println("====================================");
        System.out.println("        FARMVILLE - CONSOLA");
        System.out.println("====================================");
        System.out.println(
                "Jugador: " + jugador.getNombre()
        );
        System.out.println(
                "Monedas: " + jugador.verSaldo()
        );
        System.out.println();
        System.out.println("1. Ver estado de la granja");
        System.out.println("2. Comprar parcela");
        System.out.println("3. Sembrar cultivo");
        System.out.println("4. Cosechar cultivo");
        System.out.println("5. Comprar animal");
        System.out.println("6. Alimentar animal");
        System.out.println("7. Recolectar productos");
        System.out.println("8. Ver saldo");
        System.out.println("9. Simular avance de tiempo");
        System.out.println("10. Salir");
        System.out.println();
    }

    /**
     * Compra una nueva parcela.
     *
     * @param jugador jugador que realiza la compra
     */
    private static void comprarParcela(
            Jugador jugador) {

        System.out.println();
        System.out.println(
                "Costo de una parcela: 20 monedas."
        );

        boolean comprada =
                jugador.comprarParcela();

        if (comprada) {

            int numero =
                    jugador.getGranja()
                            .getParcelas().size();

            System.out.println(
                    "Parcela número " + numero
                            + " comprada correctamente."
            );

        } else {
            System.out.println(
                    "No tienes suficientes monedas "
                            + "para comprar una parcela."
            );
        }
    }

    /**
     * Permite sembrar un cultivo.
     *
     * @param jugador jugador que realiza la acción
     */
    private static void sembrarCultivo(
            Jugador jugador) {

        mostrarParcelas(jugador);

        int numeroParcela =
                leerEntero(
                        "Seleccione el número de la parcela: "
                );

        Parcela parcela =
                buscarParcela(
                        jugador,
                        numeroParcela
                );

        if (parcela == null) {
            System.out.println(
                    "La parcela seleccionada no existe."
            );
            return;
        }

        if (!parcela.estaVacia()) {
            System.out.println(
                    "La parcela ya tiene un cultivo sembrado."
            );
            return;
        }

        Cultivo cultivo =
                seleccionarCultivo();

        if (cultivo == null) {
            return;
        }

        boolean saldoSuficiente =
                jugador.descontarMonedas(
                        cultivo.getCosto()
                );

        if (!saldoSuficiente) {
            System.out.println();
            System.out.println(
                    "No tienes suficientes monedas para sembrar "
                            + cultivo.getNombre() + "."
            );
            return;
        }

        boolean sembrado =
                parcela.sembrar(cultivo);

        if (sembrado) {
            System.out.println();
            System.out.println(
                    cultivo.getNombre()
                            + " sembrado correctamente "
                            + "en la parcela "
                            + parcela.getNumero() + "."
            );

        } else {
            jugador.agregarMonedas(
                    cultivo.getCosto()
            );

            System.out.println(
                    "No fue posible sembrar el cultivo."
            );
        }
    }

    /**
     * Muestra los cultivos disponibles.
     *
     * @return cultivo seleccionado
     */
    private static Cultivo seleccionarCultivo() {

        System.out.println();
        System.out.println("CULTIVOS DISPONIBLES");
        System.out.println(
                "1. Trigo - Costo: 5 - Ganancia: 15"
        );
        System.out.println(
                "2. Maíz - Costo: 8 - Ganancia: 20"
        );
        System.out.println(
                "3. Papa - Costo: 10 - Ganancia: 25"
        );
        System.out.println(
                "4. Zanahoria - Costo: 12 - Ganancia: 30"
        );
        System.out.println(
                "5. Lechuga - Costo: 14 - Ganancia: 35"
        );
        System.out.println(
                "6. Tomate - Costo: 16 - Ganancia: 40"
        );
        System.out.println(
                "7. Calabaza - Costo: 18 - Ganancia: 45"
        );

        int opcion =
                leerEntero(
                        "Seleccione un cultivo: "
                );

        switch (opcion) {
            case 1:
                return new Trigo();

            case 2:
                return new Maiz();

            case 3:
                return new Papa();

            case 4:
                return new Zanahoria();

            case 5:
                return new Lechuga();

            case 6:
                return new Tomate();

            case 7:
                return new Calabaza();

            default:
                System.out.println(
                        "Cultivo seleccionado inválido."
                );
                return null;
        }
    }

    /**
     * Cosecha el cultivo de una parcela.
     *
     * @param jugador jugador que realiza la cosecha
     */
    private static void cosecharCultivo(
            Jugador jugador) {

        mostrarParcelas(jugador);

        int numeroParcela =
                leerEntero(
                        "Seleccione la parcela que desea cosechar: "
                );

        Parcela parcela =
                buscarParcela(
                        jugador,
                        numeroParcela
                );

        if (parcela == null) {
            System.out.println(
                    "La parcela seleccionada no existe."
            );
            return;
        }

        /*
         * Esta validación evita intentar obtener el nombre
         * de un cultivo cuando la parcela está vacía.
         */
        if (parcela.estaVacia()) {
            System.out.println(
                    "La parcela "
                            + parcela.getNumero()
                            + " está vacía."
            );
            return;
        }

        try {
            String nombreCultivo =
                    parcela.getCultivo().getNombre();

            int ganancia =
                    parcela.cosechar();

            jugador.agregarMonedas(
                    ganancia
            );

            System.out.println();
            System.out.println(
                    "Has cosechado "
                            + nombreCultivo + "."
            );

            System.out.println(
                    "Ganancia obtenida: "
                            + ganancia
                            + " monedas."
            );

        } catch (ParcelaVaciaException e) {
            System.out.println();
            System.out.println(
                    e.getMessage()
            );

        } catch (CultivoNoListoException e) {
            System.out.println();
            System.out.println(
                    e.getMessage()
            );
        }
    }

    /**
     * Permite comprar un animal.
     *
     * @param jugador jugador que realiza la compra
     */
    private static void comprarAnimal(
            Jugador jugador) {

        Animal animal =
                seleccionarAnimal();

        if (animal == null) {
            return;
        }

        boolean comprado =
                jugador.comprarAnimal(animal);

        if (comprado) {
            System.out.println();
            System.out.println(
                    animal.getNombre()
                            + " comprado correctamente."
            );

        } else {
            System.out.println();
            System.out.println(
                    "No tienes suficientes monedas para comprar "
                            + animal.getNombre() + "."
            );
        }
    }

    /**
     * Muestra los animales disponibles.
     *
     * @return animal seleccionado
     */
    private static Animal seleccionarAnimal() {

        System.out.println();
        System.out.println("ANIMALES DISPONIBLES");
        System.out.println(
                "1. Gallina - Costo: 30 monedas"
        );
        System.out.println(
                "2. Oveja - Costo: 40 monedas"
        );
        System.out.println(
                "3. Vaca - Costo: 50 monedas"
        );

        int opcion =
                leerEntero(
                        "Seleccione un animal: "
                );

        switch (opcion) {
            case 1:
                return new Gallina();

            case 2:
                return new Oveja();

            case 3:
                return new Vaca();

            default:
                System.out.println(
                        "Animal seleccionado inválido."
                );
                return null;
        }
    }

    /**
     * Alimenta un animal de la granja.
     *
     * @param jugador jugador actual
     */
    private static void alimentarAnimal(
            Jugador jugador) {

        List<Animal> animales =
                jugador.getGranja()
                        .getAnimales();

        if (animales.isEmpty()) {
            System.out.println();
            System.out.println(
                    "No tienes animales en la granja."
            );
            return;
        }

        mostrarAnimales(jugador);

        int posicion =
                leerEntero(
                        "Seleccione el animal que desea alimentar: "
                );

        if (posicion < 1
                || posicion > animales.size()) {

            System.out.println(
                    "El animal seleccionado no existe."
            );
            return;
        }

        Animal animal =
                animales.get(posicion - 1);

        if (animal.estaAlimentado()) {
            System.out.println();
            System.out.println(
                    animal.getNombre()
                            + " ya se encuentra alimentado."
            );
            return;
        }

        animal.alimentar();

        System.out.println();
        System.out.println(
                animal.getNombre()
                        + " alimentado correctamente."
        );
    }

    /**
     * Recolecta y vende automáticamente
     * el producto generado por un animal.
     *
     * @param jugador jugador actual
     */
    private static void recolectarProducto(
            Jugador jugador) {

        List<Animal> animales =
                jugador.getGranja()
                        .getAnimales();

        if (animales.isEmpty()) {
            System.out.println();
            System.out.println(
                    "No tienes animales en la granja."
            );
            return;
        }

        mostrarAnimales(jugador);

        int posicion =
                leerEntero(
                        "Seleccione el animal: "
                );

        if (posicion < 1
                || posicion > animales.size()) {

            System.out.println(
                    "El animal seleccionado no existe."
            );
            return;
        }

        Animal animal =
                animales.get(posicion - 1);

        try {
            Producto producto =
                    animal.producir();

            int precioVenta =
                    producto.vender();

            jugador.agregarMonedas(
                    precioVenta
            );

            System.out.println();
            System.out.println(
                    animal.getNombre()
                            + " produjo "
                            + producto.getNombre() + "."
            );

            System.out.println(
                    producto.getNombre()
                            + " vendido por "
                            + precioVenta
                            + " monedas."
            );

        } catch (AnimalNoAlimentadoException e) {
            System.out.println();
            System.out.println(
                    e.getMessage()
            );
        }
    }

    /**
     * Muestra el saldo actual.
     *
     * @param jugador jugador actual
     */
    private static void mostrarSaldo(
            Jugador jugador) {

        System.out.println();
        System.out.println(
                "Saldo actual: "
                        + jugador.verSaldo()
                        + " monedas."
        );
    }

    /**
     * Hace crecer todos los cultivos pendientes.
     *
     * @param jugador jugador actual
     */
    private static void simularAvanceTiempo(
            Jugador jugador) {

        int cultivosCrecidos = 0;

        for (Parcela parcela
                : jugador.getGranja()
                .getParcelas()) {

            if (!parcela.estaVacia()
                    && !parcela.getCultivo()
                    .estaListo()) {

                parcela.getCultivo().crecer();

                cultivosCrecidos++;
            }
        }

        System.out.println();

        if (cultivosCrecidos == 0) {
            System.out.println(
                    "No hay cultivos pendientes de crecimiento."
            );

        } else {
            System.out.println(
                    "El tiempo avanzó correctamente."
            );

            System.out.println(
                    cultivosCrecidos
                            + " cultivo(s) están listos "
                            + "para cosechar."
            );
        }
    }

    /**
     * Muestra todas las parcelas.
     *
     * @param jugador jugador actual
     */
    private static void mostrarParcelas(
            Jugador jugador) {

        System.out.println();
        System.out.println("PARCELAS");

        for (Parcela parcela
                : jugador.getGranja()
                .getParcelas()) {

            if (parcela.estaVacia()) {
                System.out.println(
                        parcela.getNumero()
                                + ". Vacía"
                );

            } else {
                Cultivo cultivo =
                        parcela.getCultivo();

                String estado;

                if (cultivo.estaListo()) {
                    estado =
                            "Listo para cosechar";
                } else {
                    estado =
                            "En crecimiento";
                }

                System.out.println(
                        parcela.getNumero()
                                + ". "
                                + cultivo.getNombre()
                                + " - "
                                + estado
                );
            }
        }
    }

    /**
     * Muestra todos los animales.
     *
     * @param jugador jugador actual
     */
    private static void mostrarAnimales(
            Jugador jugador) {

        System.out.println();
        System.out.println("ANIMALES");

        List<Animal> animales =
                jugador.getGranja()
                        .getAnimales();

        for (int i = 0;
             i < animales.size();
             i++) {

            Animal animal =
                    animales.get(i);

            String estado;

            if (animal.estaAlimentado()) {
                estado = "Alimentado";
            } else {
                estado = "Sin alimentar";
            }

            System.out.println(
                    (i + 1)
                            + ". "
                            + animal.getNombre()
                            + " - "
                            + estado
            );
        }
    }

    /**
     * Busca una parcela por su número.
     *
     * @param jugador jugador actual
     * @param numero número de parcela
     * @return parcela encontrada o null
     */
    private static Parcela buscarParcela(
            Jugador jugador,
            int numero) {

        for (Parcela parcela
                : jugador.getGranja()
                .getParcelas()) {

            if (parcela.getNumero()
                    == numero) {

                return parcela;
            }
        }

        return null;
    }

    /**
     * Lee un número entero y controla
     * los errores de entrada.
     *
     * @param mensaje mensaje mostrado
     * @return número ingresado
     */
    private static int leerEntero(
            String mensaje) {

        boolean entradaValida = false;
        int numero = 0;

        while (!entradaValida) {

            try {
                System.out.print(mensaje);

                String entrada =
                        scanner.nextLine();

                numero =
                        Integer.parseInt(entrada);

                entradaValida = true;

            } catch (NumberFormatException e) {
                System.out.println(
                        "Entrada inválida. "
                                + "Debe escribir un número entero."
                );
            }
        }

        return numero;
    }
}
