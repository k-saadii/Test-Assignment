package org.page;

import org.object.ViteObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class VitePage extends ViteObject {



    public void hover(WebElement elementName) {
        Actions builder = new Actions(driver);
        builder.moveToElement(elementName).build().perform();

    }

    public String changeBasePrice() {
        hover(txt_basePrice);
        btn_pencilBase.click();
        txt_inputBasePrice.clear();
        txt_inputBasePrice.sendKeys("5");
        btn_checkIconBase.click();
        return removeDecimal(txt_sumValue.getText());
    }

    public String removeDecimal(String string) {
        String[] correctedString = string.split("\\.");

        return correctedString[0];
    }


    public void insertLabel() {

        //Can be improved with external data sheet implementation
        Map<String, String> testData = new LinkedHashMap<>();
        testData.put("Alloy surcharge", "2.15");
        testData.put("Scrap surcharge", "3.14");
        testData.put("Internal surcharge", "0.7658");
        testData.put("External surcharge", "1");
        testData.put("Storage surcharge", "0.3");

        for (Map.Entry<String, String> entry : testData.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            txt_inputLabel.click();
            txt_inputLabel.clear();
            txt_inputLabel.sendKeys("" + key);
            txt_inputValue.click();
            txt_inputValue.clear();
            txt_inputValue.sendKeys("" + value);

            btn_checkIcon.click();
        }

    }

    public void checkDecimal() {
        SoftAssert softAssert = new SoftAssert();
        boolean isVisible = isElementPresent(txt_roundedValue);

        //This will verify two cases 1)rounding off & 2)Two decimal values
        softAssert.assertTrue(isVisible, "Rounded Price is seen to the user");

        boolean setDecimalFlag = isElementPresent(txt_singleDecimal);

        //This will verify if the value 1 is stored as 1.0 or not
        softAssert.assertTrue(setDecimalFlag, "Value with no Decimal digit has 0 as decimal digit");

        softAssert.assertAll();

    }

    public void removePriceComponent(WebElement elementName, int index) {
        hover(elementName);
        WebElement trashIcon = driver.findElement(By.xpath("(//*[contains(@id,'-thrash-icon')]/i)["+index+"]"));
        trashIcon.click();

    }

    //opening edit mode for Component using index
    public void editComponent(WebElement elementName, int index) {
        hover(elementName);
        WebElement editIcon = driver.findElement(By.xpath("(//*[contains(@id,'-edit-icon')]/i)["+index+"]"));
        editIcon.click();
    }

    //Generic function to click check icon according to position of the Component being edited
    public void editLabel(String newLabel, int index, boolean click) {
        txt_labelInput.clear();
        txt_labelInput.sendKeys(newLabel);
        if (click) {//to be clicked when value need to be saved when click is true or just check error message
            clickOnCheckIcon(index);
        }

    }
    public void editPrice(String newPrice, int index, boolean click) {
        txt_priceInput.clear();
        txt_priceInput.sendKeys(newPrice);
        if (click) {
            clickOnCheckIcon(index);
        }

    }

    public boolean checkErrorMessage(WebElement element) {
        return isElementPresent(element);
    }

    //Using Index to click on particular check icon since all icon are listed together & then it returns no interaction possible
    public void clickOnCheckIcon(int index) {
        WebElement checkIcon = driver.findElement(By.xpath("(//*[contains(@id,'-check-icon')]/i)["+index+"]"));
        checkIcon.click();
    }

    /* To find the number of components added by the user in the system,
     *Will fetch the DOM everytime for updated components */
    public int getIndexOfComponent(String componentName) {
        int index = 0;
        List<WebElement> componentList;
        componentList = driver.findElements(By.xpath("//span[@class=\"\"]"));
        for (WebElement option : componentList) {
            if (option.getText().equalsIgnoreCase(componentName)) {
                index = componentList.indexOf(option);
                System.out.println("My element is at " + index);//debug statement
            }
        }
        return index;

    }
}
