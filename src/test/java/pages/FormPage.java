package pages;

import components.Form;
import org.openqa.selenium.WebDriver;

/**
 * Created by dorot on 2017-03-28.
 */
public class FormPage {

    public WebDriver driver;
    public final Form form;

    public FormPage(WebDriver driver){
        this.driver = driver;
        this.form = new Form(driver);
    }
}
