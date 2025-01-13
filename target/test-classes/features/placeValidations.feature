@pageValidation
Feature: Validatng Place API's

  @regression
  Scenario Outline: Verify if place is successfully aded using addPlace API
    Given Add Place Payload with "<Name>" "<Address>" and "<Language>"
    When User calls "AddPlaceAPI" with Post http request
    Then The API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"

    Examples: 
      | Name           | Address                   | Language   |
      | Ishan Kossambe | 29, side layout, cohen 09 | French-IN  |
      | Kavya          | Navadurga Garden Borim    | English-IN |
