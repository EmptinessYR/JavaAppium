package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactorie;
import lib.ui.factories.NavigationUIFactorie;
import lib.ui.factories.SavedObjectsFactorie;
import lib.ui.factories.SearchPageObjectFactorie;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for saved lists")
public class SavedLists extends CoreTestCase {

    private static final String
            TITLE_DESCRIPTION_JAVA = "Object-oriented programming language",
            TITLE_DESCRIPTION_APPIUM = "Automation for Apps",
            NAME_OF_LIST = "Test_list";
    private static final String
        login = "okunevtest",
        password = "12ok34un56ev";


    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value="Article"),@Feature(value="Main menu"),@Feature(value="Saved lists")})
    @DisplayName("Add and delete article from saved list")
    @Description("We open an article and add it in saved list. Then we go to saved list and delete our article from there")
    @Step("Starting test testAddAndDeleteListOfArticles")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testAddAndDeleteListOfArticles()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactorie.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring(TITLE_DESCRIPTION_JAVA);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactorie.get(driver);
        ArticlePageObject.waitForTitleElement(TITLE_DESCRIPTION_JAVA);
        String article_title = ArticlePageObject.getArticleTitle(TITLE_DESCRIPTION_JAVA);

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(NAME_OF_LIST);
            ArticlePageObject.closeArticle();

            NavigationUI NavigationUI = NavigationUIFactorie.get(driver);
            NavigationUI.clickMySavedObjects();

            SavedObjects SavedObjects = SavedObjectsFactorie.get(driver);
            SavedObjects.openListByName(NAME_OF_LIST);
            SavedObjects.swipeByArticleToDelete(article_title);
        } else if (Platform.getInstance().isIOS())
        {
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.addArticleToMyNewListIOS(NAME_OF_LIST);
            ArticlePageObject.closeArticle();
            NavigationUI NavigationUI = NavigationUIFactorie.get(driver);
            NavigationUI.clickMySavedObjects();
            SavedObjects SavedObjects = SavedObjectsFactorie.get(driver);
            SavedObjects.closeMessageAboutSync();
            SavedObjects.goToTargetReadingLists(NAME_OF_LIST);
            SavedObjects.swipeByArticleToDelete(article_title);
        } else {
            ArticlePageObject.addArticlesToMySaved();
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login,password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement(TITLE_DESCRIPTION_JAVA);
            Assert.assertEquals("We are not on the same page after login",
                    article_title,
                    ArticlePageObject.getArticleTitle("Java (programming language)")
            );

            //ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.closeArticle();
            NavigationUI NavigationUI = NavigationUIFactorie.get(driver);
            NavigationUI.openNavigation();
            NavigationUI.clickMySavedObjects();
            SavedObjects SavedObjects = SavedObjectsFactorie.get(driver);
            SavedObjects.swipeByArticleToDelete(article_title);
        }
    }

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value="Article"),@Feature(value="Main menu"),@Feature(value="Saved lists")})
    @DisplayName("Add 2 articles to saved. And delete one of them from saved list")
    @Description("We open 1 article and add it in saved list. We open 2 article and add it in saved list. Then we go to saved list, delete 1 article and check that another article stay in list")
    @Step("Starting test testSaveTwoArticlesInList")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSaveTwoArticlesInList ()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactorie.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring(TITLE_DESCRIPTION_JAVA);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactorie.get(driver);
        ArticlePageObject.waitForTitleElement(TITLE_DESCRIPTION_JAVA);
        String article_title_to_delete = ArticlePageObject.getArticleTitle(TITLE_DESCRIPTION_JAVA);
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(NAME_OF_LIST);
            ArticlePageObject.closeArticle();
            SearchPageObject.initSearchInputWithoutSkip();
            SearchPageObject.typeSearchLine("Appium");
            SearchPageObject.clickByArticleWithSubstring(TITLE_DESCRIPTION_APPIUM);
            ArticlePageObject.waitForTitleElement(TITLE_DESCRIPTION_APPIUM);
            String article_title_check = ArticlePageObject.getArticleTitle(TITLE_DESCRIPTION_APPIUM);
            ArticlePageObject.addArticleToCreatedList(NAME_OF_LIST);
            ArticlePageObject.closeArticle();
            NavigationUI NavigationUI = NavigationUIFactorie.get(driver);
            NavigationUI.clickMySavedObjects();
            SavedObjects SavedObjects = SavedObjectsFactorie.get(driver);
            SavedObjects.openListByName(NAME_OF_LIST);
            SavedObjects.swipeByArticleToDelete2(article_title_to_delete);
            SavedObjects.waitForArticleToAppearByTitle(article_title_check);
        } else if (Platform.getInstance().isIOS())  {
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.addArticleToMyNewListIOS(NAME_OF_LIST);
            ArticlePageObject.closeArticleToSearchNextArticleIOS();
            SearchPageObject.typeSearchLine("Appium");
            SearchPageObject.clickByArticleWithSubstring(TITLE_DESCRIPTION_APPIUM);
            ArticlePageObject.waitForTitleElement(TITLE_DESCRIPTION_APPIUM);
            String article_title_check = ArticlePageObject.getArticleTitle(TITLE_DESCRIPTION_APPIUM);
            ArticlePageObject.addArticleToMyExistingList(NAME_OF_LIST);
            ArticlePageObject.closeArticle();
            NavigationUI NavigationUI = NavigationUIFactorie.get(driver);
            NavigationUI.clickMySavedObjects();
            SavedObjects SavedObjects = SavedObjectsFactorie.get(driver);
            SavedObjects.closeMessageAboutSync();
            SavedObjects.goToTargetReadingLists(NAME_OF_LIST);
            SavedObjects.swipeByArticleToDelete(article_title_to_delete);
            SavedObjects.waitForArticleToAppearByTitle(article_title_check);
        } else if (Platform.getInstance().isMW()) {
            ArticlePageObject.addArticlesToMySaved();
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login,password);
            Auth.submitForm();
            ArticlePageObject.waitForTitleElement(TITLE_DESCRIPTION_JAVA);
            Assert.assertEquals("We are not on the same page after login",
                    article_title_to_delete,
                    ArticlePageObject.getArticleTitle("Java (programming language)")
            );

            //ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.closeArticle();
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine("Appium");
            SearchPageObject.clickByArticleWithSubstring(TITLE_DESCRIPTION_APPIUM);
            String article_title_check = ArticlePageObject.getArticleTitle(TITLE_DESCRIPTION_JAVA);
            System.out.println(article_title_check);
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.closeArticle();
            NavigationUI NavigationUI = NavigationUIFactorie.get(driver);
            NavigationUI.openNavigation();
            NavigationUI.clickMySavedObjects();
            SavedObjects SavedObjects = SavedObjectsFactorie.get(driver);
            SavedObjects.swipeByArticleToDelete(article_title_to_delete);
            SavedObjects.waitForArticleToAppearByTitle(article_title_check);
        }
    }
}
