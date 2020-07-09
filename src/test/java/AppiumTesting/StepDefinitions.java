package AppiumTesting;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class StepDefinitions {

    private AppiumDriver driver;

    @Before
    public void startAppium() {
        ObjectMapper objectMapper = new ObjectMapper();
        AppiumConf appiumConf = new AppiumConf();
        try {
            appiumConf = objectMapper.readValue(new File("src/test/resources/config/appium.json"), AppiumConf.class);
            System.out.println(appiumConf.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus_5X_API_29_x86");
        capabilities.setCapability(MobileCapabilityType.APP, appiumConf.apkPath);
        capabilities.setCapability(MobileCapabilityType.UDID, appiumConf.uuid);
        capabilities.setCapability("appWaitPackage", "com.loginmodule.learning");
        capabilities.setCapability("appWaitActivity", "com.loginmodule.learning.activities.LoginActivity");

        try {
            driver = new AppiumDriver(new URL(appiumConf.appiumUrl), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    @Given("^Start the application$")
    public void startApplication() {
        driver.launchApp();
    }

    @When("^I click the register button$")
    public void clickRegisterButton() {
        MobileElement el = (MobileElement) driver.findElementById("com.loginmodule.learning:id/textViewLinkRegister");
        el.click();
    }

    @Then("^I see the register button$")
    public void seeRegisterButton() {
        List<MobileElement> elementList = (List<MobileElement>) driver.findElementsById(("com.loginmodule.learning:id/appCompatButtonRegister"));
        assertFalse(elementList.isEmpty());
    }

    @Then("^Wait (\\d+) second$")
    public void waitTime(int time) throws InterruptedException {
        Thread.sleep(time);
    }

    @After
    public void killSession() {
        driver.quit();
    }
}