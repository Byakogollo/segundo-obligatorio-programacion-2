package modelo;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;



public class Sistema implements Serializable {
    private ArrayList<Area> areas;
    private ArrayList<Empleado> empleados;
    private ArrayList<Movimiento> movimientos;
    private ArrayList<Manager> managers;

    public Sistema() {
        this.areas = new ArrayList<>();
        this.empleados = new ArrayList<>();
        this.movimientos = new ArrayList<>();
        this.managers = new ArrayList<>();
    }
    
       // getters para las ventanas, para cuando necesitemos llamar a los metodos

    public ArrayList<Area> getAreas() {
        return areas;
    }

    public ArrayList<Manager> getManagers() {
        return managers;
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
    }




    // logica de Areas

    public boolean altaArea(String nombre, String descripcion, double presupuesto) throws ExcepcionesSistema{
        try{       
        if(nombre.trim().equals(""))
            throw new ExcepcionesSistema("Ingrese un nombre");
        if (this.getAreas().indexOf(nombre) != -1)
            throw new ExcepcionesSistema("El area ya fue ingresada");
        if (descripcion.trim().equals(""))
            throw new ExcepcionesSistema("Ingrese una descripcion del area");
        if(presupuesto <=0)
            throw new ExcepcionesSistema("El presupuesto del area no puede ser 0");
 }catch(ExcepcionesSistema e){
      JOptionPane.showMessageDialog(null, "Error: "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
      return false;
 }
        Area nuevaArea = new Area(nombre.trim(),descripcion.trim(), presupuesto);
        areas.add(nuevaArea);
        ordenarAreasPorNombre();
        return true;

    }

    public Area buscarAreaPorNombre(String nombre) {
        if (nombre == null)
            return null;
        int i = 0;
        while (i < areas.size()) {
            if (areas.get(i).getNombre().equalsIgnoreCase(nombre)) {
                return areas.get(i);
            }
            i++;
        }
        return null;
    }

    public void ordenarAreasPorNombre() {
        int i = 0;
        while (i < areas.size()) {
            int min = i;
            int j = i + 1;
            while (j < areas.size()) {
                String a = areas.get(j).getNombre();
                String b = areas.get(min).getNombre();
                if (a.compareToIgnoreCase(b) < 0) {
                    min = j;
                }
                j++;
            }
            if (min != i) {
                Area tmp = areas.get(i);
                areas.set(i, areas.get(min));
                areas.set(min, tmp);
            }
            i++;
        }
    }

    public ArrayList<Area> listarAreasOrdenadasPorNombre() {
        ArrayList<Area> copia = new ArrayList<>();
        int i = 0;
        while (i < this.areas.size()) {
            copia.add(this.areas.get(i)); 
            i++;
        }
            
        return copia;
    }

    public ArrayList<Area> listarAreasSinEmpleados() {
        ArrayList<Area> res = new ArrayList<>();
        int i = 0;
        while (i < areas.size()) {
            if (areas.get(i).getEmpleados().isEmpty()) {
                res.add(areas.get(i));
            }
            i++;
        }
        return res;
    }
   
    //REVISAR
    public ArrayList<Area> listarAreasQueNoTenganEmpleado(Empleado e){
        ArrayList<Area> resultado = new ArrayList<>();
        
        for (int i=0; i<this.getAreas().size();i++){
            
           if(!e.getArea().equals(this.getAreas().get(i)))
            resultado.add(this.getAreas().get(i));
        }
        System.out.println("action");
        return resultado;
    }  
    
    public boolean bajaArea(String nombreArea) {
        int i = 0;
        while (i < areas.size()) {
            Area a = areas.get(i);
            if (a.getNombre().equalsIgnoreCase(nombreArea) && a.getEmpleados().isEmpty()) {
                areas.remove(i);
                return true;
            }
            i++;
        }
        return false;
    }
    
    

    // logica de managers

    public boolean altaManager(String nombre,int ci, int celular, int antiguedad) throws ExcepcionesSistema{
         try{       
        if(nombre.trim().equals(""))
            throw new ExcepcionesSistema("Ingrese un nombre");
        if (antiguedad <= 0)
            throw new ExcepcionesSistema("La antiguedad no puede ser 0");
        if (celular == 0)
            throw new ExcepcionesSistema("El celular no puede ser 0");
        if(this.ciManagerExiste(ci) || this.ciEmpleadoExiste(ci))
            throw new ExcepcionesSistema("Esta persona ya fue ingresada al sistema");
        if(ci == 0)
            throw new ExcepcionesSistema("Ingrese una cedula");
 }catch(ExcepcionesSistema e){
      JOptionPane.showMessageDialog(null, "Error: "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
      return false;
 }

        Manager m = new Manager(nombre.trim(), ci, celular, antiguedad);
        this.managers.add(m);
        ordenarManagersPorAntiguedadDesc();
        return true;
    }

    private boolean ciManagerExiste(int ci) {
        int i = 0;
        while (i < this.managers.size()) {
            if (this.managers.get(i).getCi() == ci) {
                return true;
            }
            i++;
        }
        return false;
    }
    
    
    private boolean ciEmpleadoExiste(int ci) {
       boolean existe = false;
        for(int i = 0; i < this.getEmpleados().size() && !existe && !this.getEmpleados().isEmpty(); i++){
            if(this.getEmpleados().get(i).getCi() == ci)
                existe = true;
        }
        
        return existe;
    }

    public Manager buscarManagerPorCI(int ci) {
        int i = 0;
        while (i < managers.size()) {
            if (managers.get(i).getCi() == ci) {
                return managers.get(i);
            }
            i++;
        }
        return null;
    }

    public boolean modificarTelefonoManager(int ci, int nuevoCelular) throws ExcepcionesSistema{
        Manager m = buscarManagerPorCI(ci);
        
        try{
            if(nuevoCelular == m.getCelular())
            throw new ExcepcionesSistema("El numero nuevo no puede ser igual al anterior");
            
        }catch(ExcepcionesSistema e){
            JOptionPane.showMessageDialog(null, "Error: "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }        
        
        m.setCelular(nuevoCelular);
        return true;
    }

    public boolean bajaManager(int ci) {
        int i = 0;
        while (i < managers.size()) {
            Manager m = managers.get(i);
            if (m.getCi() == ci && m.getEmpleadosACargo().isEmpty()) {
                managers.remove(i);
                return true;
            }
            i++;
        }
        return false;
    }

    public ArrayList<Manager> listarManagersPorAntiguedadDesc() {
        ArrayList<Manager> copia = new ArrayList<>();
        int i = 0;
        while (i < managers.size()) {
            copia.add(managers.get(i));
            i++;
        }

        int j = 0;
        while (j < copia.size()) {
            int max = j;
            int k = j + 1;
            while (k < copia.size()) {
                if (copia.get(k).getAntiguedad() > copia.get(max).getAntiguedad()) {
                    max = k;
                }
                k++;
            }
            if (max != j) {
                Manager tmp = copia.get(j);
                copia.set(j, copia.get(max));
                copia.set(max, tmp);
            }
            j++;
        }
        return copia;
    }

    public void ordenarManagersPorAntiguedadDesc() {
        int i = 0;
        while (i < managers.size()) {
            int max = i;
            int j = i + 1;
            while (j < managers.size()) {
                if (managers.get(j).getAntiguedad() > managers.get(max).getAntiguedad()) {
                    max = j;
                }
                j++;
            }
            if (max != i) {
                Manager tmp = managers.get(i);
                managers.set(i, managers.get(max));
                managers.set(max, tmp);
            }
            i++;
        }
    }

    // logica empleados

    public boolean altaEmpleado(String nombre, int ci, int celular, String textoCV, double salarioMensual, Manager manager, Area area) throws ExcepcionesSistema {
       
        if(ci <= 0)
            throw new ExcepcionesSistema("La cedula no puede ser 0");
        if(celular <= 0)
            throw new ExcepcionesSistema("El celular no puede ser 0");
        if(salarioMensual <= Double.parseDouble("0"))
            throw new ExcepcionesSistema("El salario no puede ser 0");
        if (ciEmpleadoExiste(ci)||ciManagerExiste(ci))
            throw new ExcepcionesSistema("El empleado ya fue ingresado");
        double costoAnual = salarioMensual * 12.0;
        if (!area.tienePresupuestoPara(costoAnual)) {
            throw new ExcepcionesSistema("El area no tiene el presupuesto para ese empleado");
        }
                
        Empleado e = new Empleado(nombre.trim(), ci,
                celular,
                salarioMensual,
                textoCV,
                manager,
                area);

        empleados.add(e);
        area.agregarEmpleado(e);
        manager.agregarEmpleado(e);

        ordenarEmpleadosPorSalarioAsc();
        return true;
    }

    public ArrayList<Empleado> listarEmpleadosPorSalarioAsc() {
        ArrayList<Empleado> copia = new ArrayList<>();
        int i = 0;
        while (i < empleados.size()) {
            copia.add(empleados.get(i));
            i++;
        }
        // selección por salario asc
        int j = 0;
        while (j < copia.size()) {
            int min = j;
            int k = j + 1;
            while (k < copia.size()) {
                if (copia.get(k).getSalarioMensual() < copia.get(min).getSalarioMensual()) {
                    min = k;
                }
                k++;
            }
            if (min != j) {
                Empleado tmp = copia.get(j);
                copia.set(j, copia.get(min));
                copia.set(min, tmp);
            }
            j++;
        }
        return copia;
    }

    private void ordenarEmpleadosPorSalarioAsc() {
        int i = 0;
        while (i < empleados.size()) {
            int min = i;
            int j = i + 1;
            while (j < empleados.size()) {
                if (empleados.get(j).getSalarioMensual() < empleados.get(min).getSalarioMensual()) {
                    min = j;
                }
                j++;
            }
            if (min != i) {
                Empleado tmp = empleados.get(i);
                empleados.set(i, empleados.get(min));
                empleados.set(min, tmp);
            }
            i++;
        }
    }
    
    
    public Empleado buscarEmpleadoPorCI(String ci){
        
        ci=ci.replaceAll("[^0-9]", "");
        
        for(Empleado elem : this.getEmpleados()){
            
            if(elem.getCi() == Integer.parseInt(ci)){
                
                return elem;
            }
            
            
        }
        
            return null;
        
        
    }
    
    public ArrayList<Empleado> getEmpleadosPorArea(Area a){
        ArrayList<Empleado> resultado = new ArrayList<>();
        for(int i = 0; i<this.getEmpleados().size(); i++){
            
            if(this.getEmpleados().get(i).getArea().getNombre().equals(a.getNombre()))
            resultado.add(this.getEmpleados().get(i));
            
        }
        return resultado;
    }

    // movimientos de empleados entre areas

    public boolean moverEmpleado(int mes, Empleado emp, Area destino)throws ExcepcionesSistema {
        try{
            
        if (!(emp instanceof Empleado))
            throw new ExcepcionesSistema("Seleccione un empleado");
        if(!(destino instanceof Area))
            throw new ExcepcionesSistema("Seleccione un area de destino");
        
        Area origen = emp.getArea();
                
        
        int mesesRestantes = 13 - mes;
        double montoNecesario = emp.getSalarioMensual() * mesesRestantes;

        if (!destino.tienePresupuestoPara(montoNecesario)) 
            throw new ExcepcionesSistema("El area seleccionada no tiene presupuesto para ese empleado");
        
         origen.removerEmpleado(emp);

        destino.agregarEmpleado(emp);

        emp.setArea(destino);

        movimientos.add(new Movimiento(mes, origen, destino, emp));

        return true;
        
        }catch(ExcepcionesSistema ex){
            JOptionPane.showMessageDialog(null, "Error: "+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }

       return false;
    }

    public ArrayList<Movimiento> listarMovimientosPorMesAsc() {
        ArrayList<Movimiento> copia = new ArrayList<>();
        int i = 0;
        while (i < movimientos.size()) {
            copia.add(movimientos.get(i));
            i++;
        }
        int j = 0;
        while (j < copia.size()) {
            int min = j;
            int k = j + 1;
            while (k < copia.size()) {
                if (copia.get(k).getMes() < copia.get(min).getMes()) {
                    min = k;
                }
                k++;
            }
            if (min != j) {
                Movimiento tmp = copia.get(j);
                copia.set(j, copia.get(min));
                copia.set(min, tmp);
            }
            j++;
        }
        return copia;
    }

       public int calcularColor(Empleado emp) {
        
     double v = emp.getSalarioMensual();
            int resultado = (int)Math.floor(v); 
            
            if(resultado > 255)
            resultado=255;
            else if(resultado < 0)
            resultado = 1;
            
            
            
              return resultado;
        
    }
    
    
    // precarga de datos

    public void inicializar() {
        try{
            
        
        this.altaArea("Personal", "Reclutamiento de personal, promociones, gestión de cargos", 100000.00);
        this.altaArea("RRHH", "Relacionamiento en la empresa, organigrama, gestión de equipos", 80000.00);
        this.altaArea("Seguridad",
                "Seguridad física, vigilancia, seguridad informática, protocolos y políticas de seguridad", 120000.00);
        this.altaArea("Comunicaciones",
                "Comunicaciones internas, reglas y protocolos, comunicaciones con proveedores y clientes", 20000.00);
        this.altaArea("Marketing",
                "Acciones planificadas, publicidad en medios masivos, publicidad en redes, gestión de redes", 95000.00);

        this.altaManager("Ana Martínez", 45683691, 99123456, 10);
        this.altaManager("Ricardo Morales", 32145893, 94121212, 4);
        this.altaManager("Laura Torales", 35892575, 99654321, 1);
        this.altaManager("Juan Pablo Zapata", 45551977, 99202020, 5);
        
        }catch(ExcepcionesSistema e){
            
            JOptionPane.showMessageDialog(null, "Error: "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);

        }
    }

 


    
    
}

 