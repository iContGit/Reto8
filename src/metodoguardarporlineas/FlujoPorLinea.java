package metodoguardarporlineas;
import java.io.*;

public class FlujoPorLinea {
    public static void guardarPorLineas(String ruta, String[] lineas) {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(ruta))) {
            for (String linea : lineas) {
                escritor.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

