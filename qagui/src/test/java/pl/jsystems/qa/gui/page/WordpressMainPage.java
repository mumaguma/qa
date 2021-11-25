package pl.jsystems.qa.gui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WordpressMainPage extends BasePage {

    public WordpressMainPage(WebDriver driver) {
        super(driver);
    }

    public WebElement loginLink = driver.findElement(By.linkText("Log In"));
    public WebElement cookieBannerAcceptButton = driver.findElement(By.className("a8c-cookie-banner-accept-all-button"));

    public void waitForLoginLink() {
        cookieBannerAcceptButton.click();
        elementToBeClickable(loginLink, 5);
    }

    @FindBy(css = "#lpc-picture\\.3 > div")
    public WebElement picture;

    @FindBy(id = "lpc-button")
    public WebElement startYourWebsite;


}