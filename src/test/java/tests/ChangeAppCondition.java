package tests;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Epic;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactorie;
import lib.ui.factories.SearchPageObjectFactorie;
import org.junit.Assert;
import org.junit.Test;
import java.time.Duration;

@Epic("Tests for change devise conditions")
public class ChangeAppCondition extends CoreTestCase {

    String TITLE_DESCRIPTION_JAVA = "Object-oriented programming language";


    @Test
    public void testChangeScreenOrientationSearchResults ()
    {
        if (Platform.getInstance().isMW()) {
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactorie.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(TITLE_DESCRIPTION_JAVA);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactorie.get(driver);
        //Ищем подзаголовок статьи
        String undertitel_before_rotation = ArticlePageObject.getArticleTitle(TITLE_DESCRIPTION_JAVA);
        // Вывод значения переменной в консоль
        System.out.println("undertitle before rotation: " + undertitel_before_rotation);

        this.rotateScreenLandscape();

        //Ищем подзаголовок статьи
        String undertitel_after_rotation = ArticlePageObject.getArticleTitle(TITLE_DESCRIPTION_JAVA);
        // Вывод значения переменной в консоль
        System.out.println("undertitle after rotation: " + undertitel_after_rotation);

        Assert.assertEquals(
                "Article undertitle have been changed after screen rotation",
                undertitel_before_rotation,
                undertitel_after_rotation
        );

        this.rotateScreenPortrait();

        //Ищем подзаголовок статьи
        String undertitel_after_second_rotation = ArticlePageObject.getArticleTitle(TITLE_DESCRIPTION_JAVA);

        // Вывод значения переменной в консоль
        System.out.println("undertitle after 2 rotation: " + undertitel_after_second_rotation);

        Assert.assertEquals(
                "Article undertitle have been changed after screen rotation",
                undertitel_before_rotation,
                undertitel_after_second_rotation
        );

    }

    @Test
    public void  testCheckSearchArticleInBackGround()
    {
        if (Platform.getInstance().isMW()) {
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactorie.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForSearchResult(TITLE_DESCRIPTION_JAVA);

        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            this.backgroundApp(2);
            driver.runAppInBackground(Duration.ofSeconds(2));
        } else {
            System.out.println("Method backgroundApp() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }

        SearchPageObject.waitForSearchResult(TITLE_DESCRIPTION_JAVA);
    }

/*  Тест для истории, todo сдеалать отдельный набор тестов с главной страницей
    @Test
    public void testOnbordingSwipe()
    {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find button Skip",
                5
        );

        MainPageObject.swipeUpToFindElement(
                By.id("org.wikipedia:id/footerActionButton"),
                "Cannot find button-link 'More top read'",
                20
        );
    }*/
}
