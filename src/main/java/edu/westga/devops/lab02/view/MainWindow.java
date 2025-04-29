package edu.westga.devops.lab02.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Map;

/**
 * CodeBehind To Handle Processing for the MainWindow
 *
 * @author	HallieGearhart
 * @version Spring 2025
 */
public class MainWindow {

    @FXML private TextField itemNameField;
    @FXML private TextField quantityField;
    @FXML private ListView<String> shoppingListView;
    @FXML private Label errorLabel;

    private final ObservableList<String> shoppingList = FXCollections.observableArrayList();
    private final Map<String, Integer> itemQuantities = new HashMap<>();

    @FXML
    public void initialize() {
        shoppingListView.setItems(shoppingList);
    }

    @FXML
    public void handleAddItem() {
        String item = itemNameField.getText().trim();
        if (item.isEmpty()) {
            errorLabel.setText("Item name cannot be empty.");
            return;
        }
        if (!itemQuantities.containsKey(item)) {
            itemQuantities.put(item, 0);
            shoppingList.add(formatItem(item));
            errorLabel.setText("");
        } else {
            errorLabel.setText("Item already exists.");
        }
        itemNameField.clear();
    }

    @FXML
    public void handleRemoveItem() {
        String selected = shoppingListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            errorLabel.setText("Select an item to remove.");
            return;
        }
        String item = extractItemName(selected);
        shoppingList.remove(selected);
        itemQuantities.remove(item);
        errorLabel.setText("");
    }

    @FXML
    public void handleUpdateQuantity() {
        String selected = shoppingListView.getSelectionModel().getSelectedItem();
        String qtyText = quantityField.getText().trim();

        if (selected == null) {
            errorLabel.setText("Select an item to update.");
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(qtyText);
            if (quantity <= 0) {
                errorLabel.setText("Quantity must be greater than 0.");
                return;
            }
        } catch (NumberFormatException e) {
            errorLabel.setText("Enter a valid number.");
            return;
        }

        String item = extractItemName(selected);
        itemQuantities.put(item, quantity);
        int index = shoppingList.indexOf(selected);
        shoppingList.set(index, formatItem(item));
        errorLabel.setText("");
        quantityField.clear();
    }

    private String formatItem(String item) {
        return item + " (Qty: " + itemQuantities.get(item) + ")";
    }

    private String extractItemName(String formattedItem) {
        return formattedItem.split(" \\(Qty: ")[0];
    }
}