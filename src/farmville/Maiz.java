package farmville;

/**
 * Representa un cultivo de maíz.
 */
public class Maiz extends Cultivo {

    /**
     * Constructor de la clase Maiz.
     */
    public Maiz() {
        super("Maíz", 8, 20);
    }

    /**
     * Cosecha el maíz.
     *
     * @return ganancia obtenida
     */
    @Override
    public int cosechar() {
        return realizarCosecha();
    }
}
