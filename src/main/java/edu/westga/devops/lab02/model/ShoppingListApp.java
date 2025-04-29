package edu.westga.devops.lab02.view;

public class ShoppingListApp {

    private MainWindow mainWindow;

    public ShoppingListApp() {
        this.mainWindow = new MainWindow();
    }

    public static void main(String[] args) {
        ShoppingListApp app = new ShoppingListApp();
    }
	
	public MainWindow getMainWindow() {
		return mainWindow;
    }
}
