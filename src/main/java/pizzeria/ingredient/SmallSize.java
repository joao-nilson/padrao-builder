package pizzeria.ingredient;

public class SmallSize implements Size {

    @Override
    public String getName() {
        return "Small";
    }

    @Override
    public double getPrice() {
        return 20.0;
    }
}
