package ru.azh.autotests;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.azh.pages.FirstPage;
import ru.azh.pages.FourthPage;
import ru.azh.pages.SecondPage;
import ru.azh.pages.ThirdPage;
import java.time.Month;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class ActiumTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setup()
    {
        System.setProperty("webdriver.gecko.driver", "c:/Program Files/Mozilla Firefox/browser/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
    }

    @Test
    public void testAction()
    {
        FirstPage fp = new FirstPage(driver);
        fp.Navigate();
        fp.InitPageAttributes();

        // 1) Fill first and second question
        fp.CheckBoxesAction();
        fp.FillDate(Calendar.getInstance());

        // 2) Validate that third question is mandatory
        fp.GoToNextPage();
        Assert.assertTrue("!!! Expected error message is not exist!!!",fp.AssertErrorMsgExist());

        // 3) Fill third question and go to another step
        fp.FillAnswerField(Month.of(Calendar.MONTH).name());
        fp.GoToNextPage();
        try{
            fp.IsSecondPageLoad();
        }
        catch(Throwable e){
            fp.GoToNextPage();
        }
        Assert.assertTrue("!!!The next page is not runed!!!",fp.IsSecondPageLoad());
        SecondPage sp = new SecondPage(driver);

        // 4) Fill next questions
        sp.FillFirstQuestion();
        sp.ChoseColor();

        // 5) Go back to first step
        sp.GoToPrevPage();

        // 6) Reverse text in third question
        fp = new FirstPage(driver);
        fp.InitPageAttributes();
        fp.ReverseAnswerField();

        // 7) Go to second step
        fp.GoToNextPage();

        // 8) Check that both questions are still filed
        sp = new SecondPage(driver);
        sp.CheckFirstAnswerIsFilled();
        sp.CheckSecondAnswerIsFilled();

        // 9) Go to last step
        sp.GoToNextPage();

        // 10) Fill last question and send form
        ThirdPage tp = new ThirdPage(driver);
        tp.SelectYes();
        tp.GoToNextPage();
        FourthPage fthp = new FourthPage(driver);
        fthp.CheckConformation();
    }

    @AfterClass
    public static void finish()
    {
        driver.close();
    }

}
