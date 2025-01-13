package utilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class SpotifyRefreshTokenTroubleshooter {

    public static void main(String[] args) {
        String clientId = ConfigReader.getProperty("client_id");
        String clientSecret = ConfigReader.getProperty("client_secret");
        String redirectUri = ConfigReader.getProperty("redirect_uri");
        String authorizationCode = ConfigReader.getProperty("authorization_code_from_user"); // Kullanıcıdan alınan 'code'

        if (authorizationCode == null || authorizationCode.isEmpty()) {
            System.err.println("Authorization code is missing. Please provide a valid code.");
            return;
        }

        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "authorization_code");
        params.put("code", authorizationCode);
        params.put("redirect_uri", redirectUri);
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);

        System.out.println("Sending request to exchange authorization code for tokens...");
        Response response = RestAssured
                .given()
                .contentType("application/x-www-form-urlencoded")
                .formParams(params)
                .post("https://accounts.spotify.com/api/token");

        if (response.statusCode() == 200) {
            String accessToken = response.jsonPath().getString("access_token");
            String refreshToken = response.jsonPath().getString("refresh_token");

            System.out.println("Tokens retrieved successfully!");
            System.out.println("Access Token: " + accessToken);
            System.out.println("Refresh Token: " + refreshToken);

            // Refresh token'ı güvenli bir şekilde saklayın
            // Örneğin, bir dosyaya yazabilir ya da bir veritabanında saklayabilirsiniz
        } else {
            System.err.println("Failed to exchange authorization code for tokens.");
            System.err.println("Status Code: " + response.statusCode());
            System.err.println("Response: " + response.getBody().asString());
        }
    }
}
