package com.fleet.stepDefinitions;

import com.fleet.pages.CalendarEventsPage;
import com.fleet.utilities.BrowserUtils;
import com.fleet.utilities.Driver;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class HW_stepDefinitions {
    CalendarEventsPage calendarEventsPage = new CalendarEventsPage();

    @Then("hover on tree dots on tester meeting")
    public void hover_on_tree_dots_on_tester_meeting() throws InterruptedException {
        Actions actions = new Actions(Driver.get());
        actions.clickAndHold(calendarEventsPage.threeDots);
        Thread.sleep(3000);
    }

    @Then("user verifies {string} , {string} , {string} options displayed")
    public void user_verifies_options_displayed(String string, String string2, String string3) {
        List<String> ExpectedMenu = new ArrayList<>();
        ExpectedMenu.add(string);
        ExpectedMenu.add(string2);
        ExpectedMenu.add(string3);

        int a = calendarEventsPage.viewDeleteEdit.size();
        for (int i = 0; i<a ; i++ ){
            Assert.assertEquals(ExpectedMenu, calendarEventsPage.viewDeleteEdit.get(i) );
        }

    }

    @Then("user clicks on Grid Option button")
    public void user_clicks_on_Grid_Option_button() {
        calendarEventsPage.waitUntilLoaderMaskDisappear();
        calendarEventsPage.clickGridButton();
    }

    @Then("desellect all option except Title")
    public void desellect_all_option_except_Title() {
        calendarEventsPage.GridSettings(1,6);


    }

    @Then("Verify that Title column is displayed")
    public void verify_that_Title_column_is_displayed() {
        calendarEventsPage.TitleColunm.isDisplayed();

    }
    @Then("clicks on {string} button")
    public void clicks_on_button(String string) {
        calendarEventsPage.waitUntilLoaderMaskDisappear();
        BrowserUtils.waitForClickablility(calendarEventsPage.cceButton,5);
        calendarEventsPage.cceButton.click();
    }

    @Then("user click Save And close button")
    public void user_click_Save_And_close_button() {
        // BrowserUtils.waitForClickablility(calendarEventsPage.sacElement,10);
        BrowserUtils.wait(10);
        calendarEventsPage.sacElement.click();

    }

    @Then("user verifies that following statements are available")
    public void user_verifies_that_following_statements_are_available(List<String> dataTable) {
        calendarEventsPage.waitUntilLoaderMaskDisappear();
        Assert.assertEquals(dataTable,calendarEventsPage.getSaveAndCloseElements());
    }

    @Then("user click Cancel button")
    public void user_click_Cancel_button() {
        calendarEventsPage.waitUntilLoaderMaskDisappear();
        BrowserUtils.waitForVisibility(calendarEventsPage.cancel,10);
        BrowserUtils.waitForClickablility(calendarEventsPage.cancel,10);
        calendarEventsPage.cancel.click();

    }

    @Then("user verifies {string} page subtitle is displayed")
    public void user_verifies_page_subtitle_is_displayed(String string) {
        calendarEventsPage.waitUntilLoaderMaskDisappear();
        BrowserUtils.waitForVisibility(calendarEventsPage.ccePageSubtitle,15);
        //Assert.assertEquals(string,calendarEventsPage.ccePageSubtitle.getText(),"wrong title");
        calendarEventsPage.ccePageSubtitle.isDisplayed();
        String result = calendarEventsPage.ccePageSubtitle.getText();
        System.out.println(result);

    }

    @Then("user should see difference is one hour")
    public void user_should_see_difference_is_one_hour() {
        BrowserUtils.wait(5);

        calendarEventsPage.time_1Box.click();
        BrowserUtils.wait(5);
        calendarEventsPage.sixAM.click();
        String time1 = calendarEventsPage.sixAM.getAttribute("innerHTML");
        String realFigure = time1.substring(0,4);

        String[] array = realFigure.split(":");
        String firstHour = array[0].concat(array[1]);
        System.out.println("First hour = "+ firstHour);
        double FirstBoxTime = Double.parseDouble(firstHour);
        System.out.println("FirstHourInteger = "+ FirstBoxTime);
        BrowserUtils.wait(5);

        calendarEventsPage.time_2Box.click();
        String time2 = calendarEventsPage.sevenAM.getAttribute("innerHTML");
        String realFigure2= time2.substring(0,4);

        String[] array2 = realFigure2.split(":");
        String secondHour = array2[0].concat(array2[1]);
        System.out.println("Ending hour = "+ secondHour);
        double SecondBoxTime = Double.parseDouble(secondHour);
        System.out.println("EndingHourInteger = "+SecondBoxTime);

        double difference = SecondBoxTime -FirstBoxTime;
        System.out.println("difference = "+ difference);
        Assert.assertEquals("100",difference);











        calendarEventsPage.time_2Box.click();

        BrowserUtils.wait(5);

        List<String> timeList2 = calendarEventsPage.timeStart2();
        String f2= timeList2.get(7);
        System.out.println(f2);



    }





}
