package pizzeria;

import java.util.ArrayList;
import java.util.List;

import pizzeria.ingredient.Cheese;
import pizzeria.ingredient.Crust;
import pizzeria.ingredient.MozzarellaCheese;
import pizzeria.ingredient.Sauce;
import pizzeria.ingredient.Size;
import pizzeria.ingredient.TomatoSauce;
import pizzeria.ingredient.Topping;

public class PizzaBuilder {

    public static final int MAX_TOPPINGS = 5;

    private Size size;
    private Crust crust;
    private Sauce sauce = new TomatoSauce();
    private Cheese cheese = new MozzarellaCheese();
    private final List<Topping> toppings = new ArrayList<>();

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

    public PizzaBuilder addTopping(Topping topping) {
        if (topping == null) {
            throw new IllegalArgumentException("Invalid topping");
        }
        toppings.add(topping);
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
        return new Pizza(size, crust, sauce, cheese, new ArrayList<>(toppings));
    }
}
