package farmville;

/**
 * Representa un cultivo de lechuga.
 */
public class Lechuga extends Cultivo {

    /**
     * Constructor de la clase Lechuga.
     */
    public Lechuga() {
        super("Lechuga", 14, 35);
    }

    /**
     * Cosecha la lechuga.
     *
     * @return ganancia obtenida
     */
    @Override
    public int cosechar() {
        return realizarCosecha();
    }
}
