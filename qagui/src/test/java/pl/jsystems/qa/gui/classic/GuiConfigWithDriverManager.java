package pl.jsystems.qa.gui.classic;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pl.jsystems.qa.gui.GuiConfig;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class GuiConfigWithDriverManager {

    protected WebDriver driver;

    @BeforeAll
    public static void setUpAll() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
    }


    @BeforeEach
    public void setUp() {
//        driver = new ChromeDriver();
//        driver.manage().deleteAllCookies();   // opcjonalnie

        if (GuiConfig.MACHINE.equals("local")) {
            setupLocalConfiguration();
        } else {
            setupRemoteConfiguration();
        }

    }

    private void setupRemoteConfiguration() {
    }

    private void setupLocalConfiguration() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

//    private WebDriver setWebDriver() {
//        switch (GuiConfig.BROWSER) {
//            case "chrome":
//                try {
//                    System.setProperty("webdriver.chrome.driver", Paths.get(getClass().getClassLoader()
//                            .getResource("drivers/chromedriver.exe").toURI()).toFile().getAbsolutePath());
//                } catch (URISyntaxException e) {
//                    e.printStackTrace();
//                }
//                return new ChromeDriver();
//            case "firefox":
//                try {
//                    System.setProperty("webdriver.gecko.driver", Paths.get(getClass().getClassLoader()
//                            .getResource("drivers/geckodriver.exe").toURI()).toFile().getAbsolutePath());
//                } catch (URISyntaxException e) {
//                    e.printStackTrace();
//                }
//                return new FirefoxDriver();
//            case "edge":
//                return new EdgeDriver();
//        }
//    }


}
