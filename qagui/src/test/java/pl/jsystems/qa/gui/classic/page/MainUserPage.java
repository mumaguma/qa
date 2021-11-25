package pl.jsystems.qa.gui.classic.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainUserPage extends BasePage {

//    public WebElement avatar = driver.findElement(By.className("gravatar"));
    public WebElement avatar = driver.findElement(By.cssSelector(".masterbar__item-me[href='/me']"));

    public MainUserPage(WebDriver driver) {
        super(driver);
    }

    public void clickAvatar() {
//        if (isThisMainUserPage()) {
            elementToBeClickable(this.avatar, 5);
            click(this.avatar, 5);
//        } else {
//            System.out.printf("\nSeems we've landed not on My Home, but on " + driver.getTitle() + " page.");
//        }
    }

//    public boolean isThisMainUserPage() {
//        return driver.getCurrentUrl().equals("https://wordpress.com/home/szkolenie202111.wordpress.com");
//    }


}
