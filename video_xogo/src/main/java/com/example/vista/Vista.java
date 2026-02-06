package com.example.vista;

import java.util.ArrayList;

import org.bson.Document;

import com.example.controlador.Controlador;

public class Vista {

    private final Controlador controlador = new Controlador();

    /**
     * Muestra los datos de la partida por consola
     * @param lista
     */
    private void mostrarDatos(ArrayList<Document> lista) {
        for (Document partida : lista) {
            System.out.println(partida);
        }
    }

    public Vista() {
        controlador.addPartida("Partida", "Mario", "Space Invaders", 1200, 15, 3);
        controlador.addPartida("Partida", "Mario", "Space Invaders", 1500, 18, 4);
        controlador.addPartida("Partida", "Mario", "Space Invaders", 250, 4, 1);
        controlador.addPartida("Partida", "Mario", "Space Invaders", 9999, 60, 20);

        controlador.addPartida("Partida", "Luigi", "Space Invaders", 1300, 16, 4);
        controlador.addPartida("Partida", "Luigi", "Space Invaders", 1500, 12, 5);
        controlador.addPartida("Partida", "Luigi", "Space Invaders", 250, 4, 1);
        controlador.addPartida("Partida", "Luigi", "Space Invaders", 15, 1, 1);

        controlador.addPartida("Partida", "Peach", "Space Invaders", 999, 13, 2);
        controlador.addPartida("Partida", "Peach", "Space Invaders", 1500, 12, 5);
        controlador.addPartida("Partida", "Peach", "Space Invaders", 2500, 20, 6);
        controlador.addPartida("Partida", "Peach", "Space Invaders", 2100, 17, 6);

        mostrarDatos(controlador.puntuacionTotalXogador("Partida")); 
        mostrarDatos(controlador.puntuacionMax("Partida"));
        mostrarDatos(controlador.partidaMaisCurta("Partida"));
        mostrarDatos(controlador.rankingXogadores("Partida"));
        mostrarDatos(controlador.listarPartidas("Partida"));
        mostrarDatos(controlador.puntuacionMedia("Partida"));
    }
}
