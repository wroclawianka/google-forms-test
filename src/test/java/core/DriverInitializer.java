package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Created by dorota.zelga on 23/03/2017.
 */
public class DriverInitializer {

    private static final String CHROME_DRIVER_PATH_PATTERN = "src/test/resources/driver/chrome/%s/chromedriver";
    public static WebDriver driver;
    private static String OS = System.getProperty("os.name").toLowerCase();
    private static String chromeDriverPath;

    static public WebDriver openChromeDriver() {
        setChromeDriverPath();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=cz");
        driver = new ChromeDriver(options);
        return driver;
    }

    private static void setChromeDriverPath() {

        if (isWindows()) {
            chromeDriverPath = String.format(CHROME_DRIVER_PATH_PATTERN, "windows");
            chromeDriverPath += ".exe";
        } else if (isUnix()) {
            chromeDriverPath = String.format(CHROME_DRIVER_PATH_PATTERN, "linux");
        } else if (isMac()) {
            chromeDriverPath = String.format(CHROME_DRIVER_PATH_PATTERN, "macosx");
        }
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    }

    private static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    public static boolean isMac() {
        return (OS.indexOf("mac") >= 0);
    }

    public static boolean isUnix() {
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);
    }
}
