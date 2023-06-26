package Controllers;

import Models.Inventory;
import Models.Part;
import Models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Mainform implements Initializable {
    Stage stage;
    Parent scene;

    @FXML
    private TableColumn<Part, Integer> partIdColumn;

    @FXML
    private TextField partIdSearchBar;

    @FXML
    private TableColumn<Part, Integer> partInventoryColumn;

    @FXML
    private TableColumn<Part, String> partNameColumn;

    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    @FXML
    private TableView<Part> partTableView;

    @FXML
    private TableColumn<Product, Integer> productIdColumn;

    @FXML
    private TextField productIdSearchBar;

    @FXML
    private TableColumn<Product, Integer> productInventoryColumn;

    @FXML
    private TableColumn<Product, String> productNameColumn;

    @FXML
    private TableColumn<Product, Double> productPriceColumn;

    @FXML
    private TableView<Product> productTableView;

    @FXML
    void searchPart(ActionEvent event) {

        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> partIDLookup = FXCollections.observableArrayList();
        String searchedInput = partIdSearchBar.getText();

        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(searchedInput) || part.getName().contains(searchedInput)) {
                partIDLookup.add(part);
            }
        }

    partTableView.setItems(partIDLookup);

    if (partIDLookup.size() == 0) {
        alertVariables(3);
    }

    }

    @FXML
    void searchProduct(ActionEvent event) {

        ObservableList<Product> allProducts = Inventory.getAllProducts();
        ObservableList<Product> productIDLookup = FXCollections.observableArrayList();
        String searchedInput = productIdSearchBar.getText();

        for (Product product : allProducts) {
            if (String.valueOf(product.getId()).contains(searchedInput) || product.getName().contains(searchedInput)) {
                productIDLookup.add(product);
            }
        }

        productTableView.setItems(productIDLookup);

        if (productIDLookup.size() == 0) {
            alertVariables(4);
        }
    }

    @FXML
    void deletePartSelection(ActionEvent event) throws IOException {

        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error:");
            alert.setContentText("Deletion did not occur: The part selection cannot be empty.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm:");
            alert.setContentText("Delete selected part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(selectedPart);
            }
        }

    }

    @FXML
    void deleteProductSelection(ActionEvent event) {

        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error:");
            alert.setContentText("Deletion did not occur: The product selection cannot be empty.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm:");
            alert.setContentText("Delete selected product?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {

                ObservableList<Part> associatedPart = selectedProduct.getAllAssociatedParts();
                 if (associatedPart.size() >= 1) {
                     alertVariables(5);
                 } else {
                     Inventory.deleteProduct(selectedProduct);
                 }
            }
        }
    }

    @FXML
    void mainPartSearchKeyTyped(KeyEvent event) {
        if (partIdSearchBar.getText().isEmpty()) {
            partTableView.setItems(Inventory.getAllParts());
        }
    }

    @FXML
    void mainProductSearchKeyTyped(KeyEvent event) {
        if (productIdSearchBar.getText().isEmpty()) {
            productTableView.setItems(Inventory.getAllProducts());
        }
    }

    @FXML
    void screenExit(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning:");
        alert.setContentText("Exit the program?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    @FXML
    void screenSwitchAddParts(ActionEvent event) throws IOException {

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/Views/AddPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void screenSwitchAddProducts(ActionEvent event) throws IOException {

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/Views/AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void screenSwitchModifyParts(ActionEvent event) throws IOException {

        Part partModifySelection = partTableView.getSelectionModel().getSelectedItem();
        if (partModifySelection == null) {
            alertVariables(1);
        } else {
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/Views/ModifyPart.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    @FXML
    void screenSwitchModifyProducts(ActionEvent event) throws IOException {

        Product productModifySelection = productTableView.getSelectionModel().getSelectedItem();
        if (productModifySelection == null) {
            alertVariables(2);
        } else {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/Views/ModifyProduct.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    private void alertVariables(int alertCases) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        switch (alertCases) {
            case 1 -> {
                alert.setTitle("Error:");
                alert.setContentText("The part selection cannot be empty.");
                alert.showAndWait();
            }
            case 2 -> {
                alert.setTitle("Error:");
                alert.setContentText("The product selection cannot be empty.");
                alert.showAndWait();
            }
            case 3 -> {
                alert.setTitle("Error:");
                alert.setHeaderText("Part not found.");
                alert.showAndWait();
            }
            case 4 -> {
                alert.setTitle("Error:");
                alert.setHeaderText("Product not found.");
                alert.showAndWait();
            }
            case 5 -> {
                alert.setTitle("Error:");
                alert.setHeaderText("Associated Parts:");
                alert.setContentText("All parts must be removed from a product prior to deleting.");
                alert.showAndWait();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Add data into the Parts Table View
        partTableView.setItems(Inventory.getAllParts());
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Add data into the Products Table View
        productTableView.setItems(Inventory.getAllProducts());
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));



    }
}