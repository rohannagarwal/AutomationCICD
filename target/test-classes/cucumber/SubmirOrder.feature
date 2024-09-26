@tag
Feature: Purchase the order from Ecommerce Website
 I Want to use this template for my feature file
 
 Background:
 Given I landed on Ecommerce Page
 
 
 @Regression
 Scenario Outline: Positive test of submitting the order
 Given Logged in with username <name> and password <password>
 When I add product <productName> to cart
 And Checkout <productName> and submit the order
 Then "THANKYOU FOR THE ORDER." message will be displayed on ConfirmationPage
 
 Examples:
 |name                                          |password         |productName      |
 |nagarwalrohan3@gmail.com                      |Nagarwal@123     |ZARA COAT 3      |