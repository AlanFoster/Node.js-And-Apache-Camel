@ignore
Feature: View website
  As an unregistered user
  I want to see what products are available
  So that I can add items to my shopping cart

  Background:
    Given the application is running
    # And there is a mocked service running
    # And there is a customer with the following details

  Scenario: View Homepage
    Given I am on the home page
    And the title says 'Home'
    Then I shall be happy

  Scenario: Logging in
    Given I am on the home page
    And I am not logged in
    When I click the 'Login' link
    Then I will be taken to the 'Login' page
    When I log in with the following details
      | email | password |
      | foo   | bar      |
    Then I will be taken to the 'Shopping Cart' page
    And the page says 'Welcome foo to your shopping cart Empty Shopping Cart'

  Scenario: Correct Username And Wrong Passowrd
    Given I am on the home page
    And I am not logged in
    When I click the 'Login' link
    Then I will be taken to the 'Login' page
    When I log in with the following details
      | email | password      |
      | foo   | wrongPassword |
    Then I will not be logged in
    And the page says 'Error! The supplied credentials were invalid!'

  Scenario: Wrong Username And Wrong Passowrd
    Given I am on the home page
    And I am not logged in
    When I click the 'Login' link
    Then I will be taken to the 'Login' page
    When I log in with the following details
      | email | password      |
      | wrongUser   | wrongPassword |
    Then I will not be logged in
    And the page says 'Error! The supplied credentials were invalid!'