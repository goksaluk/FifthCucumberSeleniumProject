package com.fleet.stepDefinitions;

import com.fleet.pages.CreateCarPage;
import com.fleet.pages.VehiclesPage;
import com.fleet.utilities.BrowserUtils;

import io.cucumber.java.en.Then;

import java.util.List;
import java.util.Map;

public class CreateCarStepDefinitions {

    CreateCarPage createCarPage = new CreateCarPage();
    VehiclesPage vehiclesPage = new VehiclesPage();

    @Then("user click on {string} button")
    public void user_click_on_button(String string) {
        vehiclesPage.waitUntilLoaderMaskDisappear();
        if (string.equals("Create Car")) {
            vehiclesPage.clickToCreateACar();
        }
    }

    @Then("user adds new car information:")
    public void user_adds_new_car_information(List<Map<String, String>> dataTable) {
        createCarPage.waitUntilLoaderMaskDisappear();
        System.out.println(dataTable);
        int row = 1;
        for (Map<String, String> map : dataTable) {
            createCarPage.licensePlateElement.sendKeys(map.get("License Plate"));
            createCarPage.driverElement.sendKeys(map.get("Driver"));
            createCarPage.locationElement.sendKeys(map.get("Location"));
            createCarPage.modelYearElement.sendKeys(map.get("Model Year"));
            createCarPage.colorElement.sendKeys(map.get("Color"));
            BrowserUtils.wait(2);//for demo
            if (row == dataTable.size()) {
                createCarPage.clickSaveAndClose();
            } else {
                createCarPage.clickSaveAndAddNew();
            }
            BrowserUtils.wait(2);
            row++;
        }
    }

}