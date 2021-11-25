package pl.jsystems.qa.gui.classic;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.jsystems.qa.gui.GuiConfigurationTutor;
import pl.jsystems.qa.gui.classic.page.*;

import java.util.Set;

import static pl.jsystems.qa.gui.GuiConfig.*;

@Tags({@Tag("FrontEnd"), @Tag("smoke"), @Tag("param")})
@DisplayName("Frontend test")
public class KatalonTest extends GuiConfigurationTutor {

    WordpressMainPage wordpressMainPage;
    LoginPage loginPage;
    MainUserPage mainUserPage;
    MyProfilePage myProfilePage;
    NotificationPage notificationPage;

    @Tag("Login")
    @DisplayName("Login test")
    @Test
    public void testUntitledTestCase() {
        driver.get("https://wordpress.com/");
        driver.findElement(By.linkText("Log In")).click();
        driver.findElement(By.id("usernameOrEmail")).click();
        driver.findElement(By.id("usernameOrEmail")).clear();
        driver.findElement(By.id("usernameOrEmail")).sendKeys("automation112021");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //ERROR: Caught exception [unknown command []]
    }

    @Tag("Login")
    @DisplayName("Log in and click-through test")
//    @ParameterizedTest(name = "Credentials u: {0}, p: <hidden>")
//    @CsvFileSource(resources = "/credentials.csv")
//    public void loginTest(String userName, String userPass) {
    public void loginTest() {

        driver.get(BASE_URL);
//        driver.get("https://wordpress.com/");
        wordpressMainPage = new WordpressMainPage(driver);
        wordpressMainPage.waitForPageLoaded(5);
        wordpressMainPage.loginLink.click();

        loginPage = new LoginPage(driver);
        loginPage.waitForPageLoaded(5);
        loginPage.supressGdprBanner();
        loginPage.enterUser(LOGIN);
        loginPage.enterPass(PASSWORD);

        mainUserPage = new MainUserPage(driver);
        mainUserPage.waitForPageLoaded(10);
        Assertions.assertTrue(mainUserPage.avatar.isDisplayed());
        Assertions.assertTrue(mainUserPage.avatar.isEnabled());
        mainUserPage.clickAvatar();

        myProfilePage = new MyProfilePage(driver);
        myProfilePage.waitForPageLoaded(5);
        Assertions.assertEquals(myProfilePage.getProfileName(), LOGIN);
        myProfilePage.toggleProfileCheckbox();
        myProfilePage.clickLogOutButton();
        myProfilePage.waitForPageLoaded(6);

    }

    @Disabled
    @Tag("Notification")
    @DisplayName("Notification")
    @Test
    public void notification() {
        driver.get("https://wordpress.com/");
        logIn();
        mainUserPage = new MainUserPage(driver);
        Assertions.assertTrue(mainUserPage.avatar.isDisplayed());

        driver.get("https://wordpress.com/me");

        myProfilePage = new MyProfilePage(driver);

        myProfilePage.notificationLabel.click();
        notificationPage = new NotificationPage(driver);

        Assertions.assertTrue(notificationPage.commentNotificationCheckBox.isSelected());
        Assertions.assertFalse(notificationPage.aveSettingsButton.isEnabled());
        notificationPage.commentNotificationCheckBox.click();

        Assertions.assertFalse(notificationPage.commentNotificationCheckBox.isSelected());
        Assertions.assertTrue(notificationPage.aveSettingsButton.isEnabled());

        notificationPage.commentNotificationCheckBox.click();
        Assertions.assertTrue(notificationPage.commentNotificationCheckBox.isSelected());
        Assertions.assertFalse(notificationPage.aveSettingsButton.isEnabled());

        myProfilePage.clickLogOutButton();


    }

    @Tag("Keys_Action")
    @DisplayName("Keys short")
    @Test
    public void kaysInteraction() {
        Actions action = new Actions(driver);
        action.sendKeys(Keys.chord(Keys.CONTROL, "R")).perform();
        action.sendKeys(Keys.chord(Keys.ESCAPE)).perform();
        action.sendKeys(Keys.chord(Keys.ENTER)).perform();


    }

    @Disabled
    @Tag("Keys_Action")
    @DisplayName("Simple action")
    @Test
    public void actionTest() {
        driver.navigate().to("https://wordpress.com");
        wordpressMainPage = new WordpressMainPage(driver);

        Actions action = new Actions(driver);

        action.moveToElement(wordpressMainPage.picture)
                .clickAndHold()
                .moveToElement(wordpressMainPage.startYourWebsite)
                .release();
        action.build().perform();

        action.moveToElement(wordpressMainPage.loginLink)
                .click();
        action.build().perform();



    }

    private void logIn() {
        wordpressMainPage = new WordpressMainPage(driver);
        wordpressMainPage.loginLink.click();

        loginPage = new LoginPage(driver);
        loginPage.enterUser("automation112021");
        loginPage.userContinueButton.click();
        loginPage.enterPass("Test112021");
        loginPage.loginButton.click();
    }

    @Tag("Scroll")
    @DisplayName("scroll")
    @Test
    public void pageScroll() {

        String contactUrl = "http://www.testdiary.com/training/selenium/selenium-test-page/";

        driver.navigate().to(contactUrl);

        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Open page in the same window")));

        int hyperlinkYCoordinate = driver.findElement(By.linkText("Open page in the same window")).getLocation().getY();
        int hyperlinkXCoordinate = driver.findElement(By.linkText("Open page in the same window")).getLocation().getX();



        JavascriptExecutor jsexecutor = (JavascriptExecutor) driver;
        jsexecutor.executeScript("window.scrollBy(" + hyperlinkXCoordinate + "," + hyperlinkYCoordinate + ")", "");


        (new WebDriverWait(driver, 100))
                .until(ExpectedConditions.elementToBeClickable(By.linkText("Open page in the same window")));

        driver.findElement(By.linkText("Open page in the same window")).click();
    }

    @Tag("Scroll")
    @Test
    void scrollIntoView(){
        driver.get("http://manos.malihu.gr/repository/custom-scrollbar/demo/examples/complete_examples.html");
        JavascriptExecutor je = (JavascriptExecutor) driver;

        WebElement element = driver.findElement(By.xpath("//*[@id=\"mCSB_9_container\"]/ul/li[4]/img"));

        je.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @Disabled
    @Tag("Alert")
    @DisplayName("alert")
    @Test
    public void popupHandler() {
        driver.switchTo().alert();
        driver.findElement(By.id("userId")).sendKeys("username");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.switchTo().alert().accept();
        driver.switchTo().alert().dismiss();
        driver.switchTo().defaultContent();

        String pageId = driver.getWindowHandle();
        driver.switchTo().window(pageId);

        String title = driver.getTitle();
        Assertions.assertEquals("title", title);
    }

    @Tag("Frame")
    @DisplayName("Frame")
    @Test
    public void frameTest(){
        String contactUrl = "http://www.testdiary.com/training/selenium/selenium-test-page/";
        driver.get(contactUrl);
        new WebDriverWait(driver, 10)

                .until(ExpectedConditions.visibilityOfElementLocated(By.name("testframe")));

        WebElement testframe = driver.findElement(By.name("testframe"));

        driver.switchTo().frame(testframe);

        String expectedFrameText = driver.findElement(By.id("testpagelink")).getText();

        String actualFrameText = "My frame text";
        if(actualFrameText.equals(expectedFrameText)){
            System.out.println("Found my frame text");
        }
        else {
            System.out.println("Did not find my frame text");
        }

        driver.switchTo().parentFrame();
    }

    @Tags({@Tag("Window"), @Tag("scroll")})
    @DisplayName("Window test")
    @Test
    public void windowTest() {

        String firstPageWindow = null;
        String secondWindow = null;

        String urlDiary = "http://www.testdiary.com/training/selenium/selenium-test-page/";

        String openWindow = "Open page in a new window";
        By openWindowLink = By.linkText(openWindow);

        driver.navigate().to(urlDiary);

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(openWindowLink));


        WebElement hyperlinkElement = driver.findElement(openWindowLink);

        firstPageWindow = driver.getWindowHandle();

        int hyperlinkElementYCoord = hyperlinkElement.getLocation().getY();
        int hyperlinkElementXCoord = hyperlinkElement.getLocation().getX();

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        jsExecutor.executeScript("window.scrollBy(" + hyperlinkElementXCoord + "," + hyperlinkElementYCoord + ")", "");

        wait.until(ExpectedConditions.elementToBeClickable(openWindowLink));

        hyperlinkElement.click();

        Set<String> windowHandles = driver.getWindowHandles();

        for (String window: windowHandles) {
            if (!firstPageWindow.equals(window)){
                secondWindow = window;
            }
        }

        driver.switchTo().window(secondWindow);

        System.out.println(secondWindow);
        System.out.println(firstPageWindow);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("testpagelink")));

        driver.switchTo().window(secondWindow).close();
        driver.switchTo().window(firstPageWindow);

        wait.until(ExpectedConditions.visibilityOfElementLocated(openWindowLink));


    }



}
