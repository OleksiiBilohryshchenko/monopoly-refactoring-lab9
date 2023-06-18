package edu.sumdu.monopoly;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class GoToJailCardTest {
    GameMaster gameMaster;
    Card jailCard = new JailCard(Card.TYPE_CC);

    @BeforeEach
    public void setUp() {
		gameMaster = GameMaster.instance();
		gameMaster.setGameBoard(new GameBoardCCJail());
		gameMaster.setNumberOfPlayers(1);
		gameMaster.reset();
		gameMaster.setGUI(new MockGUI());
		gameMaster.getGameBoard().addCard(jailCard);
    }

    @Test
    public void testJailCardAction() {
		Card card = gameMaster.drawCCCard();
		assertEquals(jailCard, card);
		card.applyAction();
		Cell cell = gameMaster.getCurrentPlayer().getPosition();
		assertEquals(gameMaster.getGameBoard().queryCell("Jail"), cell);
    }

    @Test
    public void testJailCardLabel() {
        assertEquals("Go to Jail immediately without collecting" +
        		" $200 when passing the GO cell", jailCard.getLabel());
    }

    @Test
    public void testJailCardUI() {
        gameMaster.movePlayer(0, 1);
        assertTrue(gameMaster.getGUI().isDrawCardButtonEnabled());
        assertFalse(gameMaster.getGUI().isEndTurnButtonEnabled());
        gameMaster.btnDrawCardClicked();
        assertFalse(gameMaster.getGUI().isDrawCardButtonEnabled());
		Cell cell = gameMaster.getCurrentPlayer().getPosition();
		assertEquals(gameMaster.getGameBoard().queryCell("Jail"), cell);
		assertTrue(gameMaster.getGUI().isEndTurnButtonEnabled());
    }
}
