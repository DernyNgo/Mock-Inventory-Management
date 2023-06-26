package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddProduct implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TableColumn<?, ?> partIdColumn;

    @FXML
    private TableColumn<?, ?> partIdColumn1;

    @FXML
    private TextField partIdText;

    @FXML
    private TableColumn<?, ?> partInventoryColumn;

    @FXML
    private TableColumn<?, ?> partInventoryColumn1;

    @FXML
    private TextField partInventoryText;

    @FXML
    private TextField partMaxText;

    @FXML
    private TextField partMinText;

    @FXML
    private TableColumn<?, ?> partNameColumn;

    @FXML
    private TableColumn<?, ?> partNameColumn1;

    @FXML
    private TextField partNameText;

    @FXML
    private TableColumn<?, ?> partPriceColumn;

    @FXML
    private TableColumn<?, ?> partPriceColumn1;

    @FXML
    private TextField partPriceText;

    @FXML
    private TableView<?> partTableView;

    @FXML
    private TableView<?> partTableView1;

    @FXML
    void addProductSearch(ActionEvent event) {

    }

    @FXML
    void cancelAddProduct(ActionEvent event) throws IOException {

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/Views/Mainform.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void removeAddProduct(ActionEvent event) {

    }

    @FXML
    void saveAddProduct(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}