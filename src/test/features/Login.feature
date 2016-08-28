Feature: Login feature for OHRM site
  As a admin
  I want to see login page
  So that I can login successfully

  @login
  Scenario: Admin can login successfully with valid credentials
    Given Admin is on login page
    When Admin login with below details
    |admin|
    |aediMNjU|
    And admin select login
    Then Admin should login successfully
    And Admin should welcome message as "Welcome Admin"
