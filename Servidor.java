package EjercicioClase2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {
    
    ServerSocket servidor;
    Socket socket;
    PrintWriter escritor;
    BufferedReader lector;
    int puerto;
    
    public Servidor()
    {
        try 
        {
            puerto = 1000;
            servidor = new ServerSocket(puerto);
            int veces=0;
            System.out.println("Esperando cliente en puerto: " + puerto);
            
            while(true)
            {
                veces++;
                muestraConcesionarios m = new muestraConcesionarios(servidor.accept(), veces);                
                //System.out.println(veces);
                Thread hilo = new Thread(m);
                hilo.start();                
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args)
    {
        new Servidor();
    }
    
}
