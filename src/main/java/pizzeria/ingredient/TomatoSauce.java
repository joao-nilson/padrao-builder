package pizzeria.ingredient;

public class TomatoSauce implements Sauce {

    @Override
    public String getName() {
        return "Tomato";
    }

    @Override
    public double getPrice() {
        return 1.0;
    }
}
