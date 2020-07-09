@start
Feature: Android Register

  Scenario: Start Test Application
    Given Start the application
    Then I click the register button

  Scenario: Click Register Button
    Given Start the application
    When I click the register button
    Then Wait 5 second
    Then I see the register button