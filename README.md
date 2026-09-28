# Selenium Test Automation Framework

A hybrid test automation framework built using Java, Selenium WebDriver,
TestNG and Page Object Model (POM).

## Technologies Used

- Java
- Selenium WebDriver 4.x
- TestNG
- Maven
- Page Object Model (POM)
- ExtentReports
- Log4j2
- Git & GitHub
- Apache POI / Excel test data

## Test Coverage

- Account Registration
- Login
- Product Search
- Registration Validation


## Framework Structure

```text
src/test/java
├── pageObjects
├── testCases
├── testBase
├── utils
└── listeners

testData
└── EmailData.xlsx
└──testdatahybrid.xlsx


Page Objects

Contains reusable page-specific methods and locators.

Test Cases

Contains TestNG test classes.

Test Base

Contains WebDriver setup, browser configuration and common methods.

Utils

Contains reusable utilities and DataProviders.

Listeners

Contains ExtentReports/TestNG listener implementation.

Test Data

Contains external test data used by the automation tests.

Reporting

ExtentReports is used for test execution reporting.

Log4j2 is used for execution logging.

Test Execution

Tests can be executed using TestNG or Maven.