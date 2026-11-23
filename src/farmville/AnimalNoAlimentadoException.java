package farmville;

/**
 * Excepción que ocurre cuando se intenta obtener un producto
 * de un animal que todavía no ha sido alimentado.
 */
public class AnimalNoAlimentadoException extends RuntimeException {

    /**
     * Constructor de la excepción.
     *
     * @param mensaje descripción del error
     */
    public AnimalNoAlimentadoException(String mensaje) {
        super(mensaje);
    }
}
