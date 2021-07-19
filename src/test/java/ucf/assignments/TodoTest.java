package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void setId() {
        Todo todo = new Todo();
        String id = "1";
        todo.setId(id);

        assertEquals(id, todo.getId());
    }

    @Test
    void setTitle() {
        Todo todo = new Todo();
        String title = "desc";
        todo.setTitle(title);

        assertEquals(title, todo.getTitle());
    }

    @Test
    void setItems() {
        ArrayList<Item> items = new ArrayList<>();
        Item item = new Item();
        items.add(item);

        Todo todo = new Todo();
        todo.setItems(items);

        assertEquals(items, todo.getItems());
    }

    @Test
    void getId() {
        Todo todo = new Todo();
        String id = "1";
        todo.setId(id);

        assertEquals(id, todo.getId());
    }

    @Test
    void getTitle() {
        Todo todo = new Todo();
        String title = "desc";
        todo.setTitle(title);

        assertEquals(title, todo.getTitle());

    }

    @Test
    void getItems() {
        ArrayList<Item> items = new ArrayList<>();
        Item item = new Item();
        items.add(item);

        Todo todo = new Todo();
        todo.setItems(items);

        assertEquals(items, todo.getItems());
    }

    @Test
    void addItem(){
        Item item = new Item();

        Todo todo = new Todo();
        todo.addItem(item);

        Item item1 = todo.getItems().get(0);

        assertEquals(item, item1);
    }

    @Test
    void updateItem(){
        Item item = new Item();

        Todo todo = new Todo();
        todo.addItem(item);

        item = new Item();
        todo.updateItem("1",item);

        Item item1 = todo.getItems().get(0);

        assertEquals(item, item1);
    }


    @Test
    void deleteItem(){
        Item item = new Item();

        Todo todo = new Todo();
        todo.addItem(item);

        todo.deleteItem("1");

        int size = todo.getItems().size();

        assertEquals(0,size);
    }

    @Test
    void getItem(){
        Item item = new Item();

        Todo todo = new Todo();
        todo.addItem(item);


        Item item1 = todo.getItem("1");

        assertEquals(item, item1);
    }

    @Test
    void getIndex(){
        Item item = new Item();

        Todo todo = new Todo();
        todo.addItem(item);


        int id = todo.getIndex("1");

        assertEquals(0, id);
    }


}