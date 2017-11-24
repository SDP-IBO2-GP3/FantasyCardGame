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

#Change random
#Scenario: Elf Card Power
 #   When Player1 is playing a Elf
  #  Then Player1 copies one card from the player2 kingdom
   # And the player2 get the same kingdom size

Scenario: Dryad Card Power
    When Player1 is playing a Dryad
    Then Player1 has one more card in his kingdom
    And Player2 has one card less in his kingdom