package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;
import util.Props;

import java.util.concurrent.TimeUnit;


public class BaseTest {



    protected WebDriver driver;
    @BeforeSuite
    public void initSuite () {
        System.out.println("Suite started " + this.getClass().getSimpleName());
    }

    @AfterSuite
    public void tearDownSuite () {
        System.out.println("Suite "+ this.getClass().getSimpleName()+" finished ");
    }

    @BeforeClass
    public void initWebDriver () throws Exception {
        driver = getDriver(Props.getKey("browser"));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = false, enabled = false)
    public void tearDown() throws Exception {
        if (this.driver != null){
            this.driver.quit();
            this.driver = null;
        }
    }

    private WebDriver getDriver (String driver) throws Exception {
        switch (driver){
            case "firefox":
                System.setProperty("webdriver.gecko.driver",Props.getKey("browser.path"));
                return new FirefoxDriver();
            case "chrome":
                return new ChromeDriver();
            case "IE":
                return new InternetExplorerDriver();
            default:
                throw new Exception("Unsupported browser");
        }
    }
}
