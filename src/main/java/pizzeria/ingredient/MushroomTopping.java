package pizzeria.ingredient;

public class MushroomTopping implements Topping {

    @Override
    public String getName() {
        return "Mushroom";
    }

    @Override
    public double getPrice() {
        return 3.0;
    }
}
