Feature: Test Soap Service
  As an unsure developer
  I want to consume and produce soap services
  So that I can write a great application


  Scenario: Testing Waters
    Given there is a mocked soap service running on port '10000'
    And there is a valid generated wsdl
    When I call the 'GetProduct' soap service with the product id '123'
    Then I get valid product details back