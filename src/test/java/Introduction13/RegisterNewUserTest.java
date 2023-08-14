package Introduction13;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

    // Test scenario ID:1 - Successful register
// 1. Navigate to the Login page
// 2. Click 'Register' button
// 3. Check that the 'Sign up' header is displayed
// 4. Enter valid username. Validate that a green check is displayed after entering the value.
// 5. Enter valid email. Validate that a green check is displayed after entering the value.
// 6. Enter password. Validate that a green check is displayed after entering the value.
// 7. Confirm password . Validate that a green check is displayed after entering the value.
// 8. Click Sign in.
// 9. Validate a successful pop-up message
// 10. Validate that the user landed on http://training.skillo-bg.com/posts/all
// 11. Navigate to the Profile page
// 12. Validate that the username is correctly displayed on the Profile page
    public class RegisterNewUserTest {
        private static ChromeDriver driver;
        private final String BASE_URL = "http://training.skillo-bg.com/";
        private final String HOME_URL = BASE_URL + "posts/all";
        private final String REGISTER_URL = BASE_URL + "users/register";

        @BeforeMethod
        public void setup() {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
            driver.get(HOME_URL);
        }

        @Test
        @Parameters({"username", "email", "password"})
        public void registerNewUserTest(String username, String email, String password) {
            System.out.println("1. Navigate to the Login page");
            WebElement loginBtn = driver.findElement(By.id("nav-link-login"));
            clickElement(loginBtn);

            System.out.println("2. Click 'Register' button");
            WebElement registerBtn = driver.findElement(By.linkText("Register"));
            clickElement(registerBtn);

            System.out.println("3. Check that the 'Sign up' header is displayed");
            WebElement header = driver.findElement(By.tagName("h4"));
            String actualHeaderText = header.getText();
            Assert.assertEquals(actualHeaderText, "Sign up", "Expected header text to be Sign up, but got " +
                    actualHeaderText);

            System.out.println("4. Enter valid username. Validate that a green check is displayed after entering the value.");
            WebElement usernameField = driver.findElement(By.name("username"));
            //username = "auto" + new Random().nextInt(10000);
            populateField(usernameField, username);

            System.out.println("5. Enter valid email. Validate that a green check is displayed after entering the value.");
            WebElement emailField = driver.findElement(By.cssSelector("input[formcontrolname='email']"));
            //email = username + "@abv.bg";
            populateField(emailField, email);

            System.out.println("6. Enter password. Validate that a green check is displayed after entering the value.");
            WebElement passField = driver.findElement(By.id("defaultRegisterFormPassword"));
            //password = "Secret123";
            populateField(passField, password);

            System.out.println("7. Confirm password . Validate that a green check is displayed after entering the value.");
            WebElement confirmPassField = driver.findElement(By.id("defaultRegisterPhonePassword"));
            populateField(confirmPassField, password);

            System.out.println("8. Click Sign in.");
            WebElement signInBtn = driver.findElement(By.id("sign-in-button"));
            clickElement(signInBtn);

            System.out.println("9. Validate a successful pop-up message");
            WebElement toastElement = driver.findElement(By.cssSelector(".toast-message"));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.visibilityOf(toastElement));
            String toastMsg = toastElement.getText().trim();
            Assert.assertEquals(toastMsg, "Successful register!", "Registration not successful: " + toastMsg);

            System.out.println("10. Validate that the user landed on http://training.skillo-bg.com/posts/all");
            wait.until(ExpectedConditions.urlToBe(HOME_URL));

            System.out.println("11. Navigate to the Profile page");
            WebElement profileLink = driver.findElement(By.id("nav-link-profile"));
            clickElement(profileLink);

            System.out.println("12. Validate that the username is correctly displayed on the Profile page");
            WebElement usernameHeader = driver.findElement(By.cssSelector(".profile-user-settings"));
            wait.until(ExpectedConditions.visibilityOf(usernameHeader));
            String actualUsername = usernameHeader.getText();
            Assert.assertEquals(actualUsername, username, "Username is not correct. Actual username: " + actualUsername);
        }

        private void clickElement(WebElement element) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        }

        private void populateField(WebElement element, String text) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions.visibilityOf(element));
            element.sendKeys(text);
            String classes = element.getAttribute("class");
            Assert.assertTrue(classes.contains("is-valid"), "The field is not valid!");
        }

        @AfterMethod
        public void cleanup() {
            driver.close();
        }
    }

