package com.example.controlador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.example.modelo.Empleado;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.BsonField;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;

public class ControladorEmpleado {

    /**
     * Crea la coleccion Empleados si no existe
     */
    public ControladorEmpleado() {
        try (MongoProvider provider = new MongoProvider()) {
            if (provider.getCollection("Empleados") == null) {
                provider.crearCollection("Empleados");
            }
        } catch (Exception e) {
            System.out.println("Error al crear la coleccion: " + e.getMessage());
        }
    }

    /**
     * Delega la creación del empleado al ControladorEmpleado
     * 
     * @param emp_no       Id del empleado
     * @param nombre       Nombre del empleado
     * @param departamento Departamento del empleado
     * @param salario      Salario del empleado
     * @param fechaAlta    Fecha de alta del empleado
     * @param oficio       Oficio del empleado
     * @param comision     Comisión del empleado
     * @return Devuelve un objeto de tipo Empleado
     */
    public Empleado crearEmpleado(int emp_no, String nombre, int departamento, int salario, String fechaAlta,
            String oficio,
            Integer comision) {
        return new Empleado(emp_no, nombre, departamento, salario, fechaAlta, oficio, comision);
    }

    /**
     * Inseta un empleado en la base de datos
     * 
     * @param empleado Empleado a insertar
     */
    public void insertarEmpleado(Empleado empleado) {
        try (MongoProvider provider = new MongoProvider()) {

            Document document = new Document("Emp_no", empleado.getEmp_no())
                    .append("nombre", empleado.getNombre())
                    .append("departamento", empleado.getDepartamento())
                    .append("salario", empleado.getSalario())
                    .append("fechaalta", empleado.getFechaAlta());

            if (empleado.getOficio() != null) {
                document.append("oficio", empleado.getOficio());
            }

            if (empleado.getComision() != null) {
                document.append("comision", empleado.getComision());
            }

            provider.getCollection("Empleados").insertOne(document);
        } catch (Exception e) {
            System.out.println("Error al insertar el empleado: " + e.getMessage());
        }
    }

    /**
     * Ve los empleados de un departamento
     * 
     * @param coleccion    Nombre de la coleccion
     * @param departamento Departameto a comprobar
     */
    public void verEmpleadoDepartamento(String coleccion, int departamento) {
        try (MongoProvider provider = new MongoProvider()) {
            Document document = new Document("departamento", departamento);
            ArrayList<Document> empleados = new ArrayList<>();

            provider.getCollection(coleccion).find(document).into(empleados);

            System.out.println("=== VER ===");
            for (Document empleado : empleados) {
                System.out.println(empleado);
            }
            System.out.println("=== VER ===");

        } catch (Exception e) {
            System.out.println("Error al visualizar los empleados: " + e.getMessage());
        }
    }

    /**
     * Los empleados que hay en dos departamentos
     * 
     * @param coleccionName Nombre de la coleccion
     * @param departamento1 Departamento1 a comprobar
     * @param departamento2 Departamento2 a comprobar
     */
    public void verEmpleadoDepartamentos(String coleccionName, int departamento1, int departamento2) {
        try (MongoProvider provider = new MongoProvider()) {
            MongoCollection<Document> colecion = provider.getCollection(coleccionName);

            ArrayList<Document> empleados = new ArrayList<>();

            Bson filtro = Filters.or(
                    Filters.eq("departamento", departamento1),
                    Filters.eq("departamento", departamento2));

            // Bson filtro = Filters.in("departamento", departamento1, departamento2);
            // Tambien sirve y es mas corto

            colecion.find(filtro).into(empleados);

            System.out.println("=== VER ===");
            for (Document empleado : empleados) {
                System.out.println(empleado);
            }
            System.out.println("=== VER ===");

        } catch (Exception e) {
            System.out.println("Error al visualizar los empleados: " + e.getMessage());
        }
    }

    /**
     * Muestra los empleados con una salario mallor al ingresado y su oficio
     * 
     * @param coleccionName Nombre de la coleccion
     * @param oficio        Oficio a comprobar
     * @param salario       Salario a comprobar
     */
    public void verEmpleadoSalarioOficio(String coleccionName, String oficio, int salario) {
        try (MongoProvider provider = new MongoProvider()) {
            MongoCollection<Document> colecion = provider.getCollection(coleccionName);
            ArrayList<Document> empleados = new ArrayList<>();

            Bson filtro = Filters.and(
                    Filters.eq("oficio", oficio),
                    Filters.gt("salario", salario));

            colecion.find(filtro).into(empleados);

            System.out.println("=== VER ===");
            for (Document empleado : empleados) {
                System.out.println(empleado);
            }
            System.out.println("=== VER ===");

        } catch (Exception e) {
            System.out.println("Error al visualizar los empleados: " + e.getMessage());
        }
    }

    /**
     * Muestra el salario medio de los empleados
     * 
     * @param collection Nombre de la coleccion
     */
    public double mediaSalario(String collection) {
        double sumaSalario = 0;

        try (MongoProvider provider = new MongoProvider()) {
            MongoCollection<Document> coleccion = provider.getCollection(collection);
            ArrayList<Document> lista = new ArrayList<>();

            coleccion.find().into(lista);

            for (Document document : lista) {
                sumaSalario += document.getInteger("salario");
            }

            sumaSalario = (sumaSalario / lista.size());

            // El agregate esta incompleto y probablemente mal
            /*AggregateIterable<Document> agg = coleccion.aggregate(
                    Arrays.asList(
                            Aggregates.group("id", new BsonField("salarioMedio",
                                    new BsonDocument("$avg", new BsonString())))));*/

        } catch (Exception e) {
            System.out.println("Error al calcular la media de salario: " + e.getMessage());
        }
        return sumaSalario;
    }

    /**
     * Ve el salario medio y maximo por departamento
     * 
     * @param collection   Nombre de la coleccion
     * @param departamento Departamento a consultar
     */
    public void verSalarioMediaMAxDepartamento(String collection, int departamento) {
        try (MongoProvider provider = new MongoProvider()) {
            MongoCollection<Document> coleccion = provider.getCollection(collection);

            int sumaSalario = 0;
            ArrayList<Document> lista = new ArrayList<>();

            Bson filtro = Filters.and(Filters.eq("departamento", departamento));
            coleccion.find(filtro).into(lista);
            int salarioMax = coleccion.find(filtro).sort(new Document("salario", -1)).first().getInteger("salario");

            for (Document document : lista) {
                sumaSalario += document.getInteger("salario");
            }
            sumaSalario = sumaSalario / lista.size();

            System.out.println("El salario medio y maximo del dapartamento: " + departamento + " salario medio: "
                    + sumaSalario + " salario max: " + salarioMax);
        } catch (Exception e) {
            System.out.println("Error al calcular el salario maximo: " + e.getMessage());
        }
    }

     public void verSalarioMediaMAxDepartamento2(String collection, int departamento) {
        try (MongoProvider provider = new MongoProvider()) {
            MongoCollection<Document> coleccion = provider.getCollection(collection);
            ArrayList<Document> lista = new ArrayList<>();

            List<Bson> pipeline = List.of(
                Aggregates.group("$departamento", 
                    Accumulators.sum("numEmpleados", 1),
                    Accumulators.avg("salarioMedio", "$salario"),
                    Accumulators.max("salarioMaximo", "$salario")
                ),
                Aggregates.sort(Sorts.ascending("departamento"))
            );

            coleccion.aggregate(pipeline).into(lista);
            
            for (Document empleado : lista) {
                System.out.println(empleado);
            }

        } catch (Exception e) {
            System.out.println("Error al calcular el salario maximo: " + e.getMessage());
        }
    }

    /**
     * Muestra el/los empleados con el salario maximo
     * 
     * @param collectionName Nombre de la coleccion
     * @return Arraylist con los nombres de los empleados que tienen el salario
     *         maximo
     */
    public ArrayList<String> empleadoMAxSalario(String collectionName) {
        ArrayList<String> listaResultado = null;

        try (MongoProvider provider = new MongoProvider()) {
            MongoCollection<Document> coleccion = provider.getCollection(collectionName);

            ArrayList<Document> lista = new ArrayList<>();

            int salarioMax = coleccion.find().sort(new Document("salario", -1)).first().getInteger("salario");

            Bson filtro = Filters.and(Filters.eq("salario", salarioMax));

            coleccion.find(filtro).into(lista);

            listaResultado = new ArrayList<>();
            for (Document document : lista) {
                listaResultado.add(document.get("nombre").toString());
            }
        } catch (Exception e) {
            System.out.println("Error al encontrar el empleado con el salario maximo: " + e.getMessage());
        }

        return listaResultado;
    }

    /**
     * Aumenta el salario de los empleados de un oficcio
     * 
     * @param coleccionName Nombre de la coleccion
     * @param oficio        Oficio del empleado
     * @param salario       Cantidad de salario a aumentar
     */
    public void aumentarSalario(String coleccionName, String oficio, int salario) {
        try (MongoProvider provider = new MongoProvider()) {
            MongoCollection<Document> coleccion = provider.getCollection(coleccionName);

            Bson filtro = Filters.and(
                    Filters.eq("oficio", oficio));

            Bson update = Updates.inc("salario", salario);

            coleccion.updateMany(filtro, update);

        } catch (Exception e) {
            System.out.println("Error al aumentar el salario de los analistas: " + e.getMessage());
        }
    }

    /**
     * Reduce la comision de todos los empleados
     * 
     * @param coleccionName Nombre de la coleccion
     * @param comision      Cantidad a reducir
     */
    public void cambiarComision(String coleccionName, int comision) {
        try (MongoProvider provider = new MongoProvider()) {
            MongoCollection<Document> coleccion = provider.getCollection(coleccionName);

            Bson filtro = Filters.and(
                    Filters.exists("comision"),
                    Filters.gte("comision", comision));

            Bson update = Updates.inc("comision", comision);

            coleccion.updateMany(filtro, update);

        } catch (Exception e) {
            System.out.println("Error al reducir la comision: " + e.getMessage());
        }
    }

    /**
     * Borra un empeado apartir de su id
     * 
     * @param collectionName Nombre de la coleccion
     * @param emp_no         Id del empleado
     */
    public void borrarEmpleado(String collectionName, int emp_no) {
        try (MongoProvider provider = new MongoProvider()) {
            MongoCollection<Document> coleccion = provider.getCollection(collectionName);

            coleccion.deleteOne(Filters.eq("Emp_no", emp_no));
        } catch (Exception e) {
            System.out.println("Error al borrar un empleado: " + e.getMessage());
        }
    }
}