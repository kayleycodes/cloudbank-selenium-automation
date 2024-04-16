package cloudbanktestcases;

import cloudbankpages.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ClientTestCase {

    //WebDriver driver;
    String randomAlphabet = RandomStringUtils.randomAlphabetic(8);
    String randomNumeric = RandomStringUtils.randomNumeric(8);
    String randomAlphaNumeric = RandomStringUtils.randomAlphanumeric(8);
    String clientAccountNumber;
    Homepage homePage;
    CreateClientPage createClientPage;
    ClientListPage clientListPage;
    TestSetupTestCase setUp = new TestSetupTestCase();


    @BeforeTest
    void setUp() {
        setUp.chromeDriverSetUp();
        setUp.driver.get("http://localhost:8000/bank/login");
        setUp.logIn();

        homePage = new Homepage(setUp.driver);
        createClientPage = new CreateClientPage(setUp.driver);
        clientListPage = new ClientListPage(setUp.driver);
    }

    @Test
    void createNewClient(){

        String firstName = "Client"+randomAlphabet;
        String lastName = "Test"+randomAlphabet;
        String address = "Manila "+randomAlphaNumeric;
        String mobileNum = "12"+randomNumeric;
        String emailAddress = "testabc"+randomAlphaNumeric+"@gmail.com";

        SoftAssert softAssert = new SoftAssert();

        //Navigate to Create Client Page from Dashboard
        assertTrue(homePage.welcomeUserTxt.isDisplayed());
        homePage.clientsBtn.click();

        WebDriverWait wait = new WebDriverWait(setUp.driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(homePage.newClientBtn));
        homePage.newClientBtn.click();

        //Create New Client
        softAssert.assertTrue(createClientPage.createClientTitle.getText().equals("Create Client"),
                "Create Client Title Not Found");

        //Fill out form
        createClientPage.fillOutForm(firstName, lastName, address, mobileNum, emailAddress);

        //Verify Client successfully created
        assertTrue(clientListPage.clientListTitle.isDisplayed());
        clientListPage.verifyClientExists(createClientPage.accountNumber);

        softAssert.assertAll();

        clientAccountNumber = createClientPage.accountNumber;
    }

    @Test
    void updateClientDetails() {

        String firstName = "FOR UPDATE "+randomAlphabet;
        String lastName = "Test "+randomAlphabet;
        String address = "Manila "+randomAlphaNumeric;
        String mobileNum = "12"+randomNumeric;
        String emailAddress = "testabc"+randomAlphaNumeric+"@gmail.com";

        SoftAssert softAssert = new SoftAssert();

        //Navigate to Client list from Dashboard
        assertTrue(homePage.welcomeUserTxt.isDisplayed());
        homePage.clientsBtn.click();

        WebDriverWait wait = new WebDriverWait(setUp.driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(homePage.clientListBtn));
        homePage.clientListBtn.click();

        //Update Client Details
        softAssert.assertTrue(clientListPage.clientListTitle.getText().equals("Client List"),
                "Client List Title Not Found");
        clientListPage.getClientNameElement("FOR UPDATE").click();
        softAssert.assertTrue(createClientPage.updateClientTitle.getText().equals("Update Client"),
                "Update Client Title Not Found");
        createClientPage.clearAllTextFieldValue();
        createClientPage.fillOutForm(firstName, lastName, address, mobileNum, emailAddress);

        //Verify Client details is successfully updated
        assertTrue(clientListPage.clientListTitle.isDisplayed());
        clientListPage.getClientNameElement("FOR UPDATE").click();

        softAssert.assertTrue(createClientPage.updateClientTitle.getText().equals("Update Client"),
                "Update Client Title Not Found");

        softAssert.assertTrue(createClientPage.firstNameTxt.getAttribute("value").equals(firstName),
                "First Name is not updated");
        softAssert.assertTrue(createClientPage.lastNameTxt.getAttribute("value").equals(lastName),
                "Last Name is not updated");
        softAssert.assertTrue(createClientPage.addressTxt.getAttribute("value").equals(address),
                "Address is not updated");
        softAssert.assertTrue(createClientPage.mobileNumberTxt.getAttribute("value").equals(mobileNum),
                "Mobile Number is not updated");
        softAssert.assertTrue(createClientPage.emailAddressTxt.getAttribute("value").equals(emailAddress),
                "Email Address is not updated");

        softAssert.assertAll();
    }

    @Test
    void deleteClient() {

        //create new user
        createNewClient();
        tearDown();

        //setup new driver then log in
        setUp();

        //instantiate related classes
        SoftAssert softAssert = new SoftAssert();
        DeleteConfirmationPage deleteConfirmationPage = new DeleteConfirmationPage(setUp.driver);

        //Navigate to Create System User Page from Dashboard
        assertTrue(homePage.welcomeUserTxt.isDisplayed());
        homePage.clientsBtn.click();

        WebDriverWait wait = new WebDriverWait(setUp.driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(homePage.clientListBtn));
        homePage.clientListBtn.click();

        //delete user
        softAssert.assertTrue(clientListPage.clientListTitle.getText().equals("Client List"),
                "Client List Not Found");
        wait.until(ExpectedConditions.visibilityOf(clientListPage.getClientNameElementByAccountNumber(clientAccountNumber)));
        clientListPage.getClientNameElementByAccountNumber(clientAccountNumber).click();

        softAssert.assertTrue(createClientPage.updateClientTitle.getText().equals("Update Client"),
                "Update Client Title Not Found");
        Assert.assertEquals(createClientPage.accountNumberTxt.getAttribute("value"), clientAccountNumber, "Client account number does not match");

        createClientPage.deleteBtn.click();

        softAssert.assertTrue(deleteConfirmationPage.deleteConfirmationTitle.getText().equals("Delete Confirmation"),
                "Delete Confirmation Title Not Found");

        deleteConfirmationPage.yesDeleteBtn.click();

        //verify user is successfully deleted from the list
        assertTrue(clientListPage.clientListTitle.isDisplayed());
        try {
            assertFalse(clientListPage.getClientNameElementByAccountNumber(clientAccountNumber).isDisplayed(), "Client still exists");
        }
        catch(NoSuchElementException e){}

        softAssert.assertAll();
    }

    @AfterTest
    void tearDown(){
        setUp.driver.quit();
    }


}
