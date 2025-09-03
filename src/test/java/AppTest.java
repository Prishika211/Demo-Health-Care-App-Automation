import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class AppTest {
    static AndroidDriver driver;

    public static void main(String[] args) throws MalformedURLException{
        openMobileApp();
    }

    public static void openMobileApp() throws MalformedURLException{
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("sdk_gphone64_x86_64");
        options.setUdid("emulator-5554");
        options.setPlatformName("Android");
        options.setPlatformVersion("14");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("org.simple.clinic.staging");
//        options.setAppPackage("com.reddit.frontpage");
        options.setAppActivity("org.simple.clinic.setup.SetupActivity");
//        options.setAppActivity("com.reddit.launch.main.MainActivity");

        URL url = new URL("http://127.0.0.1:4723/");

        driver = new AndroidDriver(url, options);

        //First Page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.id("org.simple.clinic.staging:id/nextButton")
        )).click();

        //Second Page
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("org.simple.clinic.staging:id/introOneTextView")));
        String actualSearchHyperTension=driver.findElement((AppiumBy.id("org.simple.clinic.staging:id/introOneTextView"))).getText();
        assertEquals(actualSearchHyperTension, "Search & find \n" +
                "thousands of patients \n" +
                "with hypertension");

        String actualBloodPressure = driver.findElement(AppiumBy.id("org.simple.clinic.staging:id/introTwoTextView")).getText();
        assertEquals(actualBloodPressure, "Maintain records of \n" +
                "blood pressures \n" +
                "& medicines");

        String actualReminderMessage = driver.findElement(AppiumBy.id("org.simple.clinic.staging:id/introThreeTextView")).getText();
        assertEquals(actualReminderMessage,"Patients receive\n" +
                "reminder messages\n" +
                "to return for visits");

        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.id("org.simple.clinic.staging:id/getStartedButton")
        )).click();

        //Third Page
        wait.until(ExpectedConditions.visibilityOfElementLocated(new AppiumBy.ByAndroidUIAutomator("new UiSelector().text(\"Terms of Use\")")));
        String actualTermsUsage = driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiSelector().text(\"Terms of Use\")")).getText();
        assertEquals(actualTermsUsage, "Terms of Use");

        driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiSelector().text(\"AGREE AND CONTINUE\")")).click();

        //Fourth Page
        wait.until(ExpectedConditions.visibilityOfElementLocated(new AppiumBy.ByAndroidUIAutomator("new UiSelector().text(\"India\")")));
        String actualFirstCountry = driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiSelector().text(\"India\")")).getText();
        assertEquals(actualFirstCountry, "India");

        driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiSelector().text(\"India\")")).click();

        //Fifth Page
        wait.until(ExpectedConditions.visibilityOfElementLocated(new AppiumBy.ByAndroidUIAutomator("new UiSelector().text(\"Goa\")"))).click();

        //Sixth Page
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("org.simple.clinic.staging:id/phoneNumberEditText"))).sendKeys("1212121234");

        driver.findElement(AppiumBy.id("org.simple.clinic.staging:id/nextButton")).click();

        //Seventh Page
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("org.simple.clinic.staging:id/pinEditText"))).sendKeys("1234");


    }
}
