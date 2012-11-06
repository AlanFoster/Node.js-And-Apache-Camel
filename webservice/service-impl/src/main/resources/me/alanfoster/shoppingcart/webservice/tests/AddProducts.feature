Feature: Adding Products to a customer's shopping cart
  As an Api user
  I want to be able to add products to a customer's account
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

  Scenario: Adding a single product to a shopping cart
    When I call the addProductToCustomerAccount operation with the following information
      | CustomerId | ProductId | Quantity |
      | 1          | 2         | 1        |
    Then there will be no errors given in the response
    And the returned customer shall have the following core information
      | CustomerId | Email        | Password      |
      | 1          | alan@foo.com | rawTextForNow |
    And the returned customer shall have the following shopping cart
      | ProductId | Name | Description       | Price | Quantity |
      | 2         | Milk | Only the freshest | 0.89  | 1        |

  Scenario: Adding two products to a shopping cart
    When I call the addProductToCustomerAccount operation with the following information
      | CustomerId | ProductId | Quantity |
      | 1          | 2         | 5        |
    Then there will be no errors given in the response
    And the returned customer shall have the following core information
      | CustomerId | Email        | Password      |
      | 1          | alan@foo.com | rawTextForNow |
    And the returned customer shall have the following shopping cart
      | ProductId | Name | Description       | Price | Quantity |
      | 2         | Milk | Only the freshest | 0.89  | 5        |

  Scenario: Two calls with adding two of the same products to a shopping cart
   When I call the addProductToCustomerAccount operation with the following information
      | CustomerId | ProductId | Quantity |
      | 1          | 2         | 1        |
    Then there will be no errors given in the response
    And the returned customer shall have the following core information
      | CustomerId | Email        | Password      |
      | 1          | alan@foo.com | rawTextForNow |
    And the returned customer shall have the following shopping cart
      | ProductId | Name | Description       | Price | Quantity |
      | 2         | Milk | Only the freshest | 0.89  | 1        |
    When I call the addProductToCustomerAccount operation with the following information
      | CustomerId | ProductId | Quantity |
      | 1          | 2         | 1        |
    Then there will be no errors given in the response
    And the returned customer shall have the following core information
      | CustomerId | Email        | Password      |
      | 1          | alan@foo.com | rawTextForNow |
    And the returned customer shall have the following shopping cart
      | ProductId | Name | Description       | Price | Quantity |
      | 2         | Milk | Only the freshest | 0.89  | 2        |
      
  Scenario: Two calls with adding two different products to a shopping cart
    When I call the addProductToCustomerAccount operation with the following information
      | CustomerId | ProductId | Quantity |
      | 1          | 2         | 1        |
    Then there will be no errors given in the response
    And the returned customer shall have the following core information
      | CustomerId | Email        | Password      |
      | 1          | alan@foo.com | rawTextForNow |
    And the returned customer shall have the following shopping cart
      | ProductId | Name | Description       | Price | Quantity |
      | 2         | Milk | Only the freshest | 0.89  | 1        |
    When I call the addProductToCustomerAccount operation with the following information
      | CustomerId | ProductId | Quantity |
      | 1          | 3         | 2        |
    Then there will be no errors given in the response
    And the returned customer shall have the following core information
      | CustomerId | Email        | Password      |
      | 1          | alan@foo.com | rawTextForNow |
    And the returned customer shall have the following shopping cart
      | ProductId | Name | Description       | Price | Quantity |
      | 2         | Milk | Only the freshest | 0.89  | 1        |
      | 3         | Eggs | Free Range        | 1.19  | 2        |