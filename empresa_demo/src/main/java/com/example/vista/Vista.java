package com.example.vista;

import com.example.controlador.Controlador;
import com.example.modelo.Empleado;

public class Vista {
    private Controlador controlador = new Controlador();

    public Vista() {

        Empleado empleado = controlador.crearEmpleado(1, "Juan", 10, 1000, "10/10/1999", null, null);
        controlador.insertarEmpleado(empleado);

        empleado = controlador.crearEmpleado(2, "Alicia", 10, 1400, "07/08/2000", "Profesora", null);
        controlador.insertarEmpleado(empleado);

        empleado = controlador.crearEmpleado(3, "María Jesús", 20, 1500, "05/01/2005", "Analista", 100);
        controlador.insertarEmpleado(empleado);

        empleado = controlador.crearEmpleado(4, "Alberto", 20, 1100, "15/11/2001", null, null);
        controlador.insertarEmpleado(empleado);

        empleado = controlador.crearEmpleado(5, "Fernando", 30, 1400, "20/11/1999", "Analista", 200);
        controlador.insertarEmpleado(empleado);

        controlador.verEmpleadoDepartamento("Empleados", 10);
        controlador.verEmpleadoDepartamentos("Empleados", 10, 20);
        controlador.verEmpleadoSalarioOficio("Empleados", "Profesora", 1300);
        controlador.aumentarSalario("Empleados", "Analista", 100);
        controlador.cambiarComision("Empleados", -20);
        
        System.out.println("El salario medio es: " + controlador.mediaSalario("Empleados"));

        controlador.verSalarioMediaMAxDepartamento("Empleados", 10);
        
        
        
        for (String empleadoMax : controlador.empleadoMAxSalario("Empleados")) {
            System.out.println("Empleado con nombre: " + empleadoMax + " tiene el salario maximo");
        }


    }
}