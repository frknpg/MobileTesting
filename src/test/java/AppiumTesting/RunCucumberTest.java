package AppiumTesting;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/AppiumTesting"},
        plugin = {"io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm"})
public class RunCucumberTest {

}
