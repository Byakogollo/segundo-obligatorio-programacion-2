/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mainPackage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import modelo.Sistema;
import ventanas.inicializacion.VentanaInicial;
import ventanas.inicializacion.VentanaIntegrantes;
/**
 *
 * @author jacqu
 */
public class SegundoObligatorioProgramacion2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       
                Sistema modelo = new Sistema();
                
                VentanaIntegrantes vIntegrantes = new VentanaIntegrantes();
                
                vIntegrantes.setVisible(true);
                
                
    
             Timer t = new Timer(4000, e-> {
                 vIntegrantes.dispose();
                 VentanaInicial ventana = new VentanaInicial(modelo);
                ventana.setVisible(true);
                     });
             t.setRepeats(false);
             t.start();
                           
                                                      
                
                     
              
                
          
    }
    
}
