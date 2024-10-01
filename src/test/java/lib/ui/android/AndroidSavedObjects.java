package lib.ui.android;

import lib.ui.SavedObjects;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSavedObjects extends SavedObjects {

    static
    {
        LIST_BY_NAME_TPL = "xpath://*[@resource-id='org.wikipedia:id/item_title' and @text='{LIST_NAME}']";
        ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']";
        ARTICLE_TITLE_DESCRIPTION_ID = "id:org.wikipedia:id/page_list_item_description";
    }

    public AndroidSavedObjects (RemoteWebDriver driver)
    {
        super(driver);
    }
}
