Feature: Create contacts using beans


  Scenario: Create contact
    Given I logged into suiteCRM
    When I save a new contact:
      | firstName | lastName | department | officePhone | cellPhone | email           |
      | Kevin     | Gates    | IT         |  4157126806 | 265723569 | gates@apple.com |
    Then I should see contact information for "Kevin Gates"



  Scenario Outline: Create contact
    Given I logged into suiteCRM
    When I save a new contact:
      | firstName   | lastName   | department   | officePhone | cellPhone | email   |
      | <firstname> | <lastname> | <department> | <office>    | <cell>    | <email> |
    Then I should see contact information for "<firstname> <lastname>"

    Examples: 
      | firstname | lastname | department | office     | cell     | email             |
      | Admiralka | Kunka    | navy       | 2385756327 | 26846429 | admiral@valve.com |
      | Johny     | Smith    | navy       |      63639 |  7543795 | icefrog@valve.com |
