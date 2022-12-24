package libs;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase {
    private static final String PLATFORM_ANDROID = "android";
    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub/";

    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
        driver = new AndroidDriver(new URL(AppiumURL), capabilities);

    }

    @After
    public void tearDown() {

        driver.quit();

    }
    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception
    {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (platform.equals(PLATFORM_ANDROID))
        {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "and80");
            capabilities.setCapability("platformVersion", "9");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("orientation","PORTRAIT");
            capabilities.setCapability("app", "C:/JavaAppiumAutomation/JavaAppiumAutomation/apks/org.wikipedia.apk");

        }
        else
        {
            throw new Exception("Cannot get run platform from Env" + platform);
        }
        return capabilities;
    }
}
