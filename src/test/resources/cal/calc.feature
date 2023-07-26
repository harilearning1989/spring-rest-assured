Feature: arithmetic operations...
  Scenario Outline: client wants to multiply two numbers
    When the client calls /calc/mul with values  and
    Then the client receives answer as

    Examples:
      | number1 | number2 | result |
      | 2       | 3       | 6      |
      | 4       | 6       | 24     |
      | 7       | 8       | 56     |