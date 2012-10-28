Feature: View Products
  As an unregistered user
  I want to see what products are available
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
    Given I am on the home page
    And I am not logged in
    When I click the 'Login' link
    Then I will be taken to the 'Login' page
    When I log in with the following details
      | email | password |
      | foo   | bar      |
    Then I will be taken to the 'Shopping Cart' page
    And the page says 'Welcome foo to your shopping cart Empty Shopping Cart'

  Scenario: Adding an item to a shopping cart
    Given I am on the products page
    When I click the add to cart button for 'Chicken'
    Then my shopping cart will look like the following
      | Name    | Amount |
      | Chicken | 1      |
