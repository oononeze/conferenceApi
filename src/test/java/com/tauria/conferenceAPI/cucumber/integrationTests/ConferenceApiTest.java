package com.tauria.conferenceAPI.cucumber.integrationTests;

import com.tauria.conferenceAPI.ConferenceApi;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;


@CucumberContextConfiguration
@SpringBootTest(classes = {ConferenceApi.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConferenceApiTest {
}
