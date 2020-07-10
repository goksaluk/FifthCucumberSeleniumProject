package com.fleet.stepDefinitions;

import com.fleet.pages.LoginPage;
import io.cucumber.java.en.*;

public class TopMenuStepDefinitions {
    LoginPage loginPage = new LoginPage();


    @Then("user navigates to {string} then to {string}")
    public void user_navigates_to_then_to(String module, String submodule) {
        loginPage.navigateTo(module, submodule);
    }

}