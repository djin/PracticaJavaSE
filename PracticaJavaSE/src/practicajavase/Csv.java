package practicajavase;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Carlos Gómez Muñoz
 */
public class Csv {

    public File archivo = null;
    public FileReader fr = null;
    public BufferedReader br = null;

    public ArrayList getDatos(String path) {
        ArrayList<String> valores = new ArrayList<String>();
        try {
            archivo = new File(path);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null) {
                valores.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return valores;
    }
    
    public void borrarCsv(String path){  //////////////////////////////////////// REVISAR
        archivo = new File(path);
        archivo.delete();
    }
    
}
