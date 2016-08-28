package com.OHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Shohil on 28/08/2016.
 */
public class BrowserFactory extends BaseClass {
    //Launching selected browser and Open the URL
    public static WebDriver StartBrowser(String Browser, String URL) throws MalformedURLException, InterruptedException {
        if (driver == null || !isSessionActive()) {
            driver = startRemoteWebBrowser(Browser, URL);
        }
        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static boolean isSessionActive() {
        try {

            return driver.findElements(By.tagName("body")).size() > 0;

        } catch (Exception e) {

        }
        return false;
    }


    public static void QuitBrowser() {
        driver = null;
    }

    protected static WebDriver startRemoteWebBrowser(String browser, String URL) {
        //Switch for the Test Environment true="Selenium Grid" and false="Local Machine"
        if (true) {
            try {

                //Defining the Test Environment (Operating System,Browser,Version)
                DesiredCapabilities caps=DesiredCapabilities.chrome();
                caps.setCapability("platform", Platform.WIN10);
                caps.setCapability("version", "52.0");

                //Instantiating Remote WebDriver Object and Supplying Test environment parameters and selenium Grid url to Remote WebDriver
                driver = new RemoteWebDriver(new URL(LoadProps.getProperty("saucelabsGRID")), caps);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } else {
            try {
                if (browser.equalsIgnoreCase("Firefox")) {
                    //Open Firefox Browser
                    driver = new FirefoxDriver();
                } else if (browser.equalsIgnoreCase("Chrome")) {
                    //Open Chrome Browser
                    System.setProperty("webdriver.chrome.driver", "src/main/Browsers/chromedriver.exe");
                    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                    driver = new ChromeDriver(capabilities);

                } else {
                    if (browser.equalsIgnoreCase("IE")) {
                        //Open IE Browser
                        System.setProperty("webdriver.ie.driver", "src/main/Browsers/IEDriverServer.exe");
                        driver = new InternetExplorerDriver();
                    } else if (browser.equalsIgnoreCase("Safari")) {
//                    Open Safari Browser
                        driver = new SafariDriver();
                    } else if (browser.equalsIgnoreCase("Opera")) {
                        //Open Opera Browser
                        driver = new OperaDriver();
                    } else {
                        throw new RuntimeException("Browser give " + browser + " did not load..");
                    }
                }
            }
            catch(Exception e)
            {
                throw new RuntimeException("Browser give " + browser + " did not load..");

            }
        }
        //Opening the required URL
        driver.get(URL);
        return driver;
    }
}
