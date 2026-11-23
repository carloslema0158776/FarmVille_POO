package farmville;

/**
 * Representa una oveja que produce lana.
 */
public class Oveja extends Animal {

    /**
     * Constructor de la clase Oveja.
     */
    public Oveja() {
        super("Oveja", 40);
    }

    /**
     * Produce lana si la oveja está alimentada.
     *
     * @return lana producida
     * @throws AnimalNoAlimentadoException si no fue alimentada
     */
    @Override
    public Producto producir() {
        if (!estaAlimentado()) {
            throw new AnimalNoAlimentadoException(
                    "La oveja debe ser alimentada antes de producir."
            );
        }

        consumirAlimento();
        return new Lana();
    }
}
