@FullTestTag @ModuleTag
Feature: Test with two parameters

  @testDemoTag @testDemoTagFeature
  Scenario Outline: Test with few parameter in method
    Given I have 1 "apple" in my pocket
    When I eat one
    Then I have data in my pocket
    |col1|col1|col3|
    |1|1|3|
    |2|2|3|
    |3|3|3|