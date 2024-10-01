package lib.ui;

import lib.Platform;
import lib.ui.MainPageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject
{
    protected static String
        TITLE,
        TITLE_XPATH_TPL,
        TITLE_TPL,
        FOOTER_ELEMENT,
        SAVE_BUTTON,
        ADD_TO_LIST_BUTTON,
        REMOVE_FROM_MY_LIST_BUTTON,
        MY_LIST_INPUT,
        MY_LIST_OK_BUTTON,
        CREATED_LIST_ID,
        TITLE_DESCRIPTION_XPATH,
        CREATE_A_NEW_LIST,
        SEARCH_CANCEL_BUTTON,
        NAVIGATE_UP_BUTTON,
        SEARCH_CANCEL_CLOSE_BUTTON,
        CREATED_LIST_ID_TPL;

    public ArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    private String getTitleDescriptionXpathByTitle(String description_title)
    {
        if (Platform.getInstance().isAndroid()) {
        return TITLE_XPATH_TPL.replace("{TITLE_DESCRIPTION}", description_title);
        } else {
            return TITLE.replace("{TITLE_DESCRIPTION}", description_title);
        }

    }

    private String getTitleOfExistingList(String name_of_list)
    {
        return CREATED_LIST_ID_TPL.replace("{NAME_OF_ARTICLE}", name_of_list);
    }

    public WebElement waitForTitleElement(String title_description)
    {
        String title_description_xpath = getTitleDescriptionXpathByTitle(title_description);
        return this.waitForElementPresent(title_description_xpath, "Cannot find article on page!", 15);
    }

    public String getArticleTitle(String title_description)
    {
        WebElement title_element = waitForTitleElement(title_description);
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isIOS()) {
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }
    }

    public void swipeToFooter()
    {
        if (Platform.getInstance().isAndroid()){
        this.swipeUpToFindElement(
                FOOTER_ELEMENT,
                "Cannot find the end of article",
                20
        );
        } else if (Platform.getInstance().isIOS()) {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT,
            "Cannot find the end of article",
            20
            );
        } else if (Platform.getInstance().isMW()) {
            this.scrollWebPageTillElementNotVisible(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40
            );
        }

    }

    public void addArticleToMyNewListIOS(String name_of_list)
    {
        //Кликаем на Save
        /*this.waitForElementAndClick(
                SAVE_BUTTON,
                "Cannot find element 'Save'",
                5
        );*/

        //Кликаем на "+"
        this.waitForElementAndClick(
                ADD_TO_LIST_BUTTON,
                "Cannot find element 'Add to List'",
                5
        );

        //создаем новый список
        this.waitForElementAndClick(
                CREATE_A_NEW_LIST,
                "Cannot find button '+ Create a new list'",
                5
        );

        //Задаем данные в Name of this list
        this.waitForElementAndSendKeys(
                MY_LIST_INPUT,
                name_of_list,
                "Cannot find Name of this list",
                5
        );

        //кликаем на Ok
        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot find button Ok",
                5
        );
    }

    public void addArticleToMyExistingList(String name_of_list)
    {
        this.waitForElementAndClick(
                SAVE_BUTTON,
                "Cannot find element 'Save to later'",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_LIST_BUTTON,
                "Cannot find element 'Add to List'",
                5
        );

        String name_of_existing_list = getTitleOfExistingList(name_of_list);

        this.waitForElementAndClick(
                name_of_existing_list,
                "Cannot find element 'Add to List'",
                5
        );
    }

    public void addArticleToMyList (String name_of_list)
    {
        //Кликаем на Save
        this.waitForElementAndClick(
                SAVE_BUTTON,
                "Cannot find element 'Save'",
                5
        );

        //Кликаем на Add to List
        this.waitForElementAndClick(
                ADD_TO_LIST_BUTTON,
                "Cannot find element 'Add to List'",
                5
        );

        //Задаем данные в Name of this list
        this.waitForElementAndSendKeys(
                MY_LIST_INPUT,
                name_of_list,
                "Cannot find Name of this list",
                5
        );

        //кликаем на Ok
        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot find button Ok",
                5
        );
    }

    public void addArticleToCreatedList (String name_of_list)
    {
        //Кликаем на Save
        this.waitForElementAndClick(
                SAVE_BUTTON,
                "Cannot find element 'Save'",
                5
        );

        //Кликаем на Add to List
        this.waitForElementAndClick(
                ADD_TO_LIST_BUTTON,
                "Cannot find element 'Add to List'",
                5
        );

        //Выбираем нужный нам список
        this.waitForElementAndClick(
                CREATED_LIST_ID,
                "Cannot find list " + name_of_list,
                5
        );
    }

    public void addArticlesToMySaved()
    {
        if (Platform.getInstance().isMW()){
            this.removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(SAVE_BUTTON, "Cannot find option to add article to saved list", 5);
    }

    public void removeArticleFromSavedIfItAdded ()
    {
        if (this.isElementPresent(REMOVE_FROM_MY_LIST_BUTTON)) {
            this.waitForElementAndClick(
                    REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click button to remove an article from saved",
                    1
            );
            this.waitForElementPresent(
                    REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot find button to add an article to saved list after removing it from this list before"
            );
        }
    }

    public void closeArticle()
    {
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {
            //Выходим на страницу поиска
            this.waitForElementAndClick(
                    NAVIGATE_UP_BUTTON,
                    "Cannot find button Navigate up",
                    5
            );

            if (Platform.getInstance().isAndroid()) {
                //Выходим на главную страницу
                this.waitForElementAndClick(
                        NAVIGATE_UP_BUTTON,
                        "Cannot find button Navigate up in Search",
                        5
                );
            } else {
                this.waitForElementAndClick(
                        SEARCH_CANCEL_BUTTON,
                        "Cannot find button Navigate up in Search",
                        5
                );
            }
        } else {
            System.out.println("Method close article do nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void closeArticleToSearchNextArticleIOS()
    {
        //Выходим на страницу поиска
        this.waitForElementAndClick(
                NAVIGATE_UP_BUTTON,
                "Cannot find button Navigate up",
                5
        );
        this.waitForElementAndClick(
                SEARCH_CANCEL_CLOSE_BUTTON,
                "Cannot find cansel search button",
                5
        );
    }

    public void assertElementPresentArticleTitleDescription()
    {
        // Проверяем наличие элемента pcs-edit-section-title-description, так как у Заголовка нет ID
        this.assertElementPresent(
                TITLE_DESCRIPTION_XPATH,
                "Title description element is not present"
        );
    }
}
