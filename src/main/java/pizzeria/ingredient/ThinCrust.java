package pizzeria.ingredient;

public class ThinCrust implements Crust {

    @Override
    public String getName() {
        return "Thin";
    }

    @Override
    public double getPrice() {
        return 2.0;
    }
}
