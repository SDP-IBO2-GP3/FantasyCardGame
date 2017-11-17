Feature: Playground

  Scenario: Initialize Playground
    Given Playground is initialized
    Then Player1 has 5 cards in his hand
    And Player1 has 0 cards in his kingdom

  Scenario: Initialize Deck
    Given: Deck is initialized
    Then Deck has 50 cards
<<<<<<< HEAD
    And Different races
=======
    And Different races

  #One player
  Scenario: Play a Korrigan
    Given Players1 plays a Korrigan
    Then Player1 has 2 more cards in his hand
    And Player2 has 2 cards less in his hand

  Scenario: Play a Gnome
    Given Player1 plays a Gnome
    Then Player1 has 2 more cards in his hand

  Scenario: Play a Goblin
    Given Player1 plays a Goblin
    Then Player1 switches his hand with Player2

  #Scenario: Play an Elf
   # Given Player1 plays an Elf
    #Then

  Scenario: Play a Dryad
    Given Player1 plays a Dryad
    Then Player2 has one less card in his kingdom
    And Player1 has one more card in his kingdom

  Scenario: Play a Troll
    Given Player1 plays a Troll
    Then Player1 and Player2 switch their kingdoms


>>>>>>> origin/master
