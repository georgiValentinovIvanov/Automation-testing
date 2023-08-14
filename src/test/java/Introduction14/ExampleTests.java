package Introduction14;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ExampleTests {
    private WebDriver driver;
    private WebDriverWait smallWait;

    private WebDriverWait mediumWait;

    private String baseUrl = "https://demoqa.com";

    @BeforeSuite
    protected final void setupTestSuite() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    protected final void setUpTestMethod() {
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        smallWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        mediumWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testCheckBox() {
        driver.get(baseUrl + "/checkbox");
        WebElement label = smallWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[for='tree-node-home']")));
        // Check the root checkbox
        label.click();

        WebElement checkBox = driver.findElement(By.id("tree-node-home"));
        Assert.assertTrue(checkBox.isSelected(), "Checkbox with expected to be selected but is not!");

        WebElement result = driver.findElement(By.id("result"));
        String expectedResult = "You have selected :\n" + "home\n" + "desktop\n" + "notes\n" + "commands\n" + "documents\n" + "workspace\n" + "react\n" + "angular\n" + "veu\n" + "office\n" + "public\n" + "private\n" + "classified\n" + "general\n" + "downloads\n" + "wordFile\n" + "excelFile";
        String actualResult = result.getText();
        Assert.assertEquals(actualResult, expectedResult, "Selection text is incorrect!");

        // Uncheck the checkbox
        label.click();
        Assert.assertFalse(checkBox.isSelected(), "Checkbox with expected to NOT be selected but it is!");
    }

    @Test
    public void testCheckBoxes() {
        driver.get(baseUrl + "/checkbox");

        // Expand the tree
        WebElement expandArrow = driver.findElements(By.cssSelector("[title='Toggle']")).get(0);
        smallWait.until(ExpectedConditions.elementToBeClickable(expandArrow));
        expandArrow.click();

        // Select the 'Documents' checkbox
        WebElement documentsLabel = driver.findElement(By.cssSelector("[for='tree-node-documents']"));
        smallWait.until(ExpectedConditions.elementToBeClickable(documentsLabel));
        documentsLabel.click();
        WebElement documentsCheckBox = driver.findElement(By.id("tree-node-documents"));
        Assert.assertTrue(documentsCheckBox.isSelected(), "Document checkbox not selected!");

        // Select 'Downloads' checkbox
        WebElement downloadsLabel = driver.findElement(By.cssSelector("[for='tree-node-downloads']"));
        smallWait.until(ExpectedConditions.elementToBeClickable(downloadsLabel));
        downloadsLabel.click();
        WebElement downloadsCheckBox = driver.findElement(By.id("tree-node-downloads"));
        Assert.assertTrue(downloadsCheckBox.isSelected());

        // Verify that the 'Documents' checkbox is still selected
        Assert.assertTrue(documentsCheckBox.isSelected());

        WebElement result = driver.findElement(By.id("result"));
        String expectedResult = "You have selected :\n" + "documents\n" + "workspace\n" + "react\n" + "angular\n" + "veu\n" + "office\n" + "public\n" + "private\n" + "classified\n" + "general\n" + "downloads\n" + "wordFile\n" + "excelFile";
        String actualResult = result.getText();
        Assert.assertEquals(actualResult, expectedResult, "Selection text is incorrect!");
    }

    @Test
    public void testRadioButtons() {
        driver.get(baseUrl + "/radio-button");
        // Click the 'Yes' radio button label
        WebElement yesRadioButtonLabel = smallWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[for='yesRadio']")));
        yesRadioButtonLabel.click();

        // Check that the 'Yes' radio button became selected
        WebElement yesRadioButton = driver.findElement(By.id("yesRadio"));
        Assert.assertTrue(yesRadioButton.isSelected(), "'Yes' radio button expected to be selected!");
        String actualText = driver.findElement(By.className("mt-3")).getText();
        String expResultYesRadio = "You have selected Yes";

        Assert.assertEquals(actualText, "You have selected Yes", "Incorrect result message!");

        // Click the 'Impressive' radio button label
        WebElement impressiveRadioButtonLabel = smallWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[for='impressiveRadio']")));
        impressiveRadioButtonLabel.click();
        WebElement impressiveRadioButton = driver.findElement(By.id("impressiveRadio"));
        Assert.assertTrue(impressiveRadioButton.isSelected(), "'Impressive' radio button expected to be selected!");


        // The first radio button becomes deselected
        Assert.assertFalse(yesRadioButton.isSelected(), "'Yes' radio button expected to NOT be selected!");

        actualText = driver.findElement(By.className("mt-3")).getText();
        Assert.assertEquals(actualText, "You have selected Impressive", "Incorrect result message!");

        WebElement noRadioButtonLabel = smallWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[for='noRadio']")));
        // Click cannot select a radio button that is disabled
        noRadioButtonLabel.click();
        WebElement noRadioButton = driver.findElement(By.id("noRadio"));

        if (noRadioButton.isEnabled()) {
            Assert.assertTrue(noRadioButton.isSelected());
        } else {
            System.out.println("The NO radio button is disabled");
        }
    }

    @Test
    public void testTables() {
        driver.get(baseUrl + "/webtables");
        WebElement table = driver.findElement(By.className("rt-table"));
        int emailCellIndex = 0;

        // Get Column header

        WebElement tableColumnHeader = table.findElement(By.className("rt-thead"));
        // WebElement tableColumnHeader = driver.findElement(By.cssSelector(" .rt-table .rt-thead"));
        List<WebElement> columnsHeaderCells = tableColumnHeader.findElements(By.cssSelector("[role='columnheader']"));

        // Get the Email column index
        System.out.println("Column names: ");

     /*   for (int i = 0; i < columnsHeaderCells.size(); i++) {
            WebElement columnCell = columnsHeaderCells.get(i);*/

        for (WebElement columnCell : columnsHeaderCells) {
            String columnName = columnCell.getText();
            if (columnName.equals("Email")) {
                emailCellIndex = columnsHeaderCells.indexOf(columnCell);
                break;
            }

            System.out.print(columnCell.getText() + ", ");
        }

        // Get Rows
        WebElement tableBody = table.findElement(By.className("rt-tbody"));
        List<WebElement> rows = tableBody.findElements(By.cssSelector("[role='row']"));

        // Find a row by email and delete it
        for (WebElement row : rows) {
            // Get Cells
            List<WebElement> cells = row.findElements(By.cssSelector("[role='gridcell']"));
            String cellText = cells.get(emailCellIndex).getText();
            if (cellText.equals("alden@example.com")) {
                // Delete the row
                System.out.println("Deleting the row with email 'alden@example.com'");
                WebElement deleteButton = row.findElement(By.cssSelector("[id^=delete-record]"));
                smallWait.until(ExpectedConditions.elementToBeClickable(deleteButton));
                deleteButton.click();
                break;
            }
        }

        //Get refreshed table body
        tableBody = table.findElement(By.className("rt-tbody"));
        rows = tableBody.findElements(By.cssSelector("[role='row']"));

        //Verify that the deleted row is removed
        for (WebElement row : rows) {
            // Get Cells
            List<WebElement> cells = row.findElements(By.cssSelector("[role='gridcell']"));
            String cellText = cells.get(emailCellIndex).getText();
            Assert.assertNotEquals(cellText, "alden@example.com", "Expected a row with email 'alden@example.com' to NOT exist adter the deletion!");
        }
    }

    @Test
    public void testNewTab() {
        driver.get(baseUrl + "/browser-windows");
        // Click New Tab button
        WebElement button = smallWait.until(ExpectedConditions.elementToBeClickable(By.id("tabButton")));
        button.click();

        // When a new tab/window is opened, the driver focus remains on the initial tab/window, if no switch command is executed
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://demoqa.com/browser-windows", "Incorrect url!");

        //Get the available tabs
        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        String secondWindow = windows.get(1);

        //Switch the driver focus on the 2nd tab
        driver.switchTo().window(secondWindow);
        //driver.manage().window().maximize();

        currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://demoqa.com/sample");

        WebElement headline = driver.findElement(By.id("sampleHeading"));
        String actualHeadlineText = headline.getText();

        Assert.assertEquals(actualHeadlineText, "This is a sample page");
    }

    @Test
    public void testNewWindow() {
        driver.get(baseUrl + "/browser-windows");
        // Click on 'New Window' button
        WebElement button = smallWait.until(ExpectedConditions.elementToBeClickable(By.id("windowButton")));
        button.click();

        //When new tab/window is opened, the driver focus remains on the initial tab/window, if no switch command is executed
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://demoqa.com/browser-windows", "Incorrect url!");

        //Get available tabs
        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        String secondWindow = windows.get(1);

        //Switch the driver focus on 2nd tab
        driver.switchTo().window(secondWindow);
        driver.manage().window().maximize();
        currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://demoqa.com/sample");
        WebElement headline = driver.findElement(By.id("sampleHeading"));
        String actualHeadlineText = headline.getText();
        Assert.assertEquals(actualHeadlineText, "This is a sample page");

        //Switch back to first tab/window
        String firstWindow = windows.get(0);
        //Switch the driver focus on 1st tab
        driver.switchTo().window(firstWindow);
        currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://demoqa.com/browser-windows", "Incorrect url!");
    }

    @Test
    public void testAlert() {
        driver.get(baseUrl + "/alerts");
        // Click on the button for 'Click Button to see alert'
        WebElement alertButton = smallWait.until(ExpectedConditions.elementToBeClickable(By.id("alertButton")));
        alertButton.click();

        // Switch the context to the alert
        Alert alert = driver.switchTo().alert();

        // After switchTo().alert() we cannot interact with elements from the main window
        // Uncommenting this line will result in an error
        // alertButton.getText();

        String actualAlertText = alert.getText();
        Assert.assertEquals(actualAlertText, "You clicked a button");
        alert.accept();

        // After alert().accept() we can interact with elements from the main window
        alertButton.getText();
    }

    @Test
    public void testAlertWithDelay() {
        driver.get(baseUrl + "/alerts");
        // Click the button for 'On button click, alert will appear after 5 seconds'
        WebElement confirmBoxButton = smallWait.until(ExpectedConditions.elementToBeClickable(By.id("timerAlertButton")));
        confirmBoxButton.click();

        // Switch the context to the alert
        mediumWait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "This alert appeared after 5 seconds", "Incorrect alert message!");

        alert.accept();
    }

    @Test
    public void testConfirmBox() {
        driver.get(baseUrl + "/alerts");
        // Click the button for 'On button click, confirm box will appear'
        WebElement confirmBoxButton = smallWait.until(ExpectedConditions.elementToBeClickable(By.id("confirmButton")));
        confirmBoxButton.click();

        // Switch the context to the alert
        smallWait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();

        // Confirm Box can be accepted or dismissed
        alert.dismiss();
        String actualMessage = driver.findElement(By.id("confirmResult")).getText();
        Assert.assertEquals(actualMessage, "You selected Cancel");
    }

    //populate alert
    @Test
    public void testPromptBox() {
        driver.get(baseUrl + "/alerts");

        // Click on the button for "On button click, prompt box will appear"
        WebElement promptBoxButton = smallWait.until(ExpectedConditions.elementToBeClickable(By.id("promtButton")));
        promptBoxButton.click();

        String name = "Harry";

        // Switch the context and interact with the alert
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(name);
        alert.accept();

        String actualText = driver.findElement(By.id("promptResult")).getText();
        String expectedText = "You entered " + name;
        Assert.assertEquals(actualText, expectedText, "Incorrect result text!");
    }

    @Test
    public void testHover() {
        driver.get(baseUrl + "/tool-tips");
        WebElement element = smallWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toolTipButton")));

        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        String tooltipText = driver.findElement(By.className("tooltip-inner")).getText();
        Assert.assertEquals(tooltipText, "You hovered over the Button");
    }

    @Test
    public void testDragAndDrop() throws InterruptedException {
        driver.get(baseUrl + "/droppable");
        WebElement draggedElem = smallWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("draggable")));
        WebElement droppedElem = smallWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("droppable")));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(draggedElem, droppedElem).perform();
        // Another way to do a drah and drop
        // actions.moveToElement(draggedElem).clickAndHold().moveToElement(droppedElem).release(droppedElem).perform();
        smallWait.until(ExpectedConditions.textToBe(By.cssSelector("#droppable p"), "Dropped!"));
    }

    @Test
    public void testIFrames() {
        driver.get(baseUrl + "/frames");

        //Find all iframes
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));

        /*
        iframe content is available after switch
        cannot execute before switch driver.findElement(By.id("sampleHeading")).getText();
        */
        //Switch by iframe name or id
        driver.switchTo().frame("frame1");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sampleHeading")));
        String frameHeadingText = heading.getText();
        Assert.assertEquals(frameHeadingText, "This is a sample page");

        /*
        While driver is switched to particular frame the other page content is unavailable
        driver.findElement(By.cssSelector("#framesWrapper div")).getText();
         */

        //Go back to main page
        driver.switchTo().defaultContent();
        String mainPageText = driver.findElement(By.cssSelector("#framesWrapper div")).getText();
        Assert.assertEquals(mainPageText, "Sample Iframe page There are 2 Iframes in this page. Use browser inspecter or firebug to check out the HTML source. In total you can switch between the parent frame, which is this window, and the two frames below");

        //Switch to 3rd frame by index
        driver.switchTo().frame(2);

        frameHeadingText = driver.findElement(By.id("sampleHeading")).getText();
        Assert.assertEquals(frameHeadingText, "This is a sample page");

        //Switch to 2nd frame by WebElement
        driver.switchTo().defaultContent();
        iframes = driver.findElements(By.tagName("iframe"));
        WebElement iframeElement = iframes.get(1);
        driver.switchTo().frame(iframeElement);
    }

    @AfterMethod
    protected final void cleanUpTestMethod() {
        //  driver.close();
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}
