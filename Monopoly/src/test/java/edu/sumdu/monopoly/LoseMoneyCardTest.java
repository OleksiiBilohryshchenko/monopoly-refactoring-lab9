package edu.sumdu.monopoly;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class LoseMoneyCardTest {
    GameMaster gameMaster;
    Card loseMoneyCard;

    @BeforeEach
    public void setUp() {
		gameMaster = GameMaster.instance();
		gameMaster.setGameBoard(new GameBoardCCLoseMoney());
		gameMaster.setNumberOfPlayers(1);
		gameMaster.reset();
		gameMaster.setGUI(new MockGUI());
		loseMoneyCard = new MoneyCard("Pay 20 dollars", -20, Card.TYPE_CC);
		gameMaster.getGameBoard().addCard(loseMoneyCard);
    }

    @Test
    public void testLoseMoneyCardAction() {
        int origMoney = gameMaster.getCurrentPlayer().getMoney();
		Card card = gameMaster.drawCCCard();
		assertEquals(loseMoneyCard, card);
		card.applyAction();
		assertEquals(origMoney - 20, gameMaster.getCurrentPlayer().getMoney());
    }

    @Test
    public void testLoseMoneyCardUI() {
        gameMaster.movePlayer(0, 1);
        assertTrue(gameMaster.getGUI().isDrawCardButtonEnabled());
        assertFalse(gameMaster.getGUI().isEndTurnButtonEnabled());
        gameMaster.btnDrawCardClicked();
        assertFalse(gameMaster.getGUI().isDrawCardButtonEnabled());
		assertTrue(gameMaster.getGUI().isEndTurnButtonEnabled());
    }
}
