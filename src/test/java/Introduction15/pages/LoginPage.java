package Introduction15.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final String URLLogin = "http://training.skillo-bg.com/users/login";
    WebDriver driver;
    WebDriverWait wait;
    @FindBy(css = "form .h4")
    WebElement signInText;
    @FindBy(name = "usernameOrEmail")
    WebElement userNameField;
    @FindBy(name = "password")
    WebElement passField;

    @FindBy(id = "sign-in-button")
    WebElement signInBtn;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    }

    public void enterUserNameOrEmail(String userName) {
        wait.until(ExpectedConditions.visibilityOf(userNameField));
        userNameField.sendKeys(userName);
    }

    public void enterPass(String pass) {
        wait.until(ExpectedConditions.visibilityOf(passField));
        passField.sendKeys(pass);
    }

    public void clickSignInBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(signInBtn));
        signInBtn.click();
    }

    public void checkURL() {
        wait.until(ExpectedConditions.urlToBe(URLLogin));

    }

    public String getSignInHeaderText() {
        wait.until(ExpectedConditions.visibilityOf(signInText));
        return signInText.getText();

    }
}
