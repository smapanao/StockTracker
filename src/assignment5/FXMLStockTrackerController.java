/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment5;

import assignment5.model.Stock;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author seanmapanao
 */
public class FXMLStockTrackerController implements Initializable {

    @FXML
    private ListView<Stock> stockItems;

    @FXML
    private TextField txtProdID;

    @FXML
    private TextField txtProdName;

    @FXML
    private TextField txtQoh;

    @FXML
    private TextField txtRsp;

    @FXML
    private TextField txtSalePrice;

    @FXML
    private TextField txtBuyPrice;

    @FXML
    private Label lblSelectedItem;

    @FXML
    private Button buy;

    @FXML
    private TextField txtBuyAmt;

    @FXML
    private Label lblPrice;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Displays premade items in list
        Stock[] stock = {new Stock("234_XYZ", "itemX", 20, 25, 10, 3),
            new Stock("567_DDD", "itemY", 10, 15, 7, 4),
            new Stock("999_AAA", "itemZ", 15, 20, 5, 2)};

        //displays premade items in list
        ObservableList<Stock> obsStock = FXCollections.observableArrayList(stock);
        stockItems.setItems(obsStock);

        //default text before user presses "Buy" button
        lblPrice.setText("ReStock Fee");

        //Sets a default selection if user presses buy without choosing an item
        stockItems.getSelectionModel().select(0);
        lblSelectedItem.setText(stockItems.getSelectionModel().getSelectedItem().toString());

        //listener that updats selected item label in bottom left side of screen
        stockItems.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> lblSelectedItem.setText(newValue.toString()));
    }

    @FXML
    private void addProd(ActionEvent event) {

        //user inputs
        String prodID, prodName;
        int qoh, rsp;
        double salePrice, buyPrice;
        boolean isValid = true;

        prodName = txtProdName.getText();

        //for productID validation - must be 3 numbers + "_" + 3 capital letters
        String regex = "\\d{3}_[A-Z]{3}";

        if (!txtProdID.getText().matches(regex) || txtProdID.getText().isEmpty() == true) {
            isValid = false;
        }
        
        //more validation, number textfield must be field and >= 0
        if (!txtQoh.getText().matches("\\b\\d+\\b") || txtQoh.getText().isEmpty() == true) {
            isValid = false;
        }

        if (!txtRsp.getText().matches("\\b\\d+\\b") || txtRsp.getText().isEmpty() == true) {
            isValid = false;
        }

        if (!txtSalePrice.getText().matches("\\b\\d+\\b") || txtSalePrice.getText().isEmpty() == true) {
            isValid = false;
        }

        if (!txtBuyPrice.getText().matches("\\b\\d+\\b") || txtBuyPrice.getText().isEmpty() == true) {
            isValid = false;
        }

        if (isValid == true) {
            prodID = txtProdID.getText();
            qoh = Integer.parseInt(txtQoh.getText());
            rsp = Integer.parseInt(txtRsp.getText());
            salePrice = Double.parseDouble(txtSalePrice.getText()); //TextField --> double
            buyPrice = Double.parseDouble(txtBuyPrice.getText()); //TextField --> double

            //adds new items to list when "Add Product" button is pressed
            stockItems.getItems().add(new Stock(prodID, prodName, qoh, rsp, salePrice, buyPrice));
        } else {
            lblSelectedItem.setText("Error");//update label that there's an error
        }
    }

    @FXML
    private void buy(ActionEvent event) {
        
        //validation for buy amount text field
        if(txtBuyAmt.getText().matches("\\b\\d+\\b")){
        
        int amtBought = Integer.parseInt(txtBuyAmt.getText()); //TextField --> int
        Stock selectedItem = stockItems.getSelectionModel().getSelectedItem();//Gets content of selected item
        Double buyPrice = selectedItem.getBuyPrice(); //TextField --> double
        Double totalPrice = buyPrice * amtBought; //TextField --> double 
        
        //Updates label
        lblPrice.setText("Total ReStock Fee: $" + String.format("%.2f", totalPrice));
        lblSelectedItem.setText(stockItems.getSelectionModel().getSelectedItem().toString());
        }
        else{
        lblPrice.setText("Please enter a valid amount");
        }
    }
}
