Feature: Playground

  Scenario: Initialize Playground
    Given Playground is initialized
    Then Deck is filled
    And Players have 6 cards in their hand