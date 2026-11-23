package farmville;

/**
 * Representa un producto generado por un animal.
 */
public abstract class Producto implements Vendible {

    private String nombre;
    private int precioVenta;

    /**
     * Constructor de la clase Producto.
     *
     * @param nombre nombre del producto
     * @param precioVenta precio de venta del producto
     */
    public Producto(String nombre, int precioVenta) {
        this.nombre = nombre;
        this.precioVenta = precioVenta;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return nombre del producto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el precio de venta.
     *
     * @return precio de venta del producto
     */
    public int getPrecioVenta() {
        return precioVenta;
    }

    /**
     * Vende el producto.
     *
     * Cada subclase deberá implementar este método.
     *
     * @return cantidad de monedas obtenidas
     */
    @Override
    public abstract int vender();
}
