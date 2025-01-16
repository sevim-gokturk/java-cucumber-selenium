@Api @Spotify @1
Feature: CRUD operation for Spotify Playlist via Spotify API

  Scenario Outline: Create a new playlist
    Given I create request body with given : "<name>", "<description>", "<public>"
    When I send a POST request to "/users/{user_id}/playlists" with user ID "<user_id>" and the playlist details
    Then the status code should be 201
    And the response should contain "name" with value "<name>"
    And the response should contain "description" with value "<description>"
    And the response should contain "public" with value "<public>"

    Examples:
      | user_id  | name             | description                     | public |
      | nil_idil | My New Playlist3 | A cool playlist created via API | true  |

  Scenario Outline: Get created playlist
    Given I send a GET request to "/playlists/{playlist_id}" with the stored playlist ID
    Then the status code should be 200
    And the response should contain "name" with value "<name>"
    And the response should contain "description" with value "<description>"
    And the response should contain "public" with value "<public>"

    Examples:
      | name             | description                     | public |
      | My New Playlist3 | A cool playlist created via API | true  |

  Scenario Outline: Change Playlist Details
    Given I create request body with given : "<name>", "<description>", "<public>"
    When I send a PUT request to "/playlists/{playlist_id}" with stored playlist id and the playlist details
    Then the status code should be 200
    And the response should contain "name" with value "<name>"
    And the response should contain "description" with value "<description>"

    Examples:
      | name             | description                          | public |
      | My New Playlist5 | A enjoyable playlist created via API | true  |
