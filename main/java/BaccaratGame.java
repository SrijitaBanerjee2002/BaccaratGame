import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class BaccaratGame extends Application {
	private BorderPane root;
	private ArrayList<Card> playerHand;
	private ArrayList<Card> bankerHand;
	private BaccaratDealer theDealer;
	private BaccaratGameLogic gameLogic;
	private double currentBet;
	private double totalWinnings;

	public static void main(String[] args) {
		launch(args);
	}

	// default constructor for BaccaratGame class
	public BaccaratGame() {
		playerHand = new ArrayList<>();
		bankerHand = new ArrayList<>();
		theDealer = new BaccaratDealer();
		gameLogic = new BaccaratGameLogic();
		currentBet = 0;
		totalWinnings = 0.0;
	}

	// implementing getters and setters for the member variables
	public ArrayList<Card> getPlayerHand() {
		return playerHand;
	}

	public void setPlayerHand(ArrayList<Card> thePlayerHand) {
		playerHand = thePlayerHand;
	}

	public ArrayList<Card> getBankerHand() {
		return bankerHand;
	}

	public void setBankerHand(ArrayList<Card> theBankerHand) {
		bankerHand = theBankerHand;
	}
	public BaccaratDealer getTheDealer() {
		return theDealer;
	}

	public void setTheDealer(BaccaratDealer theDeal) {
		theDealer = theDeal;
	}

	public BaccaratGameLogic getGameLogic() {
		return gameLogic;
	}

	public void setGameLogic(BaccaratGameLogic theGameLogic) {
		gameLogic = theGameLogic;
	}
	public double getCurrentBet() {
		return currentBet;
	}

	public void setCurrentBet(double theCurrentBet) {
		currentBet = theCurrentBet;
	}

	public double getTotalWinnings() {
		return totalWinnings;
	}

	public void setTotalWinnings(double theTotalWinnings) {
		totalWinnings = theTotalWinnings;
	}

	// evaluating winnings based on the gameLogic class, calculating the totalWinnings, and returning the totalWinnings.
	public double evaluateWinnings() {
		String winner = gameLogic.whoWon(playerHand, bankerHand);
		int playerTotalScore = gameLogic.handTotal(playerHand);
		int bankerTotalScore = gameLogic.handTotal(bankerHand);
		boolean playerCard = gameLogic.evaluatePlayerDraw(playerHand);
		boolean bankerCard = gameLogic.evaluateBankerDraw(bankerHand, playerHand.get(0));
		if (winner.equals("Player")) {
			if (playerCard) {
				totalWinnings += currentBet * 2;
			} else {
				totalWinnings += currentBet;
			}
		} else if (winner.equals("Banker")) {
			if (bankerCard) {
				totalWinnings += currentBet * 2 * 0.95;
			} else {
				totalWinnings += currentBet * 0.95;
			}
		} else if (winner.equals("Tie")) {
			totalWinnings += currentBet;
		}
		currentBet = 0.0;
		playerHand.clear();
		bankerHand.clear();
		return totalWinnings;
	}

	// displaying the cards for each hand: one for the banker and one for the player
	private void displayHand(ArrayList<Card> hand, Pane stackpane) {
		for (Card card : hand) {
			ImageView cardImageView = card.display();
			stackpane.getChildren().add(cardImageView);
		}
	}

	// connects all the buttons to the existing game utilizing JavaFX
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Baccarat Game");
		root = new BorderPane();
		Scene scene = new Scene(root, 800, 800);
		primaryStage.setScene(scene);
		String backgroundImage = getClass().getResource("/welcome_background.png").toExternalForm();
		root.setStyle("-fx-background-image: url('" + backgroundImage + "'); -fx-background-size: cover;");
		MenuBar menu = new MenuBar();
		Menu options = new Menu("Options");
		MenuItem restart = new MenuItem("Fresh start");
		MenuItem exit = new MenuItem("Exit");
		options.getItems().addAll(restart,exit);
		menu.getMenus().add(options);
		root.setTop(menu);
		VBox gameInterface = new VBox(10);
		gameInterface.setStyle("-fx-padding: 30;");
		root.setCenter(gameInterface);
		Label bet = new Label("Enter your bet:");
		TextField betInput = new TextField();
		Button placeBetButton = new Button("Place Bet");
		gameInterface.getChildren().addAll(bet, betInput, placeBetButton);
		Button play = new Button("Play");
		gameInterface.getChildren().add(play);
		TextArea text = new TextArea();
		gameInterface.getChildren().add(text);
		text.setEditable(false); // this is where the results are displayed
		HBox player = new HBox(10);
		HBox banker = new HBox(10);
		gameInterface.getChildren().addAll(new Label("Player's Hand"), player, new Label("Banker's Hand"), banker);
		// taking a valid input for the bet
		placeBetButton.setOnAction(e -> {
			try {
				currentBet = Double.parseDouble(betInput.getText());
				text.appendText("Your bet is $" + currentBet + "\n");
			} catch (NumberFormatException ex) {
				text.appendText("Please enter a valid number to bet.\n");
			}
		});
		// intially playing the game
		play.setOnAction(e -> {
			playerHand = new ArrayList<>();
			bankerHand = new ArrayList<>();
			if (currentBet <= 0) {
				text.appendText("Please place a valid bet to play the game.\n");
				return;
			}
			text.clear();
			if (player.getChildren().size() + playerHand.size() > 7 || banker.getChildren().size() + bankerHand.size() > 7) {
				player.getChildren().clear();
				banker.getChildren().clear();
			}
			playerHand.addAll(theDealer.dealHand());
			bankerHand.addAll(theDealer.dealHand());
			displayHand(playerHand, player);
			displayHand(bankerHand, banker);
			int playerTotal = gameLogic.handTotal(playerHand);
			int bankerTotal = gameLogic.handTotal(bankerHand);
			if (playerTotal == 8 || playerTotal == 9 || bankerTotal == 8 || bankerTotal == 9) {
				text.appendText("Natural win!\n");
				text.appendText("Player Total: " + playerTotal + "\n");
				text.appendText("Banker Total: " + bankerTotal + "\n");
			} else if (playerTotal != 8 && playerTotal != 9 && bankerTotal != 8 && bankerTotal != 9) {
				String winner = gameLogic.whoWon(playerHand, bankerHand);
				if (winner.equals("Player")) {
					totalWinnings += currentBet;
					text.appendText("Player wins!\n");
				} else if (winner.equals("Banker")) {
					totalWinnings -= currentBet;
					text.appendText("Banker wins!\n");
				} else {
					text.appendText("It's a tie!\n");
				}
				text.appendText("Player Total: " + playerTotal + "\n");
				text.appendText("Banker Total: " + bankerTotal + "\n");
			}
			if (totalWinnings <= 0) {
				text.appendText("Game over. You have no more winnings.\n");
			} else if (totalWinnings > 0) {
				text.appendText("Total Winnings: $" + totalWinnings + "\n");
			}
			currentBet = 0.0;
		});

		// resetting the variables for a fresh start if the user clicked on the Fresh Start button
		restart.setOnAction(e -> {
			totalWinnings = 0.0;
			text.clear();
			betInput.clear();
			text.appendText("Total winnings have been reset to $0.0.\n");
			playerHand.clear();
			bankerHand.clear();
			player.getChildren().clear();
			banker.getChildren().clear();
			currentBet = 0.0;
			totalWinnings = 0.0;
		});

		// exiting the game if the user clicked on the Exit button
		exit.setOnAction(e -> primaryStage.close());
		theDealer = new BaccaratDealer();
		gameLogic = new BaccaratGameLogic();
		primaryStage.show();
	}
}