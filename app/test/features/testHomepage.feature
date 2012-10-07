Feature: View website
  As an unregistered user
  I want to see what products are available
  So that I can add items to my shopping cart

  Scenario: View Homepage
    Given I am on the home page
    And the title says 'Home'
    Then I shall be happy