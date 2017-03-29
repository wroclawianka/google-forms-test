package core;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by dorot on 2017-03-28.
 */
public class Scroll {

    private WebDriver driver;

    public Scroll(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollTo(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        //document.getElementById('elementid').focus()
    }
}
