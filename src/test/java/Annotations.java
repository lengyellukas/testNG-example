import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.Assertion;

public class Annotations {

    WebDriver driver;
    WebDriverWait driverWait;
    String BASE_URL = "http://automationpractice.com/index.php";
    String USER_NAME = "lengyel.luk@gmail.com";
    String PASSWORD = "rpqTeGYsgW3MLmV";

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(BASE_URL);
        System.out.println("Open Chrome and main page - BEFORE CLASS");
    }

    @BeforeMethod
    public void signIn() {
        WebElement signInButton = driver.findElement(By.cssSelector("a.login"));
        Highlighter.highlightElement(driver, signInButton);
        signInButton.click();

        WebElement userName = driver.findElement(By.id("email"));
        Highlighter.highlightElement(driver, userName);
        userName.sendKeys(USER_NAME);


        WebElement password = driver.findElement(By.id("passwd"));
        Highlighter.highlightElement(driver, password);
        password.sendKeys(PASSWORD);

        WebElement buttonLogin = driver.findElement(By.id("SubmitLogin"));
        Highlighter.highlightElement(driver, buttonLogin);
        buttonLogin.click();

        System.out.println("Sign In as customer - BEFORE METHOD");
    }

    @Test
    public void searchForTshirt() {
        WebElement searchBox = driver.findElement(By.id("search_query_top"));
        Highlighter.highlightElement(driver, searchBox);
        searchBox.sendKeys("T-shirt");

        WebElement performSearchBtn = driver.findElement(By.xpath("//button[@name='submit_search']"));
        Highlighter.highlightElement(driver, performSearchBtn);
        performSearchBtn.click();

        WebElement searchTerm = driver.findElement(By.cssSelector("h1.product-listing span.lighter"));
        String searchTermString = searchTerm.getText();
        Assert.assertEquals(searchTermString, "\"T-SHIRT\"");

        System.out.println("Search for T-shirt test done - TEST");
    }

    @Test
    public void searchForBlouse() {
        WebElement searchBox = driver.findElement(By.id("search_query_top"));
        Highlighter.highlightElement(driver, searchBox);
        searchBox.sendKeys("Blouse");

        WebElement performSearchBtn = driver.findElement(By.xpath("//button[@name='submit_search']"));
        Highlighter.highlightElement(driver, performSearchBtn);
        performSearchBtn.click();

        WebElement searchTerm = driver.findElement(By.cssSelector("h1.product-listing span.lighter"));
        String searchTermString = searchTerm.getText();
        Assert.assertEquals(searchTermString, "\"BLOUSE\"");

        System.out.println("Search for T-shirt test done - TEST");
    }

    @AfterMethod
    public void signOut() {
        //driverWait = new WebDriverWait(driver, 5);
        WebElement buttonLogout = driver.findElement(By.cssSelector("a.logout"));
        //waitFor(buttonLogout);
        Highlighter.highlightElement(driver, buttonLogout);
        buttonLogout.click();

        System.out.println("Signing out - AFTER METHOD");
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
        System.out.println("Close Chrome - AFTER CLASS");
    }


}
