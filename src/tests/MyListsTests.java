package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    @Test
    public void testSaveArticleToMyList(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWihSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";
        ArticlePageObject.saveArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyList();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticles() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput(); // search first article
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWihSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String first_article_title = ArticlePageObject.getArticleTitle(); // check first article title
        String name_of_folder = "Learning programming";
        ArticlePageObject.saveArticleToMyList(name_of_folder); // save first article
        ArticlePageObject.clickSearchButton(); // search second article
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWihSubstring("JavaScript");
        ArticlePageObject.waitForTitleElement();
        String title_before_saving = ArticlePageObject.getArticleTitle(); //check second article title
        ArticlePageObject.saveArticleToAlreadyCreatedFolder(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver); // go to my list
        NavigationUI.clickMyList();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder); // open created folder
        MyListsPageObject.swipeByArticleToDelete(first_article_title);  // delete first article
        MyListsPageObject.openArticleByTitle(title_before_saving); // open second article

        String title_after_saving = ArticlePageObject.getArticleTitle(); // get article title after saving

        assertEquals(
                "Title of saved article does not match title of open article",
                title_before_saving,
                title_after_saving
        );
    }
}
