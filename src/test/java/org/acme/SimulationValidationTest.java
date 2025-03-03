package org.acme;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class SimulationValidationTest {

    @Test
    public void testSimulationGravityEffect() {
        // Initialisation de deux corps avec une masse significative pour tester la gravité
        CorpsCeleste body1 = new CorpsCeleste(100, 100, 0, 0, 10e11);
        CorpsCeleste body2 = new CorpsCeleste(-100, -100, 0, 0, 10e11);

        // Simulation de la mise à jour de leur position en tenant compte de la gravité
        Simulation simulation = new Simulation(List.of(body1, body2));
        simulation.updateSimulation(1.0);

        assertNotEquals(body1.getPositionX(), 100, "La position X du premier corps ne doit pas rester inchangée.");
        assertNotEquals(body2.getPositionY(), -100, "La position Y du deuxième corps ne doit pas rester inchangée.");
    }

    @Test
    public void testPlanetBounceBehavior() {
        CorpsCeleste planet = new CorpsCeleste(350, 100, 10, 10, 10e11);
        planet.updatePosition(1.0); // Déplace la planète

        // Vérifie si la planète a rebondi correctement en sortant des limites
        assertEquals(-10, planet.getVitesseX(), "La planète devrait rester dans les limites après rebond.");
    }
}
