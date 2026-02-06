package com.example.controlador;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;

public class ControladorPartida {
    /**
     * Crea la coleccion Partida si no existe en la base de datos
     */
    public ControladorPartida() {
        try (MongoProvider provider = new MongoProvider()) {
            if (provider.getCollection("Partida") == null) {
                provider.crearCollection("Partida");
            }
        } catch (Exception e) {
            System.out.println("Error al crear la coleccion: " + e.getMessage());
        }
    }

    /**
     * Añade una Partida a la base de datos
     * 
     * @param nColeccion nombre de la coleccion
     * @param xogador    Nombre del xogador
     * @param xogo       Nombre del xogo
     * @param puntuacion Puntuacion que alcanzo el xogador
     * @param duracion   Tiempo total de la partida
     * @param nivel      Nivel alcanzado en la partida
     */
    public void addPartida(String nColeccion, String xogador, String xogo, int puntuacion, int duracion, int nivel) {
        try (MongoProvider provider = new MongoProvider()) {
            Document document = new Document("xogador", xogador)
                    .append("xogo", xogo)
                    .append("puntuacion", puntuacion)
                    .append("duracion", duracion)
                    .append("nivel", nivel);

            provider.getCollection(nColeccion).insertOne(document);
        } catch (Exception e) {
            System.out.println("Error al añadir una partida: " + e.getMessage());
        }
    }

    /**
     * Muesta la puntuacion total del xogador (suma de todas sus puntuaciones)
     * 
     * @param nColeccion Nombre de la coleccion
     * @return ArrayList<Document> con los datos de la partida
     */
    public ArrayList<Document> puntuacionTotalXogador(String nColeccion) {
        ArrayList<Document> lista = new ArrayList<>();

        try (MongoProvider provider = new MongoProvider()) {
            MongoCollection<Document> collection = provider.getCollection(nColeccion);

            List<Bson> pipeline = List.of(Aggregates.group("$xogador",
                    Accumulators.sum("TotalPuntuacion", "$puntuacion")));

            collection.aggregate(pipeline).into(lista);

        } catch (Exception e) {
            System.out.println("error al comprobar la puntuacion total del xogador: " + e.getMessage());
        }

        return lista;
    }

    /**
     * Muestra la puntuacion maxima que alcanzaron los xogadores
     * 
     * @param nColeccion Nombre de la coleccion
     * @return ArrayList<Document> con los datos de la partida
     */
    public ArrayList<Document> puntuacionMax(String nColeccion) {
        ArrayList<Document> lista = new ArrayList<>();

        try (MongoProvider provider = new MongoProvider()) {
            MongoCollection<Document> collection = provider.getCollection(nColeccion);

            List<Bson> pipeline = List.of(
                    Aggregates.group("$xogador", Accumulators.max("maxPuntuacion", "$puntuacion")));

            collection.aggregate(pipeline).into(lista);
        } catch (Exception e) {
            System.out.println("Error al mostrar la puntuacion maxima de los xogadores: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Muestra la partida mas corta de los xogadores
     * 
     * @param nColeccion Nombre de la coleccion
     * @return ArrayList<Document> con los datos de la partida
     */
    public ArrayList<Document> partidaMaisCurta(String nColeccion) {
        ArrayList<Document> lista = new ArrayList<>();

        try (MongoProvider provider = new MongoProvider()) {
            MongoCollection<Document> collection = provider.getCollection(nColeccion);

            List<Bson> pipeline = List.of(
                    Aggregates.group("$xogador", Accumulators.min("duracionMinima", "$duracion")));

            collection.aggregate(pipeline).into(lista);
        } catch (Exception e) {
            System.out.println("Error al mostrar la partida mas corta del xogador: " + e.getMessage());
        }

        return lista;
    }

    /**
     * Muestra un ranking de los xogadores apartir de su puntuacion total
     * 
     * @param nColeccion Nombre de la coleccion
     * @return ArrayList<Document> con los datos de la partida
     */
    public ArrayList<Document> rankingXogadores(String nCollection) {
        ArrayList<Document> lista = new ArrayList<>();

        try (MongoProvider provider = new MongoProvider()) {
            MongoCollection<Document> collection = provider.getCollection(nCollection);

            List<Bson> pipeline = List.of(Aggregates.group("$xogador",
                    Accumulators.sum("puntuacionMaxima", "$puntuacion")),
                    Aggregates.sort(new Document("puntuacionMaxima", -1)));

            collection.aggregate(pipeline).into(lista);
        } catch (Exception e) {
            System.out.println("Error al mostrar el ranking: " + e.getMessage());
        }

        return lista;
    }

    /**
     * Muestra todas las partidas pero solo los campos xogador, xogo e puntuacion
     * 
     * @param nColeccion Nombre de la coleccion
     * @return ArrayList<Document> con los datos de la partida
     */
    public ArrayList<Document> listarPartidas(String nCollection) {
        ArrayList<Document> lista = new ArrayList<>();

        try (MongoProvider provider = new MongoProvider()) {
            MongoCollection<Document> collection = provider.getCollection(nCollection);

            collection.find().projection(new Document("xogador", 1)
                    .append("xogo", 1)
                    .append("puntuacion", 1)
                    .append("_id", 0)).into(lista);

        } catch (Exception e) {
            System.out.println("Error al mostrar el xogador, xogo e a puntuacion: " + e.getMessage());
        }

        return lista;
    }

    /**
     * Muestra la puntuacion media de los xogadores
     * 
     * @param nColeccion Nombre de la coleccion
     * @return ArrayList<Document> con los datos de la partida
     */
    public ArrayList<Document> puntuacionMedia(String nCollection) {
        ArrayList<Document> lista = new ArrayList<>();

        try (MongoProvider provider = new MongoProvider()) {
            MongoCollection<Document> collection = provider.getCollection(nCollection);

            List<Bson> pipelline = List.of(Aggregates.group("$xogador",
                    Accumulators.avg("puntuacionMedia", "$puntuacion")),
                    Aggregates.sort(new Document("puntuacionMedia", -1)));

            collection.aggregate(pipelline).into(lista);
        } catch (Exception e) {
            System.out.println("Error al mostrar la media: " + e.getMessage());
        }

        return lista;
    }
}
