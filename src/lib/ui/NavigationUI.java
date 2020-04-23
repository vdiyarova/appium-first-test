package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject{
    private static final String
            MY_LIST = "//android.widget.FrameLayout[@content-desc='My lists']";
    public NavigationUI(AppiumDriver driver){
        super(driver);
    }

    public void clickMyList(){
        this.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find button 'My lists'",
                5
        );
    }
}
