package farmville;

/**
 * Excepción que ocurre cuando se intenta cosechar
 * un cultivo que todavía no está listo.
 */
public class CultivoNoListoException extends RuntimeException {

    /**
     * Constructor de la excepción.
     *
     * @param mensaje descripción del error
     */
    public CultivoNoListoException(String mensaje) {
        super(mensaje);
    }
}
