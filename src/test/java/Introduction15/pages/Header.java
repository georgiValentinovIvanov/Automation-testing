package Introduction15.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Header extends BasePage{
    WebDriverWait wait;
    @FindBy(id = "homeIcon")
    WebElement logoBtn;
    @FindBy(id = "nav-link-home")
    WebElement homeBtn;

    @FindBy(id = "nav-link-profile")
    WebElement profileBtn;
    @FindBy(id = "nav-link-new-post")
    WebElement newPostBtn;

    @FindBy(id = "nav-link-login")
    WebElement loginLink;


    public Header(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));


    }

    public void clickToLogo(){
        wait.until(ExpectedConditions.elementToBeClickable(logoBtn));
        logoBtn.click();
    }
    public void goToProfile(){
        wait.until(ExpectedConditions.elementToBeClickable(profileBtn));
        profileBtn.click();
    }
    public void goToHome(){
        wait.until(ExpectedConditions.elementToBeClickable(homeBtn));
        homeBtn.click();
    }
    public void goToNewPost(){
        clickElement(newPostBtn);
    }
    public void goToLogin() {
        clickElement(loginLink);

    }
}
