package test;

import org.page.VitePage;
import org.controller.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ViteTest extends BaseTest {

    VitePage vitePage;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        vitePage = new VitePage();
    }

    //Sample Test
    @Test(priority = 1)
    public void pageLoad() {
        String txt_pageLoad = vitePage.txt_priceComponent.getText();
        Assert.assertEquals(txt_pageLoad, "Price Components");
    }


    @Test(priority = 2)
    public void testFlow1() {
        String actualString = vitePage.changeBasePrice();
        Assert.assertEquals(actualString, "5");

    }

    @Test(priority = 3)
    public void testFlow2() {
        vitePage.insertLabel();
        vitePage.checkDecimal();
    }


    @Test(priority = 4)
    public void testFlow3() {
        double sumDifference;

        int indexOfInternalSurge = vitePage.getIndexOfComponent("Internal surcharge");

        double basePrice = Double.parseDouble(vitePage.txt_sumValue.getText());//get existing price

        sumDifference = basePrice - 0.77;//Value carried by InternalSurge

        //Used to remove the Internal Surge Component for checking price updated or not
        vitePage.removePriceComponent(vitePage.txt_InternalSurge, indexOfInternalSurge + 1);//Index value for InternalSurge

        Double newPrice = Double.parseDouble(vitePage.txt_sumValue.getText());//get changed price

        System.out.println(newPrice + " is same as " + sumDifference);//debug check

        Assert.assertEquals(newPrice, sumDifference, "The new price is reflected properly");

    }

    @Test(priority = 5)
    public void testFlow4() throws InterruptedException {
        SoftAssert testFlow_4 = new SoftAssert();

        int storageSurcharge = vitePage.getIndexOfComponent("Storage surcharge");

        Thread.sleep(2000);

        vitePage.editComponent(vitePage.txt_StorageSurge, storageSurcharge + 1);

        /* Passing single char value for field error check, Keeping click event false for check icon */
        vitePage.editLabel("T", storageSurcharge + 1, false);

        boolean isErrorSeen = vitePage.checkErrorMessage(vitePage.txt_labelError);

        //Sending index according to the position of Component Storage Surge
        vitePage.clickOnCheckIcon(storageSurcharge + 1);

        boolean isPreviousLabel = isElementPresent(vitePage.txt_StorageSurge);

        testFlow_4.assertTrue(isErrorSeen, "Error Message on Field length is Seen");
        testFlow_4.assertTrue(isPreviousLabel, "Storage Surge is seen  again since text is smaller ");

        testFlow_4.assertAll();

    }

    @Test(priority = 6)
    public void testFlow5() throws InterruptedException {
        SoftAssert testFlow_5 = new SoftAssert();

        int scrapSurcharge = vitePage.getIndexOfComponent("Scrap surcharge");

        vitePage.editComponent(vitePage.txt_ScrapSurcharge, scrapSurcharge + 1);

        Thread.sleep(2000);

        String currentPrice = vitePage.txt_priceInput.getAttribute("value");//getText does not work here hence attribute value

        vitePage.editPrice("-5", scrapSurcharge + 1, false);

        boolean isErrorSeen = vitePage.checkErrorMessage(vitePage.txt_labelError);

        vitePage.clickOnCheckIcon(scrapSurcharge + 1);

        //since price will be dynamic ,better to check it during execution
        boolean isPreviousPrice = isElementPresent(By.xpath("//div[text()=" + currentPrice + "]"));

        testFlow_5.assertTrue(isErrorSeen, "Error Message on Negative Values is seen");

        testFlow_5.assertTrue(isPreviousPrice, "Previous Price is reflected again");

        testFlow_5.assertAll();


    }

    @Test(priority = 7)
    public void testFlow6() {
        double sumDifference;

        int alloySurcharge = vitePage.getIndexOfComponent("Alloy surcharge");

        double basePrice = Double.parseDouble(vitePage.txt_sumValue.getText());//get existing price

        vitePage.editComponent(vitePage.txt_AlloySurcharge, alloySurcharge + 1);

        vitePage.editPrice("1.79", alloySurcharge + 1, true);

        sumDifference = basePrice - 2.15 + 1.79;//Value carried by Alloy Surge

        Double newPrice = Double.parseDouble(vitePage.txt_sumValue.getText());//get changed price

        Assert.assertEquals(newPrice, sumDifference, "The new price is reflected properly");
    }

}
