package Introduction16.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Header extends BasePage{
    @FindBy(id = "nav-link-home")
    WebElement homeLink;

    @FindBy(css = "#nav-link-login")
    WebElement loginLink;

    @FindBy(linkText = "Profile")
    WebElement profileLink;

    @FindBy(id = "nav-link-new-post")
    WebElement newPostLink;


    public Header(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void goToLogin() {
        clickElement(loginLink);
    }

    public void goToHome() {
        clickElement(homeLink);
    }

    public void goToProfile() {
        clickElement(profileLink);
    }

    public void goToNewPost() {
        clickElement(newPostLink);
    }
}
