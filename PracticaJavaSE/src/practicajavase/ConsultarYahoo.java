package practicajavase;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.*;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 *
 * @author Carlos Gómez Muñoz
 */
public class ConsultarYahoo {

    public void getDatosValor(String valor) {
        try {
            String urlPath;
            urlPath = "http://ichart.yahoo.com/table.csv?s=" + valor + "&a=0&b=1&c=2012&d=11&e=31&f=2012&g=d&ignore=.csv";
            URL url = new URL(urlPath);
            URLConnection urlConnection = url.openConnection();
            ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream("csvdescargados/"+valor + ".csv");
            fileOutputStream.getChannel().transferFrom(rbc, 0, 1 << 24);
            rbc.close();
            fileOutputStream.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
