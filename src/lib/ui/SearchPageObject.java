package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class SearchPageObject extends MainPageObject {
    private static final String
            SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "xpath://*[contains(@text, 'Search…')]",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
            SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
            EMPTY_RESULT_LABEL = "xpath://*[contains(@text, 'No results found')]",
            SEARCH_INPUT_TEXT = "id:org.wikipedia:id/search_src_text";
//            SEARCH_RESULT_BY_SUBSTRINGS_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']//*[@text='{TITLE}']";

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
                SEARCH_INIT_ELEMENT,
                "Cannot find and click search init element",
                5
        );
        this.waitForElementPresent(
                SEARCH_INPUT,
                "Cannot find search input after click"
        );
    }

    public void typeSearchLine(String search_line){
        this.waitForElementAndSendKeys(
                SEARCH_INPUT,
                search_line,
                "Cannot find and type into search input",
                5
        );
    }

    public void waitForSearchResult(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(
               search_result_xpath,
                "Cannot find search result with substring" + substring,
                15
        );
    }

    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(
                SEARCH_CANCEL_BUTTON,
                "Cannot find search cancel button",
                5
        );
    }

    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(
                SEARCH_CANCEL_BUTTON,
                "Search cancel button is still present on the page",
                5
        );
    }

    public void clickCancelSearch(){
        this.waitForElementAndClick(
                SEARCH_CANCEL_BUTTON,
                "Cannot find and click to search button",
                5
        );
    }

    public void clickByArticleWihSubstring(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(
                search_result_xpath,
                "Cannot find and click search result with substring" + substring,
                10
        );
    }

    public int getAmountOfFoundArticles(){
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request ",
                15
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel(){
        this.waitForElementPresent(
                EMPTY_RESULT_LABEL,
                "Cannot find empty result label by the request ",
                15);
    }

    public void assertThereIsNoResultOfSearch(){
        this.assertElementNotPresent(
                SEARCH_RESULT_ELEMENT,
                "We supposed not find any results"
        );
    }

    public WebElement waitForInputSearchText(){
        return this.waitForElementPresent(
                SEARCH_INPUT_TEXT,
                "Cannot find element 'Search...'"
        );
    }

    public String getInputSearchText(){
        return waitForInputSearchText().getAttribute("text");
    }

//    public void waitForTitleAndDescription(){
//        this.waitForElementPresent(//*[@resource-id='org.wikipedia:id/page_list_item_container']/*[@text='Java (programming language)' and @text='Object-oriented programming language']
//                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Java (programming language)'][@text='Object-oriented programming language']"),
//                "Ne poluchilos(",
//                15
//        );
//    }
}
