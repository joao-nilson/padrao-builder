package pizzeria;

import java.util.Collections;
import java.util.List;

/**
 * Product: an immutable pizza. It can only be created through {@link PizzaBuilder}.
 */
public class Pizza {

    public enum Size { SMALL, MEDIUM, LARGE }

    public enum Crust { THIN, TRADITIONAL, STUFFED }

    public enum Sauce { TOMATO, BBQ, WHITE, PESTO }

    public enum Cheese { MOZZARELLA, CHEDDAR, PARMESAN, GORGONZOLA }

    private final Size size;
    private final Crust crust;
    private final Sauce sauce;
    private final Cheese cheese;
    private final List<String> toppings;

    // package-private: only the builder (same package) can create a Pizza
    Pizza(Size size, Crust crust, Sauce sauce, Cheese cheese, List<String> toppings) {
        this.size = size;
        this.crust = crust;
        this.sauce = sauce;
        this.cheese = cheese;
        this.toppings = Collections.unmodifiableList(toppings);
    }

    public Size getSize() {
        return size;
    }

    public Crust getCrust() {
        return crust;
    }

    public Sauce getSauce() {
        return sauce;
    }

    public Cheese getCheese() {
        return cheese;
    }

    public List<String> getToppings() {
        return toppings;
    }

    @Override
    public String toString() {
        return size + " pizza | crust: " + crust + " | sauce: " + sauce
                + " | cheese: " + cheese + " | toppings: " + (toppings.isEmpty() ? "none" : toppings);
    }
}
