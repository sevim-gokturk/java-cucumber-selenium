@Api @Spotify @createPlaylist

Feature: Spotify Playlist Creation
  As a Spotify user
  I want to create a new playlist
  So that I can organize my music collections

  Scenario: Create a new playlist via the Spotify API
    Given I send a POST request to given endpoint with the request body:
      | name                 | description                              | public | endpoint                         |
      | My New Playlist1     | A cool playlist created via API          | false  | /v1/users/{user_id}/playlists    |
    Then the status code should be 201
    And the response should contain "name" with value "My New Playlist1"
    And the response should contain "description" with value "A cool playlist created via API"
    And the response should contain "public" with value "false"