Feature: Creating contacts

  Scenario: Create contact using CREATE page
    Given I logged into suiteCRM
    And I open the create contact page
    And I enter the first name "Gee" and the last name "Moff"
    And I enter the phone number"202-000-0000"
    And I enter the department "IT"
    When click on the save button
    Then I should see contact information for "Gee Moff"

  Scenario: Create contact using CREATE page
    Given I logged into suiteCRM
    And I open the create contact page
    And I enter the first name "Sarah" and the last name "Lawrence"
    And I enter the phone number"202-000-0000"
    And I enter the department "IT"
    When click on the save button
    Then I should see contact information for "Sarah Lawrence"


  Scenario Outline: Create multiple contacts
    Given I logged into suiteCRM
    And I open the create contact page
    And I enter the first name "<firstname>" and the last name "<lastname>"
    And I enter the phone number"<phonenumber>"
    And I enter the department "<department>"
    When click on the save button
		Then I should see contact information for "<firstname> <lastname>"
    Examples: 
      | firstname | lastname | phonenumber       | department |
      #| Satoshi   | Nakamuro | 98642523596638362 | IT         |
      | John      | Smith    |         836924536 | Sales      |
      #| Bonnie    | Clyde    | 98642523596638362 | Boss       |
      | Gee       | Moff     |        5555555555 | Owner      |
