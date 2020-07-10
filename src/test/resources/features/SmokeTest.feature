@smoke_test
Feature: Smoke test

  Background: open login page and login as store manager
    Given user is on the login page
    Then user logs in as store manager

  Scenario: Verify dashboard page
    And user verifies that "Dashboard" page subtitle is displayed

  Scenario: Verify Manage Dashboards page
    And user navigates to "Dashboards" then to "Manage Dashboards"
    Then user verifies that "All Manage Dashboards" page subtitle is displayed

  Scenario: Verify Manage Vehicle page
    And user navigates to "Fleet" then to "Vehicles"
    Then user verifies that "All Cars" page subtitle is displayed

  Scenario: Verify Manage Accounts page
    And user navigates to "Customers" then to "Acco"
    Then user verifies that "All Accounts" page subtitle is displayed
