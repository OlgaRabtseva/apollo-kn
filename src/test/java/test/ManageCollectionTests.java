package test;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobject.BookStorePage;
import pageobject.LoginPage;

public class ManageCollectionTests extends TestBase {

    @Test
    public void addBookToCollectionTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        BookStorePage bookStorePage = loginPage.login(USER_NAME, PASS);
        String firstBookId = bookStorePage.openBookStore().openFirstBookAndGetID();
        List<String> userBookIdList = bookStorePage.addBookToCollection().openProfile().getBooksList();

        Assert.assertTrue(userBookIdList.size() > 0);
        Assert.assertTrue(userBookIdList.contains(firstBookId));
    }

    @Test
    public void removeBookFromCollectionTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        BookStorePage bookStorePage = loginPage.login(USER_NAME, PASS);
        String firstBookId = bookStorePage.openBookStore().openFirstBookAndGetID();
        List<String> booksBefore = bookStorePage.addBookToCollection().openProfile().getBooksList();
        List<String> booksAfter = bookStorePage.removeBook(firstBookId).getBooksList();

        Assert.assertTrue(booksBefore.size() > booksAfter.size());
        Assert.assertFalse(booksAfter.contains(firstBookId));
    }


}
