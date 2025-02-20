package org.acme;

import java.util.List;

public class Simulation {
    private static final double G = 6.67430e-11;
    private final List<CorpsCeleste> corpsCelestes;

    public Simulation(List<CorpsCeleste> corps) {
        this.corpsCelestes = corps;
    }

    public void calculerForces(double dt) {
        int n = corpsCelestes.size();
        double[][] forcesX = new double[n][n];
        double[][] forcesY = new double[n][n];

        // 1️⃣ Calcul des forces entre chaque paire de corps
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                CorpsCeleste a = corpsCelestes.get(i);
                CorpsCeleste b = corpsCelestes.get(j);

                double dx = b.getPositionX() - a.getPositionX();
                double dy = b.getPositionY() - a.getPositionY();
                double r = Math.sqrt(dx * dx + dy * dy);

                if (r == 0) continue; // Évite la division par zéro

                double force = (G * a.getMasse() * b.getMasse()) / (r * r);
                double forceX = force * (dx / r);
                double forceY = force * (dy / r);

                forcesX[i][j] = forceX;
                forcesY[i][j] = forceY;
                forcesX[j][i] = -forceX;
                forcesY[j][i] = -forceY;
            }
        }

        // 2️⃣ Mise à jour des vitesses et positions
        for (int i = 0; i < n; i++) {
            CorpsCeleste a = corpsCelestes.get(i);
            double totalForceX = 0;
            double totalForceY = 0;

            for (int j = 0; j < n; j++) {
                if (i != j) {
                    totalForceX += forcesX[i][j];
                    totalForceY += forcesY[i][j];
                }
            }

            // Accélération a = F / m
            double ax = totalForceX / a.getMasse();
            double ay = totalForceY / a.getMasse();

            // Mise à jour des vitesses : v = v + a * dt
            a.setVitesseX(a.getVitesseX() + ax * dt);
            a.setVitesseY(a.getVitesseY() + ay * dt);

            // Mise à jour des positions : x = x + v * dt
            a.setPositionX(a.getPositionX() + a.getVitesseX() * dt);
            a.setPositionY(a.getPositionY() + a.getVitesseY() * dt);
        }
    }

    public List<CorpsCeleste> ListeCorps() {
        return this.corpsCelestes;
    }

}
