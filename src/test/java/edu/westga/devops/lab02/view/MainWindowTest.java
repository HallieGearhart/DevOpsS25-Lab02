package edu.westga.devops.lab02.view;

import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

public class MainWindowTest extends ApplicationTest {

    private MainWindow controller;
    private TextField itemNameField;
    private TextField quantityField;
    private ListView<String> shoppingListView;
    private Label errorLabel;
    private Button addItemButton;
    private Button removeItemButton;
    private Button updateQuantityButton;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/westga/devops/lab02/view/MainWindow.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        stage.setScene(new Scene(root));
        stage.show();

        itemNameField = lookup("#itemNameField").query();
        quantityField = lookup("#quantityField").query();
        shoppingListView = lookup("#shoppingListView").query();
        errorLabel = lookup("#errorLabel").query();
        addItemButton = lookup("#addItemButton").query();
        removeItemButton = lookup("#removeItemButton").query();
        updateQuantityButton = lookup("#updateQuantityButton").query();
    }

    @BeforeEach
    void clearFields() {
        interact(() -> {
            itemNameField.clear();
            quantityField.clear();
            shoppingListView.getItems().clear();
            errorLabel.setText("");
        });
    }

    @Test
    void addItem_validName_itemAddedToList() {
        clickOn(itemNameField).write("Apples");
        clickOn(addItemButton);

        assertEquals(1, shoppingListView.getItems().size());
        assertTrue(shoppingListView.getItems().get(0).contains("Apples"));
        assertEquals("", errorLabel.getText());
    }

    @Test
    void addItem_emptyName_errorShown() {
        clickOn(itemNameField).write("");
        clickOn(addItemButton);

        assertEquals(0, shoppingListView.getItems().size());
        assertEquals("Item name cannot be empty.", errorLabel.getText());
    }

    @Test
    void updateQuantity_validQuantity_quantityUpdated() {
        itemNameField.setText("Eggs");
        controller.handleAddItem();
        shoppingListView.getSelectionModel().select(0);
        quantityField.setText("3");

        controller.handleUpdateQuantity();

        String updatedItem = shoppingListView.getItems().get(0);
        assertTrue(updatedItem.contains("Qty: 3"));
        assertEquals("", errorLabel.getText());
    }

	@Test
	void updateQuantity_invalidQuantity_quantityNotUpdated() {
		itemNameField.setText("Eggs");
		controller.handleAddItem();
		shoppingListView.getSelectionModel().select(0);
    
		interact(() -> {
			quantityField.setText("-1");
			controller.handleUpdateQuantity();
		});

		String updatedItem = shoppingListView.getItems().get(0);
		assertTrue(updatedItem.contains("Qty: 0"));
		assertEquals("Quantity must be greater than 0.", errorLabel.getText());
	}
	
	@Test
	void updateQuantity_nonNumericInput_quantityNotUpdated() {
		itemNameField.setText("Eggs");
		controller.handleAddItem();
		shoppingListView.getSelectionModel().select(0);

		interact(() -> {
			quantityField.setText("invalid");
			controller.handleUpdateQuantity();
		});

		String updatedItem = shoppingListView.getItems().get(0);
		assertTrue(updatedItem.contains("Qty: 0"));
		assertEquals("Enter a valid number.", errorLabel.getText());
	}
	
	@Test
	void updateQuantity_noItemSelected_errorShown() {
		itemNameField.setText("Eggs");
		controller.handleAddItem();

		interact(() -> {
			quantityField.setText("3");
			controller.handleUpdateQuantity();
		});

		String updatedItem = shoppingListView.getItems().get(0);
		assertTrue(updatedItem.contains("Qty: 0"));
		assertEquals("Select an item to update.", errorLabel.getText());
	}
}
