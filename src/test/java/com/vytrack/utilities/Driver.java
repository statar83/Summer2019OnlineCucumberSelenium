package com.vytrack.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {

    //only one private static instance
    private static WebDriver driver;

    //to prevent class instantiation
    //    you cannot do like this, if constructor is private Driver obj = new Driver()
    private Driver() {

    }


    //if switch statement complains on string parameter
    //change java version to 7+, better at least 8
    //File--> Project Structure--> Set Project language level to at least 8 or above
    public static WebDriver get() {
        //if webdriver object was not created yet
        if(driver == null){
            //create webdriver object based on browser value from properties file
            String browser = ConfigurationReader.getProperty("browser");
            switch (browser){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    //optional   (true) or (false)
                    //to run tests without interface, set to true
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setHeadless(false);
                    //optional    (true) or (false)
                    driver = new ChromeDriver(chromeOptions);   //insert chromeOptions
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    //if browser type is wrong, stop tests and throw exception
                    //no browser will be opened
                    throw new RuntimeException("Wrong browser type!");
            }
        }
        //if webdriver object was created - you can use it
        return driver;
    }

    public static void close(){
        //if driver still exist
        if(driver!=null){
            //close all browsers
            driver.quit();
            //destroy driver object, ready for garbage collector
            driver = null;
        }
    }
}