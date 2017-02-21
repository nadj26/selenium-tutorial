import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by jo on 20/02/17.
 */
public class Registration {

    String driverPath = "/Users/jo/Downloads/selenium-tutorial/";
    private WebDriver driver;
    private String baseUrl;

    @Before
    public void setUp() throws Exception{

        System.out.println("=======settings=======");

        System.setProperty("webdriver.chrome.driver",driverPath+"chromedriver");
        //System.setProperty("webdriver.gecko.driver", driverPath+"geckodriver");

        driver =new ChromeDriver();
        //driver =new FirefoxDriver();

        baseUrl="http://www.creditplace.com";

    }

    @Test
    public void accessToWebsite() throws Exception{

        System.out.println("=======launching=======");
        driver.get(baseUrl);

        System.out.println("Application title is =============" + driver.getTitle());

        WebElement joinClick = driver.findElement(By.linkText("Join"));
        joinClick.click();

        System.out.println("Join page title is =============" + driver.getTitle());

        System.out.println("==========Form============");
        driver.findElement(By.id("edit-name")).sendKeys("nadjou");
        driver.findElement(By.id("edit-last-name")).sendKeys("nadjar");
        driver.findElement(By.id("edit-email")).sendKeys("nadjar@uuu.com");
        driver.findElement(By.id("edit-password")).sendKeys("012345Ml!");
        driver.findElement(By.id("edit-city")).sendKeys("rtyui");
        driver.findElement(By.id("edit-street")).sendKeys("jjjj!");
        driver.findElement(By.id("edit-phone")).sendKeys("0144556677");

        driver.findElement(By.id("edit-submit")).click();
        System.out.println("form2 page title is =============" + driver.getTitle());

        System.out.println("======scroll down in #edit-terms-conditions========");

        JavascriptExecutor js;
        js = (JavascriptExecutor) driver;
        js.executeScript("var h = window.jQuery(\"#edit-terms-conditions\")[0].scrollHeight;" +
                "window.jQuery(\"#edit-terms-conditions\").animate({ scrollTop: h })");

        WebElement checkBoxElement=driver.findElement(By.id("edit-agree-terms"));

        if ( !driver.findElement(By.id("edit-agree-terms")).isSelected() && driver.findElement(By.id("edit-agree-terms")).isDisplayed())
        {
            driver.findElement(By.id("edit-agree-terms")).click();
        }
        else {
            System.out.println("invisibles");
            js.executeScript("window.jQuery(\"#edit-agree-terms\").prop(\"checked\", true);" +
                    "window.jQuery(\"#edit-submit\").toggleClass(\"form-submit enabled\",true);");

        }

    }
    @After
    public void endOfTest() throws Exception{
        System.out.println("======Goodbye======= 10s remaining");
        Thread.sleep(10000);
        driver.quit();
    }

}
