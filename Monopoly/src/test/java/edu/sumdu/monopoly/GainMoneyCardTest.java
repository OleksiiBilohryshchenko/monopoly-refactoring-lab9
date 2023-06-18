package edu.sumdu.monopoly;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class GainMoneyCardTest {
    Card gainMoneyCard;
    GameMaster gameMaster;

    @BeforeEach
    public void setUp() {
		gameMaster = GameMaster.instance();
		gameMaster.setGameBoard(new GameBoardCCGainMoney());
		gameMaster.setNumberOfPlayers(1);
		gameMaster.reset();
		gameMaster.setGUI(new MockGUI());
		gainMoneyCard = new MoneyCard("Get 50 dollars", 50, Card.TYPE_CC);
		gameMaster.getGameBoard().addCard(gainMoneyCard);
    }

    @Test
    public void testGainMoneyCardAction() {
        int origMoney = gameMaster.getCurrentPlayer().getMoney();
		Card card = gameMaster.drawCCCard();
		assertEquals(gainMoneyCard, card);
		card.applyAction();
		assertEquals(origMoney + 50, gameMaster.getCurrentPlayer().getMoney());
    }

    @Test
    public void testGainMoneyCardUI() {
        gameMaster.movePlayer(0, 1);
        assertTrue(gameMaster.getGUI().isDrawCardButtonEnabled());
        assertFalse(gameMaster.getGUI().isEndTurnButtonEnabled());
        gameMaster.btnDrawCardClicked();
        assertFalse(gameMaster.getGUI().isDrawCardButtonEnabled());
		assertTrue(gameMaster.getGUI().isEndTurnButtonEnabled());
    }
}
