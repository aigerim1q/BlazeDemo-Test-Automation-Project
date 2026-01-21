package com.blazedemo.tests;

import com.blazedemo.base.BaseTest;
import com.blazedemo.pages.ConfirmationPage;
import com.blazedemo.pages.HomePage;
import com.blazedemo.pages.PurchasePage;
import com.blazedemo.pages.ReservePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookingFlowTests extends BaseTest {
    
    @Test(priority = 1, description = "Verify flight selection and purchase page navigation")
    public void testFlightSelection() {
        logStep("Test Case: Flight Selection");
        
        logStep("Step 1: Search for flights");
        HomePage homePage = new HomePage(driver);
        homePage.searchFlights("Boston", "London");
        
        logStep("Step 2: Verify Reserve page loaded");
        ReservePage reservePage = new ReservePage(driver);
        Assert.assertTrue(reservePage.isPageLoaded(), "Reserve page not loaded");
        
        logStep("Step 3: Verify flights are available");
        int flightCount = reservePage.getFlightCount();
        
        logger.info("Expected Result: At least 1 flight should be available");
        logger.info("Actual Result: " + flightCount + " flights available");
        
        Assert.assertTrue(flightCount > 0, "No flights available");
        
        logStep("Step 4: Select first flight");
        reservePage.selectFirstFlight();
        
        logStep("Step 5: Verify Purchase page loaded");
        PurchasePage purchasePage = new PurchasePage(driver);
        boolean isPurchasePageLoaded = purchasePage.isPageLoaded();
        
        logger.info("Expected Result: Purchase page should be loaded");
        logger.info("Actual Result: Purchase page loaded = " + isPurchasePageLoaded);
        
        Assert.assertTrue(isPurchasePageLoaded, "Purchase page not loaded");
        logStep("Test Passed: Flight selection successful");
    }
    
    @Test(priority = 2, description = "Verify complete booking flow end-to-end")
    public void testCompleteBookingFlow() {
        logStep("Test Case: Complete Booking Flow (End-to-End)");
        
        logStep("Step 1: Search for flights from Portland to Dublin");
        HomePage homePage = new HomePage(driver);
        homePage.searchFlights("Portland", "Dublin");
        
        logStep("Step 2: Select a flight");
        ReservePage reservePage = new ReservePage(driver);
        reservePage.selectFirstFlight();
        
        logStep("Step 3: Fill purchase form");
        PurchasePage purchasePage = new PurchasePage(driver);
        purchasePage.completePurchase(
            "John Smith",
            "123 Main Street",
            "New York",
            "NY",
            "10001",
            "4111111111111111",
            "12",
            "2026",
            "John Smith"
        );
        
        logStep("Step 4: Verify confirmation page");
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        boolean isConfirmed = confirmationPage.isConfirmationDisplayed();
        
        logger.info("Expected Result: Booking should be confirmed");
        logger.info("Actual Result: Confirmation displayed = " + isConfirmed);
        
        Assert.assertTrue(isConfirmed, "Booking confirmation failed");
        logStep("Test Passed: Complete booking flow successful");
    }
    
    @Test(priority = 3, description = "Verify confirmation page displays success message")
    public void testConfirmationMessage() {
        logStep("Test Case: Confirmation Message Verification");
        
        logStep("Step 1: Complete booking flow");
        HomePage homePage = new HomePage(driver);
        homePage.searchFlights("Paris", "Berlin");
        
        ReservePage reservePage = new ReservePage(driver);
        reservePage.selectFirstFlight();
        
        PurchasePage purchasePage = new PurchasePage(driver);
        purchasePage.completePurchase(
            "Jane Doe",
            "456 Oak Avenue",
            "Los Angeles",
            "CA",
            "90001",
            "5500000000000004",
            "11",
            "2027",
            "Jane Doe"
        );
        
        logStep("Step 2: Verify confirmation title");
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        String confirmationTitle = confirmationPage.getConfirmationTitle();
        
        logger.info("Expected Result: Confirmation title should contain 'Thank you'");
        logger.info("Actual Result: Title = " + confirmationTitle);
        
        Assert.assertTrue(confirmationTitle.contains("Thank you"), "Confirmation message not displayed correctly");
        logStep("Test Passed: Confirmation message verified");
    }
}
