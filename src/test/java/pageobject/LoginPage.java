package pageobject;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(id = "login")
    private WebElement loginBtn;
    @FindBy(id = "userName")
    private WebElement userNameField;
    @FindBy(id = "password")
    private WebElement passwordField;
    @FindBy(id = "submit")
    private WebElement logoutBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public BookStorePage login(String userName, String pass) {
        wait.until(ExpectedConditions.visibilityOf(loginBtn));
        loginBtn.click();
        wait.until(ExpectedConditions.visibilityOf(userNameField));
        userNameField.sendKeys(userName);
        passwordField.sendKeys(pass);
        loginBtn.click();
        wait.until(ExpectedConditions.visibilityOf(logoutBtn));
        assertTrue(logoutBtn.isDisplayed());
        return new BookStorePage(driver);
    }


}
