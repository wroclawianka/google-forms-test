package components;

import core.Wait;
import enums.Buttons;
import enums.Questions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by dorot on 2017-03-28.
 */
public class Form {

    private WebDriver driver;
    private Wait wait;

    public Form(WebDriver driver) {
        this.driver = driver;
        this.wait = new Wait(driver);
    }

    public Question goToQuestion(Questions question) {
        return new Question(driver, question.getName());
    }

    public Form clickButton(Buttons button) {
        String buttonLocator = "//descendant::div[(@role='button') and descendant::span[contains(text(),'%s')]]";
        String buttonXpath = String.format(buttonLocator, button.getName());
        wait.untilElementIsVisible(By.xpath(buttonXpath)).click();
        return this;
    }
}
