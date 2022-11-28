package libs.tests;
import libs.CoreTestCase;
import libs.ui.SearchInList;
import libs.ui.SearchPageObject;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Selenium");
        SearchPageObject.clickByArticleWithSubstrDescription("Testing framework for web applications");

    }

    @Test


    public void testSearchList() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        SearchInList SearchInList = new SearchInList(driver);
        SearchInList.PresentSearchResult();
        SearchPageObject.initSearchButtonX();
        SearchPageObject.checkOutResult();
    }


    @Test
    public void testAmountOfEmptySearch() {
        String search_line = "liwdqwdawdasdsadasd";
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.getEmptyResult();
        SearchPageObject.assertThereIsNoResult();
    }
}
