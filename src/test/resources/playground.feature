Feature: Playground

  Scenario: Initialize Playground
    Given Playground is initialized
    Then Player1 has 5 cards in his hand
    And Player1 has 0 cards in his kingdom

  Scenario: Initialize Deck
    Given: Deck is initialized
    Then Deck has 50 cards
    And Different races