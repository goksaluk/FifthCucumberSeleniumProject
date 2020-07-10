package com.fleet.pages;

import com.fleet.utilities.BrowserUtils;
import com.fleet.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;

public class CalendarEventsPage extends BasePage {

    @FindBy(css = "[title='Create Calendar event']")
    public WebElement createCalendarEvent;

    @FindBy(css = "span[class='grid-header-cell__label']")
    public List<WebElement> columnNames;

    @FindBy(css = "button[class*='btn dropdown-toggle']")
    public WebElement viewPerPageToggle;

    @FindBy(css = "[class='grid-row row-click-action']:nth-of-type(17)>[class='action-cell grid-cell grid-body-cell']>div>div>a")
    public WebElement threeDots;

    @FindBy(css ="[class='grid-row row-click-action']:nth-of-type(17)>td:nth-of-type(9) ul a")
    public List<WebElement> viewDeleteEdit;

    @FindBy(css = "[class='fa-cog hide-text']")
    public WebElement gridButton;

    @FindBy(xpath = "//tr//td[3]")
    public List<WebElement>  allGrids;

    @FindBy(linkText = "Create Calendar Event")
    public WebElement cceButton;

    @FindBy(css = "[class='caret']")
    public WebElement sacElement;

    @FindBy(xpath = "(//ul[@class='dropdown-menu'])[3]/li")
    public List<WebElement> saveAndCloseElements;

    @FindBy(css = "[class='pull-left btn-group icons-holder']>a")
    public WebElement cancel;

    @FindBy(css = "[class='oro-subtitle']")
    public WebElement ccePageSubtitle;

    @FindBy(xpath = "(//ul[@class ='ui-timepicker-list'])[1]/li\")")
    public List<WebElement> startTime;


    @FindBy(xpath = "(//input[@placeholder='time'])[1]")
    public WebElement time_1Box;

    @FindBy(xpath = "((//ul[@class ='ui-timepicker-list'])[1]/li)[13]")
    public WebElement sixAM;

    @FindBy(linkText = "((//ul[@class ='ui-timepicker-list'])[2]/li)[1]")
    public WebElement sevenAM;


    @FindBy(xpath = "(//input[@placeholder='time'])[2]")
    public WebElement time_2Box;

    public List<String> timeStart2() {

        List<WebElement> timeMenu = Driver.get().findElements(By.xpath("(//ul[@class ='ui-timepicker-list'])[2]/li"));
        List<String> timeItem2 = new ArrayList<>();

        for (WebElement each : timeMenu) {
            timeItem2.add(each.getText());
        }
        return timeItem2;
    }





    @FindBy(css = "[class*='btn-group'] [class='dropdown-menu pull-right'] li")
    public List<WebElement> viewPerPageOptions;

    @FindBy(css = "td div[class='dropdown']")
    public List<WebElement> evenOptions;

    @FindBy(xpath = "(//span[@class = 'grid-header-cell__label'])[1]")
    public WebElement TitleColunm;

    @FindBy(css = "[title='Grid Settings']")
    public WebElement gridSettings;

    @FindBy(xpath = "//div[text()='Grid Settings']")
    public WebElement gridSettingsTitle;

    public By gridSettingsTitleBy = By.xpath("//div[text()='Grid Settings']");



    public void clickToCreateCalendarEvent() {
        BrowserUtils.waitForVisibility(createCalendarEvent, 5);
        BrowserUtils.waitForClickablility(createCalendarEvent, 5);
        createCalendarEvent.click();
    }

    public List<String> getViewDeleteEdit(){
        return BrowserUtils.getListOfString(viewDeleteEdit);
    }

    public void clickGridButton(){
        BrowserUtils.waitForVisibility(gridButton,5);
        BrowserUtils.waitForClickablility(gridButton,5);
        gridButton.click();
    }

    public List<String> getColumnNames() {
        return BrowserUtils.getListOfString(columnNames);
    }

    public List<String> getSaveAndCloseElements(){

        return BrowserUtils.getListOfString(saveAndCloseElements);
    }

    public List<String> getViewPerPageOptions() {
        BrowserUtils.waitForVisibility(viewPerPageToggle, 10);
        BrowserUtils.clickWithWait(viewPerPageToggle);
        return BrowserUtils.getListOfString(viewPerPageOptions);
    }

    public void GridSettings(int firstIndex, int lastIndex){

        for( int i = firstIndex; i<=lastIndex; i++) {

            allGrids.get(i).click();

        }
    }

    public WebElement getCalendarEventOption(String title, String optionText) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), 15);
        Actions actions = new Actions(Driver.get());
        optionText = optionText.substring(0, 1).toUpperCase() + optionText.substring(1).toLowerCase();

        By threeDotsBy = By.xpath("//td[normalize-space()='" + title + "']/following-sibling::td//a[@class='dropdown-toggle']");
        By optionBy = By.xpath("//ul[@class='dropdown-menu dropdown-menu__action-cell launchers-dropdown-menu detach dropdown-menu__floating']//a[@title='" + optionText + "']");

        System.out.println(optionBy.toString());
        wait.until(ExpectedConditions.presenceOfElementLocated(threeDotsBy));

        WebElement threeDotsElement = Driver.get().findElement(threeDotsBy);
        wait.until(ExpectedConditions.visibilityOf(threeDotsElement));

        actions.moveToElement(threeDotsElement).pause(500).build().perform();

        wait.until(ExpectedConditions.presenceOfElementLocated(optionBy));
        WebElement optionWebElement = Driver.get().findElement(optionBy);
        System.out.println(Driver.get().findElements(optionBy).size());
        wait.until(ExpectedConditions.visibilityOf(optionWebElement));

        return optionWebElement;
    }

    public void selectGridSettings(String option, String selectOrUnselect) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), 10);
        openGridSettings();
        option = option.substring(0, 1).toUpperCase() + option.substring(1).toLowerCase();
        By gridOptionBy = By.xpath("//table[@class='grid table-hover table table-condensed']//label[text()='" + option + "']/../following-sibling::td//input[@type='checkbox']");
        wait.until(ExpectedConditions.presenceOfElementLocated(gridOptionBy));
        WebElement gridOptionElement = Driver.get().findElement(gridOptionBy);
        if (selectOrUnselect.equalsIgnoreCase("selects") && !gridOptionElement.isSelected()) {
            BrowserUtils.clickWithWait(gridOptionElement);
        } else if ((selectOrUnselect.contains("deselects") || selectOrUnselect.contains("unselects")) && gridOptionElement.isSelected()) {
            BrowserUtils.clickWithWait(gridOptionElement);
        }
    }

    public void openGridSettings() {
        waitUntilLoaderMaskDisappear();
        if (Driver.get().findElements(gridSettingsTitleBy).size() == 0 || !gridSettingsTitle.isDisplayed()) {
            BrowserUtils.waitForVisibility(gridSettings, 10);
            BrowserUtils.clickWithWait(gridSettings);
            BrowserUtils.waitForVisibility(gridSettings, 5);
        }
    }

    public boolean verifyGridOptionIsSelected(String optionText, String selectedOrUnselected) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), 10);
        openGridSettings();
        optionText = optionText.substring(0, 1).toUpperCase() + optionText.substring(1).toLowerCase();
        By gridOptionBy = By.xpath("//table[@class='grid table-hover table table-condensed']//label[text()='" + optionText + "']/../following-sibling::td//input[@type='checkbox']");
        wait.until(ExpectedConditions.presenceOfElementLocated(gridOptionBy));
        WebElement gridOptionElement = Driver.get().findElement(gridOptionBy);
        return gridOptionElement.isSelected();
    }


}