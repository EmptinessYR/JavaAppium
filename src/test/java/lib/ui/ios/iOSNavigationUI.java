package lib.ui.ios;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSNavigationUI extends NavigationUI {

    static {
        SAVED_OBJECTS = "id:Saved";
    }

    public iOSNavigationUI (RemoteWebDriver driver)
    {
        super(driver);
    }
}
