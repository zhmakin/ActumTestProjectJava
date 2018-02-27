package ru.azh.pages;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SecondPage {
    protected static WebDriver driver;
    protected static WebDriverWait wait;

    WebElement FirstQuestionInputField, ColorDropbox, BackButton, NextButton;

    private void InitPageAttributes()
    {
        FirstQuestionInputField = driver.findElement(By.ByXPath.xpath("//textarea[@jsname='YPqjbf']"));
        ColorDropbox = driver.findElement(By.ByXPath.xpath("//div[@class='quantumWizMenuPaperselectDropDown exportDropDown']"));
        BackButton = driver.findElement(By.ByXPath.xpath("(//span[@class='quantumWizButtonPaperbuttonLabel exportLabel'])[1]"));
        NextButton = driver.findElement(By.ByXPath.xpath("(//span[@class='quantumWizButtonPaperbuttonLabel exportLabel'])[2]"));
    }

    public SecondPage(WebDriver d)
    {
        driver = d;
        PageFactory.initElements(d,this);
        wait = new WebDriverWait(d, 2);
        InitPageAttributes();
    }

    public void FillFirstQuestion()
    {
        List<String> favorites = new ArrayList<String>();
        favorites.add("The big band theory");
        favorites.add("Suits");
        favorites.add("Black list");
        favorites.add("Elementary");
        favorites.add("Sherlok");

        Random randomIndex = new Random();
        String randomLine = favorites.get(randomIndex.nextInt(4)) + "\n" +
                favorites.get(randomIndex.nextInt(4)) + "\n" +
                favorites.get(randomIndex.nextInt(4));

        FirstQuestionInputField.sendKeys(randomLine);
    }

    public void ChoseColor()
    {
        ColorDropbox.click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@jsname='V68bde']"))));
        driver.findElement(By.xpath("//div[@jsname='V68bde']/div[3]")).click();
    }

    public void GoToPrevPage()
    {
        this.BackButton.click();
    }

    public void GoToNextPage()
    {
        this.NextButton.click();
    }

    public void CheckFirstAnswerIsFilled()
    {
        Assert.assertNotNull("!!!Answer field is empty!!!",FirstQuestionInputField.getAttribute("data-initial-value"));
    }

    public void CheckSecondAnswerIsFilled()
    {
        String selectedColor = driver.findElement(By.xpath("//div[@aria-selected='true']")).getText();
        Assert.assertEquals("!!!Color is not right!!!",selectedColor, "Red");
    }

}
