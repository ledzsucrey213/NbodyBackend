package org.acme;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data

public class CorpsCeleste {

    private double positionX;
    private double positionY;
    private double vitesseX;
    private double vitesseY;
    private double masse;


    // Constructeur par défaut, la masse sera initialisée à 10e11
    public CorpsCeleste(double positionX, double positionY, double vitesseX, double vitesseY) {
        this(positionX, positionY, vitesseX, vitesseY, 10e11);  // Masse par défaut
    }

    // Constructeur avec la possibilité de spécifier la masse
    public CorpsCeleste(double positionX, double positionY, double vitesseX, double vitesseY, double masse) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.vitesseX = vitesseX;
        this.vitesseY = vitesseY;
        this.masse = masse;  // Masse spécifiée
    }

    public CorpsCeleste() {
    }

    // Méthode pour mettre à jour la position et gérer les rebonds
    public void updatePosition(double temps) {
        // Mise à jour de la position en fonction de la vitesse
        this.positionX += vitesseX * temps;
        this.positionY += vitesseY * temps;

        // Gestion des rebonds : si la position dépasse les limites, on inverse la vitesse
        if (this.positionX <= -350 || this.positionX >= 350) {
            this.vitesseX = -this.vitesseX;  // Inverser la vitesse sur l'axe X
        }

        if (this.positionY <= -350 || this.positionY >= 350) {
            this.vitesseY = -this.vitesseY;  // Inverser la vitesse sur l'axe Y
        }
    }



}




