package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class GooglePage {
    public GooglePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//button[@id='W0wltc']")
    public  WebElement rejectAll;
    @FindBy(name = "q")
    public WebElement searchBox;
    @FindBy(xpath = "(//div[@class='QS5gu sy4vM'])[2]")
    public WebElement popUpOkButton;
}