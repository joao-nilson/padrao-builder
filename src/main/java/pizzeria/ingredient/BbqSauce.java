package pizzeria.ingredient;

public class BbqSauce implements Sauce {

    @Override
    public String getName() {
        return "BBQ";
    }

    @Override
    public double getPrice() {
        return 1.5;
    }
}
