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

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class DeleteClientDetailTestCase {

    WebDriver driver;
    Homepage objHomePage;
    CreateClientPage objCreateClientPage;
    ClientListPage objClientListPage;
    DeleteConfirmationPage objDeleteConfirmationPage;

    String clientToBeDeleted;

    @Test
    void delete_client() {

        //setup driver, log in and create new user
        ClientCreationTestCase newClient = new ClientCreationTestCase();
        newClient.setUp();
        newClient.createNewClient();
        clientToBeDeleted = newClient.clientAccountNum;

        //setup new driver then log in
        newClient.setUp();

        //instantiate related classes
         SoftAssert softAssert = new SoftAssert();
        objHomePage = new Homepage(newClient.driver);
        objCreateClientPage = new CreateClientPage(newClient.driver);
        objClientListPage = new ClientListPage(newClient.driver);
        objDeleteConfirmationPage = new DeleteConfirmationPage(newClient.driver);

        //Navigate to Create System User Page from Dashboard
        assertTrue(objHomePage.welcomeUserTxt.isDisplayed());
        objHomePage.clientsBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(objHomePage.clientListBtn));
        objHomePage.clientListBtn.click();

        //delete user
        softAssert.assertTrue(objClientListPage.clientListTitle.getText().equals("Client List"),
                "Client List Not Found");
        wait.until(ExpectedConditions.visibilityOf(objClientListPage.getClientNameLocator(clientToBeDeleted)));
        objClientListPage.getClientNameLocator(clientToBeDeleted).click();

        softAssert.assertTrue(objCreateClientPage.updateClientTitle.getText().equals("Update Client"),
                "Update Client Title Not Found");
        Assert.assertEquals(objCreateClientPage.accountNumTxt.getAttribute("value"), clientToBeDeleted, "Client account number does not match");

        objCreateClientPage.deleteBtn.click();

        softAssert.assertTrue(objDeleteConfirmationPage.deleteConfirmationTitle.getText().equals("Delete Confirmation"),
                "Delete Confirmation Title Not Found");

        objDeleteConfirmationPage.yesDeleteBtn.click();

        //verify user is successfully deleted from the list
        assertTrue(objClientListPage.clientListTitle.isDisplayed());
        try {
            assertFalse(objClientListPage.getClientNameLocator(clientToBeDeleted).isDisplayed(), "Client still exists");
        }
        catch(NoSuchElementException e){}

        softAssert.assertAll();

        //newClient.driver.quit();
    }
}
