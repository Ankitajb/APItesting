Feature: Validating Place API
@AddPlace @Regression
  Scenario Outline: Verify if place is being added successfully added using AddPlaceAPI
    Given Add Place payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "POST" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "GetPlaceAPI"
    Examples:
      | name   | language | address         |
      |AAhouse | English  | Cross Road      |
     # |BBhouse | Spanish  | Word Cross Road |
@DeletePlace @Regression
    Scenario: Verify if Delete Place functionality is working fine
      Given DeletePlace Payload
      When user calls "DeletePlaceAPI" with "delete" http request
      Then the API call is success with status code 200
      And "status" in response body is "OK"


