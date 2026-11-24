package pizzeria.ingredient;

public class PepperoniTopping implements Topping {

    @Override
    public String getName() {
        return "Pepperoni";
    }

    @Override
    public double getPrice() {
        return 4.0;
    }
}
