package Introduction11;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebDriverExampleTests {
    private static WebDriver driver;

    @BeforeMethod
    private static void setupDriver() {
        System.out.println("==========Setting up WebDriver==========");
        WebDriverManager.chromedriver().setup();
        /* If you get error 500, try this
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(opt); */

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    private static void testMyFirstWebDriver() {
        driver.get("http://training.skillo-bg.com:4300/posts/all");
    }

    @Test
    private static void testFindElement() {
        driver.get("http://training.skillo-bg.com:4200/posts/all");
        WebElement login = driver.findElement(By.xpath("//a[@id='nav-link-login']"));
        login.click();
    }

    @Test
    private static void testFindElement2() {
        driver.get("http://training.skillo-bg.com:4300/posts/all");

        //Finds Login link which you can use to interact with on a later stage
        WebElement loginElement = driver.findElement(By.id("nav-link-login"));

        //Finds Home link which you can use to interact with on a later stage
        WebElement homeElement = driver.findElement(By.linkText("Home"));
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    @Test
    private static void testFindElements() {

        driver.get("http://training.skillo-bg.com:4300/posts/all");
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //Finds all posts elements
        List<WebElement> posts = driver.findElements(By.xpath("//*[@class='post-feed-img']"));
        System.out.println("The number of post elements is: " + posts.size());
    }

    @Test
    private static void testClick() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("http://training.skillo-bg.com:4300/posts/all");

        WebElement login = driver.findElement(By.id("nav-link-login"));

        login.click();
    }

    @Test
    private static void testSendKeys() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://training.skillo-bg.com:4300/posts/all");

        WebElement login = driver.findElement(By.id("nav-link-loginfsdf"));
        login.click();

        WebElement username = driver.findElement(By.name("usernameOrEmail"));

        //type in username field
        username.sendKeys("test");
        username.sendKeys("proba1");
    }

    @Test
    private static void testClear() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("http://training.skillo-bg.com:4300/posts/all");

        WebElement login = driver.findElement(By.id("nav-link-login"));
        login.click();

        WebElement username = driver.findElement(By.id("defaultLoginFormUsername"));

        //type in username field
        username.sendKeys("test");
        username.clear();
    }

    @Test
    private static void testCommonCommands() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://training.skillo-bg.com:4300/posts/all");

        //return title of the page as a string
        String titleOfThePage = driver.getTitle();
        System.out.println("The title is: " + titleOfThePage);

        WebElement login = driver.findElement(By.id("nav-link-login"));
        login.click();
        WebElement rememberMe = driver.findElement(By.xpath("//input[@formcontrolname='rememberMe']"));
        rememberMe.click();

        //return true if element is selected(checked)
        System.out.println("Element is selected: " + rememberMe.isSelected());

        WebElement signInButton = driver.findElement(By.id("sign-in-button"));
        //return text of the field as a string
        String buttonText = signInButton.getText();
        System.out.println("The text of the element is: " + buttonText);

        //return true if element is displayed on the page
        System.out.println("The element is displayed: " + signInButton.isDisplayed());

        //return true if element is enabled, and you can interact with it
        System.out.println("The element is enabled: " + signInButton.isEnabled());
    }

    @Test
    private static void testDropdowns() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.amazon.com/");

        // Select value from dropdown
        WebElement select = driver.findElement(By.id("searchDropdownBox"));
        //select a ne Web Elemnt
        Select dropdownCategory = new Select(select);
        dropdownCategory.selectByValue("search-alias=amazon-devices");


        //dropdownCategory.selectByVisibleText("Books");
        // dropdownCategory.selectByValue("search-alias=arts-crafts-intl-ship");

    }

    @Test
    private static void implicitWait() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://training.skillo-bg.com:4300/posts/all");
        WebElement goToLoginBtn = driver.findElement(By.id("nav-link-login"));
        goToLoginBtn.click();
    }

    @Test
    private static void explicitWait() {
        driver.get("http://training.skillo-bg.com:4300/posts/all");
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement goToLoginBtn = driver.findElement(By.id("nav-link-login"));

        // Wait for posts to not become visible
        By allPostLocator = By.tagName("app-all-posts");
        WebElement allPosts = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(allPostLocator));
        goToLoginBtn.click();

        // Wait for posts to disappear
        webDriverWait.until(ExpectedConditions.invisibilityOf(allPosts));
    }

    @AfterMethod
    private static void closeDriver() {
        System.out.println("==========Closing WebDriver session==========");
        driver.close();
    }
}
