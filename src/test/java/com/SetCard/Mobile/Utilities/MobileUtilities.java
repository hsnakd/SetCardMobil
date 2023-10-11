package com.SetCard.Mobile.Utilities;

import io.appium.java_client.TouchAction;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;



import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;


public class MobileUtilities {



    public static String getScreenshot(String name) throws IOException {
        // name the screenshot with the current date time to avoid duplicate name
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        // TakesScreenshot ---> interface from selenium which takes screenshots
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        // full path to the screenshot location
        String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + name + date + ".png";
        File finalDestination = new File(target);
        // save the screenshot to the path given
        FileUtils.copyFile(source, finalDestination);
        return target;
    }

    public static String captureScreenShot() {
        TakesScreenshot takesScreenshot = (TakesScreenshot) Driver.getDriver();
        String basecode64 = takesScreenshot.getScreenshotAs(OutputType.BASE64);
        return basecode64;
    }

    public void waitElement(String value){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 50000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(value)));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(value)));

    }
   public void wait (int value){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), value);
   }


    public void sendInfo(String values, String values2)throws InterruptedException {
        WebElement element = Driver.getDriver().findElementByXPath(values2);
        waitElement(values2);
        element.sendKeys(values);
    }

    public void clickButtonID(String values) {
        WebElement element = Driver.getDriver().findElementById(values);
        element.click();
    }

    public void clickButton(String values) {
        WebElement element = Driver.getDriver().findElementByXPath(values);
        element.click();
    }

    public void isVisible(String values, String values2) throws InterruptedException {
        WebElement element = Driver.getDriver().findElementByXPath(values);
        element.isDisplayed();
        String elementText = element.getText();
        System.out.println(element.getText());
        Assert.assertEquals(elementText, values2);
    }

    public  void pullToRefresh(){

        int deviceWidth = Driver.getDriver().manage().window().getSize().getWidth();
        int deviceHeight = Driver.getDriver().manage().window().getSize().getHeight();

        int midX = (deviceWidth / 2);
        int midY = (deviceHeight / 2);

        int bottomEdge = (int) (deviceHeight * 0.85f);

        new TouchAction(Driver.getDriver())
                .press(point(midX,midY))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(point(midX, bottomEdge))
                .release().perform();
    }

}

