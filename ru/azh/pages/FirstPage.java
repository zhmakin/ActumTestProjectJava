package ru.azh.pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Calendar;
import java.util.List;

public class FirstPage {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    private String url = "https://docs.google.com/forms/d/e/1FAIpQLSeY_W-zSf2_JxR4drhbgIxdEwdbUbE4wXhxHZLgxZGiMcNl7g/viewform";

    List<WebElement> UncheckedBoxes;
    WebElement dayDateField, monthDateField, yearDateField, ErrorMessage, AnswerInputField, NextButton;

    public void InitPageAttributes()
    {
        UncheckedBoxes = driver.findElements(By.ByXPath.xpath("//div[@aria-label='Check this']"));
        dayDateField = driver.findElement(By.ByXPath.xpath("(//input[@jsname='YPqjbf'])[1]"));
        monthDateField = driver.findElement(By.ByXPath.xpath("(//input[@jsname='YPqjbf'])[2]"));
        yearDateField = driver.findElement(By.ByXPath.xpath("(//input[@jsname='YPqjbf'])[3]"));
        ErrorMessage = driver.findElement(By.ByXPath.xpath("//div[@id='i.err.1133270322']"));
        AnswerInputField = driver.findElement(By.ByXPath.xpath("//input[@name='entry.1864473569']"));
        NextButton = driver.findElement(By.ByXPath.xpath("//span[@class='quantumWizButtonPaperbuttonLabel exportLabel']"));
    }

    public FirstPage(WebDriver d)
    {
        driver = d;
        PageFactory.initElements(d,this);
        wait = new WebDriverWait(d, 2);
    }

    public void Navigate()
    {
        driver.get(url);
    }

    public void CheckBoxesAction()
    {
        for (WebElement element:UncheckedBoxes) {
            element.click();
        }
    }

    public void FillDate(Calendar date)
    {
        date.add(Calendar.DATE,6);
        dayDateField.clear();
        Integer day = date.get(Calendar.DAY_OF_MONTH);
        dayDateField.sendKeys(day.toString());

        monthDateField.clear();
        Integer month = date.get(Calendar.MONTH) + 1;
        monthDateField.sendKeys(month.toString());

        yearDateField.clear();
        Integer year = date.get(Calendar.YEAR);
        yearDateField.sendKeys(year.toString());
    }

    public boolean AssertErrorMsgExist()
    {
        return ErrorMessage.isDisplayed();
    }

    public void FillAnswerField(String text)
    {
        AnswerInputField.clear();
        AnswerInputField.sendKeys(text);
        wait.until(ExpectedConditions.attributeToBe(AnswerInputField,"data-initial-value",text));
    }

    public void GoToNextPage()
    {
        NextButton.click();
    }

    public boolean IsSecondPageLoad()
    {
        return driver.findElement(By.className("freebirdMaterialHeaderbannerPagebreakText")).isDisplayed();
    }

    public void ReverseAnswerField()
    {
        StringBuffer sb = new StringBuffer(AnswerInputField.getAttribute("value"));
        AnswerInputField.clear();
        AnswerInputField.sendKeys(sb.reverse());
    }
}
