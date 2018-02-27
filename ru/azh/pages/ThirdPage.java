package ru.azh.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ThirdPage {
    protected static WebDriver driver;
    protected static WebDriverWait wait;

    WebElement YesRadioButtons, BackButton, NextButton;

    private void InitPageAttributes()
    {
        YesRadioButtons = driver.findElement(By.ByXPath.xpath("//div[@data-value='Yes']"));
        NextButton = driver.findElement(By.ByXPath.xpath("(//span[@class='quantumWizButtonPaperbuttonLabel exportLabel'])[2]"));
    }

    public ThirdPage(WebDriver d)
    {
        driver = d;
        PageFactory.initElements(d,this);
        wait = new WebDriverWait(d, 2);
        InitPageAttributes();
    }

    public void SelectYes()
    {
        YesRadioButtons.click();
        if(YesRadioButtons.getAttribute("tabindex")=="0") YesRadioButtons.click();
        Assert.assertEquals("!!!Element is not selected!!!",YesRadioButtons.getAttribute("tabindex"),"0");
    }

    public void GoToNextPage()
    {
        NextButton.click();
    }
}
