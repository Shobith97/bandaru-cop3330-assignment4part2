package ucf.assignments;

import org.junit.jupiter.api.Test;
import ucf.assignments.Item;
import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void setId() {
        Item item = new Item();
        String id = "1";
        item.setId(id);

        assertEquals(id, item.getId());
    }

    @Test
    void setDescription() {
        Item item = new Item();
        String desc = "1";
        item.setDescription(desc);

        assertEquals(desc, item.getDescription());
    }

    @Test
    void setDueDate() {
        Item item = new Item();
        String date = "11/11/2021";
        item.setDueDate(date);

        assertEquals(date, item.getDueDate());
    }

    @Test
    void setComplete() {
        Item item = new Item();
        boolean complete = false;
        item.setComplete(complete);

        assertEquals(complete, item.isComplete());
    }

    @Test
    void getId() {
        Item item = new Item();
        String id = "1";
        item.setId(id);

        assertEquals(id, item.getId());
    }

    @Test
    void getDescription() {
        Item item = new Item();
        String desc = "1";
        item.setDescription(desc);

        assertEquals(desc, item.getDescription());

    }

    @Test
    void getDueDate() {
        Item item = new Item();
        String date = "11/11/2021";
        item.setDueDate(date);

        assertEquals(date, item.getDueDate());
    }

    @Test
    void isComplete() {
        Item item = new Item();
        boolean complete = true;
        item.setComplete(complete);

        assertEquals(complete, item.isComplete());
    }

}