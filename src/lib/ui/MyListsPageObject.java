package lib.ui;

import io.appium.java_client.AppiumDriver;

public class MyListsPageObject extends MainPageObject{
    private final static String
            FOLDER_BY_NAME_TPL = "xpath://android.widget.TextView[@text='{FOLDER_NAME}']",
            ARTICLE_TITLE_TPL = "xpath://*[@text='{ARTICLE_TITLE}']";

    public MyListsPageObject(AppiumDriver driver){
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getFolderXpathByName(String name_of_folder){
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getArticleTitleXpath (String article_title){
        return ARTICLE_TITLE_TPL.replace("{ARTICLE_TITLE}", article_title);
    }
    /*  TEMPLATES METHODS */

    public void openFolderByName(String name_of_folder){
        String folder_nam_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_nam_xpath,
                "Cannot find folder by name " + name_of_folder,
                10
        );
    }

    public void waitForArticleToAppearByTitle(String article_title){
        String article_title_xpath = getArticleTitleXpath(article_title);
        this.waitForElementPresent(
                article_title_xpath,
                "Cannot find saved article by title " + article_title,
                5
        );
    }

    public void swipeByArticleToDelete(String article_title){
        this.waitForArticleToAppearByTitle(article_title);
        String article_title_xpath = getArticleTitleXpath(article_title);
        this.swipeElementToLeft(
                article_title_xpath,
                "Cannot find saved article"
        );
        this.waitForArticleToDisappearByTitle(article_title);
    }

    public void waitForArticleToDisappearByTitle(String article_title){
        String article_title_xpath = getArticleTitleXpath(article_title);
        this.waitForElementNotPresent(
                article_title_xpath,
                "Saved article still present with title " + article_title,
                5
        );
    }

    public void openArticleByTitle(String article_title){
        String article_title_xpath = getArticleTitleXpath(article_title);
        this.waitForElementAndClick(
                article_title_xpath,
                "Cannot find saved article 'JavaScript' ",
                15
        );
    }
}
