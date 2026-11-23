package farmville;

/**
 * Representa un cultivo de calabaza.
 */
public class Calabaza extends Cultivo {

    /**
     * Constructor de la clase Calabaza.
     */
    public Calabaza() {
        super("Calabaza", 18, 45);
    }

    /**
     * Cosecha la calabaza.
     *
     * @return ganancia obtenida
     */
    @Override
    public int cosechar() {
        return realizarCosecha();
    }
}
