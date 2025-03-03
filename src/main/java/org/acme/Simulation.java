package org.acme;

import java.util.List;

public class Simulation {

    private static final double G = 6.67430e-11; // Constante gravitationnelle
    private List<CorpsCeleste> corps;

    public Simulation(List<CorpsCeleste> corps) {
        this.corps = corps;
    }

    public void updateSimulation(double temps) {
        // Calcul des forces gravitationnelles et mise à jour des vitesses et positions
        for (int i = 0; i < corps.size(); i++) {
            CorpsCeleste corps1 = corps.get(i);
            double forceX = 0;
            double forceY = 0;

            for (int j = 0; j < corps.size(); j++) {
                if (i != j) {
                    CorpsCeleste corps2 = corps.get(j);
                    double[] force = calculateGravitationalForce(corps1, corps2);
                    forceX += force[0];
                    forceY += force[1];
                }
            }

            // Met à jour la vitesse en fonction de la force
            corps1.setVitesseX(corps1.getVitesseX() + forceX * temps / corps1.getMasse());
            corps1.setVitesseY(corps1.getVitesseY() + forceY * temps / corps1.getMasse());

            // Met à jour la position
            corps1.updatePosition(temps);
        }
    }

    private double[] calculateGravitationalForce(CorpsCeleste corps1, CorpsCeleste corps2) {
        double dx = corps2.getPositionX() - corps1.getPositionX();
        double dy = corps2.getPositionY() - corps1.getPositionY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        // Si la distance est trop petite pour éviter la division par zéro
        if (distance < 1e-10) {
            return new double[]{0, 0};
        }

        double forceMagnitude = G * corps1.getMasse() * corps2.getMasse() / (distance * distance);
        double forceX = forceMagnitude * dx / distance;
        double forceY = forceMagnitude * dy / distance;

        return new double[]{forceX, forceY};
    }

    public List<CorpsCeleste> getCorps() {
        return corps;
    }
}
