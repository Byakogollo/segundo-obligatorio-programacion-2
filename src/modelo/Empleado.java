/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import persistencia.ArchivoGuardar;

/**
 *
 * @author jacqu
 */
public class Empleado extends Persona {
    
    private double salarioMensual;
    private boolean curriculum;
    private Manager manager;
    private Area area;


    public Empleado(String nombre, int ci, int celular,double salarioMensaual, String curriculum, Manager manager, Area area) {
        super(nombre, ci,celular);
        this.salarioMensual = salarioMensaual;
        this.manager = manager;
        this.area = area;
        this.curriculum = new ArchivoGuardar().guardarCurriculum(curriculum, ci);
    }

    public double getSalarioMensual() { return salarioMensual; }
    
    public Manager getManager() { return manager; }
   
    public Area getArea() { return area; }
    
    public void setArea(Area area) { this.area = area; }

    
   
    
    
    
    
    
}
