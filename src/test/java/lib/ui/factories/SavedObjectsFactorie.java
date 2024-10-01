package lib.ui.factories;

import lib.Platform;
import lib.ui.SavedObjects;
import lib.ui.android.AndroidSavedObjects;
import lib.ui.ios.iOSSavedObjects;
import lib.ui.mobile_web.MWNavigationUI;
import lib.ui.mobile_web.MWSavedObjects;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SavedObjectsFactorie
{
    public static SavedObjects get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidSavedObjects(driver);
        } else if (Platform.getInstance().isIOS()){
            return new iOSSavedObjects(driver);
        }  else {
            return new MWSavedObjects(driver);
        }
    }
}
