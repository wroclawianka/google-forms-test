package features.steps;

import components.Question;
import core.DataHelper;
import core.DriverInitializer;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import enums.Buttons;
import enums.Questions;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.FormPage;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dorot on 2017-03-29.
 */
public class TestGoogleFormSteps {

    private static final String URL = "https://docs.google.com/forms/" +
            "d/e/1FAIpQLSeY_W-zSf2_JxR4drhbgIxdEwdbUbE4wXhxHZLgxZGiMcNl7g/viewform?c=0&w=1";
    private WebDriver driver;
    private FormPage formPage;
    private DataHelper dataHelper;

    private static List<String> pickNRandom(List<String> lst, int n) {
        List<String> copy = new LinkedList<String>(lst);
        Collections.shuffle(copy);
        return copy.subList(0, n);
    }

    @Before
    public void setUpClass() {
        driver = new DriverInitializer().openChromeDriver();
        driver.get(URL);
        formPage = new FormPage(driver);
        dataHelper = new DataHelper();
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    @When("^you fill first and second question$")
    public void youFillFirstAndSecondQuestion() {
        formPage.form.goToQuestion(Questions.CHECK_THAT)
                .selectCheckboxes("Check this");

        formPage.form.goToQuestion(Questions.SELECT_DATE)
                .setDate(dataHelper.daysFromNow(5));
    }

    @And("^validate that third question is mandatory$")
    public void validateThatThirdQuestionIsMandatory() {
        formPage.form.clickButton(Buttons.NEXT);

        Question question = formPage.form.goToQuestion(Questions.MANDATORY);
        Assert.assertTrue(question.alertMessage("Tato otázka je povinná.").isDisplayed());
    }

    @And("^fill third question and go to another step$")
    public void fillThirdQuestionAndGoToAnotherStep() {
        formPage.form.goToQuestion(Questions.MANDATORY)
                .setInput(dataHelper.currentMonth());

        formPage.form.clickButton(Buttons.NEXT);
    }

    @And("^fill next questions$")
    public void fillNextQuestions() {
        List<String> movies = Arrays
                .asList("Dr House", "Game of Thrones", "Mr Robot", "Interstellar", "Guardians of the Galaxy");

        formPage.form.goToQuestion(Questions.FAVOURITE_MOVIES)
                .setTextAndEnter(pickNRandom(movies, 3));

        formPage.form.goToQuestion(Questions.FAVOURITE_COLOR).getDropdown()
                .open().select("Yellow");
    }

    @And("^go back to first step$")
    public void goBackToFirstStep() {
        formPage.form.clickButton(Buttons.BACK);
    }

    @And("^reverse text in third question$")
    public void reverseTextInThirdQuestion() {
        formPage.form.goToQuestion(Questions.MANDATORY)
                .reverseValue();
    }

    @And("^go to second step$")
    public void goToSecondStep() {
        formPage.form.clickButton(Buttons.NEXT);
    }

    @And("^check that both questions are still filed$")
    public void checkThatBothQuestionsAreStillFiled() {

        String currentValue = formPage.form.goToQuestion(Questions.FAVOURITE_MOVIES).getText();
        Assert.assertTrue(currentValue.length() > 0);

        String currentOption = formPage.form.goToQuestion(Questions.FAVOURITE_COLOR).getDropdown().getOption();
        Assert.assertTrue(currentOption.equals("Yellow"));
    }

    @And("^go to last step$")
    public void goToLastStep() {
        formPage.form.clickButton(Buttons.NEXT);
    }

    @And("^fill last question and send form$")
    public void fillLastQuestionAndSendForm() {
        formPage.form.goToQuestion(Questions.DONE)
                .clickRadioButton("Yes");

        formPage.form.clickButton(Buttons.SUBMIT);
    }
}
