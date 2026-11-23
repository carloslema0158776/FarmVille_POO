package farmville;

/**
 * Representa un elemento capaz de producir un producto.
 */
public interface Producible {

    /**
     * Genera un producto.
     *
     * @return producto generado
     */
    Producto producir();
}
