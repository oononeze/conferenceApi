package com.tauria.conferenceAPI.cucumber.integrationTests.conferenceSteps;

import com.tauria.conferenceAPI.cucumber.integrationTests.ConferenceApiTest;
import io.cucumber.java.en.*;

public class ConferenceUsers extends ConferenceApiTest {

    @Given("That there is a valid conference room with several users " +
            "\\(Authenticated,guests,optional required)")
    public void thatThereIsAValidConferenceRoomWithSeveralUsersAuthenticatedGuestsOptionalRequired() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    //retrieving optional users
    @Given("That the system is queried for optional users")
    public void thatTheSystemIsQueriedForOptionalUsers() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("The get optional users operation invoked.operation should succeed.")
    public void theGetOptionalUsersOperationInvokedOperationShouldSucceed() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("All optional users in that room should be returned.")
    public void allOptionalUsersInThatRoomShouldBeReturned() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    //retrieving all users
    @Given("That the system is queried for all users")
    public void thatTheSystemIsQueriedForAllUsers() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("The get all users operation invoked.operation should succeed.")
    public void theGetAllUsersOperationInvokedOperationShouldSucceed() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("All  users in that room should be returned.")
    public void allUsersInThatRoomShouldBeReturned() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    //retrieving required users
    @Given("That the system is queried for required users")
    public void thatTheSystemIsQueriedForRequiredUsers() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("The get required users operation invoked.operation should succeed.")
    public void theGetRequiredUsersOperationInvokedOperationShouldSucceed() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("All required users in that room should be returned.")
    public void allRequiredUsersInThatRoomShouldBeReturned() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    //adding user to a conference
    @Given("That there are is a guest user to add to a conference")
    public void thatThereAreIsAGuestUserToAddToAConference() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("User added to a conference, operation should fail is conference does not allow guests")
    public void userAddedToAConferenceOperationShouldFailIsConferenceDoesNotAllowGuests() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("If conference allows guest, operation should succeed.")
    public void ifConferenceAllowsGuestOperationShouldSucceed() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
