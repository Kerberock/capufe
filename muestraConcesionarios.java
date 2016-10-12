package EjercicioClase2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class muestraConcesionarios implements Runnable{
    
    PrintWriter pw;
    Socket socket;
    BufferedReader lector;
    int veces;

    public muestraConcesionarios(Socket socket, int veces) 
    {
        try 
        {
            lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));            
            pw = new PrintWriter(socket.getOutputStream(), true);
            this.veces = veces;
        } 
        
        catch (IOException ex) 
        {
            Logger.getLogger(muestraConcesionarios.class.getName()).log(Level.SEVERE, null, ex);
        }                
    }
    
    @Override
    public void run() {        
        int cant=0;
                
        try 
        {
            //System.out.println("Entramos a run...");
            cant = Integer.valueOf(lector.readLine())-2;
            System.out.println("Estoy recibiendo "+cant+" datos.");
            Set<String> lst = new HashSet<>();
            
            for(int i=0; i<cant;i++)
            {
                String[] s = lector.readLine().split(",");
                if (!s[1].equals(""))
                lst.add(s[1]);
                //System.out.println(i+": "+s[2]);
                //System.out.println(i+": "+lector.readLine());
            }
            
            pw.println(veces);
            System.out.println("Cantidad de veces a enviar: "+veces);
            
            for(String s:lst)
            {
                System.out.println(s);
                pw.println(s);
            }
            
            System.out.println("Envío de datos procesados completado\n");
            System.out.println("En espera de cliente...");
        }
            
         catch (IOException ex) 
         {
            Logger.getLogger(muestraConcesionarios.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        finally
        {
            try 
            {
                lector.close();
                pw.close();
                //socket.close();
            } 
            
            catch (IOException ex) 
            {
                Logger.getLogger(muestraConcesionarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
