package pizzeria;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import pizzeria.ingredient.Cheese;
import pizzeria.ingredient.Crust;
import pizzeria.ingredient.Sauce;
import pizzeria.ingredient.Size;
import pizzeria.ingredient.Topping;

public class Pizza {

    private final Size size;
    private final Crust crust;
    private final Sauce sauce;
    private final Cheese cheese;
    private final List<Topping> toppings;

    Pizza(Size size, Crust crust, Sauce sauce, Cheese cheese, List<Topping> toppings) {
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

    public List<Topping> getToppings() {
        return toppings;
    }

    public double getTotalPrice() {
        double total = size.getPrice() + crust.getPrice() + sauce.getPrice() + cheese.getPrice();
        for (Topping topping : toppings) {
            total += topping.getPrice();
        }
        return total;
    }

    @Override
    public String toString() {
        String toppingNames = toppings.isEmpty()
                ? "none"
                : toppings.stream().map(Topping::getName).collect(Collectors.toList()).toString();
        return size.getName() + " pizza | crust: " + crust.getName() + " | sauce: " + sauce.getName()
                + " | cheese: " + cheese.getName() + " | toppings: " + toppingNames
                + " | total: $" + String.format(Locale.US, "%.2f", getTotalPrice());
    }
}
