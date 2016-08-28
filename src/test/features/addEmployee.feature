Feature: Adding employee on OHRM Website
    As a Admin
    I want to add employee
    so that I can see that employee in employee list

  @addEmployee
  Scenario: Admin can add employee
    Given Admin is logged in on OHRM website
    When Admin select PIM modual and select Add Employee
    And select create login details checkbox
    And enter below details
      |Rajesh|Patel|jigi.patel|jigipatel|jigipatel|finance traniee|
    And select Save
    Then Admin should see employee details in employee list
