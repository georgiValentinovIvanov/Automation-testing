package Introduction11;

import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverWithDriverPath {
    public static void main(String[] args) {
        // To run this example download the chromedriver.exe for your browser version
        // and modify the path to it. This example works if the driver is put in automation/src/test/resources/drivers/ folder
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        driver.get("http://training.skillo-bg.com:4300/posts/all");
        driver.manage().window().maximize();
        driver.close();
    }
}
