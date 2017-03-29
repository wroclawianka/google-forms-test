package components;

import core.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by dorot on 29/03/2017.
 */
public class Dropdown {

    public WebDriver driver;
    private String DROPDOWN_PATH;
    private Wait wait;

    public Dropdown(WebDriver driver, String path) {
        this.driver = driver;
        this.wait = new Wait(driver);
        this.DROPDOWN_PATH = String.format(path, "/descendant::div[@role='listbox']");
    }

    public Dropdown open() {
        String button = DROPDOWN_PATH + "/descendant::div[@role='option']";
        wait.untilElementIsVisible(By.xpath(button)).click();
        return this;
    }

    public void select(String value) {
        String openDropdown = DROPDOWN_PATH + "//descendant::div[contains(@class,'exportSelectPopup')]";
        String option = openDropdown + String.format("//descendant::div[@aria-label='%s']", value);
        wait.untilElementIsVisible(By.xpath(openDropdown));
        wait.untilElementIsVisible(By.xpath(option)).click();
        wait.untilElementIsInvisible(By.xpath(openDropdown));
    }

    public String getOption() {
        String selectedOption = DROPDOWN_PATH + "//div[contains(@class,'isSelected')]";
        return wait.untilElementIsVisible(By.xpath(selectedOption)).getAttribute("data-value");
    }
}
