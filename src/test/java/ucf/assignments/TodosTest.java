package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodosTest {
    @Test
    void addTodo(){
        Todos todos = new Todos();
        Todo todo = new Todo();
        todos.addToDo(todo);

        Todo todo1 = todos.getTodoLists().get(0);

        assertEquals(todo, todo1);
    }

    @Test
    void updateTodo(){
        Todos todos = new Todos();
        Todo todo = new Todo();
        todos.addToDo(todo);

        todo = new Todo();
        todos.update(0,todo);

        Todo todo1 = todos.getTodoLists().get(0);

        assertEquals(todo, todo1);
    }


    @Test
    void deleteTodo(){
        Todos todos = new Todos();
        Todo todo = new Todo();
        todos.addToDo(todo);

        todos.delete(todo);

        int size = todos.getTodoLists().size();

        assertEquals(0, size);

    }

    @Test
    void getTodo(){
        Todos todos = new Todos();
        Todo todo = new Todo();
        todos.addToDo(todo);

        Todo todo1 = todos.getTodo("1");


        assertEquals(todo, todo1);

    }

    @Test
    void getIndex(){
        Todos todos = new Todos();
        Todo todo = new Todo();
        todos.addToDo(todo);

        int index = todos.getIndex("1");


        assertEquals(0,index);

    }
}
