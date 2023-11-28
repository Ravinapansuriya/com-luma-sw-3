package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.List;

public class WomenTest extends Utility {
    String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyTheSortByProductNameFilter() throws InterruptedException {
        //Mouse Hover on Women Menu
        mouseHoverToElement(By.xpath("//span[normalize-space()='Women']"));
        //Mouse Hover on Tops
        mouseHoverToElement(By.cssSelector("a[id='ui-id-9'] span:nth-child(2)"));
        //Click on Jackets
        clickOnElement(By.cssSelector("a[id='ui-id-11'] span"));
        //Select Sort By filter “Product Name”
        selectByValueFromDropDown(By.xpath("//div[@class='page-wrapper']//div[2]//div[3]//select[1]"), "name");
        //Verify the products name display in alphabetical order
        Thread.sleep(1000);
        //Verify the products name display in alphabetical order
        List<WebElement> listOfElements = driver.findElements(By.cssSelector("ol.products.list.items.product-items>li>div>div>strong>a.product-item-link"));

        List<String> array = new ArrayList<>();
        for (WebElement products : listOfElements) {
            array.add(products.getText());
        }

        Assert.assertTrue(array.get(0).equalsIgnoreCase("Adrienne Trek Jacket"));
        System.out.println(array.get(0));
        Assert.assertTrue(array.get(11).equalsIgnoreCase("Stellar Solar Jacket"));
        System.out.println(array.get(11));
    }

    @Test
    public void verifyTheSortByPriceFilter() {
        //Mouse Hover on Women Menu
        mouseHoverToElement(By.xpath("//span[normalize-space()='Women']"));
        //Mouse Hover on Tops
        mouseHoverToElement(By.cssSelector("a[id='ui-id-9'] span:nth-child(2)"));
        //Click on Jackets
        clickOnElement(By.cssSelector("a[id='ui-id-11'] span"));
        //Select Sort By filter “Price”
        selectByVisibleTextFromDropDown(By.id("sorter"), "Price");
        //Verify the products price display in Low to High
        verifyElements("error", "Price", By.xpath("(//select[@id='sorter'])[1]//option[@value='price']"));
    }

    @After
    public void tearDown() {
        //closeBrowser();
    }
}
