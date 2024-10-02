package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactorie;
import lib.ui.factories.SearchPageObjectFactorie;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for articles")
public class ArticleTests extends CoreTestCase {

    private static final String
            TITLE_DESCRIPTION_JAVA = "Object-oriented programming language",
            TITLE_DESCRIPTION_APPIUM = "Automation for Apps";

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value="Article")})
    @DisplayName("Compare article title with expected one")
    @Description("We open an article 'Java (programming language)' and compare title description with expected value")
    @Step("Starting test testCompareArticleTitle")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCompareArticleTitle()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactorie.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring(TITLE_DESCRIPTION_JAVA);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactorie.get(driver);

        if (Platform.getInstance().isIOS()) {
            try {
                // Добавляем ожидание на 5 секунд перед переходом к следующему шагу
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String article_title = ArticlePageObject.getArticleTitle(TITLE_DESCRIPTION_JAVA);

        //ArticlePageObject.takeScreenshot("article_page");

        if (Platform.getInstance().isMW()) {
            Assert.assertEquals(
                    "We see unexpected title",
                    "Java (programming language)",
                    article_title
            );
        } else {
            Assert.assertEquals(
                    "We see unexpected title",
                    TITLE_DESCRIPTION_JAVA,
                    article_title
            );
        }

    }

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value="Article")})
    @DisplayName("Swipe article to the footer")
    @Description("We open an article and  swipe it to the footer")
    @Step("Starting test testSwipeArticle")
    @Severity(value = SeverityLevel.MINOR)
    public void testSwipeArticle()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactorie.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring(TITLE_DESCRIPTION_APPIUM);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactorie.get(driver);
        ArticlePageObject.waitForTitleElement(TITLE_DESCRIPTION_APPIUM);
        ArticlePageObject.swipeToFooter();
    }

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value="Article")})
    @DisplayName("Article has expected description")
    @Severity(value = SeverityLevel.TRIVIAL)
    public void testArticleHasTitleDescription()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactorie.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring(TITLE_DESCRIPTION_JAVA);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactorie.get(driver);
        ArticlePageObject.assertElementPresentArticleTitleDescription();
    }
}
