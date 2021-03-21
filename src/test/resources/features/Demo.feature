Feature: Is it my date
  Everybody wants to know when it's Friday

  Scenario: Sunday isn't Friday
    Given today is "Sunday"
    When I ask whether it's "Friday" yet
    Then I should be told "Sunday"
    
  Scenario: Sunday isn't Sunday
    Given today is "Monday"
    When I ask whether it's "Monday" yet
    Then I should be told "Monday"