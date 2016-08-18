package tests;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import util.Props;

import java.util.concurrent.TimeUnit;


public class BaseTest {


    protected WebDriver driver;

    @BeforeSuite
    public void initSuite() {
        System.out.println("Suite started " + this.getClass().getSimpleName());
    }

    @AfterSuite
    public void tearDownSuite() {
        System.out.println("Suite " + this.getClass().getSimpleName() + " finished ");
    }

    @BeforeClass
    public void initWebDriver() throws Exception {
        driver = initDriver(Props.getKey("browser"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        if (this.driver != null) {
            this.driver.quit();
            this.driver = null;
        }
    }

    private WebDriver initDriver(String driver) throws Exception {
        switch (driver) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", Props.getKey("firefox.path"));
                return new FirefoxDriver();
            case "chrome":

                // handling some issues
                ChromeOptions chromeOptions = new ChromeOptions();
                DesiredCapabilities dc = new DesiredCapabilities();
                dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);

                System.setProperty("webdriver.chrome.driver", Props.getKey("chrome.path"));
                chromeOptions.addArguments("test-type");
                chromeOptions.addArguments("--disable-extensions");
                dc.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                return new ChromeDriver(dc);
            case "opera":
                return new OperaDriver();
            case "IE":
                /*
                not implemented yet
                 */
                return new InternetExplorerDriver();
            default:
                throw new Exception("Unsupported browser");
        }
    }
}
