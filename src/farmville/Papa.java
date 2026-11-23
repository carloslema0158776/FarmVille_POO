package farmville;

/**
 * Representa un cultivo de papa.
 */
public class Papa extends Cultivo {

    /**
     * Constructor de la clase Papa.
     */
    public Papa() {
        super("Papa", 10, 25);
    }

    /**
     * Cosecha la papa.
     *
     * @return ganancia obtenida
     */
    @Override
    public int cosechar() {
        return realizarCosecha();
    }
}
