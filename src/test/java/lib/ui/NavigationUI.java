package lib.ui;

import lib.Platform;
import lib.ui.MainPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject {

   protected static String
      SAVED_OBJECTS,
      OPEN_NAVIGATION;

   public NavigationUI (RemoteWebDriver driver)
   {
      super(driver);
   }

   public void openNavigation()
   {
      if (Platform.getInstance().isMW()) {
         this.waitForElementAndClick(OPEN_NAVIGATION, "Cannot find and click navigation button", 5);
      } else {
         System.out.println("Method openNavigation() does nothing for platform " + Platform.getInstance().getPlatformVar());
      }
   }

   public void clickMySavedObjects ()
   {
      if (Platform.getInstance().isMW()) {
         this.tryClickElementWithFewAttempts(
                 SAVED_OBJECTS,
                 "Cannot find Saved objects",
                 5
         );
      } else {
         //Выходим в сохраненные объекты
         this.waitForElementAndClick(
                 SAVED_OBJECTS,
                 "Cannot find Saved objects",
                 5
         );
      }
   }
}
