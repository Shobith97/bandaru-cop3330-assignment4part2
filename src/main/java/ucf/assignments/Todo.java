package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Shobit Bandaru
 */
import java.util.ArrayList;

public class Todo {
    private String id;
    private String title;
    private ArrayList<Item> items = new ArrayList<>(100);

    public Todo(String id, String title) {
        this.title = title;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void updateItem(String id,Item item) {
        if(getIndex(id) != -1){
            items.set(getIndex(id),item);
        }
    }

    public void deleteItem(String id) {
        if(getItem(id) != null){
            items.remove(getItem(id));
        }
    }

    private Item getItem(String id) {
        for (Item i : items) {
            if (i.getId().equals(id)) {
                return i;
            }
        }
        return null;
    }

    private int getIndex(String id) {
        for(int i=0; i<items.size(); i++){
            if(id.equals(items.get(i).getId())){
                return i;
            }
        }
        return -1;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
