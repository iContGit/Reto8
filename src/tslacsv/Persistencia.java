package tslacsv;
import java.io.*;
import java.util.ArrayList;
import static  metodoguardarporlineas.FlujoPorLinea.guardarPorLineas;

public class Persistencia {

    public void punto1(String ruta){
        String linea;
        String lineaCopia;
        ArrayList<String> listaDeLineas = new ArrayList<String>();
        String ruta2 ="C:\\Users\\Sebastian\\Documents\\Flujo de archivos Java\\punto1.txt"; //cambiar por ruta personal
        try (BufferedReader lectorPorLineas = new BufferedReader(new FileReader(ruta))) {
            band:
            while((linea = lectorPorLineas.readLine()) != null ) {
                String DatosBolsa[] = linea.split(",");
                String precioCierre;
                if(DatosBolsa[0].equals("Date")) continue band;
                if((Double.parseDouble(DatosBolsa[4]) < (double)200)){
                    precioCierre = "MUY BAJO";
                }else if ((Double.parseDouble(DatosBolsa[4]) >= (double)200) && (Double.parseDouble(DatosBolsa[4]) <(double)300)){
                    precioCierre = "BAJO";
                }else if((Double.parseDouble(DatosBolsa[4]) >= (double)300) && (Double.parseDouble(DatosBolsa[4]) <(double)500)) {
                    precioCierre = "MEDIO";
                }else if ((Double.parseDouble(DatosBolsa[4]) >= (double)500) && (Double.parseDouble(DatosBolsa[4]) <(double)600)){
                    precioCierre = "ALTO";
                }else{precioCierre = "MUY ALTO";}
                lineaCopia = DatosBolsa[0]+"\t" + precioCierre;
                listaDeLineas.add(lineaCopia);
            }
            String[] listaDeLineasArreglo = new String[listaDeLineas.size()];
            listaDeLineasArreglo = listaDeLineas.toArray(listaDeLineasArreglo);
            guardarPorLineas(ruta2, listaDeLineasArreglo);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
