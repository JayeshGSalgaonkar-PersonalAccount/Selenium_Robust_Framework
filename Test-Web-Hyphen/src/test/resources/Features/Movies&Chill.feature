Feature: Test Movies & Chills

  Background: Open browser, verify server is up
    Given the server is running, Movies & Chills homepage is displayed

  Scenario: Verify web access, API level validation - total pages, results & movies every page
    When validate total pages & total results

  Scenario: Validate movies displayed on every page
    When validate movies displayed on every page

  Scenario: check for duplicate movies in total number of pages
    When check for duplicate movies in total number of pages

  Scenario: Validate description of every movie
    When user is able to click on selective movie for description
    Then movie details page is open with Runtime, Overview & Top Billed Cast details (only five allowed)
    And user is able to navigate back to movie list of same page (error observed, user is taken to first page)

  Scenario: Verify cast details
    When user clicks on cast name
    Then cast details - Name, Biography should be displayed, with section Known For - listing more movies
    And user is able to navigate back to same movie

  Scenario: Validate user navigation
    When user is able to navigate between pages
    Then user is able to navigate between different page slots