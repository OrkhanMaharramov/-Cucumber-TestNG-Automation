Feature: SugarCRM menu options
@J&H
  Scenario: Verify Collaboration menu options
    Given I logged into suiteCRM
    When I hover over the Collaboration menu
    Then followwing menu options should be visible for Collaboration:
      | Home      |
      | Emails    |
      | Documents |
      | Projects  |


  Scenario: Verify Support menu options
    Given I logged into suiteCRM
    When I hover over the Support menu
    #When I hover over the Sales menu
    #When I hover over the All menu
    Then followwing menu options should be visible for Support:
      | Home     |
      | Accounts |
      | Contacts |
      | Cases    |
      
      
  @J&H
  Scenario: Verify Sales menu options
    Given I logged into suiteCRM
    When I hover over the Sales menu
    Then followwing menu options should be visible for Sales:
      | Home     |
      | Accounts |
      | Contacts |
      | Opportunities |
      | Leads |
