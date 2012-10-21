@ignore
Feature: Test Soap Service
  As an unsure developer
  I want to consume and produce soap services
  So that I can write a great application

  Test the basic functionality of a soap webservice and whether or not it works

  Scenario: Testing Waters
    Given there is a mocked soap service running on port '10001'
    And there is a basic mock service running
    And there is a valid generated wsdl
    When I call the 'GetProduct' soap service with the product id '1'
    Then I get valid product details back

  @ignore
  Scenario: Testing using json in steps
    When I test cucumber.js with the following json
    """
        {
          "foo" : "bar"
        }
      """
    Then the key foo shall equal bar