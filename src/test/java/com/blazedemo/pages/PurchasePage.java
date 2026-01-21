package com.blazedemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PurchasePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static Logger logger = LogManager.getLogger(PurchasePage.class);
    
    // Locators
    private By pageTitle = By.tagName("h2");
    private By nameField = By.id("inputName");
    private By addressField = By.id("address");
    private By cityField = By.id("city");
    private By stateField = By.id("state");
    private By zipCodeField = By.id("zipCode");
    private By cardTypeDropdown = By.id("cardType");
    private By creditCardNumberField = By.id("creditCardNumber");
    private By creditCardMonthField = By.id("creditCardMonth");
    private By creditCardYearField = By.id("creditCardYear");
    private By nameOnCardField = By.id("nameOnCard");
    private By purchaseButton = By.cssSelector("input.btn.btn-primary");
    
    // Constructor
    public PurchasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    // Page Actions
    public boolean isPageLoaded() {
        try {
            WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
            boolean loaded = title.getText().contains("Your flight from");
            logger.info("Purchase page loaded: " + loaded);
            return loaded;
        } catch (Exception e) {
            logger.error("Purchase page not loaded");
            return false;
        }
    }
    
    public void fillPassengerInfo(String name, String address, String city, String state, String zipCode) {
        logger.info("Filling passenger information");
        
        WebElement nameEl = wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
        nameEl.clear();
        nameEl.sendKeys(name);
        
        WebElement addressEl = driver.findElement(addressField);
        addressEl.clear();
        addressEl.sendKeys(address);
        
        WebElement cityEl = driver.findElement(cityField);
        cityEl.clear();
        cityEl.sendKeys(city);
        
        WebElement stateEl = driver.findElement(stateField);
        stateEl.clear();
        stateEl.sendKeys(state);
        
        WebElement zipEl = driver.findElement(zipCodeField);
        zipEl.clear();
        zipEl.sendKeys(zipCode);
        
        logger.info("Passenger information filled");
    }
    
    public void fillPaymentInfo(String cardNumber, String month, String year, String nameOnCard) {
        logger.info("Filling payment information");
        
        WebElement cardNumberEl = driver.findElement(creditCardNumberField);
        cardNumberEl.clear();
        cardNumberEl.sendKeys(cardNumber);
        
        WebElement monthEl = driver.findElement(creditCardMonthField);
        monthEl.clear();
        monthEl.sendKeys(month);
        
        WebElement yearEl = driver.findElement(creditCardYearField);
        yearEl.clear();
        yearEl.sendKeys(year);
        
        WebElement nameEl = driver.findElement(nameOnCardField);
        nameEl.clear();
        nameEl.sendKeys(nameOnCard);
        
        logger.info("Payment information filled");
    }
    
    public void clickPurchaseFlight() {
        logger.info("Clicking Purchase Flight button");
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(purchaseButton));
        button.click();
    }
    
    public void completePurchase(String name, String address, String city, String state, String zipCode,
                                 String cardNumber, String month, String year, String nameOnCard) {
        fillPassengerInfo(name, address, city, state, zipCode);
        fillPaymentInfo(cardNumber, month, year, nameOnCard);
        clickPurchaseFlight();
    }
}
