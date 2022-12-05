import org.testng.Assert;
import org.testng.annotations.Test;

public class Dependency {

    @Test (groups = "basic")
    public void test1_openBrowser() {
        System.out.println("Open browser");
    }

    @Test (dependsOnMethods = "test1_openBrowser", groups = "basic")
    public void test2_goToURL() {
        System.out.println("Navigating to base URL");
    }

    @Test (dependsOnMethods = "test2_goToURL", groups = "basic")
    public void test3_signIn() {
        System.out.println("Signing to the app");
        //making the test to fail to test dependsOnMethods annotation
        //Assert.assertTrue(false);
    }

    @Test (dependsOnMethods = "test3_signIn")
    public void test4_putProductToCart() {
        System.out.println("Putting a product to a shopping cart");
    }

    @Test (dependsOnMethods = { "test3_signIn", "test4_putProductToCart" } )
    public void test5_checkout() {
        System.out.println("Checking out");
    }


    @Test (dependsOnMethods = "test3_signIn", groups = "basic" )
    public void test7_SignOut() {
        System.out.println("signing out");
    }
}

