package com.SetCard.Mobile.Utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class Driver {
    private static AppiumDriver<MobileElement> driver;
    private static URL url;

    private Driver() {
    }

    public static AppiumDriver<MobileElement> getDriver() {
        if (Objects.isNull(driver)) {

            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
            desiredCapabilities.setCapability(MobileCapabilityType.VERSION, "14.0");
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "abraham");
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
            desiredCapabilities.setCapability("appPackage", "com.setcard.flexlife.development");
            desiredCapabilities.setCapability("appActivity", "com.setcard.flexlife.MainActivity");
            desiredCapabilities.setCapability("autoGrantPermissions", false);


            try {
                url = new URL("http://localhost:4723/wd/hub");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            driver = new AndroidDriver<>(url, desiredCapabilities);

        }
        return driver;
    }

    public static void closeDriver() {
        if (Objects.nonNull(driver)) {
            driver.closeApp();
            driver = null;
        }
    }
}