@calendar_events
Feature: All calendar events

  Scenario: Verify column names
    Given user is on the login page
    And user logs in as store manager
    Then user navigates to "Activities" then to "Calendar Events"
    And user verifies that column names are displayed
      | TITLE             |
      | CALENDAR          |
      | START             |
      | END               |
      | RECURRENT         |
      | RECURRENCE        |
      | INVITATION STATUS |
  @HW_4_1
  Scenario: Verify "view2 "edit" and "delete" options
    Given user is on the login page
    And user logs in as store manager
    Then user navigates to "Activities" then to "Calendar Events"
    Then hover on tree dots on tester meeting
    And user verifies "View" , "Edit" , "Delete" options displayed
  @HW_4_2
  Scenario: Verify that Title column is displayed
    Given user is on the login page
    And  user logs in as store manager
    Then user navigates to "Activities" then to "Calendar Events"
    And user clicks on Grid Option button
    Then desellect all option except Title
    And  Verify that Title column is displayed
  @HW_4_3
  Scenario: Verify save and close options available
    Given user is on the login page
    Then user logs in as store manager
    Then user navigates to "Activities" then to "Calendar Events"
    And  clicks on "Create Calendar Event" button
    Then  user click Save And close button
    And user verifies that following statements are available
      |Save And Close|
      |Save And New  |
      |Save          |
  @HW_4_4
  Scenario: Verify aal calendar eevent page subtitle
    Given user is on the login page
    Then user logs in as store manager
    Then user navigates to "Activities" then to "Calendar Events"
    And  clicks on "Create Calendar Event" button
    Then  user click Cancel button
    And user verifies "All Calendar Events" page subtitle is displayed

  @HW_4_5
  Scenario: Verify time is one hour
    Given user is on the login page
    Then user logs in as store manager
    Then user navigates to "Activities" then to "Calendar Events"
    And  clicks on "Create Calendar Event" button
    Then user should see difference is one hour