package cashwise;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CashwiseHomePage;
import utilities.ApplicationFlow;
import utilities.Config;
import utilities.Driver;

public class CashwiseHomeTests {

    Faker faker = new Faker();
    CashwiseHomePage cashwiseHomePage = new CashwiseHomePage();

    @BeforeMethod
    public void setup(){
        Driver.getDriver().get(Config.getValue("cashwiseURL"));
    }
    @AfterMethod
    public void cleanUp(){
        Driver.quit();
    }

    @Test
    public void contactUs(){


        cashwiseHomePage.contactNameInput.sendKeys(faker.name().fullName());
        cashwiseHomePage.contactPhoneInput.sendKeys(faker.phoneNumber().subscriberNumber(9));
        cashwiseHomePage.contactEmailInput.sendKeys(faker.internet().emailAddress());
        ApplicationFlow.pause(3000);
        cashwiseHomePage.sendBtn.click();
        Assert.assertTrue(cashwiseHomePage.contactSubmittedPopup.isDisplayed());


    }
    @Test
    public void languageOptions(){

        cashwiseHomePage.languageOptionsBtn.click();
        for (WebElement option: cashwiseHomePage.languageOptions){
            System.out.println(option.getText());
        }
        Assert.assertEquals(cashwiseHomePage.languageOptions.size(),2);
    }



}
