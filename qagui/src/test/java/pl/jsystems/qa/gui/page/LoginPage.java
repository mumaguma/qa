package pl.jsystems.qa.gui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public WebElement usernameInput = driver.findElement(By.id("usernameOrEmail"));
    public WebElement passInput = driver.findElement(By.id("password"));
    public WebElement userContinueButton = driver.findElement(By.className("login__form-action"));
    public WebElement loginButton = driver.findElement(By.className("login__form-action"));
    public WebElement gdprBannerButton = driver.findElement(By.className("gdpr-banner__acknowledge-button"));

    public void enterUser(String userName) {
        visibilityOfElementLocated(By.id("usernameOrEmail"), 3);
        this.usernameInput.click();
        this.usernameInput.clear();
        this.usernameInput.sendKeys(userName);
        this.userContinueButton.click();
    }

    public void enterPass(String userPass) {
        visibilityOfElementLocated(By.id("password"), 3);
        this.passInput.click();
        this.passInput.clear();
        this.passInput.sendKeys(userPass);
        this.loginButton.click();
    }

    public void supressGdprBanner() {
        if(gdprBannerButton.isDisplayed()){
            gdprBannerButton.click();
        }
    }

}