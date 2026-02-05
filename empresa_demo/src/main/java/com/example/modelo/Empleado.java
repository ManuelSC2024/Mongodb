package com.example.modelo;

public class Empleado {
    private int emp_no;
    private String nombre;
    private int departamento;
    private int salario;
    private String fechaAlta;
    private String oficio;
    private Integer comision;

    /**
     * Crea un empleado
     * 
     * @param emp_no       Id del empleado
     * @param nombre       Nombre del empleado
     * @param departamento Departamento del empleado
     * @param salario      Salario del empleado
     * @param fechaAlta    Fecha de alta del empleado
     * @param oficio       Oficio del empleado
     * @param comision     Comisión del empleado
     */
    public Empleado(int emp_no, String nombre, int departamento, int salario, String fechaAlta, String oficio,
            Integer comision) {
        this.emp_no = emp_no;
        this.nombre = nombre;
        this.departamento = departamento;
        this.salario = salario;
        this.fechaAlta = fechaAlta;
        this.oficio = oficio;
        this.comision = comision;
    }

    public int getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public Integer getComision() {
        return comision;
    }

    public void setComision(Integer comision) {
        this.comision = comision;
    }
}
