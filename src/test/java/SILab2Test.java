import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class SILab2Test {

    @Test
    public void testEveryBranchCriteria() {
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> SILab2.checkCart(null, 100)
        );
        assertTrue(thrown.getMessage().contains("allItems list can't be null!"));


        List<Item> items = new ArrayList<>();
        items.add(new Item("", "123456", 100, 0));
        boolean result = SILab2.checkCart(items, 100);
        assertTrue(result);
        assertEquals("unknown", items.get(0).getName());


        List<Item> items1 = new ArrayList<>();
        items1.add(new Item("Item1", null, 100, 0));
        thrown = assertThrows(
                RuntimeException.class,
                () -> SILab2.checkCart(items1, 100)
        );
        assertTrue(thrown.getMessage().contains("No barcode!"));


        List<Item> items2 = new ArrayList<>();
        items2.add(new Item("Item1", "123a56", 100, 0));
        thrown = assertThrows(
                RuntimeException.class,
                () -> SILab2.checkCart(items2, 100)
        );
        assertTrue(thrown.getMessage().contains("Invalid character in item barcode!"));


        List<Item> items3 = new ArrayList<>();
        items3.add(new Item("Item1", "123456", 100, 0.1f));
        result = SILab2.checkCart(items3, 10);
        assertTrue(result);


        List<Item> items4 = new ArrayList<>();
        items4.add(new Item("Item1", "123456", 100, 0));
        result = SILab2.checkCart(items4, 100);
        assertTrue(result);


        List<Item> items5 = new ArrayList<>();
        items5.add(new Item("Item1", "012345", 350, 0.1f));
        result = SILab2.checkCart(items5, 285);
        assertTrue(result);


        List<Item> items6 = new ArrayList<>();
        items6.add(new Item("Item1", "123456", 100, 0.1f));
        result = SILab2.checkCart(items6, 5);
        assertFalse(result);


        List<Item> items7 = new ArrayList<>();
        items7.add(new Item("Item1", "123456", 100, 0));
        result = SILab2.checkCart(items7, 100);
        assertTrue(result);
    }

    @Test
    public void testMultipleConditionCriteria() {
        List<Item> items = new ArrayList<>();
        boolean result;

        // TTT
        items.add(new Item("Item1", "012345", 350, 0.1f));
        result = SILab2.checkCart(items, 285);
        assertTrue(result);
        items.clear();

        // TTF
        items.add(new Item("Item1", "112345", 350, 0.1f));
        result = SILab2.checkCart(items, 35);
        assertTrue(result);
        items.clear();

        // TFN
        items.add(new Item("Item1", "012345", 350, 0f));
        result = SILab2.checkCart(items, 350);
        assertTrue(result);
        items.clear();

        // FNN
        items.add(new Item("Item1", "012345", 250, 0.1f));
        result = SILab2.checkCart(items, 25);
        assertTrue(result);
        items.clear();
    }
}