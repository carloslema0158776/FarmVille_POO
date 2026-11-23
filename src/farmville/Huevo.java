package farmville;

/**
 * Representa el huevo producido por una gallina.
 */
public class Huevo extends Producto {

    /**
     * Constructor de la clase Huevo.
     */
    public Huevo() {
        super("Huevo", 10);
    }

    /**
     * Vende el huevo.
     *
     * @return precio de venta del huevo
     */
    @Override
    public int vender() {
        return getPrecioVenta();
    }
}
