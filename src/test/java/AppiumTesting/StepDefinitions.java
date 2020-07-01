package AppiumTesting;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

public class StepDefinitions {

    private AppiumDriver driver;

    @Before
    public void startAppium() {
        //TODO: Cihazdan cihaza farklılık gösterecek bilgiler json formatında bir config dosyası ile dışardan alınmalıdır.
        ObjectMapper objectMapper = new ObjectMapper();
        AppiumConf appiumConf = new AppiumConf();
;        try {
            appiumConf = objectMapper.readValue(new File("src/test/resources/config/appium.json"), AppiumConf.class);
            System.out.println(appiumConf.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus_5X_API_29_x86");
        capabilities.setCapability(MobileCapabilityType.APP, new File("apks/Sample Android App Login Test_v4.0.apk").getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.UDID, appiumConf.uuid);
        capabilities.setCapability("appWaitPackage", "com.loginmodule.learning");
        capabilities.setCapability("appWaitActivity", "com.loginmodule.learning.activities.LoginActivity");

        try {
            driver = new AppiumDriver(new URL(appiumConf.appiumUrl),
                    capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }


    @Given("^Start the application$")
    public void startApplication() {
        driver.launchApp();
    }

    @When("^This is the first step$")
    public void firstStep() {

    }

    @Then("^Wait (\\d+) second$")
    public void waitTime(int time) throws InterruptedException {
        Thread.sleep(time);
    }
}