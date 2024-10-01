package lib.ui.mobile_web;

import lib.ui.SavedObjects;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSavedObjects extends SavedObjects {

    static
    {
        LIST_BY_NAME_TPL = "id:{NAME_OF_LIST}";
        ARTICLE_BY_TITLE_TPL = "xpath://*[@id='mw-content-text']//h3[contains(text(),'{TITLE}')]";
        ARTICLE_TITLE_DESCRIPTION_ID = "id:Object-oriented programming language";
        CLOSE_SYNC_MESSAGE = "id:Close";
        READING_LISTS = "xpath://XCUIElementTypeStaticText[@name='Reading lists']";
        DELETE_ARTICLE_FROM_LIST_BUTTON = "id:swipe action delete";
        REMOVE_FROM_SAVED_BUTTON = "xpath://*[@id='mw-content-text']//h3[contains(text(),'{TITLE}')]/../../a[contains(@class,'watched')]";
    }

    public MWSavedObjects (RemoteWebDriver driver)
    {
        super(driver);
    }
}
