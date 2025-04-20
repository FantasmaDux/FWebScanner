import java.io.FileWriter;
import java.io.IOException;
import java.net.*;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class UrlProccessor {
    private final ThreadPoolExecutor executor;

    public UrlProccessor() {
        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
    }

    public void processUrls(List<String> urlList) throws MalformedURLException {
        for (String urlString : urlList) {
            try {
                executor.execute(new UrlTask(urlString));
            } catch (MalformedURLException e) {
                System.err.println("Invalid URL: " + urlString);
            }
        }
        executor.shutdown();
    }

    private static class UrlTask implements Runnable {
        private String urlString = "";

        private UrlTask(String urlString) throws MalformedURLException {
            this.urlString = urlString;
        }
        @Override
        public void run() {
            try {
                URL url = new URL(urlString);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                int responseCode = httpURLConnection.getResponseCode();
                HttpStatus status = HttpStatus.getFromCode(responseCode);

                FileWriter writer = new FileWriter("url_files/connection_log.txt", true);
                synchronized (UrlProccessor.class) {
                    writeToLog(urlString, status.getCode(), status.getDescription());
                }

                writer.close();
            }catch (UnknownHostException e) {
                writeToLog(urlString, HttpStatus.BAD_REQUEST.getCode(), HttpStatus.BAD_REQUEST.getDescription());
            } catch (SocketException e) {
                writeToLog(urlString, -999, "нет доступа из России");
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeToLog(String url, int code, String status) {
            synchronized (UrlProccessor.class) {
                try (FileWriter writer = new FileWriter("url_files/connection_log.txt", true);) {
                    writer.write("URL: " + url + " CODE: " + code + " - " + status + "\n");
                } catch (IOException e) {
                    System.out.println("Failed to write " + e.getMessage());
                }
            }
        }
    }
}
