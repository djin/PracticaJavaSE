package practicajavase;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Carlos Gómez Muñoz
 */
public class Pdf {

    public void generarReporte() {
        String driver = "org.apache.derby.jdbc.ClientDriver";
        String url = "jdbc:derby://localhost:1527/practicaJavaSE";
        String usuario = "admin";
        String clave = "admin";
        Statement statement = null;
        Connection conexion = null;
        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(url, usuario, clave);
            Map parametros = new HashMap();
            parametros.put("nombre", "TEF.MC");
            parametros.put("fechaInicio", "2012-01-01");
            parametros.put("fechaFin", "2013-01-01");
      JasperReport report = JasperCompileManager.compileReport("plantillaReporte.jrxml");
      JasperPrint print = JasperFillManager.fillReport(report, parametros, conexion);
      // Exporta el informe a PDF
      JasperExportManager.exportReportToPdfFile(print,"informe.pdf");
      //Para visualizar el pdf directamente desde java
      JasperViewer.viewReport(print, false);
//            JasperReport reporte = (JasperReport) JRLoader.loadObject(new File("plantillaReporte.jasper"));
//            Map<String, Object> parametros = new HashMap<String, Object>();
//            parametros.put("nombre", "TEF.MC");
//            parametros.put("fechaInicio", "2012-01-01");
//            parametros.put("fechaFin", "2013-01-01");
//            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
//            JRExporter exporter = new JRPdfExporter();
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("reportePDF.pdf"));
//            exporter.exportReport();
        } catch (JRException ex) {
            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
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
