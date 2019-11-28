package AppiumTesting;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

public class StepDefinitions {

    private AppiumDriver driver;

    @Before
    public void startAppium() {
        //TODO: Cihazdan cihaza farklılık gösterecek bilgiler json formatında bir config dosyası ile dışardan alınmalıdır.
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus_5X_API_29_x86");
        capabilities.setCapability(MobileCapabilityType.APP, "C:\\Users\\Furkan\\Desktop\\mobiletest\\apks\\Sample Android App Login Test_v4.0.apk");
        capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        capabilities.setCapability("appWaitPackage", "com.loginmodule.learning");
        capabilities.setCapability("appWaitActivity", "com.loginmodule.learning.activities.LoginActivity");

        try {
            driver = new AppiumDriver(new URL("http://0.0.0.0:4723/wd/hub"),
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