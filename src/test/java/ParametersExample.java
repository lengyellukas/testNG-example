import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParametersExample {
    WebDriver driver;


    @Parameters( {"browser"} )
    @Test
    public void openBrowserBasedOnParameter (String browser) {
        if (browser.equalsIgnoreCase("IE")) {
            //System.setProperty("webdriver.ie.driver", "src/IEDriverServer.exe");
            //driver = new InternetExplorerDriver ();
            System.out.println("IE would open");
        } else if (browser.equalsIgnoreCase("Firefox")) {
            //driver = new FirefoxDriver ();
            System.out.println("Firefox would open");
        } else if (browser.equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/chromedriver");
            driver = new ChromeDriver();
        }

        if(driver!=null) {
            driver.manage().window().maximize();
            driver.get("https://www.google.com");

            System.out.println("\n" + "Open " + browser);
            System.out.println("   " + driver.getTitle());
            System.out.println("Close " + browser + "\n");

            driver.quit();
        }
    }
}
