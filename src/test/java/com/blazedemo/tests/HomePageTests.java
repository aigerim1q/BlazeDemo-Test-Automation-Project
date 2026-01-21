package com.blazedemo.tests;

import com.blazedemo.base.BaseTest;
import com.blazedemo.pages.HomePage;
import com.blazedemo.pages.ReservePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends BaseTest {
    
    @Test(priority = 1, description = "Verify Home Page loads successfully")
    public void testHomePageLoad() {
        logStep("Test Case: Home Page Load Verification");
        logStep("Step 1: Initialize Home Page");
        HomePage homePage = new HomePage(driver);
        
        logStep("Step 2: Verify page is loaded");
        boolean isLoaded = homePage.isPageLoaded();
        
        logger.info("Expected Result: Home page should load successfully");
        logger.info("Actual Result: Home page loaded = " + isLoaded);
        
        Assert.assertTrue(isLoaded, "Home page failed to load");
        logStep("Test Passed: Home page loaded successfully");
    }
    
    @Test(priority = 2, description = "Verify flight search functionality")
    public void testFlightSearch() {
        logStep("Test Case: Flight Search Functionality");
        logStep("Step 1: Initialize Home Page");
        HomePage homePage = new HomePage(driver);
        
        logStep("Step 2: Select departure city: Paris");
        homePage.selectDepartureCity("Paris");
        
        logStep("Step 3: Select destination city: Buenos Aires");
        homePage.selectDestinationCity("Buenos Aires");
        
        logStep("Step 4: Click Find Flights button");
        homePage.clickFindFlights();
        
        logStep("Step 5: Verify Reserve page is loaded");
        ReservePage reservePage = new ReservePage(driver);
        boolean isReservePageLoaded = reservePage.isPageLoaded();
        
        logger.info("Expected Result: User should be redirected to Reserve page");
        logger.info("Actual Result: Reserve page loaded = " + isReservePageLoaded);
        
        Assert.assertTrue(isReservePageLoaded, "Failed to navigate to Reserve page");
        logStep("Test Passed: Flight search worked correctly");
    }
    
    @Test(priority = 3, description = "Verify page title contains expected text")
    public void testPageTitle() {
        logStep("Test Case: Home Page Title Verification");
        logStep("Step 1: Initialize Home Page");
        HomePage homePage = new HomePage(driver);
        
        logStep("Step 2: Get page title");
        String pageTitle = homePage.getPageTitle();
        
        logger.info("Expected Result: Title should contain 'Welcome to the Simple Travel Agency!'");
        logger.info("Actual Result: Page title = " + pageTitle);
        
        Assert.assertTrue(pageTitle.contains("Welcome"), "Page title is incorrect");
        logStep("Test Passed: Page title verified successfully");
    }
}
