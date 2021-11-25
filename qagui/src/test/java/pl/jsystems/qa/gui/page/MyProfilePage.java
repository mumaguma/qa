package pl.jsystems.qa.gui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyProfilePage extends BasePage{

    public MyProfilePage(WebDriver driver) {
        super(driver);
    }

    By profileNameLocator =  By.className("profile-gravatar__user-display-name");
    public WebElement profileName = driver.findElement(profileNameLocator);
    public WebElement logoutButton = driver.findElement(By.className("sidebar__me-signout-button"));
    public WebElement hideProfileCheckbox = driver.findElement(By.className("components-form-toggle"));

     public String getProfileName() {
         visibilityOfElementLocated(profileNameLocator, 5);
         return profileName.getText();
     }

     public void clickLogOutButton() {
         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", logoutButton);
         elementToBeClickable(logoutButton, 5);
         click(logoutButton, 5);
     }

     public boolean isHideProfileCheckboxOn() {
         return hideProfileCheckbox.getAttribute("class").contains("is-checked");
     }

     public void toggleProfileCheckbox() {
         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", hideProfileCheckbox);
         hideProfileCheckbox.click();
     }

    public void ensureProfileCheckboxOn() {
         if (!isHideProfileCheckboxOn()) {
             toggleProfileCheckbox();
         }
    }

    public void ensureProfileCheckboxOff() {
        if (isHideProfileCheckboxOn()) {
            toggleProfileCheckbox();
        }
    }

    @FindBy(css = "a[href=\"/me/notifications\"]")
    public WebElement notificationLabel;

}
