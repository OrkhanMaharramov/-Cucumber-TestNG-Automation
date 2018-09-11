Feature: 

  Scenario: Create contact using a map
    Given I logged into suiteCRM
    Then I create a new contact:
      | first_name   | John         |
      | last_name    | Smith        |
      | office_phone | 800-888-0000 |
      | cell_phone   | 801-888-8889 |
    Then I should see contact information for "John Smith"


  Scenario Outline: Create contact using a map
    Given I logged into suiteCRM
    Then I create a new contact:
      | first_name   | <first_name>   |
      | last_name    | <lname>        |
      | cell_phone   | <cell_phone>   |
      | office_phone | <office_phone> |
    Then I should see contact information for "<first_name> <lname>"

    Examples: 
      | first_name | lname   | cell_phone  | office_phone |
      | Michael    | Jackson |   215548639 |        55555 |
      | Bonnie     | Clyde   | 23965669823 |        88888 |
