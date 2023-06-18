package edu.sumdu.monopoly;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class MovePlayerCardTest {
    GameMaster gameMaster;
    Card movePlayerCard;

    @BeforeEach
    public void setUp() {
		gameMaster = GameMaster.instance();
		gameMaster.setGameBoard(new GameBoardCCMovePlayer());
		gameMaster.setNumberOfPlayers(1);
		gameMaster.reset();
		gameMaster.setGUI(new MockGUI());
		movePlayerCard = new MovePlayerCard("Blue 1", Card.TYPE_CC);
		gameMaster.getGameBoard().addCard(movePlayerCard);
    }

    @Test
    public void testJailCardLabel() {
        assertEquals("Go to Blue 1", movePlayerCard.getLabel());
    }

    @Test
    public void testMovePlayerCardAction() {
		Card card = gameMaster.drawCCCard();
		assertEquals(movePlayerCard, card);
		card.applyAction();
		Cell cell = gameMaster.getCurrentPlayer().getPosition();
		assertEquals(gameMaster.getGameBoard().queryCell("Blue 1"), cell);
    }

    @Test
    public void testMovePlayerCardUI() {
        gameMaster.movePlayer(0, 2);
        assertTrue(gameMaster.getGUI().isDrawCardButtonEnabled());
        assertFalse(gameMaster.getGUI().isEndTurnButtonEnabled());
        gameMaster.btnDrawCardClicked();
        assertFalse(gameMaster.getGUI().isDrawCardButtonEnabled());
		Cell cell = gameMaster.getCurrentPlayer().getPosition();
		assertEquals(gameMaster.getGameBoard().queryCell("Blue 1"), cell);
		assertTrue(gameMaster.getGUI().isEndTurnButtonEnabled());
		assertEquals(1700, gameMaster.getCurrentPlayer().getMoney());
    }
}
