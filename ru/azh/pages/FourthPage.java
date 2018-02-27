package ru.azh.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FourthPage {

    protected static WebDriver driver;
    protected static WebDriverWait wait;

    WebElement ConfirmationMessage;

    private void InitPageAttributes()
    {
        ConfirmationMessage = driver.findElement(By.ByXPath.xpath("//div[@class='freebirdFormviewerViewResponseConfirmationMessage']"));
     }

    public FourthPage(WebDriver d)
    {
        driver = d;
        PageFactory.initElements(d,this);
        wait = new WebDriverWait(d, 5);
        InitPageAttributes();
    }

    public void CheckConformation()
    {
        Assert.assertNotNull("!!!Wrong page!!!",ConfirmationMessage.getText());
    }
}
