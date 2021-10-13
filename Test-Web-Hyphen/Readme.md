# Hyphen Selenium Java Test Framework

Response to Hyphen tech assessment, as part of assessment for role of Lead Software Test Engineer

## The key components of the framework, in the sequence -
* config.properties - local execution browser, application url, API' endpoints
* DriverFactory - DriverManager control, setup, setDriver, getDriver, teardown, properties methods
* Features - Test written in BDD Feature/Scenarios
* Step Definition - Glue code for scenarios
* Page Object - UI elements of the pages
* Test Runner - runner file to trigger tests


## Pre-requisite
- Java8, Maven3.6.3 (minimum)
- Dependencies in pom.xml

## To Execution on local
- `mvn clean test`
( *For efficient execution in CI - framework could be dockerized with Selenium Grid implementations)

## Reports verification
- Navigate to - target/Cucumber - open overview-features.html in browser

## Notes & Observations
- Due to time constrain only 3 tests implement
- UI tests using selenium-java & api validating using Rest-assured
- Test failures for -
  - duplicate movie title on different pages
  - on some occasion, movie title on UI is not as per movie title as per api
- Observations, when click on back to movie, user lands on 1st page, irrespective of his previous page.

## Benefits
A loosely couples Selenium-Java framework build from scratch to verify & validate UI & API tests for MovieChill test assignment.
All components of framework execute independently, DriverFactory is responsible to initialize singleton driver per scenario - exact replication of business scenario.
Taking on demand screenshots is possible using DriverManager.embedScreenShot(filename) & save file in root.Pass_Screenshot, 
for failure cases - @After hook will take screenshot & attach to Cucumber report.
