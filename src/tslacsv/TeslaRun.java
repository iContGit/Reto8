package tslacsv;
import java.io.IOException;

public class TeslaRun {
    public static void main(String[] args) throws IOException {
        String ruta = "C:\\Users\\Sebastian\\Documents\\Flujo de archivos Java\\TSLA.csv"; // cambiar por  ruta personal
        Persistencia a = new Persistencia();
        PersistenciaNio b = new PersistenciaNio();
        a.punto1(ruta);
        //punto3 en archivo punto3.txt en GitHub.
        b.punto2(ruta);
        b.punto4(ruta);
    }
}
