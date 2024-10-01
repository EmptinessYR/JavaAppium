package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

    static {
        //TITLE = "id:Object-oriented programming language";
        TITLE = "css:#content h1";
        TITLE_XPATH_TPL = "xpath://android.view.View[@content-desc='{TITLE_DESCRIPTION}']";
        TITLE_TPL = "xpath://android.view.View[@content-desc='{TITLE}']";
        FOOTER_ELEMENT = "css:footer";
        SAVE_BUTTON = "xpath://*[@id='ca-watch']";
        NAVIGATE_UP_BUTTON = "id:Back";
        ADD_TO_LIST_BUTTON = "xpath://*[@id='ca-watch' and @title='Add this page to your watchlist']";
        REMOVE_FROM_MY_LIST_BUTTON = "xpath://*[@id='ca-watch' and @title='Remove this page from your watchlist']";
        CREATE_A_NEW_LIST = "xpath://XCUIElementTypeButton[@name='Create a new list']";
        MY_LIST_INPUT = "xpath://XCUIElementTypeTextField[@value='reading list title']";
        MY_LIST_OK_BUTTON = "xpath://XCUIElementTypeButton[@name='Create reading list']";
        SEARCH_CANCEL_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Cancel']";
        SEARCH_CANCEL_CLOSE_BUTTON = "id:Clear text";
        CREATED_LIST_ID_TPL = "id:{NAME_OF_ARTICLE}";
    }

    public MWArticlePageObject (RemoteWebDriver driver)
    {
        super(driver);
    }
}
