package com.example;

import java.nio.file.DirectoryStream.Filter;
import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.model.Filters;

public final class App {

    public static void insertarAlumnado(String nombre, int edad, String ciclo) {
        try (MongoProvider provider = new MongoProvider()) {
            Document document = new Document("nombre", nombre).append("edad", edad).append("ciclo", ciclo);

            provider.alumnado().insertOne(document);
        } catch (Exception e) {
            System.out.println("Error al insertar un alumno: " + e);
        }
    }

    public static void verAlumno(String nombre) {
        try (MongoProvider provider = new MongoProvider()) {
            Document document = new Document("nombre", nombre);
            ArrayList<Document> lista = new ArrayList<>();

            provider.alumnado().find(document).into(lista);
            System.out.println("=== VER ===");
            for (Document dato : lista) {
                System.out.println(dato);
            }
            System.out.println("=== VER ===");

        } catch (Exception e) {
            System.out.println("Error al ver un alumno: " + e);
        }
    }

    public static void borrarAlumno(String nombre) {
        try (MongoProvider provider = new MongoProvider()) {
            provider.alumnado().deleteOne(Filters.eq("nombre", nombre));
        } catch (Exception e) {
            System.out.println("Error al borrar un alumno: " + e);
        }
    }

    public static void main(String[] args) {

        System.out.println("Mirar NotebookLM! para hacer infografias");

        // mongosh -u admin -p admin123 --authenticationDatabase admin

        // ==================================================================================
        // Ejercicio 1
        // ==================================================================================

        // use domotica
        // db.createCollection ...

        // Insert
        // db.mediciones.insertOne({"sensorid": 1,"ts": new Date(2026,2,22),"tipo":
        // "temp","valor": 12,"ubicacion": "A coruña"})

        // consulta, modificar, borrar
        // db.mediciones.findOne({"sensorid":1, "ts": {$gt:ISODate("2026-01-21")}})
        // db.mediciones.findOne({"valor":{$gt: 150}
        // db.mediciones.updateMany({"tipo":{$eq: "temp"},"valor":{$lt: 20}},
        // {$set:{valor: 20}})

        // db.mediciones.deleteMany({"ts":{$lt:ISODate("2026-02-21")}})

        // db.mediciones.findOne({"sensorid":1, "ts": {$gt: new Date(Date.now() - 24 *
        // 60 *60 * 1000)}})

        // ==================================================================================
        // Ejercicio 2
        // ==================================================================================

        // use tienda
        // db.createCollection ...
        // db.clientes.insertOne({"idCliente":1, "nombre":"cliente1",
        // "emails":{"email1":"ciente1@gmail.com",
        // "email2":"cliente1@email2.com"},"dircciones":{"direccion1":"calle falsa",
        // "direccion2": "calle falsa"}})
        // db.clientes.insertOne({"idCliente":2, "nombre":"cliente2",
        // "emails":{"email1":"ciente2@gmail.com",
        // "email2":"cliente2@email2.com"},"dircciones":{"direccion1":"calle fals2a",
        // "direccion2": "calle falsa2"}})
        // db.clientes.insertOne({"idCliente":3, "nombre":"cliente3",
        // "emails":{"email1":"ciente3@gmail.com",
        // "email2":"cliente3@email2.com"},"dircciones":{"direccion1":"calle fals1a",
        // "direccion2": "calle falsa3"}})

        // db.pedidos.insertOne({"idPedido":1, "idCliente": 1, "fecha":new
        // Date(2026,1,24), "lineas":{"sku":1234, "nombre":"algo", "cantidad": 2,"
        // precio": 12}, "estado": "recivido"})
        // db.pedidos.insertOne({"idPedido":2, "idCliente": 1, "fecha":new
        // Date(2026,1,23), "lineas":{"sku":4321, "nombre":"algo2", "cantidad": 182,"
        // precio": 122}, "estado": "recivido"})
        // db.pedidos.insertOne({"idPedido":3, "idCliente": 2, "fecha":new
        // Date(2026,1,22), "lineas":{"sku":9876, "nombre":"algo3", "cantidad": 8,"
        // precio": 8}, "estado": "recivido"})

        // db.pedidos.find({"idCliente":1})
        // db.pedidos.find({"lineas.cantidad":{$gt: 100}})

        /*
         * db.pedidos.aggregate([
         * {
         * $project:
         * {
         * _id:0,
         * idPedido: 1,
         * fecha: 1,
         * estado: 1,
         * cantidadTotal: {$sum: "lineas.cantidad"}
         * }
         * },
         * {
         * $match:{
         * cantidadTotal: {$gt: 100}
         * }
         * }
         * ])
         */

        /*
         * db.pedidos.aggregate([
         * {$unwind: "$lineas"},
         * {$match: {"lineas.cantidad":{$gt:100}}},
         * {$project: {
         * _id: 0,
         * idPedido: 1,
         * idCliente: 1,
         * fecha: 1,
         * estado: 1,
         * linea: "$lineas"
         * }}
         * ])
         */

        // db.pedidos.updateOne({"idPedido": 1}, {$set:{"estado": "cancelado"}})
        // db.clientes.updateOne({"idCliente": 1},
        // {$set:{"emails.email1":"nuevoemail@cliente1.com"}})

        insertarAlumnado("Pepe", 12, "Dam");
        insertarAlumnado("Juan", 12, "Dam");

    }
}
