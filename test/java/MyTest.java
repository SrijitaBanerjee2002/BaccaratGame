import static org.junit.jupiter.api.Assertions.*;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class MyTest {

	// below are the tests implemented for Card class
	@Test
	void cardConstructorTest() {
		Card newCard = new Card("Diamonds", 1);
		assertEquals("Diamonds", newCard.getSuite());
		assertEquals(1, newCard.getValue());
	}

	@Test
	void setSuiteTest1() {
		Card newCard = new Card("Diamonds", 1);
		newCard.setSuite("Spades");
		assertEquals("Spades", newCard.getSuite());
	}

	@Test
	void setSuiteTest2() {
		Card newCard = new Card("Diamonds", 1);
		assertEquals("Diamonds", newCard.getSuite());
		newCard.setSuite("Hearts");
		assertEquals("Hearts", newCard.getSuite());
		newCard.setSuite("Spades");
		assertEquals("Spades", newCard.getSuite());
	}

	@Test
	void setValueTest1() {
		Card newCard = new Card("Diamonds", 1);
		newCard.setValue(5);
		assertEquals(5, newCard.getValue());
	}

	@Test
	void setValueTest2() {
		Card newCard = new Card("Diamonds", 1);
		assertEquals(1, newCard.getValue());
		newCard.setValue(7);
		assertEquals(7, newCard.getValue());
		newCard.setValue(9);
		assertEquals(9, newCard.getValue());
	}

	@Test
	void getSuiteTest1() {
		Card newCard = new Card("Diamonds", 1);
		assertNotEquals("Spades", newCard.getSuite());
		assertEquals("Diamonds", newCard.getSuite());
	}

	@Test
	void getSuiteTest2() {
		Card newCard = new Card("Diamonds", 1);
		assertEquals("Diamonds", newCard.getSuite());
		newCard.setSuite("Hearts");
		assertEquals("Hearts", newCard.getSuite());
		Card newCard2 = new Card("Spades", 5);
		assertEquals("Spades", newCard2.getSuite());
	}

	@Test
	void getValueTest1() {
		Card newCard = new Card("Diamonds", 1);
		assertNotEquals(5, newCard.getValue());
		assertEquals(1, newCard.getValue());
	}

	@Test
	void getValueTest2() {
		Card newCard = new Card("Diamonds", 1);
		assertEquals(1, newCard.getValue());
		newCard.setValue(3);
		assertEquals(3, newCard.getValue());
		Card newCard2 = new Card("Spades", 8);
		assertEquals(8, newCard2.getValue());
	}

	@Test
	void displayTest1() {
		Card card = new Card("Hearts", 7);
		ImageView imageView = card.display();
		assertNotNull(imageView);
		assertEquals(100, imageView.getFitWidth());
		assertEquals(150, imageView.getFitHeight());
	}

	@Test
	void displayTest2() {
		Card card1 = new Card("Diamonds", 1);
		Card card2 = new Card("Spades", 13);
		ImageView imageView1 = card1.display();
		assertNotNull(imageView1);
		assertEquals(100, imageView1.getFitWidth());
		assertEquals(150, imageView1.getFitHeight());
		ImageView imageView2 = card1.display();
		assertNotNull(imageView2);
		assertEquals(100, imageView2.getFitWidth());
		assertEquals(150, imageView2.getFitHeight());
	}

	// below are the tests implemented for BaccaratDealer class
	@Test
	void dealerConstructorTest() {
		BaccaratDealer deal = new BaccaratDealer();
		assertEquals(52, deal.deckSize());
		assertNotNull(deal.deck);
	}

	@Test
	void generateDeckTest1() {
		BaccaratDealer deal = new BaccaratDealer();
		assertEquals(52, deal.deckSize());
	}

	@Test
	void generateDeckTest2() {
		BaccaratDealer deal = new BaccaratDealer();
		ArrayList<Card> deck = deal.deck;
		assertEquals(52, deal.deckSize());
		for (Card card : deck) {
			assertTrue(card.getSuite().equals("Hearts") || card.getSuite().equals("Diamonds") || card.getSuite().equals("Clubs") || card.getSuite().equals("Spades"));
			assertTrue(card.getValue() >= 1 && card.getValue() <= 13);
		}
	}

	@Test
	void dealHandTest1() {
		BaccaratDealer deal = new BaccaratDealer();
		ArrayList<Card> hand = deal.dealHand();
		assertEquals(2, hand.size());
		assertEquals(50, deal.deckSize());
	}

	@Test
	void dealHandTest2() {
		BaccaratDealer deal = new BaccaratDealer();
		ArrayList<Card> hand = deal.dealHand();
		for (Card card : hand) {
			assertTrue(card.getSuite().equals("Hearts") || card.getSuite().equals("Diamonds") || card.getSuite().equals("Clubs") || card.getSuite().equals("Spades"));
			assertTrue(card.getValue() >= 1 && card.getValue() <= 13);
		}
		assertEquals(2, hand.size());
		assertEquals(50, deal.deckSize());
	}

	@Test
	void drawOneTest1() {
		BaccaratDealer deal = new BaccaratDealer();
		Card card = deal.drawOne();
		card = deal.drawOne();
		assertEquals(50, deal.deckSize());
		assertNotNull(card);
	}

	@Test
	void drawOneTest2() {
		BaccaratDealer deal = new BaccaratDealer();
		Card card = deal.drawOne();
		for (int i = 0; i < 51; i++) {
			card = deal.drawOne();
		}
		assertEquals(0, deal.deckSize());
		assertNotNull(card);
		assertEquals(deal.drawOne(), null);
	}

	@Test
	void shuffleDeckTest1() {
		BaccaratDealer deal = new BaccaratDealer();
		ArrayList<Card> initialDeck = new ArrayList<>(deal.deck);
		deal.shuffleDeck();
		assertNotEquals(initialDeck, deal.deck);
	}

	@Test
	void shuffleDeckTest2() {
		BaccaratDealer deal = new BaccaratDealer();
		ArrayList<Card> initialDeck = new ArrayList<>(deal.deck);
		deal.shuffleDeck();
		ArrayList<Card> shuffledDeck = new ArrayList<>(deal.deck);
		assertNotEquals(initialDeck, deal.deck);
		assertEquals(shuffledDeck, deal.deck);
		deal.shuffleDeck();
		assertNotEquals(initialDeck, deal.deck);
		assertNotEquals(shuffledDeck, deal.deck);
		assertNotNull(deal.deck);
		assertEquals(52, deal.deckSize());
	}

	// following are tests for BaccaratLogic class
	@Test
	void whoWonTest1() {
		BaccaratGameLogic logic = new BaccaratGameLogic();
		Card card1 = new Card("Diamonds", 7);
		Card card2 = new Card("Spades", 6);
		ArrayList<Card> hand1 = new ArrayList<>();
		ArrayList<Card> hand2 = new ArrayList<>();
		assertNotEquals(hand1, null);
		assertNotEquals(hand2, null);
		hand1.add(card1);
		hand2.add(card2);
		assertNotNull(hand1);
		assertNotNull(hand2);
		assertEquals("Player", logic.whoWon(hand1, hand2));
	}

	@Test
	void whoWonTest2() {
		BaccaratGameLogic logic = new BaccaratGameLogic();
		Card card1 = new Card("Diamonds", 8);
		Card card2 = new Card("Spades", 9);
		ArrayList<Card> hand1 = new ArrayList<>();
		ArrayList<Card> hand2 = new ArrayList<>();
		assertNotEquals(hand1, null);
		assertNotEquals(hand2, null);
		hand1.add(card1);
		hand2.add(card2);
		assertNotNull(hand1);
		assertNotNull(hand2);
		assertEquals("Banker", logic.whoWon(hand1, hand2));
	}

	@Test
	void whoWonTest3() {
		BaccaratGameLogic logic = new BaccaratGameLogic();
		Card card1 = new Card("Diamonds", 6);
		Card card2 = new Card("Spades", 6);
		ArrayList<Card> hand1 = new ArrayList<>();
		ArrayList<Card> hand2 = new ArrayList<>();
		assertNotEquals(hand1, null);
		assertNotEquals(hand2, null);
		hand1.add(card1);
		hand2.add(card2);
		assertNotNull(hand1);
		assertNotNull(hand2);
		assertEquals("Tie", logic.whoWon(hand1, hand2));
	}

	@Test
	void handTotalTest1() {
		BaccaratGameLogic logic = new BaccaratGameLogic();
		Card card1 = new Card("Diamonds", 6);
		Card card2 = new Card("Spades", 1);
		ArrayList<Card> hand = new ArrayList<>();
		assertNotEquals(hand, null);
		hand.add(card1);
		hand.add(card2);
		assertEquals(7, logic.handTotal(hand));
	}

	@Test
	void handTotalTest2() {
		BaccaratGameLogic logic = new BaccaratGameLogic();
		Card card1 = new Card("Diamonds", 9);
		Card card2 = new Card("Spades", 6);
		ArrayList<Card> hand = new ArrayList<>();
		assertNotEquals(hand, null);
		hand.add(card1);
		hand.add(card2);
		assertEquals(5, logic.handTotal(hand));
	}

	@Test
	void evaluateBankerDrawTest1() {
		BaccaratGameLogic logic = new BaccaratGameLogic();
		ArrayList<Card> hand = new ArrayList<>();
		Card card1 = new Card("Spades", 6);
		Card card2 = new Card("Diamonds", 6);
		hand.add(card1);
		assertTrue(logic.evaluateBankerDraw(hand, card2));
		card1.setValue(5);
		card2.setValue(2);
		assertFalse(logic.evaluateBankerDraw(hand, card2));
	}

	@Test
	void evaluateBankerDrawTest2() {
		BaccaratGameLogic logic = new BaccaratGameLogic();
		ArrayList<Card> hand = new ArrayList<>();
		Card card1 = new Card("Spades", 1);
		Card card2 = new Card("Diamonds", 1);
		hand.add(card1);
		assertTrue(logic.evaluateBankerDraw(hand, card2));
		card1.setValue(5);
		card2.setValue(3);
		assertFalse(logic.evaluateBankerDraw(hand, card2));
	}

	@Test
	void evaluatePlayerDrawTest1() {
		BaccaratGameLogic logic = new BaccaratGameLogic();
		ArrayList<Card> hand = new ArrayList<>();
		Card card = new Card("Spades", 9);
		hand.add(card);
		assertFalse(logic.evaluatePlayerDraw(hand));
	}

	@Test
	void evaluatePlayerDrawTest2() {
		BaccaratGameLogic logic = new BaccaratGameLogic();
		ArrayList<Card> hand = new ArrayList<>();
		Card card1 = new Card("Spades", 2);
		Card card2 = new Card("Clubs", 1);
		hand.add(card1);
		hand.add(card2);
		assertTrue(logic.evaluatePlayerDraw(hand));
	}

	// below are the tests for BaccaratGame class
	@Test
	void baccaratGameConstructorTest() {
		BaccaratGame game = new BaccaratGame();
		assertNotNull(game);
		assertTrue(game.getPlayerHand().isEmpty());
		assertTrue(game.getBankerHand().isEmpty());
		assertNotNull(game.getTheDealer());
	}

	@Test
	void getPlayerHandTest1() {
		BaccaratGame game = new BaccaratGame();
		ArrayList<Card> playerHand = game.getPlayerHand();
		assertNotNull(playerHand);
		assertTrue(playerHand.isEmpty());
	}

	@Test
	void getPlayerHandTest2() {
		BaccaratGame game = new BaccaratGame();
		ArrayList<Card> playerHand = new ArrayList<Card>();
		assertTrue(playerHand.isEmpty());
		assertNotNull(playerHand);
		Card card = new Card("Hearts", 8);
		playerHand.add(card);
		game.setPlayerHand(playerHand);
		assertNotNull(playerHand);
		assertFalse(playerHand.isEmpty());
	}

	@Test
	void setPlayerHandTest1() {
		BaccaratGame game = new BaccaratGame();
		Card card = new Card("Hearts", 8);
		ArrayList<Card> playerHand = new ArrayList<Card>();
		playerHand.add(card);
		game.setPlayerHand(playerHand);
		assertNotNull(playerHand);
		assertFalse(playerHand.isEmpty());
	}

	@Test
	void setPlayerHandTest2() {
		BaccaratGame game = new BaccaratGame();
		ArrayList<Card> playerHand = new ArrayList<Card>();
		Card card = new Card("Hearts", 8);
		playerHand.add(card);
		game.setPlayerHand(playerHand);
		assertNotNull(playerHand);
		assertFalse(playerHand.isEmpty());
		ArrayList<Card> newPlayerHand = game.getPlayerHand();
		assertEquals(newPlayerHand, playerHand);
	}

	@Test
	void getBankerHandTest1() {
		BaccaratGame game = new BaccaratGame();
		ArrayList<Card> bankerHand = game.getPlayerHand();
		assertNotNull(bankerHand);
		assertTrue(bankerHand.isEmpty());
	}

	@Test
	void getBankerHandTest2() {
		BaccaratGame game = new BaccaratGame();
		ArrayList<Card> bankerHand = new ArrayList<Card>();
		assertTrue(bankerHand.isEmpty());
		assertNotNull(bankerHand);
		Card card = new Card("Hearts", 8);
		bankerHand.add(card);
		game.setPlayerHand(bankerHand);
		assertNotNull(bankerHand);
	}

	@Test
	void setBankerHandTest1() {
		BaccaratGame game = new BaccaratGame();
		Card card = new Card("Hearts", 8);
		ArrayList<Card> bankerHand = new ArrayList<Card>();
		bankerHand.add(card);
		game.setPlayerHand(bankerHand);
		assertNotNull(bankerHand);
		assertFalse(bankerHand.isEmpty());
	}

	@Test
	void setBankerHandTest2() {
		BaccaratGame game = new BaccaratGame();
		ArrayList<Card> bankerHand = new ArrayList<Card>();
		Card card = new Card("Hearts", 8);
		bankerHand.add(card);
		game.setPlayerHand(bankerHand);
		assertNotNull(bankerHand);
		assertFalse(bankerHand.isEmpty());
		ArrayList<Card> newPlayerHand = game.getPlayerHand();
		assertEquals(newPlayerHand, bankerHand);
	}

	@Test
	void getTheDealerTest1() {
		BaccaratGame game = new BaccaratGame();
		BaccaratDealer theDealer = game.getTheDealer();
		assertNotNull(theDealer);
	}

	@Test
	void getTheDealerTest2() {
		BaccaratGame game = new BaccaratGame();
		BaccaratDealer theDealer = game.getTheDealer();
		assertNotNull(theDealer);
		theDealer.shuffleDeck();
		assertEquals(theDealer.deckSize(),52);
	}

	@Test
	void setTheDealerTest1() {
		BaccaratGame game = new BaccaratGame();
		BaccaratDealer theDealer = new BaccaratDealer();
		game.setTheDealer(theDealer);
		assertNotNull(theDealer);
		assertEquals(theDealer.deckSize(),52);
	}

	@Test
	void setTheDealerTest2() {
		BaccaratGame game = new BaccaratGame();
		BaccaratDealer theDealer1 = new BaccaratDealer();
		BaccaratDealer theDealer2 = game.getTheDealer();
		game.setTheDealer(theDealer1);
		assertNotNull(theDealer1);
		assertNotNull(theDealer2);
		assertEquals(theDealer1.deckSize(),52);
		assertEquals(theDealer2.deckSize(),52);
	}

	@Test
	public void getGameLogicTest1() {
		BaccaratGame game = new BaccaratGame();
		BaccaratGameLogic gameLogic = game.getGameLogic();
		assertNotNull(gameLogic);
	}

	@Test
	public void getGameLogicTest2() {
		BaccaratGame game = new BaccaratGame();
		Card card = new Card ("Hearts", 8);
		ArrayList<Card> hand1 = new ArrayList<Card>();
		hand1.add(card);
		BaccaratGameLogic gameLogic = game.getGameLogic();
		assertNotNull(gameLogic);
		int total = gameLogic.handTotal(hand1);
		assertEquals(8,total);
	}

	@Test
	void getCurrentBetTest1() {
		BaccaratGame game = new BaccaratGame();
		game.setCurrentBet(100.0);
		assertEquals(100.0, game.getCurrentBet());
	}

	@Test
	void getCurrentBetTest2() {
		BaccaratGame game = new BaccaratGame();
		assertNotNull(game);
		game.setCurrentBet(100.0);
		assertEquals(100.0, game.getCurrentBet());
		game.setCurrentBet(15.0);
		assertNotEquals(100.0, game.getCurrentBet());
		assertEquals(15.0, game.getCurrentBet());
	}

	@Test
	void setCurrentBetTest1() {
		BaccaratGame game = new BaccaratGame();
		assertEquals(game.getCurrentBet(),0.0);
		game.setCurrentBet(100.0);
		assertEquals(100.0, game.getCurrentBet());
	}

	@Test
	void setCurrentBetTest2() {
		BaccaratGame game = new BaccaratGame();
		assertNotNull(game);
		game.setCurrentBet(100);
		assertEquals(100, game.getCurrentBet());
		game.setCurrentBet(15);
		assertNotEquals(100.0, game.getCurrentBet());
		assertEquals(15.0, game.getCurrentBet());
	}

	@Test
	void getTotalWinningsTest1() {
		BaccaratGame game = new BaccaratGame();
		assertNotNull(game);
		assertEquals(game.getTotalWinnings(),0);
	}

	@Test
	void getTotalWinningsTest2() {
		BaccaratGame game = new BaccaratGame();
		assertNotNull(game);
		assertEquals(game.getTotalWinnings(),0);
		game.setTotalWinnings(10);
		assertEquals(game.getTotalWinnings(),10);
	}

	@Test
	void setTotalWinningsTest1() {
		BaccaratGame game = new BaccaratGame();
		assertEquals(game.getTotalWinnings(),0.0);
		game.setTotalWinnings(100.0);
		assertEquals(100.0, game.getTotalWinnings());
	}

	@Test
	void setTotalWinningsTest2() {
		BaccaratGame game = new BaccaratGame();
		assertNotNull(game);
		game.setTotalWinnings(100);
		assertEquals(100, game.getTotalWinnings());
		game.setTotalWinnings(15);
		assertNotEquals(100.0, game.getTotalWinnings());
		assertEquals(15.0, game.getTotalWinnings());
	}

	@Test
	void evaluateWinningsTest1() {
		BaccaratGame game = new BaccaratGame();
		Card card1 = new Card("Clubs",8);
		Card card2 = new Card("Hearts",7);
		game.setCurrentBet(20);
		game.getPlayerHand().add(card1);
		game.getBankerHand().add(card2);
		double totalWinnings = game.evaluateWinnings();
		assertEquals(20.0, totalWinnings);

	}

	@Test
	void evaluateWinningsTest2() {
		BaccaratGame game = new BaccaratGame();
		BaccaratGameLogic game1 = new BaccaratGameLogic();
		Card card1 = new Card("Clubs",8);
		Card card2 = new Card("Hearts",8);
		game.getPlayerHand().add(card1);
		game.getBankerHand().add(card2);
		game.setCurrentBet(40.0);
		double totalWinnings = game.evaluateWinnings();
		assertEquals(40.0, totalWinnings);
	}
}