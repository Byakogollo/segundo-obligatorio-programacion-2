package persistencia;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
   
   
   
}
