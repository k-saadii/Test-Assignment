package org.controller;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;


public class BaseTest extends SeleniumController {

    public BaseTest() {
        PageFactory.initElements(driver, this);
    }


    public boolean isElementPresent(By by) {
        boolean setVisible=false;
        try {
            if (driver.findElement(by).isDisplayed()) {
                setVisible = true;
            }
        } catch (NoSuchElementException e) {
            //Nothing to do
        }
        return setVisible;
    }


    public boolean isElementPresent(WebElement element) {
        boolean setVisible = false;
        try {
            if (element.isDisplayed()) {
                setVisible = true;
            }
        } catch (NoSuchElementException e) {
        //Nothing to do
        }
        return setVisible;
    }


    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        driver.close();
        driver.quit();
    }

}



