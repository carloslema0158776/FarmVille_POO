package farmville;

/**
 * Representa un elemento que puede ser cosechado.
 */
public interface Cosechable {

    /**
     * Realiza la cosecha y devuelve la cantidad de monedas ganadas.
     *
     * @return ganancia obtenida por la cosecha
     */
    int cosechar();
}
