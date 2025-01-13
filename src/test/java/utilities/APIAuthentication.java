package utilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class APIAuthentication {

    private static String accessToken;

    public static String getAccessToken() {
        if (accessToken == null || isTokenExpired()) {
            refreshAccessToken();
        }
        return accessToken;
    }

    private static void refreshAccessToken() {
        String clientId = ConfigReader.getProperty("client_id");
        String clientSecret = ConfigReader.getProperty("client_secret");
        String refreshToken = ConfigReader.getProperty("refresh_token");

        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "refresh_token");
        params.put("refresh_token", refreshToken);
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);

        Response response = RestAssured
                .given()
                .contentType("application/x-www-form-urlencoded")
                .formParams(params)
                .post("https://accounts.spotify.com/api/token");

        if (response.statusCode() == 200) {
            accessToken = response.jsonPath().getString("access_token");
        } else {
            throw new RuntimeException("Failed to refresh access token: " + response.getBody().asString());
        }
    }

    private static boolean isTokenExpired() {
        // Eğer bir expirationTime yönetimi eklemek isterseniz, burada kontrol edebilirsiniz.
        return false;
    }
}
