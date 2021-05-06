package labos_02.task5.promatraci;

import labos_02.task5.SlijedBrojeva;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IspisDatuma implements Promatrac{
    private String datoteka = "datoteka.txt";
    private DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @Override
    public void update(SlijedBrojeva sb) {

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(datoteka, true);
            for(Integer broj : sb.getKolekcijaBrojeva()){
                outputStream.write(broj.toString().getBytes(StandardCharsets.UTF_8));
                outputStream.write("\n".getBytes(StandardCharsets.UTF_8));
                }
            String datumVrijeme = LocalDateTime.now().format(myFormatObj);
            outputStream.write(datumVrijeme.getBytes(StandardCharsets.UTF_8));
            outputStream.write("\n\n".getBytes(StandardCharsets.UTF_8));
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
