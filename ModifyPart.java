package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPart implements Initializable {

    Stage stage;

    Parent scene;

    @FXML
    private RadioButton inHouse;

    @FXML
    private Label machineIdToCompanyName;

    @FXML
    private RadioButton outsourced;

    @FXML
    private TextField partIdText;

    @FXML
    private TextField partInventoryText;

    @FXML
    private TextField partMachineIdText;

    @FXML
    private TextField partMaxText;

    @FXML
    private TextField partMinText;

    @FXML
    private TextField partNameText;

    @FXML
    private TextField partPriceText;

    @FXML
    private ToggleGroup selectionToggleGroup;

    @FXML
    void cancelModifyPart(ActionEvent event) throws IOException {

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/Views/Mainform.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void inHouseTextField(ActionEvent event) {

        machineIdToCompanyName.setText("Machine ID");

    }

    @FXML
    void outsourcedTextField(ActionEvent event) {

        machineIdToCompanyName.setText("Company Name");
    }

    @FXML
    void saveModifyPart(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}