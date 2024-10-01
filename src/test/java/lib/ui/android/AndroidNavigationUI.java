package lib.ui.android;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUI extends NavigationUI {

static {
    SAVED_OBJECTS = "xpath://android.widget.FrameLayout[@content-desc=\"Saved\"]/android.widget.FrameLayout/android.widget.ImageView";
}

    public AndroidNavigationUI (RemoteWebDriver driver)
    {
        super(driver);
    }
}
