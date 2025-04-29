package edu.westga.devops.lab02.view;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;

public class ShoppingListApp extends Application {

    private MainWindow mainWindow;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/westga/devops/lab02/view/MainWindow.fxml"));
        Parent root = loader.load();

        mainWindow = loader.getController();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hallie Gearhart- Shopping List App");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void saveShoppingList() {
    }

    public void addItemToList(String item, int quantity) {
        mainWindow.handleAddItem(item, quantity);
    }
}
