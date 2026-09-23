package org.example.testCases;

import org.example.pageObjects.SearchPage;
import org.example.testBase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC003_ProductSearch extends BaseTest {
    @Test(dataProvider = "SearchData")
    public void search_matchesExpectedResult(String search_word, String expectedResult){
        SearchPage searchPage=new SearchPage(driver);
        searchPage.enter_product_name(search_word);
        searchPage.click_search_btn();
        if(expectedResult.equalsIgnoreCase("valid")){
            Assert.assertTrue(searchPage.hasResults(),"Expected results for this search_word:"+search_word+"but none appeared");
        }else{
            Assert.assertTrue(searchPage.showsNoResultsMsg(),"Expected 'no results' msg for this search_word:"+search_word+"but msg did not show up");
        }

    }

    @DataProvider(name="SearchData")
    public Object[][] Search_productData(){
        Object[][] productData={
                {"mac","valid"},
                {"apple","valid"},
                {"mobile","invalid"},
                {"phone","valid"},
                {"cameras","invalid"},
                {"tab","valid"}
        };
        return productData;

    }


}
