@Api @Spotify @Playlist
Feature: Create playlists via Spotify API

  Scenario Outline: Create a new playlist
    Given I create request body with given : "<name>", "<description>", "<public>"
    When I send a POST request to "/users/{user_id}/playlists" with user ID "<user_id>" and the playlist details
    Then the status code should be 201
    And the response should contain "name" with value "<name>"
    And the response should contain "description" with value "<description>"
    And the response should contain "public" with value "<public>"

    Examples:
      | user_id  | name             | description                     | public |
      | nil_idil | My New Playlist3 | A cool playlist created via API | false  |
      | nil_idil | Chill Vibes3     | Relaxing music to chill         | true   |
