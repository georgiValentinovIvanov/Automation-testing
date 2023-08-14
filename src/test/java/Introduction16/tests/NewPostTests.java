package Introduction16.tests;

import Introduction16.pages.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class NewPostTests extends seTestMethod{
    @DataProvider(name = "getUsers")
    public Object[][] getUsers() {
        return new Object[][]{{"auto_user", "auto_pass", new File("src/test/java/lecture17/upload/sk8.jpg"), "beach time"}};
    }

    @Test(dataProvider = "getUsers")
    public void testCreatePost(String username, String password, File file, String capText) {
        System.out.println("1. Open homepage");
        HomePage homePage = new HomePage(driver);
        homePage.openSiteURl();

        System.out.println("2. Login with existing user");
        Header header = new Header(driver);
        header.goToLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyUrl();
        loginPage.login(username, password);

        System.out.println("3. Go to profile page and get current posts count");
        header.goToProfile();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.verifyUrl();
        int existingPosts = profilePage.getExistingPostCount();

        System.out.println("4. Go to new post");
        header.goToNewPost();
        NewPostPage postPage = new NewPostPage(driver);
        postPage.verifyUrl();

        System.out.println("5. Upload a new picture");
        postPage.uploadImage(file);

        System.out.println("6. Verify that the image is visible");
        postPage.waitForImageToShow();

        System.out.println("7. Verify the image name is correct");
        Assert.assertEquals(postPage.getImageFileName(), file.getName());

        System.out.println("8. Populate the post caption");
        postPage.populateCaption(capText);

        System.out.println("9. Click create post");
        postPage.clickSubmitBtn();
        profilePage.verifyUrl();

        System.out.println("10. Verify the post number has increased");
        int currentPostCount = profilePage.getExistingPostCount();
        Assert.assertEquals(currentPostCount, existingPosts + 1, "Incorrect post number");

        System.out.println("11. Open the latest post");
        profilePage.openPostByIndex(currentPostCount - 1);
        PostModal postModal = new PostModal(driver);
        postModal.waitForDialogAppear();

    }
}
