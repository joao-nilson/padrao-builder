package pizzeria;

import org.junit.jupiter.api.Test;

import pizzeria.Pizza.Cheese;
import pizzeria.Pizza.Crust;
import pizzeria.Pizza.Sauce;
import pizzeria.Pizza.Size;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PizzaBuilderTest {

    @Test
    void shouldBuildFullyCustomPizza() {
        Pizza pizza = new PizzaBuilder()
                .size(Size.LARGE)
                .crust(Crust.STUFFED)
                .sauce(Sauce.PESTO)
                .cheese(Cheese.GORGONZOLA)
                .addTopping("Mushroom")
                .addTopping("Olives")
                .build();

        assertEquals(Size.LARGE, pizza.getSize());
        assertEquals(Crust.STUFFED, pizza.getCrust());
        assertEquals(Sauce.PESTO, pizza.getSauce());
        assertEquals(Cheese.GORGONZOLA, pizza.getCheese());
        assertEquals(Arrays.asList("Mushroom", "Olives"), pizza.getToppings());
    }

    @Test
    void shouldUseDefaultSauceAndCheeseWhenNotInformed() {
        Pizza pizza = new PizzaBuilder()
                .size(Size.MEDIUM)
                .crust(Crust.THIN)
                .build();

        assertEquals(Sauce.TOMATO, pizza.getSauce());
        assertEquals(Cheese.MOZZARELLA, pizza.getCheese());
        assertTrue(pizza.getToppings().isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenSizeIsMissing() {
        PizzaBuilder builder = new PizzaBuilder().crust(Crust.THIN);

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, builder::build);
        assertEquals("Size is required", e.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCrustIsMissing() {
        PizzaBuilder builder = new PizzaBuilder().size(Size.SMALL);

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, builder::build);
        assertEquals("Crust is required", e.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenSauceOrCheeseIsSetToNull() {
        PizzaBuilder noSauce = new PizzaBuilder().size(Size.SMALL).crust(Crust.THIN).sauce(null);
        PizzaBuilder noCheese = new PizzaBuilder().size(Size.SMALL).crust(Crust.THIN).cheese(null);

        assertEquals("Sauce is required",
                assertThrows(IllegalArgumentException.class, noSauce::build).getMessage());
        assertEquals("Cheese is required",
                assertThrows(IllegalArgumentException.class, noCheese::build).getMessage());
    }

    @Test
    void shouldRejectBlankOrNullTopping() {
        PizzaBuilder builder = new PizzaBuilder();

        assertThrows(IllegalArgumentException.class, () -> builder.addTopping(null));
        assertThrows(IllegalArgumentException.class, () -> builder.addTopping("   "));
    }

    @Test
    void shouldTrimToppingNames() {
        Pizza pizza = new PizzaBuilder()
                .size(Size.SMALL)
                .crust(Crust.THIN)
                .addTopping("  Ham ")
                .build();

        assertEquals(Arrays.asList("Ham"), pizza.getToppings());
    }

    @Test
    void shouldAcceptMaximumNumberOfToppings() {
        PizzaBuilder builder = new PizzaBuilder().size(Size.LARGE).crust(Crust.TRADITIONAL);
        for (int i = 1; i <= PizzaBuilder.MAX_TOPPINGS; i++) {
            builder.addTopping("Topping " + i);
        }

        assertEquals(PizzaBuilder.MAX_TOPPINGS, builder.build().getToppings().size());
    }

    @Test
    void shouldThrowExceptionWhenTooManyToppings() {
        PizzaBuilder builder = new PizzaBuilder().size(Size.LARGE).crust(Crust.TRADITIONAL);
        for (int i = 1; i <= PizzaBuilder.MAX_TOPPINGS + 1; i++) {
            builder.addTopping("Topping " + i);
        }

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, builder::build);
        assertEquals("Too many toppings (max 5)", e.getMessage());
    }

    @Test
    void builtPizzaToppingsShouldBeImmutable() {
        Pizza pizza = new PizzaBuilder()
                .size(Size.SMALL)
                .crust(Crust.THIN)
                .addTopping("Ham")
                .build();

        assertThrows(UnsupportedOperationException.class, () -> pizza.getToppings().add("Pineapple"));
    }

    @Test
    void reusingBuilderShouldNotChangePizzaAlreadyBuilt() {
        PizzaBuilder builder = new PizzaBuilder()
                .size(Size.MEDIUM)
                .crust(Crust.TRADITIONAL)
                .addTopping("Ham");

        Pizza first = builder.build();
        builder.addTopping("Corn");
        Pizza second = builder.build();

        assertEquals(1, first.getToppings().size());
        assertEquals(2, second.getToppings().size());
    }

    @Test
    void toStringShouldDescribePizza() {
        Pizza pizza = new PizzaBuilder()
                .size(Size.SMALL)
                .crust(Crust.THIN)
                .addTopping("Ham")
                .build();

        assertEquals("SMALL pizza | crust: THIN | sauce: TOMATO | cheese: MOZZARELLA | toppings: [Ham]",
                pizza.toString());
    }
}
