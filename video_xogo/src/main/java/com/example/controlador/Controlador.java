package com.example.controlador;

import java.util.ArrayList;

import org.bson.Document;

public class Controlador {
    private final ControladorPartida cPartida = new ControladorPartida();

    public void addXogador(String nColeccion, String xogador, String xogo, int puntuacion, int duracion, int nivel) {
        cPartida.addXogador(nColeccion, xogador, xogo, puntuacion, duracion, nivel);
    }

    public ArrayList<Document> puntuacionTotalXogador(String nColeccion) {
        return cPartida.puntuacionTotalXogador(nColeccion);
    }

    public ArrayList<Document> puntuacionMax(String nColeccion){
        return cPartida.puntuacionMax(nColeccion);
    }

}
