package com.example.demo.service;

import com.example.demo.data.Voiture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StatistiqueImplTest {

    private StatistiqueImpl statistique;

    @BeforeEach
    void setUp() {
        statistique = new StatistiqueImpl();
    }

    @Test
    void testAjouterUneVoiture() {
        Voiture v = new Voiture("Clio", 10000);
        statistique.ajouter(v);
        Echantillon e = statistique.prixMoyen();
        assertEquals(1, e.getNombreDeVoitures());
        assertEquals(10000, e.getPrixMoyen());
    }

    @Test
    void testAjouterPlusieursVoitures() {
        statistique.ajouter(new Voiture("VoitureA", 1000));
        statistique.ajouter(new Voiture("VoitureB", 2000));
        statistique.ajouter(new Voiture("VoitureC", 3000));

        Echantillon e = statistique.prixMoyen();
        assertEquals(3, e.getNombreDeVoitures());
        assertEquals(2000, e.getPrixMoyen());
    }

    @Test
    void testPrixMoyenAvecDeuxVoitures() {
        statistique.ajouter(new Voiture("Ferrari", 20000));
        statistique.ajouter(new Voiture("Fiat", 10000));

        Echantillon resultat = statistique.prixMoyen();

        assertNotNull(resultat);
        assertEquals(2, resultat.getNombreDeVoitures());
        assertEquals(15000, resultat.getPrixMoyen());
    }

    @Test
    void testPrixMoyenAvecUneVoiture() {
        statistique.ajouter(new Voiture("BMW", 5000));
        Echantillon resultat = statistique.prixMoyen();

        assertEquals(1, resultat.getNombreDeVoitures());
        assertEquals(5000, resultat.getPrixMoyen());
    }

    @Test
    void testPrixMoyenListeVide() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
            statistique.prixMoyen();
        });
    }
}