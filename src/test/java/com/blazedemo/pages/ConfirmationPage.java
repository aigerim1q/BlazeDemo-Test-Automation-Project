package com.blazedemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConfirmationPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static Logger logger = LogManager.getLogger(ConfirmationPage.class);
    
    // Locators
    private By pageTitle = By.tagName("h1");
    private By confirmationMessage = By.cssSelector("p");
    
    // Constructor
    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    // Page Actions
    public boolean isPageLoaded() {
        try {
            WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
            boolean loaded = title.getText().contains("Thank you");
            logger.info("Confirmation page loaded: " + loaded);
            return loaded;
        } catch (Exception e) {
            logger.error("Confirmation page not loaded");
            return false;
        }
    }
    
    public String getConfirmationTitle() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
        String titleText = title.getText();
        logger.info("Confirmation title: " + titleText);
        return titleText;
    }
    
    public boolean isConfirmationDisplayed() {
        try {
            WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
            boolean displayed = title.getText().toLowerCase().contains("thank you");
            logger.info("Confirmation displayed: " + displayed);
            return displayed;
        } catch (Exception e) {
            logger.error("Confirmation not displayed");
            return false;
        }
    }
}
