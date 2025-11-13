package modelo;

import java.io.Serializable;

public abstract class Persona implements Serializable {
   protected  String nombre;
   protected  int ci;
   protected  int celular;

   
   //constructor Empleado
    public Persona(String nombre, int ci, int celular) {
        this.nombre = nombre;
        this.ci = ci;
        this.celular = celular;
    }
    

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCi() {
        return ci;
    }

    public int getCelular() {
        return celular;
    }

    @Override
    public String toString() {
        return nombre + " (" + ci + ")";
    }

}
