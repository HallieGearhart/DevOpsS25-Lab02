package edu.westga.devops.lab02.test.model.shopping_list_app;

import org.junit.jupiter.api.Test;
import edu.westga.devops.lab02.view.ShoppingListApp;
import static org.junit.jupiter.api.Assertions.*;

class TestShoppingList {

    @Test
    void testShoppingListAppInitialization() {
        ShoppingListApp app = new ShoppingListApp();

        assertNotNull(app);

        assertNotNull(app.getMainWindow());
    }
}
