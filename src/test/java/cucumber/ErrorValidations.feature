 @tag
Feature: Error Validations
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce Page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed on ConfirmationPage

    Examples: 
      | name                    | password    | productName |
      | teemakareem96@gmail.com | Fatimah@12 | ZARA COAT 3 |

