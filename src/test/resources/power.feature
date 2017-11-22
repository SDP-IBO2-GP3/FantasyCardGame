# new feature
# Tags: optional
    
Feature: Each card Power
    
Scenario: Troll Card Power
    Given The Player1 and Player2 cards
    When Player1 is playing the Troll
    Then The players swap kingdoms
    And The players swap scores