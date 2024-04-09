package cloudBankTestCases;

import cloudBankPages.*;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

import static org.testng.Assert.*;

public class DeleteSystemUserTestCase {

    WebDriver driver;
    Homepage objHomePage;
    CreateSystemUserPage objCreateSysUserPage;
    SystemUserListPage objSystemUserListPage;
    DeleteConfirmationPage objDeleteConfirmationPage;
    String usernameToBeDeleted;


    @Test
    void delete_created_user(){

        //setup driver, log in and create new user
        NewUserCreationTestCase newUser = new NewUserCreationTestCase();
        newUser.setUp();
        newUser.create_new_user();
        usernameToBeDeleted = newUser.username;

        //setup new driver then log in
        newUser.setUp();

        //instantiate related classes
        SoftAssert softAssert = new SoftAssert();

        objCreateSysUserPage = new CreateSystemUserPage(newUser.driver);
        objSystemUserListPage = new SystemUserListPage(newUser.driver);
        objDeleteConfirmationPage = new DeleteConfirmationPage(newUser.driver);
        objHomePage = new Homepage(newUser.driver);


        //Navigate to Create System User Page from Dashboard
        assertTrue(objHomePage.welcomeUserTxt.isDisplayed());
        objHomePage.systemUserBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(objHomePage.systemUserListBtn));
        objHomePage.systemUserListBtn.click();

        //delete user
        softAssert.assertTrue(objSystemUserListPage.systemUserListTitle.getText().equals("System User List"),
                "System User List Not Found");
        wait.until(ExpectedConditions.visibilityOf(objSystemUserListPage.getUserNameListLocator(usernameToBeDeleted)));
        objSystemUserListPage.getUserNameListLocator(usernameToBeDeleted).click();

        softAssert.assertTrue(objCreateSysUserPage.updateUserTitle.getText().equals("Update System User"),
                "Update System User Title Not Found");
        Assert.assertEquals(objCreateSysUserPage.newUserNameTxt.getAttribute("value"), usernameToBeDeleted, "Username does not match");

        objCreateSysUserPage.deleteBtn.click();

        softAssert.assertTrue(objDeleteConfirmationPage.deleteConfirmationTitle.getText().equals("Delete Confirmation"),
                "Delete Confirmation Title Not Found");

        objDeleteConfirmationPage.yesDeleteBtn.click();

        //verify user is successfully deleted from the list
        assertTrue(objSystemUserListPage.systemUserListTitle.isDisplayed());
        try {
            assertFalse(objSystemUserListPage.getUserNameListLocator(usernameToBeDeleted).isDisplayed(), "Username still exists");
        }
        catch(NoSuchElementException e){}

        softAssert.assertAll();

        newUser.driver.quit();

    }

}
