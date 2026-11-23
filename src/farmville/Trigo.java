package farmville;

/**
 * Representa un cultivo de trigo.
 */
public class Trigo extends Cultivo {

    /**
     * Constructor de la clase Trigo.
     */
    public Trigo() {
        super("Trigo", 5, 15);
    }

    /**
     * Cosecha el trigo.
     *
     * @return ganancia obtenida
     */
    @Override
    public int cosechar() {
        return realizarCosecha();
    }
}
