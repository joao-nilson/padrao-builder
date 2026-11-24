package pizzeria;

import org.junit.jupiter.api.Test;

import pizzeria.ingredient.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IngredientStrategyTest {

    @Test
    void everyIngredientShouldHaveNameAndNonNegativePrice() {
        List<Ingredient> all = Arrays.asList(
                new SmallSize(), new LargeSize(),
                new ThinCrust(), new StuffedCrust(),
                new TomatoSauce(), new BbqSauce(),
                new MozzarellaCheese(), new CheddarCheese(),
                new PepperoniTopping(), new MushroomTopping());

        for (Ingredient ingredient : all) {
            assertNotNull(ingredient.getName());
            assertFalse(ingredient.getName().isEmpty());
            assertTrue(ingredient.getPrice() >= 0);
        }
    }

    @Test
    void sameInterfaceShouldGiveDifferentBehaviorPerImplementation() {
        Crust thin = new ThinCrust();
        Crust stuffed = new StuffedCrust();

        assertEquals("Thin", thin.getName());
        assertEquals("Stuffed", stuffed.getName());
        assertEquals(2.0, thin.getPrice(), 0.001);
        assertEquals(6.0, stuffed.getPrice(), 0.001);
    }

    @Test
    void largeSizeShouldCostMoreThanSmall() {
        assertTrue(new SmallSize().getPrice() < new LargeSize().getPrice());
    }
}
