Feature: Adding Products to a customer's shopping cart
	As an Api user
	I want to be able to products to a customer's account
	In order to keep track of what a customer wants to order
		
Scenario: Adding a product to an shopping cart
	Given the Api has the following list of customers with empty shopping carts
		| Id | Email  		 | Password    	 | 
		| 1	 | alan@foo.com  | rawTextForNow |
	And the Api has the following products
		| id | Name 				| Description			| Price |
		| 1  | Duracell AA Battery	| Lasts longer			| 4f	|
		| 2  | "Milk				| Only the freshest		| 0.89f |
		| 3  | "Eggs				| Free Range			| 1.19f |
		| 4  | "Bread				| 3 day life			| 1.20f |
	When I call the addProductToCustomerAccount operation with the following information
		| CustomerId | ProductId | Quantity |
		| 1			 | 2		 | 1		|
	Then the returned customer shall have the following core information
		| Id | Email  		 | Password    	 | 
		| 1	 | alan@foo.com  | rawTextForNow |
	And the returned customer shall have the following shopping cart
		| Product Name 	   | Quantity |
		| Milk			   | 2 		|