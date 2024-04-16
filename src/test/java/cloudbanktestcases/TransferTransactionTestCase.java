package cloudbanktestcases;

import cloudbankpages.Homepage;
import cloudbankpages.TransferTransactionListPage;
import cloudbankpages.TransferTransactionPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class TransferTransactionTestCase {

    Homepage homePage;
    TransferTransactionPage transferTransactionPage;
    TransferTransactionListPage transferTransactionListPage;
    TestSetupTestCase setUp = new TestSetupTestCase();


    @BeforeTest
    void setUp() {
        setUp.chromeDriverSetUp();
        setUp.driver.get("http://localhost:8000/bank/login");
        setUp.logIn();

        homePage = new Homepage(setUp.driver);
        transferTransactionPage = new TransferTransactionPage(setUp.driver);
        transferTransactionListPage = new TransferTransactionListPage(setUp.driver);
    }
    @Test
    void createNewTransfer() {
        SoftAssert softAssert = new SoftAssert();

        //Navigate to Transfer Transaction Page from Dashboard
        assertTrue(homePage.welcomeUserTxt.isDisplayed());
        homePage.transferBtn.click();

        WebDriverWait wait = new WebDriverWait(setUp.driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(homePage.newTransferBtn));
        homePage.newTransferBtn.click();

        //Create New Transfer Trx
        softAssert.assertTrue(transferTransactionPage.transferTransactionTitle.getText().equals("Transfer Transaction"),
                "Transfer Transaction Title Not Found");

        //Fill out form
        transferTransactionPage.fillOutForm("Cloud Strife","Tifa Lockheart", "1000");

        //Verify Transfer Transaction successfully posted
        assertTrue(transferTransactionListPage.transferTransactionListTitle.isDisplayed());
        transferTransactionListPage.verifyTransferTransactionExists(transferTransactionPage.transactionReference);

        softAssert.assertAll();
    }
    @Test
    void viewTransferTransaction(){
        SoftAssert softAssert = new SoftAssert();

        //Navigate to Withdraw Transaction List Page from Dashboard
        assertTrue(homePage.welcomeUserTxt.isDisplayed());
        homePage.transferBtn.click();

        WebDriverWait wait = new WebDriverWait(setUp.driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(homePage.transferTransactionListBtn));
        homePage.transferTransactionListBtn.click();

        //View and Verify Deposit Transaction
        transferTransactionListPage.getTransferTransactionInList("1").click();
        softAssert.assertTrue(transferTransactionPage.transferTransactionTitle.getText().equals("Transfer Transaction"),
                "Transfer Transaction Title Not Found");

        transferTransactionPage.verifyTransferTransaction("2024-04-01","1","DONE","Cloud Strife", "Tifa Lockheart","2000.0","PHP");

        softAssert.assertAll();
    }

    @AfterTest
    void tearDown(){
        setUp.driver.quit();
    }
}
