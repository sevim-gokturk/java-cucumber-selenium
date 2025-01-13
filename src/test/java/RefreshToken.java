import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class RefreshToken {
    public static void main(String[] args) {
        try {
            // Refresh token previously obtained
            String refreshToken = "AQB6jLLZnLvDotLwNrlA5dI004b-P9bqZSiVW7S5US9dzGOfNBfRyJZ1SEo3OIcO3ybZ0r1J1BFHi-ovmpKpi1C2ar16m8JsC-T-ioFMHK2euIf6o0l251yUNGKav5Ae4rk"; // Refresh token'ı buraya ekleyin.
            String clientId = "b8ea08732450471d990159b60c4688cd";
            String clientSecret = "c63f9ff8a5124b0f8bf625c8504049b4";

            // Prepare the request body
            String data = "grant_type=refresh_token"
                    + "&refresh_token=" + refreshToken
                    + "&client_id=" + clientId
                    + "&client_secret=" + clientSecret;

            // Set up the URL and the connection
            URL url = new URL("https://accounts.spotify.com/api/token");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // Send the request body
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = data.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Get the response
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
