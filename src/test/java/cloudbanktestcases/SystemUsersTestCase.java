package cloudbanktestcases;

import cloudbankpages.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import org.testng.asserts.SoftAssert;

public class SystemUsersTestCase {

    WebDriver driver;
    String randomAlphaNumeric = RandomStringUtils.randomAlphanumeric(8);
    String username;
    Homepage homepage;
    CreateSystemUserPage createSystemUserPage;
    SystemUserListPage systemUserListPage;

    TestSetupTestCase setUp = new TestSetupTestCase();


    @BeforeTest
    void setUp() {
        setUp.chromeDriverSetUp();
        setUp.driver.get("http://localhost:8000/bank/login");
        setUp.logIn();

        homepage = new Homepage(setUp.driver);
        createSystemUserPage = new CreateSystemUserPage(setUp.driver);
        systemUserListPage = new SystemUserListPage(setUp.driver);
    }

    @Test
    void createNewUser(){

        String username = "testUser"+randomAlphaNumeric;
        String password = "te$ting123";

        this.username = username;

        SoftAssert softAssert = new SoftAssert();

        //Navigate to Create System User Page from Dashboard
        assertTrue(homepage.welcomeUserTxt.isDisplayed());
        homepage.systemUserBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(homepage.newSystemUserBtn));
        homepage.newSystemUserBtn.click();

        //Create New User
        softAssert.assertTrue(createSystemUserPage.createUserTitle.getText().equals("Create System User"),
                "Create System User Title Not Found");
        createSystemUserPage.fillOutForm(username, password);

        //Verify user is successfully created
        assertTrue(systemUserListPage.systemUserListTitle.isDisplayed());
        systemUserListPage.verifyUserExists(username);

        softAssert.assertAll();
    }
    @Test
    void update_user(){

        String username = "FORUPDATE_"+randomAlphaNumeric;
        String password = "te$ting@1"+randomAlphaNumeric;

        SoftAssert softAssert = new SoftAssert();

        //Navigate to Create System User Page from Dashboard
        assertTrue(homepage.welcomeUserTxt.isDisplayed());
        homepage.systemUserBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(homepage.systemUserListBtn));
        homepage.systemUserListBtn.click();

        //update user
        softAssert.assertTrue(systemUserListPage.systemUserListTitle.getText().equals("System User List"),
                "System User List Not Found");
        systemUserListPage.getUserNameListElement("FORUPDATE").click();
        createSystemUserPage.fillOutForm(username,password);

        //Verify User successfully updated
        assertTrue(systemUserListPage.systemUserListTitle.isDisplayed());
        systemUserListPage.verifyUserExists(username);

        softAssert.assertAll();

    }
    @Test
    void deleteCreatedUser(){

        //setup driver, log in and create new user
        createNewUser();
        String usernameToBeDeleted = username;

        //setup new driver then log in
        setUp();

        //instantiate related classes
        SoftAssert softAssert = new SoftAssert();

        DeleteConfirmationPage deleteConfirmationPage = new DeleteConfirmationPage(setUp.driver);

        //Navigate to Create System User Page from Dashboard
        assertTrue(homepage.welcomeUserTxt.isDisplayed());
        homepage.systemUserBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(homepage.systemUserListBtn));
        homepage.systemUserListBtn.click();

        //delete user
        softAssert.assertTrue(systemUserListPage.systemUserListTitle.getText().equals("System User List"),
                "System User List Not Found");
        wait.until(ExpectedConditions.visibilityOf(systemUserListPage.getUserNameListElement(usernameToBeDeleted)));
        systemUserListPage.getUserNameListElement(usernameToBeDeleted).click();

        softAssert.assertTrue(createSystemUserPage.updateUserTitle.getText().equals("Update System User"),
                "Update System User Title Not Found");
        Assert.assertEquals(createSystemUserPage.newUserNameTxt.getAttribute("value"), usernameToBeDeleted, "Username does not match");

        createSystemUserPage.deleteBtn.click();

        softAssert.assertTrue(deleteConfirmationPage.deleteConfirmationTitle.getText().equals("Delete Confirmation"),
                "Delete Confirmation Title Not Found");

        deleteConfirmationPage.yesDeleteBtn.click();

        //verify user is successfully deleted from the list
        assertTrue(systemUserListPage.systemUserListTitle.isDisplayed());
        try {
            assertFalse(systemUserListPage.getUserNameListElement(usernameToBeDeleted).isDisplayed(), "Username still exists");
        }
        catch(NoSuchElementException e){}

        softAssert.assertAll();
    }
    @AfterTest
    void tearDown(){
        setUp.driver.quit();
    }
}
