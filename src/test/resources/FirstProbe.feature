Feature: First steps
  Rule: All the things are needed in proper demo
    # Here is a comment
  Scenario Outline: Demonstration
    Given User login in as '<login>' and '<password>'
    When User is logged
    Then do nothing
    Examples:
      | login  | password |
      | qwerty | qwer123  |
      | sfdgs  | adsfadsf |