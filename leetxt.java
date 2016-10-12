package EjercicioClase2;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class leetxt {
    File archivo;
    List<String> lista;
    
    public leetxt(File archivo)
    {
        lista = new ArrayList<>();
        this.archivo = archivo;
    }

    public List<String> genLis()
    {
        String tmp;
        try 
        {            
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            while((tmp = br.readLine()) != null)
            {
                //System.out.println(tmp);
                lista.add(tmp);
            }                
        } catch (FileNotFoundException ex) {
            Logger.getLogger(leetxt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(leetxt.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
    }    
}
