package pizzeria;

import pizzeria.ingredient.BbqSauce;
import pizzeria.ingredient.CheddarCheese;
import pizzeria.ingredient.LargeSize;
import pizzeria.ingredient.MushroomTopping;
import pizzeria.ingredient.PepperoniTopping;
import pizzeria.ingredient.SmallSize;
import pizzeria.ingredient.StuffedCrust;
import pizzeria.ingredient.ThinCrust;

public class Main {

    public static void main(String[] args) {
        Pizza custom = new PizzaBuilder()
                .size(new LargeSize())
                .crust(new StuffedCrust())
                .sauce(new BbqSauce())
                .cheese(new CheddarCheese())
                .addTopping(new PepperoniTopping())
                .addTopping(new MushroomTopping())
                .build();

        Pizza simple = new PizzaBuilder()
                .size(new SmallSize())
                .crust(new ThinCrust())
                .build(); 

        System.out.println("Custom: " + custom);
        System.out.println("Simple: " + simple);
    }
}
