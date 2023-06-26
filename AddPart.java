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
import java.util.Optional;
import java.util.ResourceBundle;

public class AddPart implements Initializable {

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
    void cancelAddPart(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning:");
        alert.setContentText("Upon clicking OK, all changes are cancelled and you will be re-routed to the main form.");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/Views/Mainform.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
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
    void saveAddPart(ActionEvent event) throws IOException {
        try {
            int id = Integer.parseInt(partIdText.getText());
            String name = partNameText.getText();
            int stock = Integer.parseInt(partInventoryText.getText());
            double price = Double.parseDouble(partPriceText.getText());
            int min = Integer.parseInt(partMinText.getText());
            int max = Integer.parseInt(partMaxText.getText());

            if (min > max) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning:");
                alert.setContentText("The minimum value must be less than or equal to the maximum value.");
                alert.showAndWait();
            } else if (stock > max || stock < min) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning:");
                alert.setContentText("The stock value must be between the minimum and maximum values.");
                alert.showAndWait();
            } else if (name.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning:");
                alert.setContentText("The name field cannot be blank.");
                alert.showAndWait();
            } else {
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/Views/Mainform.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }
        catch(NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning:");
            alert.setContentText("Please type valid inputs for all fields.");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}