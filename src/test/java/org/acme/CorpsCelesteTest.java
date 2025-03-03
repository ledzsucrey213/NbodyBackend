package org.acme;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CorpsCelesteTest {

    @Test
    public void testUpdatePositionWithNoBounce() {
        CorpsCeleste corps = new CorpsCeleste(100, 100, 10, 10, 10e11);
        corps.updatePosition(1.0);

        assertEquals(110, corps.getPositionX(), "Position X should be updated correctly.");
        assertEquals(110, corps.getPositionY(), "Position Y should be updated correctly.");
    }




    @Test
    public void testBounceOnXBoundary() {
        CorpsCeleste body = new CorpsCeleste(350, 0, 1, 0, 10e11);
        body.updatePosition(1);  // La position devrait rebondir sur l'axe X

        // Vérification que la position X est bien inférieure à 350
        assertEquals(-1, body.getVitesseX(), "Position Y should bounce back when out of bounds.");
    }



    @Test
    public void testBounceOnYBoundary() {
        CorpsCeleste corps = new CorpsCeleste(100, 360, 10, 10, 10e11);
        corps.updatePosition(1.0);

        assertEquals(-10, corps.getVitesseY(), "Position Y should bounce back when out of bounds.");
    }
}
