package com.blazedemo.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class BaseTest {
    protected static WebDriver driver;
    protected static Logger logger = LogManager.getLogger(BaseTest.class);
    protected static ExtentReports extent;
    protected static ExtentTest test;
    
    @BeforeSuite
    public void setupSuite() {
        logger.info("=== Starting Test Suite Execution ===");
        logger.info("Initializing Extent Reports");
        
        // Setup Extent Reports
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("BlazeDemo Test Automation Report");
        sparkReporter.config().setReportName("Test Execution Report");
        
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "BlazeDemo - Flight Booking");
        extent.setSystemInfo("Environment", "Test");
        extent.setSystemInfo("Tester", "Automation Team");
        extent.setSystemInfo("Browser", "Chrome");
        
        logger.info("Extent Reports initialized successfully");
        
        // Create directories for logs and screenshots
        createDirectory("test-output/logs");
        createDirectory("test-output/screenshots");
    }
    
    @BeforeMethod
    public void setup(java.lang.reflect.Method method) {
        logger.info("========================================");
        logger.info("Starting Test: " + method.getName());
        logger.info("========================================");
        
        test = extent.createTest(method.getName());
        test.log(Status.INFO, "Test started: " + method.getName());
        
        logger.info("Setting up ChromeDriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        
        logger.info("WebDriver initialized successfully");
        test.log(Status.INFO, "Browser launched successfully");
        
        logger.info("Navigating to BlazeDemo: https://blazedemo.com");
        driver.get("https://blazedemo.com");
        test.log(Status.INFO, "Navigated to: https://blazedemo.com");
        logger.info("Page loaded successfully");
    }
    
    @AfterMethod
    public void tearDown(ITestResult result) {
        logger.info("Starting teardown process");
        
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.error("Test FAILED: " + result.getName());
            logger.error("Failure reason: " + result.getThrowable());
            
            test.log(Status.FAIL, "Test Failed");
            test.log(Status.FAIL, result.getThrowable());
            
            // Capture screenshot on failure
            String screenshotPath = captureScreenshot(result.getName());
            if (screenshotPath != null) {
                try {
                    test.addScreenCaptureFromPath(screenshotPath);
                    logger.info("Screenshot captured: " + screenshotPath);
                } catch (Exception e) {
                    logger.error("Failed to attach screenshot: " + e.getMessage());
                }
            }
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            logger.info("Test PASSED: " + result.getName());
            test.log(Status.PASS, "Test Passed Successfully");
        } else if (result.getStatus() == ITestResult.SKIP) {
            logger.warn("Test SKIPPED: " + result.getName());
            test.log(Status.SKIP, "Test Skipped");
        }
        
        if (driver != null) {
            logger.info("Closing browser");
            driver.quit();
            test.log(Status.INFO, "Browser closed");
            logger.info("Browser closed successfully");
        }
        
        logger.info("========================================");
        logger.info("Test Completed: " + result.getName());
        logger.info("========================================\n");
    }
    
    @AfterSuite
    public void tearDownSuite() {
        logger.info("=== Test Suite Execution Completed ===");
        
        if (extent != null) {
            extent.flush();
            logger.info("Extent Report generated successfully");
        }
        
        logger.info("All tests completed");
    }
    
    // Utility method to capture screenshot
    protected String captureScreenshot(String testName) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = testName + "_" + timestamp + ".png";
            String screenshotPath = "test-output/screenshots/" + fileName;
            
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destination = new File(screenshotPath);
            FileUtils.copyFile(source, destination);
            
            logger.info("Screenshot saved: " + screenshotPath);
            return screenshotPath;
        } catch (IOException e) {
            logger.error("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
    
    // Utility method to create directory
    private void createDirectory(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
            logger.info("Created directory: " + path);
        }
    }
    
    // Helper method to log test steps
    protected void logStep(String message) {
        logger.info("STEP: " + message);
        test.log(Status.INFO, message);
    }
    
    // Helper method to log errors
    protected void logError(String message) {
        logger.error("ERROR: " + message);
        test.log(Status.FAIL, message);
    }
}
