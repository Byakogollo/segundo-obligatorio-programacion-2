package persistencia;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import modelo.*;

public class GuardarSistema {

    
    
   public GuardarSistema(String curriculum, int ci) {
    String basePath = System.getProperty("user.home") + "/cvs";
    new File(basePath).mkdirs();

    File cv = new File(basePath + "/CV"+ci+".txt");


    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(cv))) {
        out.writeObject(curriculum);
        out.close();
       
    } catch (IOException e) {
        System.out.println("Error guardando el sistema: " + e.getMessage());
       
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
   
   
   
}
