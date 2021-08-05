# Authors : Ononeze,
# Date : 31/07/2021

  @Integration
  @Team
Feature: This feature tests for one key operation on the team entity as regards to
  the Tauria challenge narrative.
  1. Adding users to teams.

  Scenario: Adding a new user to team

    Given That there is an authenticated user to add and a valid team
    When  The user is added to team operation should succeed
    Then  When user teams and team users queried assert that team and users updated
