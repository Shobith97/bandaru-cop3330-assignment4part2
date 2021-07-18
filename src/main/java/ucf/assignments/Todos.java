package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Shobit Bandaru
 */
import java.util.ArrayList;

public class Todos {

    private ArrayList<Todo> todoLists = new ArrayList<>();

    public void addToDo(Todo toDo){
        todoLists.add(toDo);
    }

    public void update(int index, Todo todo){
        todoLists.set(index,todo);
    }

    public void delete(Todo todo){
        todoLists.remove(todo);
    }

    public Todo getTodo(String id, String title){
        for(Todo list : todoLists){
            if(list.getId().equals(id) && list.getTitle().equals(title)){
                return list;
            }
        }

        return null;
    }

    public Todo getTodo(String id){
        for(Todo list : todoLists){
            if(list.getId().equals(id)){
                return list;
            }
        }

        return null;
    }

    public ArrayList<Todo> getTodoLists() {
        return todoLists;
    }

    public int getIndex(String id){
        for (int i=0; i<todoLists.size(); i++){
            Todo list = todoLists.get(i);
            if(list.getId().equals(id)){
                return i;
            }
        }
        return -1;
    }

    public void setTodoLists(ArrayList<Todo> todoLists) {
        this.todoLists = todoLists;
    }

    public String getID(String title){
        for(Todo list : todoLists){
            if(list.getTitle().equals(title)){
                return list.getId();
            }
        }
        return "";
    }
}
