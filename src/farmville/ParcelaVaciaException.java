package farmville;

/**
 * Excepción que ocurre cuando se intenta cosechar
 * una parcela que no tiene ningún cultivo.
 */
public class ParcelaVaciaException extends RuntimeException {

    /**
     * Constructor de la excepción.
     *
     * @param mensaje descripción del error
     */
    public ParcelaVaciaException(String mensaje) {
        super(mensaje);
    }
}
