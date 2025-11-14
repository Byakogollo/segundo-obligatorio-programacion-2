/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;
import modelo.Empleado;

/**
 *
 * @author Byakogollo
 */
public class CargarSistemas {
   private Scanner in;
   
    
   public String cargarCurriculum(Empleado emp){
       
   String basePath = System.getProperty("user.dir") + "/cvs";
    String resultado = "";
    
      
       this.in = new Scanner(basePath+"/CV"+emp.getCi());
       
       while(in.hasNext()){
           resultado += in.nextLine();
       }
        
    

    return resultado;
}
    
    
    
    
}
