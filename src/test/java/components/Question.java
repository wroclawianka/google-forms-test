package components;

import core.Scroll;
import core.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by dorot on 2017-03-28.
 */
public class Question {

    private final WebDriver driver;
    private final Wait wait;
    private final Scroll scroll;
    private String QUESTION_PATH;

    public Question(WebDriver driver, String name) {
        this.driver = driver;
        this.wait = new Wait(driver);
        this.scroll = new Scroll(driver);
        this.QUESTION_PATH = String.format("//div[(@role='listitem') and " +
                "descendant::div[(@role='heading') and (contains(text(),'%s'))]]", name);
    }

    public Dropdown getDropdown() {
        return new Dropdown(driver, QUESTION_PATH);
    }

    public WebElement alertMessage(String value) {
        String alert = String.format(QUESTION_PATH + "//descendant::div[(@role='alert') and (contains(text(),'%s'))]", value);
        return wait.untilElementIsVisible(By.xpath(alert));
    }

    public String getValue() {
        String inputLocator = QUESTION_PATH + "/descendant::input";
        return wait.untilElementIsVisible(By.xpath(inputLocator)).getAttribute("value");
    }

    public String getText() {
        String inputLocator = QUESTION_PATH + "/descendant::textarea";
        return wait.untilElementIsVisible(By.xpath(inputLocator)).getAttribute("value");
    }

    public void clickRadioButton(String value) {
        String radioButton = QUESTION_PATH + String.format("//descendant::label//descendant::div[@data-value='%s']", value);
        wait.untilElementIsVisible(By.xpath(radioButton)).click();
    }

    public void selectCheckboxes(String value) {
        String option = String.format(QUESTION_PATH + "/descendant::div[@data-value='%s']", value);
        By checkbox = By.xpath(option + "/descendant::div[(@role='checkbox') and (@aria-checked='false')]");
        driver.findElements(checkbox).forEach(this::selectCheckbox);
    }

    public void setDate(Date date) {
        String inputLocator = QUESTION_PATH + "/descendant::input[@type='date']";
        WebElement input = wait.untilElementIsVisible(By.xpath(inputLocator));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        scroll.scrollTo(input);
        input.click();
        input.sendKeys(simpleDateFormat.format(date));
    }

    public void setInput(String value) {
        String inputLocator = QUESTION_PATH + "/descendant::input";
        WebElement input = wait.untilElementIsVisible(By.xpath(inputLocator));
        input.sendKeys(value);
    }

    private void setTextAndEnter(String value) {
        String inputLocator = QUESTION_PATH + "/descendant::textarea";
        WebElement input = wait.untilElementIsVisible(By.xpath(inputLocator));
        input.sendKeys(value, Keys.ENTER);
    }

    public void setTextAndEnter(List<String> values) {
        values.forEach(this::setTextAndEnter);
    }

    public void reverseValue() {
        String inputLocator = QUESTION_PATH + "/descendant::input";
        WebElement input = wait.untilElementIsVisible(By.xpath(inputLocator));
        scroll.scrollTo(input);
        String currentValue = getValue();
        String newValue = new StringBuilder(currentValue).reverse().toString();
        input.clear();
        input.sendKeys(newValue);
    }

    private void selectCheckbox(WebElement checkbox) {
        scroll.scrollTo(checkbox);
        checkbox.click();
        wait.untilElementContainsAttribute(checkbox, "class", "isChecked");
    }
}
