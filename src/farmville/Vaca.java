package farmville;

/**
 * Representa una vaca que produce leche.
 */
public class Vaca extends Animal {

    /**
     * Constructor de la clase Vaca.
     */
    public Vaca() {
        super("Vaca", 50);
    }

    /**
     * Produce leche si la vaca está alimentada.
     *
     * @return leche producida
     * @throws AnimalNoAlimentadoException si no fue alimentada
     */
    @Override
    public Producto producir() {
        if (!estaAlimentado()) {
            throw new AnimalNoAlimentadoException(
                    "La vaca debe ser alimentada antes de producir."
            );
        }

        consumirAlimento();
        return new Leche();
    }
}
