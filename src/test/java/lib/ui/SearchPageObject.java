package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.Platform;
import lib.ui.MainPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject {

     protected static String
        SKIP_BUTTON,
        SEARCH_INIT_ELEMENT,
        SEARCH_INPUT_ID,
        SEARCH_CANCEL_BUTTON,
        SEARCH_CANCEL_CLOSE_BUTTON,
        SEARCH_RESULT_BY_SUBSTRING_TPL,
        SEARCH_RESULT_ELEMENT,
        SEARCH_EMPTY_RESULT_LABEL,
        SEARCH_RESULT_LIST,
        TITLE_AND_DESCRIPTION_TOGETHER_TPL,
        SEARCH_EMPTY_RESULT;

    public SearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    /*TEMPLATES METHODS*/
    private static String getResultSearchElement (String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING_TPL}",substring);
    }

    private static String getResultTitleAndDescriptionTogether (String title, String description)
    {
        return TITLE_AND_DESCRIPTION_TOGETHER_TPL.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
    }

    /*TEMPLATES METHODS*/

    @Step("Initialization the search field")
    public void initSearchInput()
    {
        if (driver instanceof AppiumDriver) {
            this.waitForElementAndClick(SKIP_BUTTON, "Cannot find and click Skip-button", 5);
        } else {
            System.out.println("Method waitForElementAndClick() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
    }

    @Step("Initialization the search field without clicking Skip")
    public void initSearchInputWithoutSkip()
    {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
    }

    @Step("Wait for search cancel button to disappear")
    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 5);
    }

    @Step("Wait for search cancel button to appear")
    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
    }

    @Step("Clicking button to cansel search result")
    public void clickCancelSearchButton()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
    }

    @Step("Clicking button to cansel search result")
    public void clickCancelCloseSearchButton()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_CLOSE_BUTTON, "Cannot find and click search/cancel cancel button", 5);
    }

    @Step("Typing '{search_line}' to the search line")
    public void typeSearchLine (String search_line)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT_ID, search_line, "Cannot find and type into search input", 5);
    }

    @Step("Waiting for search results")
    public void waitForSearchResult (String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        System.out.println ("search_result_xpath = " + search_result_xpath);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring);
    }

    @Step("Waiting for search results and select an article by substring in article title")
    public void clickByArticleWithSubstring (String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 10);
    }

    @Step("Getting amount of found articles")
    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request ",
                15
        );

        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    @Step("Waiting for empty result label")
    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_LABEL, "Cannot find empty result label",15);
    }

    @Step("Making sure there are no results for the search")
    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT,"We supposed not to find any results");
    }

    @Step("Waiting for empty search message")
    public void waitForEmptySearchResult()
    {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT,"Cannot find empty search message",15);
    }

    @Step("Making sure that all search results contains expected symbols")
    public void assertNotAllElementsContainsSearchParam(String search_param)
    {
        this.assertElementsContainText(
            SEARCH_RESULT_LIST,
                search_param,
            "Not all search results contain the word '" + search_param + "'",
            15
        );
    }

    @Step("Making sure that article has expected title and description")
    public void waitForElementByTitleAndDescription(String title, String description)
    {
        String title_and_description_xpath = getResultTitleAndDescriptionTogether(title,description);
        this.waitForElementPresent(
                title_and_description_xpath,
                "Cannot find object with title ='" + title + "' and description ='" + description + "'",
                15);
    }
}
