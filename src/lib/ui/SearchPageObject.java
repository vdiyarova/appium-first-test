package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchPageObject extends MainPageObject {
    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains(@text, 'Search…')]",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
            EMPTY_RESULT_LABEL = "//*[contains(@text, 'No results found')]",
            SEARCH_INPUT_TEXT = "org.wikipedia:id/search_src_text";

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS */

    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /*  TEMPLATES METHODS */

    public void initSearchInput(){
        this.waitForElementAndClick(
                By.xpath(SEARCH_INIT_ELEMENT),
                "Cannot find and click search init element",
                5
        );
        this.waitForElementPresent(
                By.xpath(SEARCH_INPUT),
                "Cannot find search input after click"
        );
    }

    public void typeSearchLine(String search_line){
        this.waitForElementAndSendKeys(
                By.xpath(SEARCH_INPUT),
                search_line,
                "Cannot find and type into search input",
                5
        );
    }

    public void waitForSearchResult(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(
                By.xpath(search_result_xpath),
                "Cannot find search result with substring" + substring,
                15
        );
    }

    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(
                By.id(SEARCH_CANCEL_BUTTON),
                "Cannot find search cancel button",
                5
        );
    }

    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(
                By.id(SEARCH_CANCEL_BUTTON),
                "Search cancel button is still present on the page",
                5
        );
    }

    public void clickCancelSearch(){
        this.waitForElementAndClick(
                By.id(SEARCH_CANCEL_BUTTON),
                "Cannot find and click to search button",
                5
        );
    }

    public void clickByArticleWihSubstring(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(
                By.xpath(search_result_xpath),
                "Cannot find and click search result with substring" + substring,
                10
        );
    }

    public int getAmountOfFoundArticles(){
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find anything by the request ",
                15
        );
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel(){
        this.waitForElementPresent(
                By.xpath(EMPTY_RESULT_LABEL),
                "Cannot find empty result label by the request ",
                15);
    }

    public void assertThereIsNoResultOfSearch(){
        this.assertElementNotPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "We supposed not find any results"
        );
    }

    public WebElement waitForInputSearchText(){
        return this.waitForElementPresent(
                By.id(SEARCH_INPUT_TEXT),
                "Cannot find element 'Search...'"
        );
    }

    public String getInputSearchText(){
        return waitForInputSearchText().getAttribute("text");
    }
}
