package com.fleet.pages;

import com.fleet.utilities.BrowserUtils;
import com.fleet.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class CreateCalendarEventPage extends BasePage {

    @FindBy(css = "[class='select2-chosen']")
    public WebElement owner;

    @FindBy(css = "a[title='Reset']")
    public WebElement resetBtnElement;

    @FindBy(css = ".grid-header-cell__label")
    public List<WebElement> headers;

    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_start']")
    public WebElement startDate;
    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_end']")
    public WebElement endDate;

    @FindBy(css = "[id^='time_selector_oro_calendar_event_form_start']")
    public WebElement startTime;

    @FindBy(css = "[id^='time_selector_oro_calendar_event_form_end']")
    public WebElement endTime;

    @FindBy(css = "select[class='ui-timepicker-wrapper']")
    public WebElement timeDropdown;

    @FindBy(css = "select[class='ui-datepicker-month']")
    public WebElement monthDropdown;

    @FindBy(css = "select[class='ui-datepicker-year']")
    public WebElement yearDropdown;

    @FindBy(css = "[id^='recurrence-repeat-view']")
    public WebElement repeatCheckbox;

    @FindBy(css = "[class='btn-success btn dropdown-toggle']")
    public WebElement saveAndCloseToggle;

    @FindBy(xpath = "//a[@class='btn-success btn dropdown-toggle']/following-sibling::ul//li")
    public List<WebElement> saveAndCloseOptions;

    @FindBy(css = "[title='Cancel']")
    public WebElement cancelButtonElement;

    @FindBy(css = "[name='oro_calendar_event_form[allDay]']")
    public WebElement allDayEventCheckbox;

    @FindBy(css = "[id^='recurrence-repeats']")
    public WebElement repeatsDropdown;

    @FindBy(xpath = "//label[text()='Repeat every']/../following-sibling::div//label[@class='fields-row']//input[@type='radio']")
    public WebElement repeatEveryRadioButtonElement;

    @FindBy(xpath = "//span[text()='Never']/preceding-sibling::input[@type='radio']")
    public WebElement neverRadioButtonElement;

    @FindBy(xpath = "//span[text()='After']/preceding-sibling::input[@type='radio']")
    public WebElement afteroccurrencesRadioButtonElement;

    @FindBy(xpath = "//span[text()='After']/following-sibling::input[@type='text']")
    public WebElement afterOccurrencesInputBoxButtonElement;

    @FindBy(css = "div[data-name='recurrence-summary']")
    public WebElement summaryElement;


    public void selectStartOrEndDate(String date, String startOrEnd) {
        waitUntilLoaderMaskDisappear();
        LocalDate ld = LocalDate.of(Integer.parseInt(date.substring(date.lastIndexOf("/") + 1)),
                Integer.parseInt(date.substring(0, date.indexOf("/"))),
                Integer.parseInt(date.substring(date.indexOf("/") + 1, date.lastIndexOf("/"))));

        String month = DateTimeFormatter.ofPattern("MMM").format(ld);
        int year = ld.getYear();
        int day = ld.getDayOfMonth();


        //locator for day
        String dayLocator = "//a[@class='ui-state-default' and text()='" + day + "']";

        //click on start or end date
        if (startOrEnd.equalsIgnoreCase("start")) {
            BrowserUtils.waitForVisibility(startDate, 5);
            startDate.click();
        } else {
            BrowserUtils.waitForVisibility(endDate, 5);
            endDate.click();
        }

        //select month
        new Select(monthDropdown).selectByVisibleText(month);


        //select year
        new Select(yearDropdown).selectByVisibleText(year + "");

        //select day
        Driver.get().findElement(By.xpath(dayLocator)).click();
    }

    public void selectTomorrowDay() {
        int day = LocalDate.now().plusDays(1).getDayOfMonth();
        int month = LocalDate.now().plusDays(1).getMonth().getValue();
        try {
            waitUntilLoaderMaskDisappear();
            startDate.click();
        } catch (WebDriverException e) {
            System.out.println(e.getMessage());
            BrowserUtils.clickWithWait(startDate);
        }
        Select monthSelect = new Select(monthDropdown);
        monthSelect.selectByIndex(month - 1);
        String dayLocator = "//table[@class='ui-datepicker-calendar']//a[text()='" + day + "']";
        Driver.get().findElement(By.xpath(dayLocator)).click();
    }


    public void selectADay(int plusDays) {
        int day = LocalDate.now().plusDays(plusDays).getDayOfMonth();
        int month = LocalDate.now().plusDays(plusDays).getMonth().getValue();
        waitUntilLoaderMaskDisappear();
        startDate.click();
        Select monthSelect = new Select(monthDropdown);
        monthSelect.selectByIndex(month - 1);
        String dayLocator = "//table[@class='ui-datepicker-calendar']//a[text()='" + day + "']";
        Driver.get().findElement(By.xpath(dayLocator)).click();
    }

    public void selectTodayDate() {
        int day = LocalDate.now().getDayOfMonth();
        startDate.click();
        String dayLocator = "//table[@class='ui-datepicker-calendar']//a[text()='" + day + "']";
        try {
            Driver.get().findElement(By.xpath(dayLocator)).click();
        } catch (Exception e) {
            BrowserUtils.wait(1);
            Driver.get().findElement(By.xpath(dayLocator)).click();
        }
    }

    public void selectStartTime(int plusHours) {
        String time = LocalDateTime.now().plusHours(plusHours).format(DateTimeFormatter.ofPattern("h:00 a"));
        waitUntilLoaderMaskDisappear();
        String startTimeToSelect = "(//li[text()='" + time + "'])[1]";
        startTime.click();
        new WebDriverWait(Driver.get(), 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(startTimeToSelect)));
        Driver.get().findElement(By.xpath(startTimeToSelect)).click();
    }

    public void selectStartTime(String time) {
        waitUntilLoaderMaskDisappear();
        String startTimeToSelect = "(//li[text()='" + time + "'])[1]";
        try {
            startTime.click();
        } catch (WebDriverException e) {
            System.out.println(e.getMessage());
            BrowserUtils.clickWithWait(startTime);
        }
        new WebDriverWait(Driver.get(), 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(startTimeToSelect)));
        Driver.get().findElement(By.xpath(startTimeToSelect)).click();
    }

    public void selectEndTime(String time) {
        waitUntilLoaderMaskDisappear();
        String endTimeToSelect = "(//li[text()='" + time + "'])[2]";
        startTime.click();
        Driver.get().findElement(By.xpath(endTimeToSelect)).click();
    }

    public Integer getDifferenceBetweenEndTimeAndStartTime() {
        LocalTime actualStartTime = LocalTime.parse(startTime.getAttribute("value"), DateTimeFormatter.ofPattern("h:mm a"));
        try {
            new WebDriverWait(Driver.get(), 3).until(ExpectedConditions.invisibilityOf(startTime));
        } catch (Exception e) {
            System.out.println(e);
        }
        LocalTime actualEndTime = LocalTime.parse(endTime.getAttribute("value"), DateTimeFormatter.ofPattern("h:mm a"));
        System.out.println("Start time: " + actualStartTime);
        System.out.println("End time: " + actualEndTime);
        return Math.toIntExact(ChronoUnit.HOURS.between(actualStartTime, actualEndTime));
    }

    public void selectTodaysDate() {
        int day = LocalDate.now().getDayOfMonth();
        startDate.click();
        String dayLocator = "//table[@class='ui-datepicker-calendar']//a[text()='" + day + "']";
        try {
            Driver.get().findElement(By.xpath(dayLocator)).click();
        } catch (Exception e) {
            BrowserUtils.wait(1);
            Driver.get().findElement(By.xpath(dayLocator)).click();
        }
    }

    public String getStartDate() {
        return startDate.getAttribute("value");
    }

    public String getEndDate() {
        return endDate.getAttribute("value");
    }

    public String getStartTime() {
        return startTime.getAttribute("value");
    }

    public String getEndTime() { return endTime.getAttribute("value"); }

    public void expandSaveAndCloseMenu() {
        waitUntilLoaderMaskDisappear();
        BrowserUtils.waitForVisibility(saveAndCloseToggle, 10);
        BrowserUtils.clickWithWait(saveAndCloseToggle);
    }

    public List<String> getListOfSaveAndCloseOptions() {
        waitUntilLoaderMaskDisappear();
        expandSaveAndCloseMenu();
        return BrowserUtils.getListOfString(saveAndCloseOptions);
    }

    public void clickToCancel() {
        BrowserUtils.waitForVisibility(cancelButtonElement, 10);
        BrowserUtils.clickWithWait(cancelButtonElement);
        BrowserUtils.clickWithWait(cancelButtonElement);
    }

    public void clickToSelectOrUnselectAllDayEvent(String selectOrUnselect) {
        BrowserUtils.waitForVisibility(allDayEventCheckbox, 10);
        BrowserUtils.selectOrUnSelectCheckboxOrRadioButton(allDayEventCheckbox, selectOrUnselect);
    }

    public void clickToSelectOrUnselectRepeat(String selectOrUnselect) {
        BrowserUtils.waitForVisibility(repeatCheckbox, 10);
        BrowserUtils.selectOrUnSelectCheckboxOrRadioButton(repeatCheckbox, selectOrUnselect);
    }


    public List<String> getListOfRepeatsOptions() {
        Select select = new Select(repeatsDropdown);
        return BrowserUtils.getListOfString(select.getOptions());
    }

    public String getSummaryText() {
        waitUntilLoaderMaskDisappear();
        BrowserUtils.waitForVisibility(summaryElement, 5);
        //replace all spaces and gaps with single space
        String result = summaryElement.getText().replaceAll("\\s", " ");
        return result;
    }

    public void clickToSelectOrUnselectAfter(String selectOrUnselect) {
        BrowserUtils.waitForVisibility(afteroccurrencesRadioButtonElement, 10);
        BrowserUtils.selectOrUnSelectCheckboxOrRadioButton(afteroccurrencesRadioButtonElement, selectOrUnselect);
    }

    public Integer getAfterOccurrencesValue() {
        BrowserUtils.waitForVisibility(afterOccurrencesInputBoxButtonElement, 10);
        return Integer.parseInt(afterOccurrencesInputBoxButtonElement.getAttribute("value"));
    }

    public void enterAfterOccurrences(String occurrences) {
        Actions actions = new Actions(Driver.get());
        BrowserUtils.waitForVisibility(afterOccurrencesInputBoxButtonElement, 10);
        actions.moveToElement(afterOccurrencesInputBoxButtonElement).pause(300).
                sendKeys(afterOccurrencesInputBoxButtonElement, occurrences).
                click(summaryElement).pause(300).build().perform();
        BrowserUtils.waitForPageToLoad(5);
        BrowserUtils.wait(2);
    }
}

