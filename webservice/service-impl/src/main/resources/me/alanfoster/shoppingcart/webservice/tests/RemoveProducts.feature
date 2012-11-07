Feature: Removing Products to a customer's shopping cart
  As an Api user
  I want to be able to remove products from a customer's account
  In order to keep track of what a customer wants to order

  Background:
    Given the Api has the following list of customers with empty shopping carts
      | CustomerId | Email        | Password      |
      | 1          | alan@foo.com | rawTextForNow |
    And the Api has the following products
      | ProductId | Name                | Description       | Price |
      | 1         | Duracell AA Battery | Lasts longer      | 4     |
      | 2         | Milk                | Only the freshest | 0.89  |
      | 3         | Eggs                | Free Range        | 1.19  |
      | 4         | Bread               | 3 day life        | 1.20  |
    When I call the addProductToCustomerAccount operation with the following information
      | CustomerId | ProductId | Quantity |
      | 1          | 2         | 2        |
    Then there will be no errors given in the response
    When I call the addProductToCustomerAccount operation with the following information
      | CustomerId | ProductId | Quantity |
      | 1          | 3         | 2        |
    Then there will be no errors given in the response
    And the returned customer shall have the following core information
      | CustomerId | Email        | Password      |
      | 1          | alan@foo.com | rawTextForNow |
    And the returned customer shall have the following shopping cart
      | ProductId | Name | Description       | Price | Quantity |
      | 2         | Milk | Only the freshest | 0.89  | 2        |
      | 3         | Eggs | Free Range        | 1.19  | 2        |
      
  Scenario: Removing an item from the customer's shopping cart
    When I call the removeProductFromCustomerAccount operation with the following information
      | CustomerId | ProductId | Quantity |
      | 1          | 2         | 1        |
    Then there will be no errors given in the response
    And the returned customer shall have the following core information
      | CustomerId | Email        | Password      |
      | 1          | alan@foo.com | rawTextForNow |
    And the returned customer shall have the following shopping cart
      | ProductId | Name | Description       | Price | Quantity |
      | 2         | Milk | Only the freshest | 0.89  | 1        |
      | 3         | Eggs | Free Range        | 1.19  | 2        |
      
  Scenario: Removing the last item from the customer's shopping cart with a different product remaining
    When I call the removeProductFromCustomerAccount operation with the following information
      | CustomerId | ProductId | Quantity |
      | 1          | 2         | 2        |
    Then there will be no errors given in the response
    And the returned customer shall have the following core information
      | CustomerId | Email        | Password      |
      | 1          | alan@foo.com | rawTextForNow |
    And the returned customer shall have the following shopping cart
      | ProductId | Name | Description       | Price | Quantity |
      | 3         | Eggs | Free Range        | 1.19  | 2        |
	
  Scenario: Removing all of the items from the customer's shopping cart with no products remaining
    When I call the removeProductFromCustomerAccount operation with the following information
      | CustomerId | ProductId | Quantity |
      | 1          | 2         | 2        |
    Then there will be no errors given in the response
    And I call the removeProductFromCustomerAccount operation with the following information
      | CustomerId | ProductId | Quantity |
      | 1          | 3         | 2        |
    Then there will be no errors given in the response
    And the returned customer shall have the following core information
      | CustomerId | Email        | Password      |
      | 1          | alan@foo.com | rawTextForNow |
	And the returned customer shall have no shopping cart
 
  Scenario: Trying to remove more products than the customer currently has
  Scenario: Trying to remove a product that the customer doesn't have
  Scenario: Trying to remove a product that does not exist