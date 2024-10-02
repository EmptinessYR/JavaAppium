package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactorie;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

@Epic("Tests for search screen")
public class SearchTests extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Check that we have expected item in search list")
    @Description("We open the search screen where put in search 'Java' and compare title description with expected value")
    @Step("Starting test testSearch")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSearch() throws InterruptedException
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactorie.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Check that we have cansel search button and it worked")
    @Description("We open the search screen where put in search 'Java' and press cansel search button")
    @Step("Starting test testCanselSearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCanselSearch ()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactorie.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearchButton();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Check that we have expected amount of search item")
    @Description("We open the search screen where put in search 'Linkin Park discography' and compare amount of result with expected value")
    @Step("Starting test testAmountOfNotEmptySearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAmountOfNotEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactorie.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Check that we have expected message when there is no result of search")
    @Description("We open the search screen where put in search 'sdafdsgndhgjr' and search empty result message")
    @Step("Starting test testAmountOfEmptySearch")
    @Severity(value = SeverityLevel.TRIVIAL)
    public void testAmountOfEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactorie.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "sdafdsgndhgjr";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*/*[@resource-id='org.wikipedia:id/page_list_item_title']";
        String empty_result_label = "//*[@text='No results']";
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Check that we have expected message when there is no result of search after search")
    @Description("We open the search screen where put in search 'Java', click Cansel and search empty result message")
    @Step("Starting test testSearchAndCancle")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSearchAndCancle ()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactorie.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        SearchPageObject.clickCancelCloseSearchButton();
        SearchPageObject.waitForEmptySearchResult();
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Check that all result of search contains expected text")
    @Description("We open the search screen where put in search 'Java' and check that all results titles contains 'java'")
    @Step("Starting test testSearchAndCompareResult")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSearchAndCompareResult ()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactorie.get(driver);
        String search_param = "Java";
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_param);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        SearchPageObject.assertNotAllElementsContainsSearchParam(search_param);
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Check that after search we see expected item with Title and Description")
    @Description("We open the search screen where put in search 'Java' and check that we see item with title 'Java (programming language)' and description 'Object-oriented programming language'")
    @Step("Starting test testSearchAndCompareTitleDescription")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSearchAndCompareTitleDescription ()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactorie.get(driver);
        String article_title = "Java (programming language)";
        String article_description = "Object-oriented programming language";
        String search_param = "Java";
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_param);
        SearchPageObject.waitForElementByTitleAndDescription(article_title,article_description);
    }

}
