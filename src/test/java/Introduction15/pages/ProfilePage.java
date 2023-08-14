package Introduction15.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage extends BasePage{
    private final String BASE_URL = "http://training.skillo-bg.com/users/";

    @FindBy(css = ".profile-user-settings > h2")
    WebElement usernameHeader;

    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getUsernameHeaderText() {
        return getElementText(usernameHeader);
    }

    public void verifyUrl() {
        verifyUrlContains(BASE_URL);
    }
}
