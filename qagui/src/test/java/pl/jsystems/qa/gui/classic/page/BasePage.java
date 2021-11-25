package pl.jsystems.qa.gui.classic.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void visibilityOfElementLocated(By by, int maxTime) {
        Wait wait = new WebDriverWait(this.driver, Duration.ofSeconds(maxTime));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void elementToBeClickable(WebElement webElement, int maxTime) {
        Wait wait = new WebDriverWait(this.driver, Duration.ofSeconds(maxTime));
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void click(WebElement webElement, int maxTime){
        elementToBeClickable(webElement, maxTime);
        webElement.click();
    }

    public void click(By by, int maxTime){
        elementToBeClickable(driver.findElement(by), maxTime);
        driver.findElement(by).click();
    }

    public void waitForPageLoaded(int maxTime){
        try {
            Thread.sleep(1000);
        } catch (Exception e){

        }
        Wait wait = new WebDriverWait(this.driver, Duration.ofSeconds(maxTime));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

}