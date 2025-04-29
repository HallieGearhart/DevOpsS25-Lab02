package edu.westga.devops.a4.test.view.main_window;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import org.junit.jupiter.api.Test;

import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.api.FxAssert;
import org.testfx.matcher.control.TextInputControlMatchers;

import edu.westga.devops.a7.Main;

public class TestMakeGuess extends ApplicationTest  {

	@Override
	public void start(Stage stage) throws IOException {
		(new Main()).start(stage);
	}

	@Test
	public void makeGuessThatIsTooLow() {
		this.clickOn("#guess");
		this.type(KeyCode.DIGIT0);
		this.clickOn("#makeGuessButton");
		FxAssert.verifyThat("#result", TextInputControlMatchers.hasText("Guess is too low, try again."));
	}

	@Test
	public void makeGuessThatIsTooHigh() {
		this.clickOn("#guess");
		this.type(KeyCode.DIGIT1);
		this.type(KeyCode.DIGIT0);
		this.type(KeyCode.DIGIT0);
		this.clickOn("#makeGuessButton");
		FxAssert.verifyThat("#result", TextInputControlMatchers.hasText("Guess is too high, try again."));
	}
}
