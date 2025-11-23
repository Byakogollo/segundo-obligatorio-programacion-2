/*
TRABAJO REALIZADO POR ESTEBAN NECUSE 227582 Y MARCOS MEDINA 365070
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import io.github.cdimascio.dotenv.Dotenv;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import persistencia.ArchivoCargar;



/**
 *
 * @author Byakogollo
 */
public class Gemini implements Serializable{
    private String apiKey;
    private URL url;
    
public Gemini(){
    
    ArchivoCargar key = new ArchivoCargar();
    
    
    this.apiKey = key.getApiKey();
   
      
                  
     
    
}    
    
   public String pedirReporte(String nomOrigen, double presupuestoOrigen,
           String nomEmpleado, double salarioEmpleado, int mes, String nomDestino, double presupuestoDestino)
           throws MalformedURLException, ProtocolException, IOException, ParseException, ExcepcionesSistema{
       

        //debug
       System.out.println(this.apiKey);
       
       try{
           
         this.url = new URL("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key="+this.apiKey);           
        HttpURLConnection conn = (HttpURLConnection) this.url.openConnection();
        
        conn.setRequestMethod("POST");
               
        
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setDoOutput(true);
      
     
        String mensaje = "Variables a referenciar: \n"
                +"Area origen: "+nomOrigen+
                "\n Presupuesto origen: "+presupuestoOrigen+
                "\n Nombre empleado: "+nomEmpleado+
                "\n Salario empleado: "+salarioEmpleado+
                "\n mes: "+mes+
                "\n Area Destino: "+ nomDestino+
                "\n Prespuesto Destino: "+presupuestoDestino                
                
                ;
                
        
        
         String json = """
        {
          "contents": [{
                       
            "parts": [{
                       
              "text": "Gemini, referenciando las variables de Area origen, presupuestoOrigen,
                       empleado, salarioEmpleado, mes desde que se inicia el movimiento (numeros del 1 al 12), 
                       area destino y presupuestoDestino, genera a modo informativo 
                       un reporte que indique ventajas y desventajas de mover al empleado entre areas.
                       Tene en cuenta que el criterio para realizar un movimiento entre areas es el siguiente: 
                       
                       Segun el mes del año (se asume que el movimiento es a partir del 1º de ese mes). 
                       Se selecciona un empleado y un área de origen/destino y si el presupuesto del área de destino 
                       es suficiente se realiza el movimiento. 
                       
                       Devuelve una respuesta en español que solo contenga la conclusion general de manera consiza
                       para un usuario final"
                       
            },{ "text": "%s"
                                      }]
          }]
        }
        """.formatted(mensaje);
         
    
         
         
         
         OutputStream os = conn.getOutputStream();
             os.write(json.getBytes("UTF-8"));
             
            if(conn.getResponseCode() == 403){
            throw new ExcepcionesSistema(" ApiKey Expirada");
            }
            if(conn.getResponseCode() == 400){
                System.out.println(conn.getResponseCode());
                 throw new ExcepcionesSistema(" ApiKey Desautorizada");
            }
            
         
        

        // Read response
        
        
        java.io.InputStream is = conn.getInputStream();
         String response = new String(is.readAllBytes(), "UTF-8");
        
        JSONParser parser = new JSONParser();
        JSONObject jsonResp = (JSONObject) parser.parse(response);
        
                            //debug
                            if(jsonResp instanceof JSONObject){
                                System.out.println("si");
                            }else{
                                System.out.println("no"+jsonResp.getClass());
                            }
        
                                       
          JSONArray candidates = (JSONArray) jsonResp.get("candidates");
          JSONObject firstCandidate = (JSONObject) candidates.get(0);
          
          JSONObject content = (JSONObject) firstCandidate.get("content");
          JSONArray parts = (JSONArray) content.get("parts");
          
          JSONObject firstPart = (JSONObject) parts.get(0);
          String respuesta = (String) firstPart.get("text");

          System.out.println(respuesta);  // → "si"
               
        
        
         return respuesta;
        
       }catch(ExcepcionesSistema e){
           JOptionPane.showMessageDialog(null, "Error:"+e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
       }  
        
        System.out.println("null");
        
        return null;        
        
        
     
        
        
    }

        
        
        
        
       
   
    

    
    
}
