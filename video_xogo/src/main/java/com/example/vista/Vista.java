package com.example.vista;

import java.util.ArrayList;

import org.bson.Document;

import com.example.controlador.Controlador;

public class Vista {

    private final Controlador controlador = new Controlador();

    private void mostrarDatos(ArrayList<Document> lista) {
        for (Document partida : lista) {
            System.out.println(partida);
        }
    }

    public Vista() {
        controlador.addXogador("Partida", "Mario", "Space Invaders", 1200, 15, 3);
        controlador.addXogador("Partida", "Mario", "Space Invaders", 1500, 18, 4);
        controlador.addXogador("Partida", "Mario", "Space Invaders", 250, 4, 1);
        controlador.addXogador("Partida", "Mario", "Space Invaders", 9999, 60, 20);

        controlador.addXogador("Partida", "Luigi", "Space Invaders", 1300, 16, 4);
        controlador.addXogador("Partida", "Luigi", "Space Invaders", 1500, 12, 5);
        controlador.addXogador("Partida", "Luigi", "Space Invaders", 250, 4, 1);
        controlador.addXogador("Partida", "Luigi", "Space Invaders", 15, 1, 1);

        controlador.addXogador("Partida", "Peach", "Space Invaders", 999, 13, 2);
        controlador.addXogador("Partida", "Peach", "Space Invaders", 1500, 12, 5);
        controlador.addXogador("Partida", "Peach", "Space Invaders", 2500, 20, 6);
        controlador.addXogador("Partida", "Peach", "Space Invaders", 2100, 17, 6);

        mostrarDatos(controlador.puntuacionTotalXogador("Partida")); 
        mostrarDatos(controlador.puntuacionMax("Partida"));
        mostrarDatos(controlador.partidaMaisCurta("Partida"));
        mostrarDatos(controlador.rankingXogadores("Partida"));
        mostrarDatos(controlador.listarPartidas("Partida"));
        mostrarDatos(controlador.puntuacionMedia("Partida"));
    }
}
