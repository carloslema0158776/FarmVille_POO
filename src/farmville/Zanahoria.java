package farmville;

/**
 * Representa un cultivo de zanahoria.
 */
public class Zanahoria extends Cultivo {

    /**
     * Constructor de la clase Zanahoria.
     */
    public Zanahoria() {
        super("Zanahoria", 12, 30);
    }

    /**
     * Cosecha la zanahoria.
     *
     * @return ganancia obtenida
     */
    @Override
    public int cosechar() {
        return realizarCosecha();
    }
}
