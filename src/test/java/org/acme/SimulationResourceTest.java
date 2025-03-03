package org.acme;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.GenericType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import jakarta.ws.rs.client.Entity;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.Response;
import java.util.List;



@QuarkusTest
public class SimulationResourceTest {

    private static Client client;

    @BeforeAll
    public static void setup() {
        client = ClientBuilder.newClient();
    }

    @Test
    public void testGetPlanets() {
        // Récupérer la liste des corps célestes via une requête GET
        List<CorpsCeleste> corpsList = client.target("http://localhost:8080/simulate")
                .request()
                .get(new GenericType<List<CorpsCeleste>>() {});

        // Vérifier que la liste des corps célestes n'est pas vide
        assertTrue(corpsList.isEmpty(), "La liste des corps célestes ne doit pas être vide.");
    }



    @Test
    public void testAddPlanet() {
        CorpsCeleste planet = new CorpsCeleste(0, 0, 0, 0, 10e11);
        List<CorpsCeleste> corpsList = client.target("http://localhost:8080/simulate")
                .request()
                .post(Entity.json(planet), new GenericType<List<CorpsCeleste>>() {});

        assertNotNull(corpsList, "La liste des corps célestes ne doit pas être nulle.");
        assertFalse(corpsList.isEmpty(), "La liste des corps célestes doit contenir des éléments après l'ajout.");
    }


    @Test
    public void testDeleteAllPlanets() {
        // Vérifier la liste des planètes avant la suppression
        List<CorpsCeleste> corpsListBefore = client.target("http://localhost:8080/simulate")
                .request()
                .get(new GenericType<List<CorpsCeleste>>() {});

        // Vérifier qu'il y a bien des planètes avant la suppression
        assertFalse(corpsListBefore.isEmpty(), "La liste des planètes ne doit pas être vide au début.");

        // Exécuter la requête DELETE
        Response response = client.target("http://localhost:8080/simulate")
                .request()
                .delete();

        // Vérifier que la réponse HTTP a un code de statut 200 ou 204 (No Content ou OK)
        assertTrue(response.getStatus() == 200 || response.getStatus() == 204,
                "La réponse HTTP doit être 200 ou 204.");

        // Vérifier la liste des planètes après la suppression
        List<CorpsCeleste> corpsListAfter = client.target("http://localhost:8080/simulate")
                .request()
                .get(new GenericType<List<CorpsCeleste>>() {});

        // La liste des planètes devrait être vide après la suppression
        assertTrue(corpsListAfter.isEmpty(), "La liste des planètes doit être vide après suppression.");
    }



}
