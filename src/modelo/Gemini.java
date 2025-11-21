/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import io.github.cdimascio.dotenv.Dotenv;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



/**
 *
 * @author Byakogollo
 */
public class Gemini {
    private String apiKey;
    private URL url;
    
public Gemini(){
    
    Dotenv dotenv = Dotenv.load();
    
    this.apiKey = dotenv.get("GEMINI_API_KEY");
    System.out.println(this.apiKey);
  
    
              
     
    
}    
    
   public String pedirReporte() throws MalformedURLException, ProtocolException, IOException, ParseException{
       
       this.url = new URL("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key="+this.apiKey);   
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setDoOutput(true);
            
        
         String json = """
        {
          "contents": [{
            "parts": [{
              "text": "hola gemini, si recibis esto responde 'si'"
            }]
          }]
        }
        """;

        
         try (OutputStream os = conn.getOutputStream()) {
            os.write(json.getBytes("UTF-8"));
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
                
                
       
          
        
        
        
        
     
        
        
    }

        
        
        
        
       
   
    

    
    
}
