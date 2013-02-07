package practicajavase;

import java.util.ArrayList;

/**
 *
 * @author Carlos Gómez Muñoz
 */
public class PracticaJavaSE {

    public static ArrayList<String> valores;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Csv csv = new Csv();
        valores = new ArrayList<String>();
        valores = csv.getDatos(args[0]);
        ConsultarYahoo consultarYahoo = new ConsultarYahoo();

        for (String valor : valores) {
            consultarYahoo.getDatosValor(valor);
            System.out.println(valor);
        }
        BBDD bbdd = new BBDD();
        
        bbdd.guardarValorEnBbdd(valores.get(0));
        bbdd.guardarValorEnBbdd(valores.get(1));
        Pdf pdf = new Pdf();
        pdf.generarReporte();
//        bbdd.limpiarBBDD();
    }
}
