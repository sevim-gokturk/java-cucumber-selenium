import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class SpotifyApi {
    public static void main(String[] args) {
        try {
            // Access token (önceki yanıtla alınan token)
            String accessToken =
                    "BQAkuJrKlqCbAk7qLpbKzjKJurTm80be5JATxmwymCcR9aOl_K3TbF2KDGsCh0qAJAE3VVdAyUax6-mWtgAxZDqWmqGhRyLJULNWMywn4y3cU5qEkrT9oDBRWeW06J7GaQQadk-5CQgHPB1d8xf_re8ZupgsxFoWkTUsQuQsxxJlu6UHChRMGoi_qwfa3RJB-6yH39952wrHG1AsCqY_BxrIHbqMaQvWkuCPS3EqWCB6M4y1ckYFnfW5FCc";            // API URL (kullanıcı bilgileri)
            URL url = new URL("https://api.spotify.com/v1/me");

            // Açılan bağlantıyı al
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Authorization header'ı ekle
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);

            // Yanıtı al
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println("Response: " + response.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
