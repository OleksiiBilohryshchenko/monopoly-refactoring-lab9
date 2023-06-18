package edu.sumdu.monopoly;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CardsTest {
    Card ccCard, chanceCard;
    
    GameMaster gameMaster;

    @BeforeEach
    public void setUp() {
        gameMaster = GameMaster.instance();
        gameMaster.setGameBoard(new GameBoardCCGainMoney());
        gameMaster.setNumberOfPlayers(1);
        gameMaster.reset();
        gameMaster.setGUI(new MockGUI());
        ccCard = new MoneyCard("Get 50 dollars", 50, Card.TYPE_CC);
        chanceCard = new MoneyCard("Lose 50 dollars", -50, Card.TYPE_CHANCE);
        gameMaster.getGameBoard().addCard(ccCard);
    }

    @Test
    public void testCardType() {
        Card card = gameMaster.drawCCCard();
        assertEquals(Card.TYPE_CC, ccCard.getCardType());
        card = gameMaster.drawChanceCard();
        assertEquals(Card.TYPE_CHANCE, chanceCard.getCardType());
    }
}
