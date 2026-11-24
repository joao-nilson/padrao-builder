package pizzeria.ingredient;

public class LargeSize implements Size {

    @Override
    public String getName() {
        return "Large";
    }

    @Override
    public double getPrice() {
        return 40.0;
    }
}
