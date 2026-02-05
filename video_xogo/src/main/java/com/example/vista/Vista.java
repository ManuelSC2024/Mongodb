package com.example.vista;

import java.lang.annotation.Documented;
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
        /*controlador.addXogador("Partida", "Mario", "Space Invaders", 1200, 15, 3);
        controlador.addXogador("Partida", "Mario", "Space Invaders", 1500, 18, 4);
        controlador.addXogador("Partida", "Mario", "Space Invaders", 250, 4, 1);
        controlador.addXogador("Partida", "Mario", "Space Invaders", 9999, 60, 20);*/

        mostrarDatos(controlador.puntuacionTotalXogador("Partida", "Mario")); 
        mostrarDatos(controlador.puntuacionMax(null));
        
    }
}
