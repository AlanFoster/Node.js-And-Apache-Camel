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


  Scenario: Viewing All Products
    Given I am on the home page
    And I am not logged in
    When I click the 'View All Products' link
    Then I will be taken to the 'Products' page
    And the products page will show the following information
      | # | Product Name | Description            | Price | Order       |
      | 1 | Cheese       | The finest cheese ever | £1.50 | Add To Cart |
      | 2 | Beer         | Lovely Beer            | £2.30 | Add To Cart |
      | 3 | Ramen        | Awesome Ramen          | £1.19 | Add To Cart |
      | 4 | Pizza        | Pizza pizza            | £2.99 | Add To Cart |
      | 5 | Chicken      | Chicken Description    | £3.99 | Add To Cart |