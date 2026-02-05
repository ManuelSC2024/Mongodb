package com.example.controlador;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;

public class ControladorPartida {

    public ControladorPartida() {
        try (MongoProvider provider = new MongoProvider()) {
            if (provider.getCollection("Partida") == null) {
                provider.crearCollection("Partida");
            }
        } catch (Exception e) {
            System.out.println("Error al crear la coleccion: " + e.getMessage());
        }
    }

    public void addXogador(String nColeccion, String xogador, String xogo, int puntuacion, int duracion, int nivel) {
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

    public ArrayList<Document> puntuacionMax(String nColeccion){
        ArrayList<Document> lista = new ArrayList<>();
        try (MongoProvider provider = new MongoProvider()) {
            MongoCollection<Document> collection = provider.getCollection(nColeccion);

            List<Bson> pipeline = List.of(
                Aggregates.group("$xogador", Accumulators.max("maxPuntuacion", "$puntuacion"))
            );


        } catch (Exception e) {
            // TODO: handle exception
        }
        
        return lista;
    }

}
