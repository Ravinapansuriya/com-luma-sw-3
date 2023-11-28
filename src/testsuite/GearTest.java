package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class GearTest extends Utility {
    String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldAddProductSuccessFullyToShoppinCart() throws InterruptedException {
        //Mouse Hover on Gear Menu
        mouseHoverToElement(By.xpath("//a[@id='ui-id-6']//span[@class='ui-menu-icon ui-icon ui-icon-carat-1-e']"));
        //Click on Bags
        Thread.sleep(2000);
        mouseHoverAndClickOnElement(By.cssSelector("a[id='ui-id-25'] span"));
        //Click on Product Name ‘Overnight Duffle’
        mouseHoverAndClickOnElement(By.xpath("(//a[normalize-space()='Overnight Duffle'])[1]"));
        //Verify the text ‘Overnight Duffle’
        String actualName = getTextFromElement(By.xpath("//span[@class='base']"));
        Assert.assertEquals("Invalid name", "Overnight Duffle", actualName);
        //Change Qty 3
        clearText(By.xpath("//input[@id='qty']"));
        sendTextToElement(By.xpath("//input[@id='qty']"), "3");
        // Click on ‘Add to Cart’ Button.
        clickOnElement(By.id("product-addtocart-button"));
        //Verify the text ‘You added Overnight Duffle to your shopping cart.’
        verifyElements("Invalid text", "You added Overnight Duffle to your shopping cart.", By.xpath("//div[@class='message-success success message']"));
        //Click on ‘shopping cart’ Link into message
        clickOnElement(By.xpath("//a[contains(text(),'shopping cart')]"));
        //Verify the product name ‘Overnight duffle’
        Thread.sleep(2000);
        verifyElements("Invalid product added", "Overnight Duffle", By.xpath("//td[@class='col item']/descendant::a[2]"));
        //Verify the Qty is ‘3’
        String qty = driver.findElement(By.xpath("//td[@class='col qty']/child::div[1]/descendant::input")).getAttribute("value");
        Assert.assertEquals("Invalid quantity", "3", qty);
        Thread.sleep(2000);
        //Verify the product price ‘$135.00’
        verifyElements("Invalid price", "$135.00", By.xpath("//td[@class='col subtotal']//descendant::span[contains(text(),'$135.00')]"));
        //Change Qty to ‘5’
        clearText(By.xpath("//td[@class='col qty']/child::div[1]/descendant::input"));
        sendTextToElement(By.xpath("//td[@class='col qty']/child::div[1]/descendant::input"), "5");
        Thread.sleep(3000);
        //Click on ‘Update Shopping Cart’ button
        clickOnElement(By.xpath("//div[@class='cart main actions']/descendant::button[2]"));
        //Verify the product price ‘$225.00’
        Thread.sleep(3000);
        verifyElements("Invalid text", "$225.00", By.xpath("//td[@data-th='Subtotal']//span[@class='price']"));
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}

