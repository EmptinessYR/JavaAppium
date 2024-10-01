package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    static {
        SKIP_BUTTON = "css:button#searchIcon";
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT_ID = "css:form>input[type='search']"; //в андроид версии есть id
        SEARCH_CANCEL_BUTTON = "css:button.cancel";
        SEARCH_CANCEL_CLOSE_BUTTON = "css:button.clear";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://div[contains(@class, 'wikidata-description')][contains(text(), '{SUBSTRING_TPL}')]";
        SEARCH_RESULT_ELEMENT = "css:ul.mw-mf-page-list>li.page-summary";
        SEARCH_EMPTY_RESULT_LABEL = "css:p.without-results";
        SEARCH_RESULT_LIST = "xpath://XCUIElementTypeOther[2]//XCUIElementTypeStaticText[not(@name='No recent searches yet')][1]";
        TITLE_AND_DESCRIPTION_TOGETHER_TPL = "xpath://XCUIElementTypeStaticText[@name='{TITLE}']/../XCUIElementTypeStaticText[@name='{DESCRIPTION}']";
        SEARCH_EMPTY_RESULT = "id:No recent searches yet";
    }

    public MWSearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
