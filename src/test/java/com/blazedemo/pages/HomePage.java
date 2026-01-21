package com.blazedemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static Logger logger = LogManager.getLogger(HomePage.class);
    
    // Locators
    private By pageTitle = By.tagName("h1");
    private By departureDropdown = By.name("fromPort");
    private By destinationDropdown = By.name("toPort");
    private By findFlightsButton = By.cssSelector("input.btn.btn-primary");
    
    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    // Page Actions
    public boolean isPageLoaded() {
        try {
            WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
            boolean loaded = title.isDisplayed();
            logger.info("Home page loaded: " + loaded);
            return loaded;
        } catch (Exception e) {
            logger.error("Home page not loaded");
            return false;
        }
    }
    
    public String getPageTitle() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
        String titleText = title.getText();
        logger.info("Page title: " + titleText);
        return titleText;
    }
    
    public void selectDepartureCity(String city) {
        logger.info("Selecting departure city: " + city);
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(departureDropdown));
        Select select = new Select(dropdown);
        select.selectByVisibleText(city);
    }
    
    public void selectDestinationCity(String city) {
        logger.info("Selecting destination city: " + city);
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(destinationDropdown));
        Select select = new Select(dropdown);
        select.selectByVisibleText(city);
    }
    
    public void clickFindFlights() {
        logger.info("Clicking Find Flights button");
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(findFlightsButton));
        button.click();
    }
    
    public void searchFlights(String from, String to) {
        logger.info("Searching flights from " + from + " to " + to);
        selectDepartureCity(from);
        selectDestinationCity(to);
        clickFindFlights();
    }
}
