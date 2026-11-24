package pizzeria.ingredient;

public class StuffedCrust implements Crust {

    @Override
    public String getName() {
        return "Stuffed";
    }

    @Override
    public double getPrice() {
        return 6.0;
    }
}
