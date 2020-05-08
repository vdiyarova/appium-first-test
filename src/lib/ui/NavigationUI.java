package lib.ui;

import io.appium.java_client.AppiumDriver;

public class NavigationUI extends MainPageObject{
    private static final String
            MY_LIST = "xpath://android.widget.FrameLayout[@content-desc='My lists']";
    public NavigationUI(AppiumDriver driver){
        super(driver);
    }

    public void clickMyList(){
        this.waitForElementAndClick(
                MY_LIST,
                "Cannot find button 'My lists'",
                5
        );
    }
}
