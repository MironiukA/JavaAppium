package tests;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import libs.CoreTestCase;
import libs.ui.SaveTwoArticlesPageObject;
import libs.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;

public class ArticleTests  extends CoreTestCase{

    @Test
    @DisplayName("Not Empty")
    @Description("Проверяем что-то")
    public void testAmountOfNotEmptySearch(){
        String search_line = "Linkin Park discography";
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        SearchPageObject.waitForSearchResultTitleFromList(search_line);

        int amount_of_search_results = SearchPageObject.getAmountOfFoundsArticle();

        Assert.assertTrue(
                "We found a few results",
                amount_of_search_results > 0
        );
    }

    @Test
    @DisplayName("Save 2 articles")
    @Description("Сохраняем сначала одну, потом вторую")
    public void testSaveTwoArticles(){
        String first_article_name = "Testing framework for web applications";
        String second_article_name = "Use of Selenium by organisms";
        String name_list = "Selenium";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Selenium");
        SearchPageObject.clickByArticleWithSubstrDescription(first_article_name);
        SearchPageObject.clickByArticleTitle(first_article_name);

        SaveTwoArticlesPageObject SaveTwoArticlesPageObject = new SaveTwoArticlesPageObject(driver);
        // Добавляем первую статью в избранное
        SaveTwoArticlesPageObject.initButtonSave();
        SaveTwoArticlesPageObject.initButtonAdd();
        SaveTwoArticlesPageObject.saveNewListName(name_list);
        SaveTwoArticlesPageObject.initButtonOk();
        SaveTwoArticlesPageObject.initButtonBack();
        // Добавляем вторую статью
        SearchPageObject.clickByArticleWithSubstrDescription(second_article_name);
        SearchPageObject.clickByArticleTitle(second_article_name);
        SaveTwoArticlesPageObject.initButtonSave();
        SaveTwoArticlesPageObject.initButtonAdd();
        SaveTwoArticlesPageObject.initMyList(name_list);
        SaveTwoArticlesPageObject.initButtonBack();

        SaveTwoArticlesPageObject.initButtonSaves();
        SaveTwoArticlesPageObject.initSavedList();
        SaveTwoArticlesPageObject.initMySavedNameList(name_list);
        // Удаляем вторую статью
        SaveTwoArticlesPageObject.deleteSavedArticleTwo(second_article_name);
        SaveTwoArticlesPageObject.checkForAvailable(second_article_name);
        //Открываем первую сатью и сравниваем результат
        String result_after_delete = SaveTwoArticlesPageObject.compareFirstArticle(first_article_name);
        Assert.assertEquals("Article is not compare", first_article_name,result_after_delete);
    }
}
