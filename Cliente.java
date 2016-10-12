package EjercicioClase2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

public class Cliente 
{
    JFileChooser sel;
    List<String> lista;
    leetxt txt;
    BufferedReader br;
    PrintWriter pw;
    Socket socket;
    String ip;
    int puerto;
    
    public Cliente()
    {
        try {
            ip = "localhost";
            puerto = 1000;
            socket = new Socket(ip, puerto);
            System.out.println("Cliente conectado a servidor...");
            sel = new JFileChooser();
            int seleccion = sel.showDialog(sel, "Seleccionar archivo");
            if (seleccion == JFileChooser.APPROVE_OPTION)
            {
                txt = new leetxt(sel.getSelectedFile());
                lista = txt.genLis();
            }
            //System.out.println(lista.size());
            pw = new PrintWriter(socket.getOutputStream(), true);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            pw.println(lista.size()-1);   // Cantidad de datos a enviar
            
            for(int i=1; i<lista.size(); i++)
                pw.println(lista.get(i));
            
            System.out.println("Terminé de enviar la lista.");
            
            String tmp = br.readLine();            
            int acumulador=1, veces = Integer.valueOf(tmp);
            
            
            while ((tmp = br.readLine()) != null)
            {
                System.out.println(acumulador+": "+br.readLine());
                acumulador++;
            }            
                        
            System.out.println("Terminé la recepción de datos");
            System.out.println("\n");
            System.out.println("Este programa se ha corrido: "+veces+" veces");
        } 
        
        catch (IOException ex) 
        {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally
        {
            try 
            {
                br.close();
                pw.close();
            } 
            
            catch (IOException ex) 
            {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void main(String[] args) {
        new Cliente();
    }
}
