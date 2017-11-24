# new feature
# Tags: optional
    
Feature: Each card Power

Scenario: Troll Card Power
    When Player1 is playing the Troll
    Then The players swap kingdoms
    And The players swap scores

Scenario: Goblin Card Power
    When Player1 is playing a Goblin
    Then The players swap hands

Scenario: Korrigan Card Power
    When Player1 is playing a Korrigan
    Then Player2 has two cards less in his hand
    And Player1 has two more cards in his hand