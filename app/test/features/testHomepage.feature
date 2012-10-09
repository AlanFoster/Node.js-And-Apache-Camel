Feature: View website
  As an unregistered user
  I want to see what products are available
  So that I can add items to my shopping cart

  Background:
    Given the application is running

  Scenario: View Homepage
    Given I am on the home page
    And the title says 'Home'
    Then I shall be happy

  Scenario: Logging in
    Given I am not logged in
    And I am on the home page
    When I log in with the following details
      | Name     | Value |
      | email    | foo   |
      | password | bar   |
    Then I will be logged in
    And the page says 'welcome foo to your shopping cart'
    And I shall be happyl
