package pizzeria;

import java.util.ArrayList;
import java.util.List;

import pizzeria.Pizza.Cheese;
import pizzeria.Pizza.Crust;
import pizzeria.Pizza.Sauce;
import pizzeria.Pizza.Size;

/**
 * Builder: assembles a custom pizza step by step.
 * Size and crust are required; sauce and cheese have defaults; toppings are optional.
 */
public class PizzaBuilder {

    public static final int MAX_TOPPINGS = 5;

    private Size size;
    private Crust crust;
    private Sauce sauce = Sauce.TOMATO;
    private Cheese cheese = Cheese.MOZZARELLA;
    private final List<String> toppings = new ArrayList<>();

    public PizzaBuilder size(Size size) {
        this.size = size;
        return this;
    }

    public PizzaBuilder crust(Crust crust) {
        this.crust = crust;
        return this;
    }

    public PizzaBuilder sauce(Sauce sauce) {
        this.sauce = sauce;
        return this;
    }

    public PizzaBuilder cheese(Cheese cheese) {
        this.cheese = cheese;
        return this;
    }

    public PizzaBuilder addTopping(String topping) {
        if (topping == null || topping.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid topping");
        }
        toppings.add(topping.trim());
        return this;
    }

    public Pizza build() {
        if (size == null) {
            throw new IllegalArgumentException("Size is required");
        }
        if (crust == null) {
            throw new IllegalArgumentException("Crust is required");
        }
        if (sauce == null) {
            throw new IllegalArgumentException("Sauce is required");
        }
        if (cheese == null) {
            throw new IllegalArgumentException("Cheese is required");
        }
        if (toppings.size() > MAX_TOPPINGS) {
            throw new IllegalArgumentException("Too many toppings (max " + MAX_TOPPINGS + ")");
        }
        // copy the list so reusing the builder never changes a pizza already built
        return new Pizza(size, crust, sauce, cheese, new ArrayList<>(toppings));
    }
}
