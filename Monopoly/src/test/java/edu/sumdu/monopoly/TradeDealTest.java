package edu.sumdu.monopoly;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TradeDealTest {

    @BeforeEach
    public void setUp() {
        GameMaster gameMaster = GameMaster.instance();
        gameMaster.reset();
        gameMaster.setNumberOfPlayers(2);
        gameMaster.getPlayer(0).setName("Buyer");
        gameMaster.getPlayer(1).setName("Seller");
    }

    @Test
    public void testMakeMessage() {
        TradeDeal deal = new TradeDeal();
        deal.setAmount(200);
        deal.setPropertyName("Blue 1");
        deal.setSellerIndex(1);
        Player buyer = GameMaster.instance().getPlayer(0);
        String message = "Buyer wishes to purchase Blue 1 from Seller" +
        		" for 200.  Seller, do you wish to trade your property?";
        assertEquals(message, deal.makeMessage());
    }
}
