package lib.ui.ios;

import lib.ui.SavedObjects;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSSavedObjects extends SavedObjects {
    static
    {
        LIST_BY_NAME_TPL = "id:{NAME_OF_LIST}";
        ARTICLE_BY_TITLE_TPL = "id:{TITLE}";
        ARTICLE_TITLE_DESCRIPTION_ID = "id:Object-oriented programming language";
        CLOSE_SYNC_MESSAGE = "id:Close";
        READING_LISTS = "xpath://XCUIElementTypeStaticText[@name='Reading lists']";
        DELETE_ARTICLE_FROM_LIST_BUTTON = "id:swipe action delete";
    }

    public iOSSavedObjects (RemoteWebDriver driver)
    {
        super(driver);
    }

}
