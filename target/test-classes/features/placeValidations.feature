@pageValidation @APIFramework
Feature: Validatng Place API's

  @regression @addPlace
  Scenario Outline: Verify if place is successfully aded using addPlace API
    Given Add Place Payload with "<Name>" "<Address>" and "<Language>"
    When User calls "AddPlaceAPI" with "Post" http request
    Then The API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And Verify place ID created maps to "<Name>" using "GetPlaceAPI"

    Examples: 
      | Name           | Address                   | Language  |
      | Ishan Kossambe | 29, side layout, cohen 09 | French-IN |

  #      | Kavya          | Navadurga Garden Borim    | English-IN |
  
  @deletePlace
  Scenario: Verify if delete place functionality is working
    Given DeletePlace Payload
    When User calls "DeletePlaceAPI" with "Post" http request
    Then The API call is success with status code 200
    And "status" in response body is "OK"
