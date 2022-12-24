package tests;
import libs.CoreTestCase;
import libs.ui.SaveTwoArticlesPageObject;
import libs.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.ScreenOrientation;
import java.time.Duration;

public class ChangeAppConditionTests extends CoreTestCase {
    @Test
    public void testChangeOrientationScreenInSearchResults(){
        String search_line = "Java";
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstrDescription("Object-oriented programming language");

        SaveTwoArticlesPageObject SaveTwoArticlesPageObject = new SaveTwoArticlesPageObject(driver);
        String title_before_rotation = SaveTwoArticlesPageObject.getArticleTitle();

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = SaveTwoArticlesPageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title has been changed after rotation",
                title_before_rotation,
                title_after_rotation
        );

        driver.rotate(ScreenOrientation.PORTRAIT);

        String title_after_second_rotation = SaveTwoArticlesPageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title has been changed after rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }

    @Test
    public void testCheckArticleTextAfterBackground(){
        String search_line = "java";
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForSearchResultTitleFromList("Java (programming language)");

        driver.runAppInBackground(Duration.ofSeconds(2));

        SearchPageObject.waitForSearchResultTitleFromList("Java (programming language)");
    }


}

