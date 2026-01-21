# BlazeDemo-Test-Automation-Project

## Project Overview
Automated test cases for BlazeDemo flight booking website (https://blazedemo.com) using Selenium WebDriver, TestNG, Log4j, and Extent Reports.

## Technologies Used
- Java 11
- Selenium WebDriver 4.16.1
- TestNG 7.8.0
- Log4j 2.21.1
- Extent Reports 5.1.1
- Maven

## Project Structure
```
blazedemo-automation/
├── src/test/java/com/blazedemo/
│   ├── base/BaseTest.java
│   ├── pages/
│   │   ├── HomePage.java
│   │   ├── ReservePage.java
│   │   ├── PurchasePage.java
│   │   └── ConfirmationPage.java
│   └── tests/
│       ├── HomePageTests.java
│       └── BookingFlowTests.java
├── test-output/
│   ├── ExtentReport.html
│   ├── logs/automation.log
│   └── screenshots/
├── pom.xml
├── testng.xml
└── README.md
```

## Prerequisites
1. Java JDK 11+
2. Apache Maven 3.6+
3. Google Chrome Browser

## Installation & Setup
```bash
# Install dependencies
mvn clean install
```

## Running Tests
```bash
# Run all tests
mvn clean test

# Run specific test class
mvn test -Dtest=HomePageTests
mvn test -Dtest=BookingFlowTests
```

## Test Cases
### Home Page Tests (3 tests)
1. **testHomePageLoad** - Verify home page loads successfully
2. **testFlightSearch** - Verify flight search functionality
3. **testPageTitle** - Verify page title

### Booking Flow Tests (3 tests)
1. **testFlightSelection** - Verify flight selection and navigation
2. **testCompleteBookingFlow** - Complete end-to-end booking
3. **testConfirmationMessage** - Verify confirmation page

## Test Reports
- **HTML Report**: `test-output/ExtentReport.html`
- **Logs**: `test-output/logs/automation.log`
- **Screenshots**: `test-output/screenshots/` (on failures)

## Framework Features
✅ TestNG lifecycle management (setup/teardown)
✅ Log4j logging to file
✅ HTML test reports with Extent Reports
✅ Automatic screenshots on failure
✅ Page Object Model design pattern


