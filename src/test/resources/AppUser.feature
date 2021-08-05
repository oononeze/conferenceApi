# Authors : Ononeze,
# Date : 31/07/2021

@Integration
@ApplicationUser
Feature:This feature tests for one key user operation as regards to
  the Tauria challenge narrative.
  1. User creating conference room.

  Scenario: Creating new conference room
    Given That there is a valid user in a team trying to create new conference room
    When  A conference room is to be created fail if team conference time (100hrs) exhausted
    Then  If team conference time (100hrs) not exhausted succeed.