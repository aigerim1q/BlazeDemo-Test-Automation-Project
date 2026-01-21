package com.blazedemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ReservePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static Logger logger = LogManager.getLogger(ReservePage.class);
    
    // Locators
    private By pageTitle = By.tagName("h3");
    private By flightRows = By.cssSelector("table.table tbody tr");
    private By chooseFlightButtons = By.cssSelector("input.btn.btn-small");
    
    // Constructor
    public ReservePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    // Page Actions
    public boolean isPageLoaded() {
        try {
            WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
            boolean loaded = title.getText().contains("Flights");
            logger.info("Reserve page loaded: " + loaded);
            return loaded;
        } catch (Exception e) {
            logger.error("Reserve page not loaded");
            return false;
        }
    }
    
    public int getFlightCount() {
        List<WebElement> flights = driver.findElements(flightRows);
        int count = flights.size();
        logger.info("Number of flights available: " + count);
        return count;
    }
    
    public void selectFirstFlight() {
        logger.info("Selecting first flight");
        List<WebElement> buttons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(chooseFlightButtons));
        if (!buttons.isEmpty()) {
            buttons.get(0).click();
            logger.info("First flight selected");
        }
    }
    
    public void selectFlightByIndex(int index) {
        logger.info("Selecting flight at index: " + index);
        List<WebElement> buttons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(chooseFlightButtons));
        if (index < buttons.size()) {
            buttons.get(index).click();
            logger.info("Flight at index " + index + " selected");
        }
    }
}
