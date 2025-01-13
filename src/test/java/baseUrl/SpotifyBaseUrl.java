package baseUrl;



import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import utilities.APIAuthentication;
import utilities.ConfigReader;

public class SpotifyBaseUrl {

    public static RequestSpecification spec;

    @Before
    public void setup() {
        spec = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty("base_url"))
                .addHeader("Authorization", "Bearer " + APIAuthentication.getAccessToken())
                .build();
    }
}

