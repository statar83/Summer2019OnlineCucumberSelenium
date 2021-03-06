package com.vytrack.step_definitions;

import com.vytrack.utilities.Driver;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hook {//automatically runs with every scenario without extending @Before and @After

    @Before//make sure the import comes form cucumber not junit
    public void setup() {

        System.out.println("Test setUp!");
        Driver.get().manage().window().maximize();
    }

    @After
    public void teardown(Scenario scenario) {
        //if test failed - do this
        if (scenario.isFailed()) {
            System.out.println("Test Failed");
            byte[] screenshot = ((TakesScreenshot)Driver.get()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot,"image/png");
        } else {
            System.out.println("CleanUp!");
            System.out.println("Test Completed!");
        }
        System.out.println("+++++++++++++++++++++++++");
        Driver.close();
    }
}
