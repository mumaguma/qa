package pl.jsystems.qa.gui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class GuiConfiguration {

    protected WebDriver driver;

    @BeforeEach
    public void setUp() throws NullPointerException {
        try {
//            System.setProperty("webdriver.gecko.driver", Paths.get(getClass().getClassLoader()
//                    .getResource("drivers/geckodriver.exe").toURI()).toFile().getAbsolutePath());

            System.setProperty("webdriver.chrome.driver", Paths.get(getClass().getClassLoader()
                    .getResource("drivers/chromedriver.exe").toURI()).toFile().getAbsolutePath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

//        driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
//        driver.manage().deleteAllCookies();   // opcjonalnie
        driver.manage().window().maximize();

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }


}
