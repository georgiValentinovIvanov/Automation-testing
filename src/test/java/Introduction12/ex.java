package Introduction12;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ex {
    ChromeDriver driver;
    final String URL = "http://training.skillo-bg.com/";
    final String HOME_URL = URL + "posts/all";

    // 0. Load Skillo site http://training.skillo-bg.com/ - Done
    // 1. Check Skillo logo element is visible - - Done
    // 2. Click login btn- - Done
    // 3. Validate that the URL is correct - Done
    // 4. Validate that the sign in text is visible - Done
    // 5. Enter user name - Done
    // 6. Enter pass - Done
    // 7. Select remember me btn - Done
    // 8. Click sign in and check the btn is enabled - Done
    // 9. Validate that the logout btn is visible - Done

    // TODO: 6/15/2023 - Home work for next time
    // 10.Validate that the Profile tab is visible
    // 11.Validate that the new post btn is visible
    // 12.Validate that the URL is correct

    //auto_user/ auto_pass

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("0. Load Skillo site http://training.skillo-bg.com/ ");
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
    }

    @Test
    public void loginTest() {

        System.out.println("1. Check Skillo logo element is visible");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement homeLogoEl = driver.findElement(By.id("homeIcon"));
        Assert.assertTrue(homeLogoEl.isDisplayed(), "Logo icon is not visible");

        System.out.println("2.  Click login btn");
        WebElement loginBtn = driver.findElement(By.id("nav-link-login"));
        loginBtn.click();

        System.out.println(" 3. Validate that the URL is correct");
        // String actualLoginPageURL = driver.getCurrentUrl();
        String expectedLoginPageURL = "http://training.skillo-bg.com/users/login";
        //  Assert.assertEquals(actualLoginPageURL, expectedLoginPageURL ,"Login URL is not correct");
        wait.until(ExpectedConditions.urlToBe(expectedLoginPageURL));

        System.out.println("4. Validate that the sign in text is visible");
        WebElement SignBtn = driver.findElement(By.xpath("//p[text()='Sign in']"));
        Assert.assertTrue(SignBtn.isDisplayed(), "Sign Btn icon is not visible");

        System.out.println(" 5. Enter user name");
        WebElement userName = driver.findElement(By.name("usernameOrEmail"));
        userName.clear();
        userName.sendKeys("auto_user");

        System.out.println(" 6. Enter pass");
        WebElement pass = driver.findElement(By.id("defaultLoginFormPassword"));
        pass.clear();
        pass.sendKeys("auto_pass");

        System.out.println(" 7. Select remember me btn");
        WebElement rememberBtn = driver.findElement(By.cssSelector(".remember-me-button"));
        rememberBtn.click();
        Assert.assertTrue(rememberBtn.isSelected(), "Btn is selected");

        System.out.println(" 8. Click sign in and check the btn is enabled");
        WebElement signInBtn = driver.findElement(By.id("sign-in-button"));

        System.out.println("Sign in is enabled");
        Assert.assertTrue(signInBtn.isEnabled(), "Btn is enabled");
        signInBtn.click();

        System.out.println(" Validate that the logout btn is visible ");
        WebElement logOutBtn = driver.findElement(By.cssSelector(".fa-sign-out-alt"));
        Assert.assertTrue(logOutBtn.isDisplayed(), "logOutBtn icon is not visible");

        // 10.Validate that the Profile tab is visible
        WebElement profileTab = driver.findElement(By.id("nav-link-profile"));
        Assert.assertTrue(profileTab.isDisplayed(), "Profile Tab is not displayed");

        // 11.Validate that the new post btn is visible
        WebElement newPostTab = driver.findElement(By.id("nav-link-new-post"));
        Assert.assertTrue(newPostTab.isDisplayed(), "New Post button is not displayed");

        // 12.Validate that the URL is correct
        wait.until(ExpectedConditions.urlToBe(HOME_URL));
    }
    @AfterMethod
    public void cleanup() {
        driver.close();
    }
}
