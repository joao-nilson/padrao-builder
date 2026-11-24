package pizzeria.ingredient;

public class MozzarellaCheese implements Cheese {

    @Override
    public String getName() {
        return "Mozzarella";
    }

    @Override
    public double getPrice() {
        return 3.0;
    }
}
