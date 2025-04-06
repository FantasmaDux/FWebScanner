import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ScannerMain {
    public static void main(String[] args) {
        List<String> url_list = new ArrayList<>();
        try {
            url_list = Files.readAllLines(Path.of("url_files/url_list.txt"));
            System.out.println(url_list);
        } catch (IOException e) {
            System.out.println("Нет такого файла");
        }
        try {
            URL url = new URL(url_list.getFirst());
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            int responseCode = httpURLConnection.getResponseCode();
            System.out.println(responseCode);
            FileWriter writer = new FileWriter("url_files/connection_log.txt");

            String logMessage = "URL: " + url_list.getFirst() + " CODE: " + responseCode;
            writer.write(logMessage);

            writer.close();

        } catch (IOException e) {
            System.out.println("Internet connection error " + e.toString());
        }
    }

}