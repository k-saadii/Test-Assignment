package org.object;

import org.controller.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ViteObject extends BaseTest {

    @FindBy(className ="my-3")
    public  WebElement txt_priceComponent;

    @FindBy(id="BasePrice")
    public WebElement txt_basePrice;

    @FindBy(id="base-edit-icon")
    public WebElement btn_pencilBase;

    @FindBy(id="base-value-input")
    public  WebElement txt_inputBasePrice;

    @FindBy(id="base-check-icon")
    public  WebElement btn_checkIconBase;

    @FindBy(xpath="//span[@class=\"font-bold\"]")
    public WebElement txt_sumValue;

    @FindBy(id="ghost-label-input")
    public WebElement txt_inputLabel;

    @FindBy(id="ghost-value-input")
    public  WebElement txt_inputValue;

    @FindBy(id="ghost-check-icon")
    public  WebElement btn_checkIcon;

    @FindBy(xpath="//span[text()=\"Internal surcharge\"]")
    public WebElement txt_InternalSurge;

    @FindBy(xpath="//span[text()=\"Storage surcharge\"]")
    public WebElement txt_StorageSurge;

    @FindBy(xpath="//span[text()=\"Alloy surcharge\"]")
    public WebElement txt_AlloySurcharge;

    @FindBy(xpath="//span[text()=\"Scrap surcharge\"]")
    public WebElement txt_ScrapSurcharge;

    @FindBy(xpath = "//div[text()=\"0.77\"]")
    public  WebElement txt_roundedValue;

    @FindBy(xpath = "//div[text()=\"1.0\"]")
    public  WebElement txt_singleDecimal;

    @FindBy(xpath="/html/body/div/div/div/ul/div[4]/div[4]/span[1]")
    public  WebElement btn_thrashIcon;

    @FindBy(className = "font-semibold")
    public WebElement txt_labelError;

    @FindBy(xpath="//input[contains(@id,'-label-input')]")
    public WebElement txt_labelInput;

    @FindBy(xpath="//input[contains(@id,'-value-input')]")
    public WebElement txt_priceInput;



}
