package pageobject;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BookStorePage extends BasePage {

    @FindBy(xpath = "//li[@id='item-2']//span[text()='Book Store']")
    private WebElement bookStoreBtn;
    @FindBy(xpath = "//div[@class='rt-table']")
    private WebElement booksTable;
    @FindBy(xpath = "//div[@class='rt-tbody']//a")
    private List<WebElement> booksLinks;
    @FindBy(className = "profile-wrapper")
    private WebElement bookProfile;
    @FindBy(xpath = "//button[@id='addNewRecordButton' and contains(text(),'Collection')]")
    private WebElement addToCollectionBtn;
    @FindBy(xpath = "//li[@id='item-3']//span[text()='Profile']")
    private WebElement profileBtn;
    @FindBy(xpath = "//div[contains(@class,'buttonWrap')]//button[@id='submit' and text()='Delete All Books']")
    private WebElement deleteAllBooksBtn;
    @FindBy(xpath = "//button[@id='closeSmallModal-ok']")
    private WebElement popUpDeleteBookBtn;

    public BookStorePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public BookStorePage openBookStore() {
        wait.until(ExpectedConditions.visibilityOf(bookStoreBtn));
        moveToElement(bookStoreBtn);
        bookStoreBtn.click();
        wait.until(ExpectedConditions.visibilityOf(booksTable));
        return this;
    }

    public String openFirstBookAndGetID() {
        WebElement firstBook = booksLinks.get(0);
        String id = getBooksList().get(0);
        wait.until(ExpectedConditions.elementToBeClickable(firstBook));
        firstBook.click();
        wait.until(ExpectedConditions.visibilityOf(bookProfile));
        return id;
    }

    public BookStorePage addBookToCollection() {
        moveToElement(addToCollectionBtn);
        addToCollectionBtn.click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        wait.until(ExpectedConditions.elementToBeClickable(addToCollectionBtn));
        return this;
    }

    public BookStorePage openProfile() {
        moveToElement(profileBtn);
        profileBtn.click();
        wait.until(ExpectedConditions.visibilityOf(deleteAllBooksBtn));
        return this;
    }

    public List<String> getBooksList() {
        return booksLinks.stream().map(element -> element.findElement(By.xpath("./..")).getAttribute("id"))
                .collect(Collectors.toList());
    }

    public BookStorePage removeBook(String id) {
        WebElement bookTitle = driver.findElement(By.id(id));
        WebElement deleteBtn = bookTitle
                .findElement(By.xpath(".//ancestor::div[@role='row']//span[@id='delete-record-undefined']"));
        deleteBtn.click();
        wait.until(ExpectedConditions.visibilityOf(popUpDeleteBookBtn));
        popUpDeleteBookBtn.click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        wait.until(ExpectedConditions.invisibilityOf(bookTitle));
        return this;
    }
}
