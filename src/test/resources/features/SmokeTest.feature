
@smoke_test #when placed here automatically applies to all scenarios
Feature: Smoke Test

Background: open login page and login as store manager
  Given user is on the login page
  Then user logs in as store manager

  Scenario: Verify dashboard page
    And user verifies that "Dashboard" page subtitle is displayed

    Scenario: Verify  Manage Dashboard page

      When user navigates to "Dashboards" then to "Manage Dashboards"
      And user verifies that "All Manage Dashboards" page subtitle is displayed
