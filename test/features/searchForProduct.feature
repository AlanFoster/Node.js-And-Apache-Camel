@ignore
Feature: Search For Products
  As an unregistered user
  I want to search for a specific item
  So that I can add items to my shopping cart

  Background:
    Given the application is running
    And there is a mocked soap service running on port '10001'
    And the mocked soap service will return the following information when the 'GetAllProducts' operation is called
    """
      {
          "Products": {
              "Product": [
                  {
                      "productId": "1",
                      "name": "Cheese",
                      "description": "The finest cheese ever",
                      "price": "1.50"
                  },
                  {
                      "productId": "2",
                      "name": "Beer",
                      "description": "Lovely Beer",
                      "price": "2.30"
                  },
                  {
                      "productId": "3",
                      "name": "Ramen",
                      "description": "Awesome Ramen",
                      "price": "1.19"
                  },
                  {
                      "productId": "4",
                      "name": "Pizza",
                      "description": "Pizza pizza",
                      "price": "2.99"
                  },
                  {
                      "productId": "5",
                      "name": "Chicken",
                      "description": "Chicken Description",
                      "price": "3.99"
                  }
              ]
          }
      }
    """

  Scenario: Searching for a product with multiple results
    Given I am on the products page
    When I click the products search bar and input 'Ch'
    Then the products page will show the following information
      | # | Product Name | Description            | Price | Order       |
      | 1 | Cheese       | The finest cheese ever | £1.50 | Add To Cart |
      | 5 | Chicken      | Chicken Description    | £3.99 | Add To Cart |
    #And the page says '2 items matched this name'

  Scenario: Searching for a product with one result
    Given I am on the products page
    When I click the products search bar and input 'Chi'
    Then the products page will show the following information
      | # | Product Name | Description         | Price | Order       |
      | 5 | Chicken      | Chicken Description | £3.99 | Add To Cart |
    #And the page says '1 item matched this name'


  Scenario: Searching for a product with no results
    Given I am on the products page
    When I click the products search bar and input 'Chie'
    Then the products page will show the following information
      | # | Product Name | Description | Price | Order |
    #And the page says 'No results found'