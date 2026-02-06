package com.example.modelo;

public class Partida {
    private String xogador;
    private String xogo;
    private int puntuacion;
    private int duracion;
    private int nivel;

    /**
     * Crea un objeto Partida
     * 
     * @param nColeccion nombre de la colección
     * @param xogador    nombre del jugador
     * @param xogo       nombre del juego
     * @param puntuacion puntuación alcanzada por el jugador
     * @param duracion   tiempo total de la partida
     * @param nivel      nivel alcanzado en la partida
     */
    public Partida(String xogador, String xogo, int puntuacion, int duracion, int nivel) {
        this.xogador = xogador;
        this.xogo = xogo;
        this.puntuacion = puntuacion;
        this.duracion = duracion;
        this.nivel = nivel;
    }

    public String getXogador() {
        return xogador;
    }

    public void setXogador(String xogador) {
        this.xogador = xogador;
    }

    public String getXogo() {
        return xogo;
    }

    public void setXogo(String xogo) {
        this.xogo = xogo;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
