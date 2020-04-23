package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{
    private static final String
            TITLE = "org.wikipedia:id/view_page_title_text",
            FOOTER_ELEMENT = "//*[@text='View page in browser']",
            OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
            OPTIONS_ADD_TO_MY_LIST = "//android.widget.TextView[@text='Add to reading list']",
            ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
            MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "//*[@text='OK']",
            CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
            SEARCH_ARTICLE_BUTTON = "org.wikipedia:id/menu_page_search",
            MY_LIST_FOLDER_TPL = "//android.widget.TextView[@text='{NAME_OF_FOLDER}']";

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS */

    private static String getNameFolder(String name_of_folder){
        return MY_LIST_FOLDER_TPL.replace("{NAME_OF_FOLDER}", name_of_folder);
    }
    /*  TEMPLATES METHODS */


    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(
                By.id(TITLE),
                "Cannot find article title",
                15
        );
    }

    public String getArticleTitle(){
        return waitForTitleElement().getAttribute("text");
    }

    public void swipeToFooter(){
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of article",
                20
        );
    }

    public void saveArticleToMyList(String name_of_folder){
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find button to open article options",
                5
        );

        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST),
                "Cannot find options to add article to read list",
                5
        );

        this.waitForElementAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY),
                "Cannot find button 'Got it'",
                5
        );

        this.waitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT),
                "Cannot find input to set name of articles folder",
                5
        );

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot find input to set name of articles folder",
                5
        );

        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Cannot press 'OK' button fot create article folder",
                5
        );
    }

    public void saveArticleToAlreadyCreatedFolder(String name_of_folder){
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find button to open article options",
                5
        );

        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST),
                "Cannot find options to add article to read list",
                5
        );

        String my_list_xpath = getNameFolder(name_of_folder);
        this.waitForElementAndClick(
                By.xpath(my_list_xpath),
                "Cannot find folder " + name_of_folder,
                5
        );
    }

    public void closeArticle(){
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot find button to close article",
                5
        );
    }

    public void clickSearchButton(){
        this.waitForElementAndClick(
                By.id(SEARCH_ARTICLE_BUTTON),
                "Cannot find 'Search' button",
                5
        );
    }

    public void assertArticleTitlePresent(){
        this.assertElementPresent(
                By.id(TITLE),
                "Cannot find title"
        );
    }

}
