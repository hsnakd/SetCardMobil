package com.SetCard.Mobile.Test;

import com.SetCard.Mobile.Pages.BakiyeYukleme;
import com.SetCard.Mobile.Pages.LoginPage;
import com.SetCard.Mobile.Pages.MainPage;
import com.SetCard.Mobile.Utilities.Driver;
import com.SetCard.Mobile.Utilities.Log4j;
import com.SetCard.Mobile.Utilities.MobileUtilities;
import io.appium.java_client.TouchAction;
import org.testng.annotations.Test;

import static io.appium.java_client.touch.offset.PointOption.point;


public class TestPage extends TestBase {

    MobileUtilities mobile = new MobileUtilities();
    LoginPage loginPage = new LoginPage();
    MainPage mainPage = new MainPage();
    BakiyeYukleme bakiyeYukleme = new BakiyeYukleme();



    @Test(priority = 1, description = "Login")
    public void Login() throws InterruptedException {
        //kullanıcı adı ve şifreyi girip giriş yap butonuna tıklar
        mobile.clickButtonID(loginPage.notification);
        mobile.sendInfo("ibrahim.abdulbakar@gmail.com", loginPage.userName);
        mobile.sendInfo("Asd321..", loginPage.password);
        mobile.clickButton(loginPage.enterButtons);
        //pin ekranı gelince pin girer
        Thread.sleep(2000);
        Driver.getDriver().getKeyboard().sendKeys("2185");
        mobile.isVisible(loginPage.welcomeMassage, "Merhaba ibrahim");
        mobile.wait(2000);
        Log4j.info("Giriş yapıldı");

        //  }

        //   @Test(priority = 2, description = "MainPage")

        //   public void MainPage() throws InterruptedException {
        //       Login();
        mobile.isVisible(mainPage.butceYazisi, "Kullanılabilir Bütçe");
        mobile.isVisible(mainPage.flexKart, "Flexlife Kart");
        mobile.isVisible(mainPage.flexKupon, "Flexlife Kupon");
        mobile.wait(2);
        Log4j.info("Ana Sayfaya Gidildi");
        mobile.clickButton(mainPage.hemenHarcaButton);
        Log4j.info("Kart Ekranına Gidildi");
        //  @Test (priority = 3, description = "Kart Ekranı")
        //   public void KartEkranı() throws InterruptedException {
        mobile.waitElement(mainPage.name);
        mobile.isVisible(mainPage.name, "ibrahim TestEder");
        mobile.isVisible(mainPage.kartBakiyesi, "Kart Bakiyesi");
        mobile.clickButton(mainPage.bakiyeYukle);
        mobile.wait(3000);
        mobile.waitElement(bakiyeYukleme.title);
        mobile.isVisible( bakiyeYukleme.title,"Bütçeden Flexlife Kartıma Yükle:");
        Thread.sleep(2000);
        Driver.getDriver().getKeyboard().sendKeys("100");
       // Driver.getDriver().hideKeyboard();
        mobile.clickButton(bakiyeYukleme.yukleButonu);
        mobile.clickButton(bakiyeYukleme.yukleButonu);
        mobile.isVisible( bakiyeYukleme.uyarıMesajı,"₺100 tutarındaki yüklemeyi onaylıyor musunuz?");
        mobile.clickButton(bakiyeYukleme.evetButonu);
        Thread.sleep(2000);
        mobile.clickButton(bakiyeYukleme.tamamButonu);
        Thread.sleep(3000);
        mobile.clickButton(bakiyeYukleme.geriButonu);





    }


}
