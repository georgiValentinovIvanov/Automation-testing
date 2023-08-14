package Introduction16.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class DownloadTest extends seTestMethod{
    @Test
    public  void testDownload() throws InterruptedException {
        driver.get("https://demoqa.com/upload-download");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement downloadBtn =  wait.until(ExpectedConditions.elementToBeClickable(By.id("downloadButton")));
        downloadBtn.click();
        String fileName= "sampleFile.jpeg";
        File file = new File(DOWNLOAD_DIR.concat(fileName));
        Assert.assertTrue(isFileDownloaded(file, 20));

    }
}
