package org.acme;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CorpsCeleste {

    private double positionX;
    private double positionY;
    private double vitesseX;
    private double vitesseY;
    private final double masse = 1e15;

    public void updatePosition(double temps) {
        this.positionX += vitesseX*temps;
        this.positionY += vitesseY*temps;
    }



}




