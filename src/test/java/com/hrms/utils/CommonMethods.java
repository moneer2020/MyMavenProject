package com.hrms.utils;

import com.hrms.testbase.PageInitializer;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 */
public class CommonMethods extends PageInitializer {

    /**
     * This method will clear the textbox and send specified text to it.
     *
     * @param element    - WebElement
     * @param textToSend - String
     */
    public static void sendText(WebElement element, String textToSend) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys(textToSend);
    }

    public static void sendText(WebElement element, String textToSend, Keys keys) {
        element.clear();
        getWait().until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(textToSend, keys);
    }

    /**
     * This method returns an object of explicit wait
     *
     * @return WebDriverWait
     */
    private static WebDriverWait getWait() {
        return new WebDriverWait(driver, com.hrms.utils.Constants.WAIT);
    }

    /**
     * This method will wait until given element becomes clickable.
     *
     * @param element - WebElement
     */
    private static void waitForItToBeClickable(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * This method will wait until element is clickable.
     *
     * @param element - WebElement
     */
    public static void click(WebElement element) {
        waitForItToBeClickable(element);
        element.click();
    }

    /**
     * This method initializes JavascriptExecutor
     *
     * @return JavascriptExecutor
     */
    private static JavascriptExecutor getJSExecutor() {
        return (JavascriptExecutor) driver;
    }

    /**
     * This method runs the JSE click script
     *
     * @param element - WebElement
     */
    public static void javascriptClick(WebElement element) {
        getWait().until(ExpectedConditions.visibilityOf(element));
        getJSExecutor().executeScript("arguments[0].click();", element);
    }

    public static void javascriptSendText(WebElement element, String text) {
        getWait().until(ExpectedConditions.visibilityOf(element));
        getJSExecutor().executeScript("arguments[0].value='" + text + "';", element);
    }

    /**
     * This method uses the Actions class to hover over a WebElement
     *
     * @param element - WebElement
     */
    public static void mouseHover(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public static void doubleClick(WebElement element) {
        Actions action = new Actions(driver);
        action.doubleClick(element).perform();
    }

    /**
     * This method takes a screenshot
     *
     * @param fileName - String
     */
    public static byte[] takeScreenshot(String fileName) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        byte[] bytes = screenshot.getScreenshotAs(OutputType.BYTES);
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(sourceFile, new File(com.hrms.utils.Constants.SCREENSHOT_FILEPATH + fileName + getTimeStamp("YYYY-MM-dd-HH-mm-ss") + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * This method creates a String with the current time
     *
     * @param pattern - String
     * @return date
     */
    public static String getTimeStamp(String pattern) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    /**
     * @param element
     * @param attributeName
     * @return
     */
    public static String storeAttribute(WebElement element, String attributeName) {
        return element.getAttribute(attributeName);
    }

    /**
     * @param radioOrCheckboxes
     * @param value
     */
    public static void clickRadioOrCheckbox(List<WebElement> radioOrCheckboxes, String value) {
        for (WebElement radioOrCheckbox : radioOrCheckboxes) {
            String actualValue = radioOrCheckbox.getAttribute("value").trim();
            if (radioOrCheckbox.isEnabled() && actualValue.equals(value)) {
                click(radioOrCheckbox);
                break;
            }
        }
    }

    public static ArrayList<String> storeDropdownValues(WebElement element) {
        ArrayList<String> list = new ArrayList<>();

        try {
            Select select = new Select(element);
            List<WebElement> options = select.getOptions();

            for (int i = 1; i < options.size(); i++) {
                list.add(options.get(i).getText());
            }
        } catch (UnexpectedTagNameException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @param element     - web element
     * @param visibleText - visible dropdown web text
     */
    public static void selectDropDown(WebElement element, String visibleText) {
        try {
            Select select = new Select(element);
            List<WebElement> options = select.getOptions();
            for (WebElement option : options) {
                if (option.getText().trim().equals(visibleText)) {
                    select.selectByVisibleText(visibleText);
                    break;
                }
            }
        } catch (UnexpectedTagNameException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param element
     * @param index
     */
    public static void selectDropdown(WebElement element, int index) {
        try {
            Select select = new Select(element);
            List<WebElement> options = select.getOptions();
            int dropdownSize = options.size();
            if (index > 0 && dropdownSize > index) {
                select.selectByIndex(index);
            }
        } catch (UnexpectedTagNameException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param frameIndex
     */
    public static void switchToFrame(int frameIndex) {
        try {
            driver.switchTo().frame(frameIndex);
        } catch (NoSuchFrameException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param nameOrId
     */
    public static void switchToFrame(String nameOrId) {
        try {
            driver.switchTo().frame(nameOrId);
        } catch (NoSuchFrameException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param element
     */
    public static void switchToFrame(WebElement element) {
        try {
            driver.switchTo().frame(element);
        } catch (NoSuchFrameException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public static void switchToChildWindow() {
        String mainWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(mainWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    static String jsonFile;

    public static String readJson(String fileName) {
        try {
            jsonFile = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonFile;
    }
}
