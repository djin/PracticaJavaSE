package practicajavase;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos Gómez Muñoz
 */
public class BBDD {

    private static String driver = "org.apache.derby.jdbc.ClientDriver";
    private static String url = "jdbc:derby://localhost:1527/practicaJavaSE";
    private static String usuario = "admin";
    private static String clave = "admin";
    private static Csv csv = new Csv();

    public void guardarValorEnBbdd(String valor) {

        String path = "csvdescargados/" + valor + ".csv";
        ArrayList<String> datos = new ArrayList<String>();
        datos = csv.getDatos(path);
        if (!datos.isEmpty()) {
            datos.remove(0); //La primera linea son los nombres de las columnas.
            Statement statement = null;
            Connection conexion = null;
            try {
                Class.forName(driver);
                conexion = DriverManager.getConnection(url, usuario, clave);
                statement = conexion.createStatement();
                for (String linea : datos) {
                    String[] lineaSeparada = separarLinea(linea);
                    statement.executeUpdate("insert into CARLOS.VALORES values('" + valor + "','" + lineaSeparada[0] + "'," + lineaSeparada[1] + "," + lineaSeparada[2] + "," + lineaSeparada[3]
                            + "," + lineaSeparada[4] + "," + lineaSeparada[5] + "," + lineaSeparada[6] + ")");
                }
                csv.borrarCsv(path);
            } catch (SQLException ex) {
                Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                try {
                    if (conexion != null) {
                        conexion.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public String[] separarLinea(String linea) {
        String[] lineaSeparada;
        String separador = ",";
        lineaSeparada = linea.split(separador);
        return lineaSeparada;
    }

    public void limpiarBBDD() {
        Statement statement = null;
        Connection conexion = null;
        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(url, usuario, clave);
            statement = conexion.createStatement();
            statement.executeUpdate("DELETE FROM CARLOS.VALORES");
            
        } catch (SQLException ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }
}
