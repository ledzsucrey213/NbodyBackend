package org.acme;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Path("/simulate")
public class SimulationResource {

    private Simulation simulation;

    public SimulationResource() {
        // Initialisation des corps célestes avec des positions et vitesses aléatoires
        List<CorpsCeleste> corpsList = new ArrayList<>();
        corpsList.add(new CorpsCeleste(100, 100, 0, 0));  // Exemple d'un corps
        corpsList.add(new CorpsCeleste(-100, -100, 0, 0));  // Exemple d'un autre corps
        corpsList.add(new CorpsCeleste(150, -25, 0, 0));  // Exemple d'un autre corps
        corpsList.add(new CorpsCeleste(-250, 250, 0, 0));
        corpsList.add(new CorpsCeleste(50, -25, 0, 0));
        corpsList.add(new CorpsCeleste(-120, 200, 0, 0));
        corpsList.add(new CorpsCeleste(-5, 5, 0, 0));

        simulation = new Simulation(corpsList);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CorpsCeleste> simulate() {
        double temps = 0.5;  // Intervalle de temps pour la simulation (ajuster selon besoin)
        simulation.updateSimulation(temps);
        return simulation.getCorps();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<CorpsCeleste> addPlanet() {
        // Générer des positions aléatoires pour la planète
        Random rand = new Random();
        double positionX = rand.nextDouble() * 700 - 350;  // Position aléatoire entre -350 et 350
        double positionY = rand.nextDouble() * 700 - 350;  // Position aléatoire entre -350 et 350

        CorpsCeleste planet = new CorpsCeleste(positionX, positionY, 0, 0);  // Vitesse nulle
        simulation.getCorps().add(planet);  // Ajout de la planète à la simulation
        return simulation.getCorps();  // Retourne la nouvelle liste des planètes
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteAllPlanets() {
        // Vider la liste des corps célestes
        simulation.getCorps().clear();
        // return
    }

}
