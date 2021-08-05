# Authors : Ononeze,
# Date : 31/07/2021

@Integration
@Conference
Feature:This feature tests for five key team operations as regards to
  the Tauria challenge narrative.
  1. Retrieving optional users.
  2. Retrieving required users.
  3. Retrieving all users.
  4. Validating and invalidating adding guests.


  Background: set up conference with several users
    Given That there is a valid conference room with several users (Authenticated,guests,optional required)

  Scenario: Retrieving optional users
    Given That the system is queried for optional users
    When  The get optional users operation invoked.operation should succeed.
    Then  All optional users in that room should be returned.

  Scenario: Retrieving required users
    Given That the system is queried for required users
    When  The get required users operation invoked.operation should succeed.
    Then  All required users in that room should be returned.

  Scenario: Retrieving all users
    Given That the system is queried for all users
    When  The get all users operation invoked.operation should succeed.
    Then  All  users in that room should be returned.

  Scenario: Adding guest users to a conference room
    Given That there are is a guest user to add to a conference
    When  User added to a conference, operation should fail is conference does not allow guests
    Then  If conference allows guest, operation should succeed.