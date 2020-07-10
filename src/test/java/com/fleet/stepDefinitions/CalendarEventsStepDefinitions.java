package com.fleet.stepDefinitions;

import com.fleet.pages.CalendarEventsPage;
import com.fleet.pages.CreateCalendarEventPage;
import com.fleet.utilities.BrowserUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CalendarEventsStepDefinitions {

    CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
    CreateCalendarEventPage createCalendarEventPage = new CreateCalendarEventPage();

    @Then("user verifies that column names are displayed")
    public void user_verifies_that_column_names_are_displayed(List<String> dataTable) {
        System.out.println(dataTable);
        calendarEventsPage.waitUntilLoaderMaskDisappear();
        BrowserUtils.wait(3);
        Assert.assertEquals(dataTable, calendarEventsPage.getColumnNames());
    }


    @Then("user verifies that following options are displayed for {string} calendar event")
    public void user_verifies_that_following_options_are_displayed_for_calendar_event(String title, List<String> dataTable) {
        calendarEventsPage.waitUntilLoaderMaskDisappear();
        for (String optionText : dataTable) {
            Assert.assertTrue(String.format("Option %s for %s calendar event is not visible", optionText, title), calendarEventsPage.getCalendarEventOption(title, optionText).isDisplayed());
        }
    }


    @Then("user {string} following grid options")
    public void user_following_grid_options(String selectOrUnselect, List<String> dataTable) {
        for (String gridOption : dataTable) {
            calendarEventsPage.selectGridSettings(gridOption, selectOrUnselect);
        }
    }

    @Then("user should see following save and close options")
    public void user_should_see_following_save_and_close_options(List<String> dataTable) {
        Assert.assertEquals(dataTable, createCalendarEventPage.getListOfSaveAndCloseOptions());
    }

    @When("user clicks on {string} button")
    public void user_clicks_on_button(String string) {
        if (string.equalsIgnoreCase("cancel")) {
            createCalendarEventPage.clickToCancel();
        } else if (string.equalsIgnoreCase("Create Calendar Event")) {
            calendarEventsPage.clickToCreateCalendarEvent();
        }
    }

    @Then("user verifies that end time is {int} hour after start time")
    public void user_verifies_that_end_time_is_hour_after_start_time(Integer hours) {
        calendarEventsPage.waitUntilLoaderMaskDisappear();
        Assert.assertEquals(hours, createCalendarEventPage.getDifferenceBetweenEndTimeAndStartTime());
    }

    @Then("user sets starts time to {string}")
    public void user_sets_starts_time_to(String string) {
        createCalendarEventPage.waitUntilLoaderMaskDisappear();
        createCalendarEventPage.selectStartTime(string);
    }

    @Then("user verifies that end time is {string}")
    public void user_verifies_that_end_time_is(String string) {
        createCalendarEventPage.waitUntilLoaderMaskDisappear();
        Assert.assertEquals(string, createCalendarEventPage.getEndTime());
    }

    @Then("user {string} {string} option")
    public void user_option(String selectOrUnselect, String whatToSelect) {
        createCalendarEventPage.waitUntilLoaderMaskDisappear();

        if (whatToSelect.equalsIgnoreCase("all-day event")) {
            createCalendarEventPage.clickToSelectOrUnselectAllDayEvent(selectOrUnselect);
        } else if (whatToSelect.equalsIgnoreCase("repeat")) {
            createCalendarEventPage.clickToSelectOrUnselectRepeat(selectOrUnselect);
        } else  if (whatToSelect.equalsIgnoreCase("after")) {
            createCalendarEventPage.clickToSelectOrUnselectAfter(selectOrUnselect);
        } else {
            throw new RuntimeException("Wrong option!");
        }

        createCalendarEventPage.waitUntilLoaderMaskDisappear();
        BrowserUtils.wait(2);
    }


    @When("enters {string} occurrences")
    public void enters_occurrences(String occurrences) {
        createCalendarEventPage.enterAfterOccurrences(occurrences);
    }

    @Then("user verifies that {string} option is selected")
    public void user_verifies_that_option_is_selected(String whatToVerify) {
        createCalendarEventPage.waitUntilLoaderMaskDisappear();

        if (whatToVerify.equalsIgnoreCase("all-day event")) {
            Assert.assertTrue(createCalendarEventPage.allDayEventCheckbox.isSelected());
        } else if (whatToVerify.equalsIgnoreCase("repeat")) {
            Assert.assertTrue(createCalendarEventPage.repeatCheckbox.isSelected());
        } else  if (whatToVerify.equalsIgnoreCase("repeat every")) {
            Assert.assertTrue(createCalendarEventPage.repeatEveryRadioButtonElement.isSelected());
        } else if (whatToVerify.equalsIgnoreCase("never")) {
            Assert.assertTrue(createCalendarEventPage.neverRadioButtonElement.isSelected());
        } else {
            throw new RuntimeException("Wrong option!");
        }

        createCalendarEventPage.waitUntilLoaderMaskDisappear();
    }


    @Then("user verifies summary text is exactly {string}")
    public void user_verifies_summary_text_is_exactly(String string) {
        Assert.assertEquals(string, createCalendarEventPage.getSummaryText());
    }

    @Then("user verifies that start time is not visible")
    public void user_verifies_that_start_time_is_not_visible() {
        Assert.assertFalse("Start time is still visible", createCalendarEventPage.startTime.isDisplayed());
    }

    @Then("user verifies that end time is not visible")
    public void user_verifies_that_end_time_is_not_visible() {
        Assert.assertFalse("End time is still visible", createCalendarEventPage.endTime.isDisplayed());
    }

    @Then("user verifies that start date is visible")
    public void user_verifies_that_start_date_is_visible() {
        Assert.assertTrue("Start date is not visible", createCalendarEventPage.startDate.isDisplayed());
    }

    @Then("user verifies that end date is visible")
    public void user_verifies_that_end_date_is_visible() {
        Assert.assertTrue("End date is not visible", createCalendarEventPage.endDate.isDisplayed());
    }

    @Then("user verifies that following options for repeats drop-down are available")
    public void user_verifies_that_following_options_for_repeats_drop_down_are_available(List<String> dataTable) {
        Assert.assertEquals(dataTable, createCalendarEventPage.getListOfRepeatsOptions());
    }
}