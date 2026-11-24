package pizzeria.ingredient;

public class CheddarCheese implements Cheese {

    @Override
    public String getName() {
        return "Cheddar";
    }

    @Override
    public double getPrice() {
        return 3.5;
    }
}
