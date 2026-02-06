package com.example.controlador;

import java.util.ArrayList;

import org.bson.Document;

public class Controlador {
    private final ControladorPartida cPartida = new ControladorPartida();

    /**
     * Delega la creación de una partida al ControladorPartida.
     * 
     * @param nColeccion nombre de la colección
     * @param xogador    nombre del jugador
     * @param xogo       nombre del juego
     * @param puntuacion puntuación alcanzada por el jugador
     * @param duracion   tiempo total de la partida
     * @param nivel      nivel alcanzado en la partida
     */
    public void addPartida(String nColeccion, String xogador, String xogo, int puntuacion, int duracion, int nivel) {
        cPartida.addPartida(nColeccion, xogador, xogo, puntuacion, duracion, nivel);
    }

    /**
     * Obtiene la puntuación total acumulada por cada jugador.
     * 
     * @param nColeccion nombre de la colección
     * @return ArrayList<Document> con la puntuación total por jugador
     */
    public ArrayList<Document> puntuacionTotalXogador(String nColeccion) {
        return cPartida.puntuacionTotalXogador(nColeccion);
    }

    /**
     * Obtiene la puntuación máxima registrada en las partidas.
     * 
     * @param nColeccion nombre de la colección
     * @return ArrayList<Document> con la puntuación máxima
     */
    public ArrayList<Document> puntuacionMax(String nColeccion) {
        return cPartida.puntuacionMax(nColeccion);
    }

    /**
     * Obtiene la partida con menor duración.
     * 
     * @param nColeccion nombre de la colección
     * @return ArrayList<Document> con los datos de la partida más corta
     */
    public ArrayList<Document> partidaMaisCurta(String nColeccion) {
        return cPartida.partidaMaisCurta(nColeccion);
    }

    /**
     * Genera un ranking de jugadores ordenado por puntuación.
     * 
     * @param nCollection nombre de la colección
     * @return ArrayList<Document> con el ranking de jugadores
     */
    public ArrayList<Document> rankingXogadores(String nCollection) {
        return cPartida.rankingXogadores(nCollection);
    }

    /**
     * Lista todas las partidas almacenadas en la colección.
     * 
     * @param nCollection nombre de la colección
     * @return ArrayList<Document> con todas las partidas
     */
    public ArrayList<Document> listarPartidas(String nCollection) {
        return cPartida.listarPartidas(nCollection);
    }

    /**
     * Calcula la puntuación media de las partidas.
     * 
     * @param nCollection nombre de la colección
     * @return ArrayList<Document> con la puntuación media
     */
    public ArrayList<Document> puntuacionMedia(String nCollection) {
        return cPartida.puntuacionMedia(nCollection);
    }
}
