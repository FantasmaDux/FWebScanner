import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class ScannerMain {
    public static void main(String[] args) {
        List<String> urlList = new ArrayList<>();
        try {
            urlList = Files.readAllLines(Path.of("url_files/url_list.txt"));
            System.out.println(urlList);
        } catch (IOException e) {
            System.out.println("Нет такого файла");
            return;
        }
        try {
            UrlProccessor urlProccessor = new UrlProccessor();
            urlProccessor.processUrls(urlList);

        } catch (IOException e) {
            System.out.println("Internet connection error " + e.toString());
        }
    }

}