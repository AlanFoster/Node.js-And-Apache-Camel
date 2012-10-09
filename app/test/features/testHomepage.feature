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
    When I click the 'Login' link
    Then I will be taken to the 'Login' page
    When I log in with the following details
      | email | password |
      | foo   | bar      |
    Then I will be taken to the 'Shopping Cart' page
    And the page says 'Welcome foo to your shopping card Empty Shopping Cart'
