package farmville;

/**
 * Representa una gallina que produce huevos.
 */
public class Gallina extends Animal {

    /**
     * Constructor de la clase Gallina.
     */
    public Gallina() {
        super("Gallina", 30);
    }

    /**
     * Produce un huevo si la gallina está alimentada.
     *
     * @return huevo producido
     * @throws AnimalNoAlimentadoException si no fue alimentada
     */
    @Override
    public Producto producir() {
        if (!estaAlimentado()) {
            throw new AnimalNoAlimentadoException(
                    "La gallina debe ser alimentada antes de producir."
            );
        }

        consumirAlimento();
        return new Huevo();
    }
}
