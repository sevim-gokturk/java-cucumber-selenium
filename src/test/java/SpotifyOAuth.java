import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class SpotifyOAuth {
    public static void main(String[] args) {
        try {
            // Authorization code from the callback URL
            String code = "AQDPOMBX0lT6diS3snkf_Kl09PYXOkWS3mw86p__DJeoqWH7in-E6XnqSZxeV1h4RTCjW9wYt3K9l8pH_EjkQ_TRyMg0ozj0F51L-mViwKOJ3Q2w9j4VEcqUqMDAPA0506jf_vQ1IBOljHVI9mgomQICFCaiP-BJkkqFCOecpWWKG5G4ziSUxCE0w4_ZcH3MKPwkScwmJO9hXiaMw-tvujFKBkuCQpHGH5itMmdiztmbZ4b-0Jaup_bE7ju4BRgDBPnVrJVJmWUNA";
            String clientId = "b8ea08732450471d990159b60c4688cd";
            String clientSecret = "c63f9ff8a5124b0f8bf625c8504049b4";
            String redirectUri = "http://localhost:8888/callback";

            // Prepare the request body
            String data = "grant_type=authorization_code"
                    + "&code=" + code
                    + "&redirect_uri=" + redirectUri
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
