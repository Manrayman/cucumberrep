Feature: Login to HRM Application

  Background: 
    Given User is on HRMLogin page "https://opensource-demo.orangehrmlive.com/"

  @ValidCredentials
  Scenario: Login with valid credentials
    When User enters username as "Admin" and password as "admin123"
    Then User should be able to login sucessfully and new page open

  Scenario: To validate user is able to change password using forgot password link
    When User clicks on Forgot Password Link on homepage
    Then User navigates to password reset page

  Scenario: To validate when user clicks on cancel button on forgot password page user is navigated to home page
    When User clicks on Forgot Password Link on homepage
    And User clicks on cancel button on forgot password page
    Then User navigates back to home page

  @InvalidCredentials
  Scenario Outline: Login with invalid credentials
    When User enters username as "<username>" and password as "<password>"
    Then User should be able to see error message "<errorMessage>"

    Examples: 
      | username | password  | errorMessage        |
      | Admin    | admin12$$ | Invalid credentials |
      | admin$$  | admin123  | Invalid credentials |
      | abc123   | xyz$$     | Invalid credentials |
