import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
                FileWriter writer = new FileWriter("url_files/connection_log.txt", true);
                synchronized (UrlProccessor.class) {
                    String logMessage = "URL: " + url.getRef() + " CODE: " + responseCode;
                    writer.write(logMessage);
                }

                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
