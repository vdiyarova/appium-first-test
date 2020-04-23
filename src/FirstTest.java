import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.*;

public class FirstTest extends CoreTestCase {
    //Tests
    private MainPageObject MainPageObject;

    protected void setUp() throws Exception{
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }


    @Test
    public void testCompareInputSearchText() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        WebElement input_search = MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find article title",
                15
        );

        String input_search_text = input_search.getAttribute("text");

        assertEquals(
                "Text 'Search…' is missing in the input",
                "Search…",
                input_search_text
        );
    }

    @Test
    public void testSearchCancel() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Island of Indonesia']"),
                "Cannot find 'Island of Indonesia' topic searching by 'Java'",
                15
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Programming language']"),
                "Cannot find 'Programming language' topic searching by 'Java'",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find 'Cancel' button",
                5
        );

        Boolean secondResultNotPresent = MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Programming language']"),
                "'Programming language' is still present on the page",
                5
        );

        Boolean firstResultNotPresent = MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Island of Indonesia']"),
                "'Island of Indonesia' is still present on the page",
                5
        );

        assertTrue("Search results still present", firstResultNotPresent & secondResultNotPresent);
    }

    // 3 lesson



     @Test
    public void testSaveTwoArticles(){
        // search
         MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "Java";

         MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                search_line,
                "Cannot find search input",
                5
        );

        // open first article

         MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by " + search_line,
                15
        );

         MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );

        // add first article to read list

         MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5

        );

         MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Add to reading list']"),
                "Cannot find options to add article to read list",
                5
        );

        // create folder

         MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find button 'Got it'",
                5
        );

         MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of articles folder",
                5
        );

        String name_of_folder = "Learning programming";

         MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot find input to set name of articles folder",
                5
        );

         MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press 'OK' button fot create article folder",
                5
        );

        // search second article

         MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/menu_page_search"),
                "Cannot find 'Search' button",
                5
        );

         MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

         MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                search_line,
                "Cannot find search input",
                5
        );

        // open second article

         MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='JavaScript']"),
                "Cannot find 'JavaScript' topic searching by " + search_line,
                15
        );

        WebElement title_before = MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );

        String title_before_saving = title_before.getAttribute("text");

        // add second article to read list

         MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5

        );

         MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Add to reading list']"),
                "Cannot find options to add article to read list",
                5
        );

         MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='" + name_of_folder + "']"),
                "Cannot find folder " + name_of_folder,
                5
        );

        // go to read list

         MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find button to close article",
                5
        );

         MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find button 'My lists'",
                5
        );

        // open folder

         MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='"+ name_of_folder +"']"),
                "Cannot find folder " + name_of_folder,
                10
        );

        // delete first article

         MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article"
        );

         MainPageObject.assertElementNotPresent(
                 By.xpath("//*[@text='Java (programming language)']"),
                 "Article has not been deleted"
         );

        // go to second article

         MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='JavaScript']"),
                "Cannot find saved article 'JavaScript' ",
                15
        );

        // check second article title

        WebElement title_after = MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );

        String title_after_saving = title_after.getAttribute("text");

      assertEquals(
                "Title of saved article does not match title of open article",
                title_before_saving,
                title_after_saving
        );
    }

    @Test
    public void testTitlePresent(){

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_line = "Java";

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                search_line,
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by " + search_line,
                15
        );

        MainPageObject.assertElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find title"
        );
    }
}
