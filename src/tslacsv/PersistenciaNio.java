package tslacsv;
import java.nio.*;
import java.nio.channels.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import static java.nio.file.StandardOpenOption.READ;
import static  metodoguardarporlineas.FlujoPorLinea.guardarPorLineas;
public class PersistenciaNio {


    public void punto2(String ruta) throws IOException {
        ArrayList<String> listaDeLineas = new ArrayList<String>();
        String lineaCopia;
        Path miRuta = Paths.get(ruta);
        List<String> lineasArchivo;
        String ruta2 = "C:\\Users\\Sebastian\\Documents\\Flujo de archivos Java\\punto2.txt";
        try {
            lineasArchivo = Files.readAllLines(miRuta);
            String[] listaLineasArr = new String[lineasArchivo.size()];
            listaLineasArr = lineasArchivo.toArray(listaLineasArr);
            int posicionInicial = ((listaLineasArr[1].getBytes().length * 10 - 13));
            int contadorBytes;
            do {
                FileChannel canal = FileChannel.open(miRuta, READ);
                canal.position(posicionInicial);
                ByteBuffer buffer = ByteBuffer.allocate(listaLineasArr[1].getBytes().length);
                posicionInicial = ((listaLineasArr[1].getBytes().length * 10 + 20)) + posicionInicial;
                do {
                    canal.read(buffer);
                } while (buffer.hasRemaining());
                String lineaCompleta = new String(buffer.array());
                String lineaBuffereada[] = lineaCompleta.split(",");
                String precioCierre = "";
                if ((Double.parseDouble(lineaBuffereada[4]) < (double) 200)) {
                    precioCierre = "MUY BAJO";
                } else if ((Double.parseDouble(lineaBuffereada[4]) >= (double) 200) && (Double.parseDouble(lineaBuffereada[4]) < (double) 300)) {
                    precioCierre = "BAJO";
                } else if ((Double.parseDouble(lineaBuffereada[4]) >= (double) 300) && (Double.parseDouble(lineaBuffereada[4]) < (double) 500)) {
                    precioCierre = "MEDIO";
                } else if ((Double.parseDouble(lineaBuffereada[4]) >= (double) 500) && (Double.parseDouble(lineaBuffereada[4]) < (double) 600)) {
                    precioCierre = "ALTO";
                } else {
                    precioCierre = "MUY ALTO";
                }
                lineaCopia = lineaBuffereada[0] + "\t" + precioCierre;
                listaDeLineas.add(lineaCopia);


            } while (posicionInicial <= listaLineasArr[1].getBytes().length * 254 + 467);
            String[] listaDeLineasArreglo = new String[listaDeLineas.size()];
            listaDeLineasArreglo = listaDeLineas.toArray(listaDeLineasArreglo);
            guardarPorLineas(ruta2, listaDeLineasArreglo);
        } catch (IOException e) {
            System.out.println("Hubo un error al acceder el archivo: " + e.getMessage());
        }
    }

    public void punto4(String ruta)  {
        String linea;
        double menor = 1000000;
        double mayor = -1000000;
        String fechaMenor = "";
        String fechaMayor = "";
        try (BufferedReader lectorPorLineas = new BufferedReader(new FileReader(ruta))) {
            band:
            while ((linea = lectorPorLineas.readLine()) != null) {
                String celda[] = linea.split(",");
                if (celda[0].equals("Date")) continue band;
                if (menor > Double.parseDouble(celda[3])) {
                    menor = Double.parseDouble(celda[3]);
                    fechaMenor = celda[0];
                }
                if (mayor < Double.parseDouble(celda[2])) {
                    mayor = Double.parseDouble(celda[2]);
                    fechaMayor = celda[0];

                }
            }
            System.out.println( fechaMenor + " Es la fecha con la accion mas baja de todo el año detallado, por un valor de " + menor);
            System.out.println( fechaMayor + " Es la fecha con la accion mas alta de todo el año detallado, por un valor de " + mayor);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }



}