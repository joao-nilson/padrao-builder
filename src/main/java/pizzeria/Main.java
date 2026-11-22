package pizzeria;

import pizzeria.Pizza.Cheese;
import pizzeria.Pizza.Crust;
import pizzeria.Pizza.Sauce;
import pizzeria.Pizza.Size;

public class Main {

    public static void main(String[] args) {
        Pizza custom = new PizzaBuilder()
                .size(Size.LARGE)
                .crust(Crust.STUFFED)
                .sauce(Sauce.BBQ)
                .cheese(Cheese.CHEDDAR)
                .addTopping("Chicken")
                .addTopping("Onion")
                .addTopping("Bacon")
                .build();

        Pizza simple = new PizzaBuilder()
                .size(Size.SMALL)
                .crust(Crust.THIN)
                .build(); // default sauce and cheese, no toppings

        System.out.println("Custom: " + custom);
        System.out.println("Simple: " + simple);
    }
}
