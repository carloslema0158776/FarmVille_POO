package farmville;

/**
 * Representa un cultivo de tomate.
 */
public class Tomate extends Cultivo {

    /**
     * Constructor de la clase Tomate.
     */
    public Tomate() {
        super("Tomate", 16, 40);
    }

    /**
     * Cosecha el tomate.
     *
     * @return ganancia obtenida
     */
    @Override
    public int cosechar() {
        return realizarCosecha();
    }
}
