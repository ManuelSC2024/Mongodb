package com.example.controlador;

import java.util.ArrayList;

import com.example.modelo.Empleado;

public class Controlador {
    private ControladorEmpleado cEmpleado = new ControladorEmpleado();

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
            String oficio, Integer comision) {
        return cEmpleado.crearEmpleado(emp_no, nombre, departamento, salario, fechaAlta, oficio, comision);
    }

    /**
     * Delega la inserción de un empleado en la base de datos al ControladorEmpleado
     * 
     * @param empleado Empleado a insertar
     */
    public void insertarEmpleado(Empleado empleado) {
        cEmpleado.insertarEmpleado(empleado);
    }

    /**
     * Delega la visualización de los empleados de un departamento
     * 
     * @param coleccion    Nombre de la colección
     * @param departamento Departamento a filtrar
     */
    public void verEmpleadoDepartamento(String coleccion, int departamento) {
        cEmpleado.verEmpleadoDepartamento(coleccion, departamento);
    }

    /**
     * Delega la visualización de empleados pertenecientes a dos departamentos
     * 
     * @param coleccionName Nombre de la colección
     * @param departamento1 Primer departamento
     * @param departamento2 Segundo departamento
     */
    public void verEmpleadoDepartamentos(String coleccionName, int departamento1, int departamento2) {
        cEmpleado.verEmpleadoDepartamentos(coleccionName, departamento1, departamento2);
    }

    /**
     * Delega la visualización de empleados por oficio y salario
     * 
     * @param coleccionName Nombre de la colección
     * @param oficio        Oficio del empleado
     * @param salario       Salario mínimo o exacto a filtrar
     */
    public void verEmpleadoSalarioOficio(String coleccionName, String oficio, int salario) {
        cEmpleado.verEmpleadoSalarioOficio(coleccionName, oficio, salario);
    }

    /**
     * Delega el cálculo de la media salarial de los empleados
     * 
     * @param collection Nombre de la colección
     */
    public double mediaSalario(String collection) {
        return cEmpleado.mediaSalario(collection);
    }

    /**
     * Delega la visualización del salario mínimo, medio y máximo por departamento
     * 
     * @param collection   Nombre de la colección
     * @param departamento Departamento a analizar
     */
    public void verSalarioMediaMAxDepartamento(String collection, int departamento) {
        cEmpleado.verSalarioMediaMAxDepartamento(collection, departamento);
    }

  public void verSalarioMediaMAxDepartamento2(String collection, int departamento) {
        cEmpleado.verSalarioMediaMAxDepartamento2(collection, departamento);
    }


    /**
     * Delega la obtención del empleado con el salario máximo
     * 
     * @param collectionName Nombre de la colección
     * @return Lista con la información del empleado con mayor salario
     */
    public ArrayList<String> empleadoMAxSalario(String collectionName) {
        return cEmpleado.empleadoMAxSalario(collectionName);
    }

    /**
     * Delega el aumento de salario a los empleados según su oficio
     * 
     * @param coleccionName Nombre de la colección
     * @param oficio        Oficio al que se le aplicará el aumento
     * @param salario       Cantidad a aumentar
     */
    public void aumentarSalario(String coleccionName, String oficio, int salario) {
        cEmpleado.aumentarSalario(coleccionName, oficio, salario);
    }

    /**
     * Delega la reducción de comisión a los empleados
     * 
     * @param coleccionName Nombre de la colección
     * @param comision      Comisión a reducir
     */
    public void cambiarComision(String coleccionName, int comision) {
        cEmpleado.cambiarComision(coleccionName, comision);
    }

    /**
     * Delega el borrado de un empleado por su identificador
     * 
     * @param collectionName Nombre de la colección
     * @param emp_no         Id del empleado a borrar
     */
    public void borrarEmpleado(String collectionName, int emp_no) {
        cEmpleado.borrarEmpleado(collectionName, emp_no);
    }
}
