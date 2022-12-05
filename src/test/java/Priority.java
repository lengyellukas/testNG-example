import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;


public class Priority {

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

    @Test(priority = 1)
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

        WebElement signedInUserName = driver.findElement(By.cssSelector("div.header_user_info span"));
        Highlighter.highlightElement(driver, signedInUserName);
        String signedUserName = signedInUserName.getText();
        String signedUserNameCheck = "Lukas Lengyel";

        Assert.assertEquals(signedUserName, signedUserNameCheck, "The signed in user name was different than expected");

        System.out.println("Sign In as customer - TEST");
    }

    @Test(priority = 2)
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

    @Test(priority = 3)
    public void signOut() {
        //driverWait = new WebDriverWait(driver, 5);
        WebElement buttonLogout = driver.findElement(By.cssSelector("a.logout"));
        //waitFor(buttonLogout);
        Highlighter.highlightElement(driver, buttonLogout);
        buttonLogout.click();

        WebElement signedInButton = driver.findElement(By.cssSelector("a.login"));
        Assert.assertTrue(signedInButton.isDisplayed(), "Signed in button not displayed after signing out");

        System.out.println("Signing out - TEST");
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
        System.out.println("Close Chrome - AFTER CLASS");
    }
}
