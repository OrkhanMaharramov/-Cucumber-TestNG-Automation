Feature: Hr Application Database and UI data verification

Background:
Given I am on DeptEmpPage


Scenario: Department data UI and Database verification
When I search for department id 10
Then I query database with sql "Select department_name,manager_id,location_id from departments where department_id=10"
Then UI data and Database data must match


Scenario Outline: FirstName and lastname search by email-UI vs DB verification
When I search for email "<email>" to see firstname and lastname
Then I query database with sql "Select first_name,last_name From employees Where email='<email>'"
Then UI data and Database data must match
Examples:
|email|
|JWHALEN|
|HBAER|
|JRUSSEL|

@HRAppDB
Scenario Outline: Verify Number of employees for departments-UI vs DB verification
When I search for department id <departmentID> and get number of employees
And I query database with sql "Select Count(*) As Employees_Count From employees Where department_id=<departmentID>"
Then UI data and Database data must match
Examples:
|departmentID|
|10|
|20|
|30|








