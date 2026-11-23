package farmville;

/**
 * Representa un elemento que puede ser vendido.
 */
public interface Vendible {

    /**
     * Vende el elemento y devuelve su precio de venta.
     *
     * @return cantidad de monedas obtenidas
     */
    int vender();
}
