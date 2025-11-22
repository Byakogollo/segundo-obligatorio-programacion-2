/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Paths;
import java.util.Scanner;
import modelo.Empleado;
import modelo.Sistema;

/**
 *
 * @author Byakogollo
 */
public class ArchivoCargar {
    private Scanner in;
    private String linea;

    public String cargarCurriculum(Empleado emp) {

        String basePath = System.getProperty("user.dir") + "/cvs";

        String resultado = "";

        try {

            this.in = new Scanner(Paths.get(basePath + "/CV" + emp.getCi() + ".txt"));

            while (this.hayMasLineas()) {
                resultado += this.linea;
            }

        } catch (IOException e) {

            return e.toString();

        }

        return resultado;
    }

    private boolean hayMasLineas() {

        if (this.in.hasNext()) {
            linea = in.nextLine();
            return true;
        }

        return false;

    }

    public Sistema cargarSistema() throws Exception {

        String basePath = System.getProperty("user.dir") + "/persistencia";

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(basePath + "/Sistema"))) {

            Object obj = in.readObject();

            // if (obj instanceof Sistema sistema) {
            // return sistema;

            // }

            if (obj instanceof Sistema) {
                Sistema sistema = (Sistema) obj;
                return sistema;
            }

            else {

                throw new Exception("El objeto leído no es una instancia de Sistema.");

            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error leyendo el archivo: " + e.getMessage());
        }

        return null;
    }
    
    public String getApiKey(){
        
        String basePath = System.getProperty("user.dir")+ "/persistencia";

        String resultado = "";

        try {

            this.in = new Scanner(Paths.get(basePath + "/erp_api_key.txt"));

            while (this.hayMasLineas()) {
                resultado += this.linea;
            }

        } catch (IOException e) {

            return e.toString();

        }

        return resultado;
        
        
    }
    
    

}
