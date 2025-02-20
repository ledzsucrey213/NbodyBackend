package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Path("/simulate")
public class SimulationResource {

    private Simulation simulation;

    public SimulationResource() {
        // Initialisation de la simulation avec 3 corps célestes
        List<CorpsCeleste> corps = new ArrayList<>(Arrays.asList(
                new CorpsCeleste(0, 0, 0, 0),
                new CorpsCeleste(100, 50, 0, 0),
                new CorpsCeleste(-50, -100, 0, 0)
        ));


        this.simulation = new Simulation(corps);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CorpsCeleste> simulate() {
        // Effectuer un pas de simulation
        simulation.calculerForces(0.1);  // dt = 0.1 seconde

        // Retourner la liste des corps célestes
        return simulation.ListeCorps();
    }
}
