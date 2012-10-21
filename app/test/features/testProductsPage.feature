Feature: View Products
  As an unregistered user
  I want to see what products are available
  So that I can add items to my shopping cart

  Background:
    Given the application is running
    And there is a mocked soap service running on port '10001'
    And the mocked products service has the following information
    """
    {
      "Product" : "test"
    }
    """

  Scenario: Logging in
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