package cloudbanktestcases;

import cloudbankpages.Homepage;
import cloudbankpages.WithdrawTransactionListPage;
import cloudbankpages.WithdrawTransactionPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class WithdrawTransactionTestCase {

    Homepage homePage;
    WithdrawTransactionPage withdrawTrxPage;
    WithdrawTransactionListPage withdrawTrxListPage;
    TestSetupTestCase setUp = new TestSetupTestCase();

    @BeforeTest
    void setUp() {
        setUp.chromeDriverSetUp();
        setUp.driver.get("http://localhost:8000/bank/login");
        setUp.logIn();

        homePage = new Homepage(setUp.driver);
        withdrawTrxPage = new WithdrawTransactionPage(setUp.driver);
        withdrawTrxListPage = new WithdrawTransactionListPage(setUp.driver);
    }
    @Test
    void createNewWithdrawal() {
        SoftAssert softAssert = new SoftAssert();

        //Navigate to Withdraw Transaction Page from Dashboard
        assertTrue(homePage.welcomeUserTxt.isDisplayed());
        homePage.withdrawBtn.click();

        WebDriverWait wait = new WebDriverWait(setUp.driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(homePage.newWithdrawBtn));
        homePage.newWithdrawBtn.click();

        //Create New Withdraw Trx
        softAssert.assertTrue(withdrawTrxPage.withdrawTransactionTitle.getText().equals("Withdraw Transaction"),
                "Withdraw Transaction Title Not Found");

        //Fill out form
        withdrawTrxPage.fillOutForm("Cloud Strife", "1000");

        //Verify Withdraw Transaction successfully posted
        assertTrue(withdrawTrxListPage.withdrawTransactionListTitle.isDisplayed());
        withdrawTrxListPage.verifyWithdrawTransactionExists(withdrawTrxPage.trxReference);

        softAssert.assertAll();
    }
    @Test
    void viewWithdrawTransaction(){
        SoftAssert softAssert = new SoftAssert();

        //Navigate to Withdraw Transaction List Page from Dashboard
        assertTrue(homePage.welcomeUserTxt.isDisplayed());
        homePage.withdrawBtn.click();

        WebDriverWait wait = new WebDriverWait(setUp.driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(homePage.withdrawTransactionListBtn));
        homePage.withdrawTransactionListBtn.click();

        //View and Verify Deposit Transaction
        withdrawTrxListPage.getWithdrawTransactionInList("1").click();
        softAssert.assertTrue(withdrawTrxPage.withdrawTransactionTitle.getText().equals("Withdraw Transaction"),
                "Withdraw Transaction Title Not Found");

        withdrawTrxPage.verifyWithdrawTrx("2024-04-01","1","DONE","Cloud Strife","1455.0","PHP");

        softAssert.assertAll();
    }
    @AfterTest
    void tearDown(){
        setUp.driver.quit();
    }

}
