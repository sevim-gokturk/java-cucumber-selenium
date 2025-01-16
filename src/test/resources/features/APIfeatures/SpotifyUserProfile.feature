@Api @Spotify @UserProfile

Feature: Spotify User Profile API Validation
  As a Spotify API user
  I want to retrieve and validate user profile details
  So that I can ensure the API returns correct information

  Scenario: Validate Spotify user profile details
    Given I send a GET request to "/users/nil_idil"
    Then the status code should be 200
    And the response should contain "display_name" with value "nil_idil"
    And the response should contain "id" with value "nil_idil"
    And the response should contain "followers.total" with value 0
    And the response should contain "type" with value "user"
    And the response should contain "external_urls.spotify" with value "https://open.spotify.com/user/nil_idil"
