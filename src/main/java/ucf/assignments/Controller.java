package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Shobit Bandaru
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    String details = "1. The very first window is of ToDo list controller where one can add, remove and update the todo.\n " +
            "2. By entering the required details if you click add button to do will be added.\n " +
            "3. After providing the info if one click on the delete button it will delete the todo and same is the case for updation. \n " +
            "4. Entering fields and click on show button it will open upon a new window which will show the items that todo will have. \n" +
            "5. Same procedure is for adding, updating and deleting the items. \n" +
            "6. By clicking the choice box it will show the items of that todo list i.e complete or incomplete";

    @FXML
    private TextArea textArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textArea.setText(details);
    }

    @FXML
    void onBackBtnClick(ActionEvent event){
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

}
