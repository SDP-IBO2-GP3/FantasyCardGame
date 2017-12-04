Feature: Playground

  Scenario: Initialize Playground
    Given Playground is initialized
    Then Deck is filled
    And Players have 6 cards in their hand
    And Kingdoms are empty

  Scenario: Deck mixes
    Given  Deck is initialized
    When Mix the deck
    Then  the deck is mixed

  Scenario: Deck is empty
    Given Deck is full
    When Player plays all cards
    Then the deck is empty

  Scenario: Bonus score
    When Player1 has 6 different races in kingdom
    Then Player has extra point