package persistencia;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.*;

public class ArchivoGuardar {

    
    
   public boolean guardarCurriculum(String curriculum, int ci) {
   
       String basePath = System.getProperty("user.dir") + "/cvs";
       new File(basePath).mkdirs();
     
    File cv = new File(basePath + "/CV"+ci+".txt");

    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(cv))) {
        out.writeObject(curriculum);
        out.close();
       
    } catch (IOException e) {
        System.out.println("Error guardando el curriculum: " + e.getMessage());
       return false;
    }
    
    return true;
    
}
   
   public boolean guardarSistema(Sistema modelo){
       
        String basePath = System.getProperty("user.dir") + "/persistencia";
        new File(basePath).mkdirs();
        System.out.println(basePath);
        File sistema = new File(basePath + "/Sistema");

            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(sistema))) {
            out.writeObject(modelo);
            out.flush();
            return true;
                
            } catch (IOException e) {
        
                System.out.println("Error guardando el sistema: " + e.getMessage());
       
                return false;
    
            }
    }
   
   public boolean exportarMovimientos(ArrayList<Movimiento> movimientos, String nomArchivo, String path){
       
       try (FileWriter writer = new FileWriter(nomArchivo)){
           
           
           writer.append("Mes,Area Origen,Area Destino,Nombre Empleado\n");
           
           for(int i = 0; i<movimientos.size();i++){
                    Movimiento mov = movimientos.get(i);
                    
               writer.append(""+mov.getMes()
                       +mov.getOrigen()
                       +mov.getDestino()
                       +mov.getEmpleado().getNombre()
                       +"\n"
               );
               
                              
           }
           
           JOptionPane.showMessageDialog(null, "Reporte exportado correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
           return true;
       }catch(IOException e){
           e.printStackTrace();
           return false;
       }
       
       
      
   }
   
   
   
   
}
