package farmville;

/**
 * Representa al jugador que administra la granja.
 */
public class Jugador {

    private String nombre;
    private int monedas;
    private Granja granja;

    /**
     * Constructor de la clase Jugador.
     *
     * El jugador comienza con 100 monedas.
     *
     * @param nombre nombre del jugador
     */
    public Jugador(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El nombre del jugador no puede estar vacío."
            );
        }

        this.nombre = nombre.trim();
        this.monedas = 100;
        this.granja = new Granja();
    }

    /**
     * Compra una nueva parcela por 20 monedas.
     *
     * @return true si pudo comprarla; false si no tenía saldo
     */
    public boolean comprarParcela() {
        int costoParcela = 20;

        if (!descontarMonedas(costoParcela)) {
            return false;
        }

        int numeroNuevaParcela =
                granja.getParcelas().size() + 1;

        Parcela nuevaParcela =
                new Parcela(numeroNuevaParcela);

        granja.agregarParcela(nuevaParcela);

        return true;
    }

    /**
     * Compra un animal y lo agrega a la granja.
     *
     * @param animal animal que se desea comprar
     * @return true si se realizó la compra; false si no había saldo
     */
    public boolean comprarAnimal(Animal animal) {
        if (animal == null) {
            throw new IllegalArgumentException(
                    "El animal no puede ser nulo."
            );
        }

        if (!descontarMonedas(animal.getCosto())) {
            return false;
        }

        granja.agregarAnimal(animal);

        return true;
    }

    /**
     * Agrega monedas al saldo del jugador.
     *
     * @param cantidad cantidad de monedas que se agregará
     */
    public void agregarMonedas(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad debe ser mayor que cero."
            );
        }

        monedas = monedas + cantidad;
    }

    /**
     * Descuenta monedas del saldo.
     *
     * @param cantidad cantidad que se desea descontar
     * @return true si se pudo descontar; false si no había saldo
     */
    public boolean descontarMonedas(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad debe ser mayor que cero."
            );
        }

        if (monedas < cantidad) {
            return false;
        }

        monedas = monedas - cantidad;

        return true;
    }

    /**
     * Obtiene el saldo actual.
     *
     * @return cantidad actual de monedas
     */
    public int verSaldo() {
        return monedas;
    }

    /**
     * Obtiene el nombre del jugador.
     *
     * @return nombre del jugador
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la granja del jugador.
     *
     * @return granja administrada por el jugador
     */
    public Granja getGranja() {
        return granja;
    }
}
