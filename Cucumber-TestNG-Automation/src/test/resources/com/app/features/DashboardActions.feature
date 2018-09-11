Feature: Note on Dashboard

@geeMoff
 Scenario: Post a note on Dashboard
 Given I logged into suiteCRM
 When I post "Hello Everyone"
 Then Post should be displayed
 #Then I logout from application
 
@geeMoff
  Scenario: Post a note on Dashboard
 Given I logged into suiteCRM
 When I post "Hello Everyone"
 Then Post should be displayed
# Then I logout from application