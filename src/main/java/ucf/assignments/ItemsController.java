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
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ItemsController implements Initializable {
    @FXML
    private DatePicker dueDateL;

    @FXML
    private TextArea descriptionTA;

    @FXML
    private ListView<Item> itemsLV;

    @FXML
    private TextField idTL;

    @FXML
    private ChoiceBox<String> showCB;

    @FXML
    private ChoiceBox<String> completeCB;

    ObservableList<Item> items = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        completeCB.getItems().add("Mark as complete");
        completeCB.getItems().add("Mark as in-complete");

        showCB.getItems().add("All");
        showCB.getItems().add("Complete");
        showCB.getItems().add("In-Complete");

        showCB.getSelectionModel().select(0);

        showCB.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number newNo) {
                clearFields();
                settingListView(newNo.intValue());
            }
        });

        settingFormat();

        if (CONSTANTS.items != null) {
            items.addAll(CONSTANTS.items);
        }

        itemsLV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        itemsLV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Item>() {
            @Override
            public void changed(ObservableValue<? extends Item> observableValue, Item item, Item selectedItem) {
                setFields(selectedItem);
            }
        });

        itemsLV.setItems(items);
    }

    private void settingListView(int index) {
        if (index == 0) {
            items.clear();
            items.addAll(CONSTANTS.items);
        } else if (index == 1) {
            items.clear();
            for (Item item : CONSTANTS.items) {
                if (item.isComplete()) {
                    items.add(item);
                }
            }
        } else if (index == 2) {
            items.clear();
            for (Item item : CONSTANTS.items) {
                if (!item.isComplete()) {
                    items.add(item);
                }
            }
        }
    }

    private void setFields(Item selectedItem) {
        idTL.setText(selectedItem.getId());
        descriptionTA.setText(selectedItem.getDescription());
        boolean complete = selectedItem.isComplete();

        LocalDate l = LocalDate.parse(selectedItem.getDueDate());
        dueDateL.setValue(l);

        if (!complete) {
            completeCB.getSelectionModel().select(1);
        } else {
            completeCB.getSelectionModel().select(0);
        }

    }

    private void settingFormat() {
        dueDateL.setConverter(new StringConverter<LocalDate>() {
            private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            @Override
            public String toString(LocalDate localDate) {
                if (localDate == null)
                    return "";
                return dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String dateString) {
                if (dateString == null || dateString.trim().isEmpty()) {
                    return null;
                }
                return LocalDate.parse(dateString, dateTimeFormatter);
            }
        });
    }

    @FXML
    void onAddButtonClick(ActionEvent event) {
        if (!isEmptyFields()) {
            if (descriptionTA.getText().length() < 256) {

                LocalDate value = dueDateL.getValue();
                Item item = new Item(idTL.getText().toString(), descriptionTA.getText().toString(), value.toString());
                if (completeCB.getSelectionModel().getSelectedIndex() == 0) {
                    item.setComplete(true);
                } else {
                    item.setComplete(false);
                }

                if (!isExists(idTL.getText())) {
                    CONSTANTS.todo.addItem(new Item(idTL.getText().toString(), descriptionTA.getText().toString(), value.toString()));
                    items.add(item);
                }

            } else {
                DIALOG.showDialog("item can't add. Length must be less than 256", "Operation failed!");
            }
        } else {
            DIALOG.showDialog("item can't add", "Operation failed!");
        }
    }

    @FXML
    void onDeleteButtonClick(ActionEvent event) {
        if (!idTL.getText().isEmpty()) {
            CONSTANTS.todo.deleteItem(idTL.getText().toString());
            items.remove(getItem(idTL.getText().toString()));
            DIALOG.showDialog("Todo deleted", "Operation successful!");
        } else {
            DIALOG.showDialog("Fields shouldn't be empty", "Operation failed!");
        }
    }

    @FXML
    void onUpdateButtonClick(ActionEvent event) {
        if (!isEmptyFields()) {
            Item item = getItem(idTL.getText().toString());
            if (item != null) {
                if (descriptionTA.getText().length() < 256) {

                    item.setDescription(descriptionTA.getText().toString());
                    LocalDate value = dueDateL.getValue();
                    item.setDueDate(value.toString());
                    if (completeCB.getSelectionModel().getSelectedIndex() == 0) {
                        item.setComplete(true);
                    } else {
                        item.setComplete(false);
                    }

                    if (getIndex(idTL.getText().toString()) != -1) {
                        items.set(getIndex(idTL.getText().toString()), item);
                        CONSTANTS.todo.updateItem(idTL.getText().toString(), item);

                        DIALOG.showDialog("Todo updated", "Operation successful!");
                    }
                } else {
                    DIALOG.showDialog("item can't add. Length must be less than 256", "Operation failed!");
                }
            }
        } else {
            DIALOG.showDialog("Fields shouldn't be empty", "Operation failed!");
        }
    }

    @FXML
    void onBackButtonClick(ActionEvent event) {

        Parent parentScreen = null;
        try {
            parentScreen = FXMLLoader.load(getClass().getResource("TodoListScreen.fxml"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        Scene scene = new Scene(parentScreen);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    private int getIndex(String id) {
        for (int i = 0; i < items.size(); i++) {
            if (id.equals(items.get(i).getId())) {
                return i;
            }
        }
        return -1;
    }

    private boolean isEmptyFields() {
        if (idTL.getText().isEmpty() || descriptionTA.getText().isEmpty() || dueDateL.getValue() == null) {
            return true;
        } else {
            return false;
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

    private void clearFields() {
        idTL.clear();
        descriptionTA.clear();
        dueDateL.setValue(null);
    }

    private boolean isExists(String id) {
        for (Item item : CONSTANTS.items) {
            if (id.equals(item.getId())) {
                return true;
            }
        }

        return false;
    }

    @FXML
    void onSaveBtnClick(ActionEvent event) {
        try {
            FileWriter file = new FileWriter("items.txt");
            for (Item item : CONSTANTS.items) {
                String s = item.getId() + "," + item.getDescription() + "," + item.getDueDate() + "," + String.valueOf(item.isComplete()) + "\n";
                file.write(s);
            }

            file.close();

            DIALOG.showDialog("items are written to external file", "File saved!");
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }
}