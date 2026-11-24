package pizzeria;

import org.junit.jupiter.api.Test;

import pizzeria.ingredient.BbqSauce;
import pizzeria.ingredient.CheddarCheese;
import pizzeria.ingredient.LargeSize;
import pizzeria.ingredient.MozzarellaCheese;
import pizzeria.ingredient.MushroomTopping;
import pizzeria.ingredient.PepperoniTopping;
import pizzeria.ingredient.SmallSize;
import pizzeria.ingredient.StuffedCrust;
import pizzeria.ingredient.ThinCrust;
import pizzeria.ingredient.TomatoSauce;

import static org.junit.jupiter.api.Assertions.*;

class PizzaBuilderTest {

    @Test
    void shouldBuildFullyCustomPizza() {
        Pizza pizza = new PizzaBuilder()
                .size(new LargeSize())
                .crust(new StuffedCrust())
                .sauce(new BbqSauce())
                .cheese(new CheddarCheese())
                .addTopping(new MushroomTopping())
                .addTopping(new PepperoniTopping())
                .build();

        assertEquals("Large", pizza.getSize().getName());
        assertEquals("Stuffed", pizza.getCrust().getName());
        assertEquals("BBQ", pizza.getSauce().getName());
        assertEquals("Cheddar", pizza.getCheese().getName());
        assertEquals(2, pizza.getToppings().size());
        assertEquals("Mushroom", pizza.getToppings().get(0).getName());
        assertEquals("Pepperoni", pizza.getToppings().get(1).getName());
    }

    @Test
    void shouldUseDefaultSauceAndCheeseWhenNotInformed() {
        Pizza pizza = new PizzaBuilder()
                .size(new LargeSize())
                .crust(new ThinCrust())
                .build();

        assertEquals("Tomato", pizza.getSauce().getName());
        assertEquals("Mozzarella", pizza.getCheese().getName());
        assertTrue(pizza.getToppings().isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenSizeIsMissing() {
        PizzaBuilder builder = new PizzaBuilder().crust(new ThinCrust());

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, builder::build);
        assertEquals("Size is required", e.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCrustIsMissing() {
        PizzaBuilder builder = new PizzaBuilder().size(new SmallSize());

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, builder::build);
        assertEquals("Crust is required", e.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenSauceOrCheeseIsSetToNull() {
        PizzaBuilder noSauce = new PizzaBuilder().size(new SmallSize()).crust(new ThinCrust()).sauce(null);
        PizzaBuilder noCheese = new PizzaBuilder().size(new SmallSize()).crust(new ThinCrust()).cheese(null);

        assertEquals("Sauce is required",
                assertThrows(IllegalArgumentException.class, noSauce::build).getMessage());
        assertEquals("Cheese is required",
                assertThrows(IllegalArgumentException.class, noCheese::build).getMessage());
    }

    @Test
    void shouldRejectNullTopping() {
        PizzaBuilder builder = new PizzaBuilder();

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> builder.addTopping(null));
        assertEquals("Invalid topping", e.getMessage());
    }

    @Test
    void shouldAcceptMaximumNumberOfToppings() {
        PizzaBuilder builder = new PizzaBuilder().size(new LargeSize()).crust(new ThinCrust());
        for (int i = 0; i < PizzaBuilder.MAX_TOPPINGS; i++) {
            builder.addTopping(new MushroomTopping());
        }

        assertEquals(PizzaBuilder.MAX_TOPPINGS, builder.build().getToppings().size());
    }

    @Test
    void shouldThrowExceptionWhenTooManyToppings() {
        PizzaBuilder builder = new PizzaBuilder().size(new LargeSize()).crust(new ThinCrust());
        for (int i = 0; i <= PizzaBuilder.MAX_TOPPINGS; i++) {
            builder.addTopping(new MushroomTopping());
        }

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, builder::build);
        assertEquals("Too many toppings (max 5)", e.getMessage());
    }

    @Test
    void builtPizzaToppingsShouldBeImmutable() {
        Pizza pizza = new PizzaBuilder()
                .size(new SmallSize())
                .crust(new ThinCrust())
                .addTopping(new PepperoniTopping())
                .build();

        assertThrows(UnsupportedOperationException.class,
                () -> pizza.getToppings().add(new MushroomTopping()));
    }

    @Test
    void reusingBuilderShouldNotChangePizzaAlreadyBuilt() {
        PizzaBuilder builder = new PizzaBuilder()
                .size(new LargeSize())
                .crust(new ThinCrust())
                .addTopping(new PepperoniTopping());

        Pizza first = builder.build();
        builder.addTopping(new MushroomTopping());
        Pizza second = builder.build();

        assertEquals(1, first.getToppings().size());
        assertEquals(2, second.getToppings().size());
    }

    @Test
    void shouldCalculateTotalPriceFromEachIngredient() {
        // Large 40 + Stuffed 6 + BBQ 1.5 + Cheddar 3.5 + Pepperoni 4 + Mushroom 3 = 58
        Pizza pizza = new PizzaBuilder()
                .size(new LargeSize())
                .crust(new StuffedCrust())
                .sauce(new BbqSauce())
                .cheese(new CheddarCheese())
                .addTopping(new PepperoniTopping())
                .addTopping(new MushroomTopping())
                .build();

        assertEquals(58.0, pizza.getTotalPrice(), 0.001);
    }

    @Test
    void shouldCalculatePriceWithDefaultsAndNoToppings() {
        // Small 20 + Thin 2 + Tomato 1 + Mozzarella 3 = 26
        Pizza pizza = new PizzaBuilder()
                .size(new SmallSize())
                .crust(new ThinCrust())
                .build();

        assertEquals(26.0, pizza.getTotalPrice(), 0.001);
    }

    @Test
    void swappingAStrategyShouldChangeThePrice() {
        PizzaBuilder builder = new PizzaBuilder().size(new SmallSize()).crust(new ThinCrust());
        double thin = builder.build().getTotalPrice();

        double stuffed = builder.crust(new StuffedCrust()).build().getTotalPrice();

        assertEquals(4.0, stuffed - thin, 0.001);
    }

    @Test
    void toStringShouldDescribePizza() {
        Pizza pizza = new PizzaBuilder()
                .size(new SmallSize())
                .crust(new ThinCrust())
                .sauce(new TomatoSauce())
                .cheese(new MozzarellaCheese())
                .addTopping(new PepperoniTopping())
                .build();

        assertEquals("Small pizza | crust: Thin | sauce: Tomato | cheese: Mozzarella"
                        + " | toppings: [Pepperoni] | total: $30.00",
                pizza.toString());
    }
}
