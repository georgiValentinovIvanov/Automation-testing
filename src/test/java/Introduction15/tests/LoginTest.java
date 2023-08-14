package Introduction15.tests;

import Introduction15.pages.Header;
import Introduction15.pages.HomePage;
import Introduction15.pages.LoginPage;
import Introduction15.pages.ProfilePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

    }

    @DataProvider(name = "users")
    public Object[][] getUsers() {
        return new Object[][]{{"auto_user", "auto_pass", "auto_user"}, // test login by username
                {"auto_user1@abv.bg", "auto_pass", "auto_user1"}, // test login by email
        };
    }

    @Test(dataProvider = "users")
    public void LoginTest(String userNameOrEmail, String pass, String username) {

        System.out.println("1. Navigate to home page");
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();

        System.out.println("2. Navigate to login page");
        Header headerPage = new Header(driver);
        headerPage.goToLogin();

        System.out.println("3. Check the URL is correct");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.checkURL();

        System.out.println("4. Sign in is displayed ");
        String headerText = loginPage.getSignInHeaderText();
        Assert.assertEquals(headerText, "Sign in", "Incorrect Sign in header text!");

        System.out.println("5. Enter user name");
        loginPage.enterUserNameOrEmail(userNameOrEmail);

        System.out.println("6. Enter pass");
        loginPage.enterPass(pass);

        System.out.println("7. Click sign in btn");
        loginPage.clickSignInBtn();

        System.out.println("8. Check the URL is correct");
        homePage.verifyURL();

        System.out.println("9. Profile Btn is visible and user can navigate to it ");
        headerPage.goToProfile();
        System.out.println("10. Check the URL is correct");
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.verifyUrl();
        System.out.println("11. Check the user name");

        String usernameHeaderText = profilePage.getUsernameHeaderText();
        Assert.assertEquals(usernameHeaderText, username, "Incorrect username!");
    }

    @AfterMethod
    public void cleanUp() {
        this.driver.close();
    }
}
