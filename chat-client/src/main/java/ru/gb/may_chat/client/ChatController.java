package ru.gb.may_chat.client;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class ChatController implements Initializable {

    @FXML
    private VBox mainPanel;

    @FXML
    private TextArea chatArea;

    @FXML
    private ListView<String> contacts;

    @FXML
    private TextField inputField;

    @FXML
    private Button btnSend;

    String selectedName;

    public void guide(ActionEvent actionEvent) {
        Application.openHelp();
    }


    public void mockAction(ActionEvent actionEvent) {
        System.out.println("mock");
    }

    public void closeApplication(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void sendMessage(ActionEvent actionEvent) {

        String text = inputField.getText();
        if (selectedName == null || text == null || text.isBlank()) {
            return;
        }
        chatArea.appendText(selectedName + ": " + text + System.lineSeparator());
        inputField.clear();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> names = List.of("Send All", "Vasya", "Masha", "Petya", "Valera", "Nastya");
        contacts.setItems(FXCollections.observableList(names));
        MultipleSelectionModel<String> selectedContact = contacts.getSelectionModel();
        selectedContact.selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {
                selectedName = newValue;
            }
        });


    }
}
