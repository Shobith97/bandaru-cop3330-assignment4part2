package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Shobit Bandaru
 */
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class TodoListController implements Initializable {
    @FXML
    private TextField titleL;

    @FXML
    private TextField idL;

    @FXML
    private ListView<Todo> toDoListsLV;

    private ObservableList<Todo> todoLists = FXCollections.observableArrayList();
    ArrayList<Todo> lists = CONSTANTS.todos.getTodoLists();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (CONSTANTS.todos.getTodoLists().size() == 0) {
            readFile();
        }
        todoLists.addAll(lists);
        toDoListsLV.setItems(todoLists);

        toDoListsLV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        toDoListsLV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Todo>() {
            @Override
            public void changed(ObservableValue<? extends Todo> observableValue, Todo todo, Todo t1) {
                idL.setText(t1.getId());
                titleL.setText(t1.getTitle());
            }
        });

    }

    @FXML
    void onAddButtonClick(ActionEvent event) {
        if (!titleL.getText().isEmpty() && !idL.getText().isEmpty()) {
            Todo list = new Todo();

            if(!isExists(idL.getText())){
                lists.add(list);
                todoLists.add(list);
            }  else {
                DIALOG.showDialog("ToDo can't add","Operation failed!");
            }

        } else {
            DIALOG.showDialog("Fields shouldn't be empty","Operation failed!");
        }
    }

    @FXML
    void onInsBtnClick(ActionEvent event){
        Parent parentScreen = null;
        try {
            parentScreen = FXMLLoader.load(getClass().getResource("sample.fxml"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        Scene scene = new Scene(parentScreen);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    void onDeleteButtonClick(ActionEvent event) {
        if (!titleL.getText().isEmpty() && !idL.getText().isEmpty()) {
            Todo todo = CONSTANTS.todos.getTodo(idL.getText().toString(), titleL.getText().toString());

            if (todo != null) {
                CONSTANTS.todos.delete(todo);
                todoLists.remove(todo);
            }

        } else {
            DIALOG.showDialog("Fields shouldn't be empty","Operation failed!");
        }
    }

    @FXML
    void onUpdateButtonClick(ActionEvent event) {
        if (!titleL.getText().isEmpty() && !idL.getText().isEmpty()) {
            Todo todo = CONSTANTS.todos.getTodo(idL.getText().toString());
            int index = CONSTANTS.todos.getIndex(idL.getText().toString());
            todo.setTitle(titleL.getText().toString());
            todoLists.set(index, todo);
        } else {
            DIALOG.showDialog("Fields shouldn't be empty","Operation failed!");
        }

    }

    @FXML
    void onDisplayItemsClick(ActionEvent event) {
        if (!titleL.getText().isEmpty() && !idL.getText().isEmpty()) {
            Todo todo = CONSTANTS.todos.getTodo(idL.getText().toString(), titleL.getText().toString());
            CONSTANTS.items = todo.getItems();
            CONSTANTS.todo = todo;

            Parent parentScreen = null;
            try {
                parentScreen = FXMLLoader.load(getClass().getResource("ItemsScreen.fxml"));
            } catch (IOException exception) {
                exception.printStackTrace();
            }

            Scene scene = new Scene(parentScreen);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
    }

    @FXML
    void onSaveButtonClick(ActionEvent event) {
        try {
            FileWriter file = new FileWriter("toDos.txt");

            for (Todo todo : CONSTANTS.todos.getTodoLists()) {
                file.write(todo.getId() + "," + todo.getTitle() + "," + todo.getItems().size() + "\n");
                for (Item item : todo.getItems()) {
                    String s = item.getId() + "," + item.getDescription() + "," + item.getDueDate() + "," + String.valueOf(item.isComplete()) + "\n";
                    file.write(s);
                }
            }

            file.close();

            DIALOG.showDialog("Todo lists are written to external file", "File saved!");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void readFile() {
        Scanner scanner = null;
        Todo todo = null;
        Item item = null;

        try {
            scanner = new Scanner(new FileReader("toDos.txt"));
            String line = scanner.nextLine();
            String[] split = line.split(",");
            String id = split[0];
            String title = split[1];
            int size = Integer.parseInt(split[2]);
            todo = new Todo();

            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                split = s.split(",");
                id = split[0];
                if (size > 0) {
                    String description = split[1];
                    String date = split[2];
                    boolean complete = Boolean.parseBoolean(split[3]);
                    size--;
                    item = new Item();
                    if (complete) {
                        item.setComplete(true);
                    }
                    todo.addItem(item);
                } else {
                    CONSTANTS.todos.addToDo(todo);
                    title = split[1];
                    size = Integer.parseInt(split[2]);
                    todo = new Todo();
                }
            }
            CONSTANTS.todos.addToDo(todo);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    private boolean isExists(String id){
        for(Todo todo : CONSTANTS.todos.getTodoLists()){
            if(id.equals(todo.getId())){
                return true;
            }
        }
        return false;
    }

}