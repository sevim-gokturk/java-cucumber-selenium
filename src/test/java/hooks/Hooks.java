package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.ConfigReader;
import utilities.DBUtils;
import utilities.Driver;

public class Hooks {
    public static RequestSpecification spec;

    @Before(value="@Api")
    public void setUp(){
        spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("xxxUrl")).build();
    }

    @Before(value = "@Db")
    public void setUpDb() {
        if (DBUtils.getConnection() == null) {
            DBUtils.createConnection();
        }
    }
    @After(value = "@Db")
    public void tearDownDB(Scenario scenario){
        DBUtils.closeConnection();
        System.out.println("Database closed");

    }

    @After
    public void tearDown(Scenario scenario){

        if (scenario.isFailed()) {
            final byte[] screenshot=((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png","screenshots");
        }
        Driver.closeDriver();
    }
}



