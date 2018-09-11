Feature: Search by contact name
Agile Story:
As a user,
I should be able to search for my contacts,
So that I can access their information easily.

Scenario: Search contact name
Given I logged into suiteCRM
When I search for "John Doe"
Then link for user "John Doe" should be displayed
Then link for 1 user "John Doe" should be displayed
