package cloudbanktestcases;

import cloudbankpages.DepositTransactionListPage;
import cloudbankpages.DepositTransactionPage;
import cloudbankpages.Homepage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class DepositTransactionTestCase {

    WebDriver driver;
    Homepage homepage;
    DepositTransactionPage depositTransactionPage;
    DepositTransactionListPage depositTransactionListPage;
    TestSetupTestCase setUp = new TestSetupTestCase();

    @BeforeTest
    void setUp() {
        setUp.chromeDriverSetUp();
        setUp.driver.get("http://localhost:8000/bank/login");
        setUp.logIn();

        homepage = new Homepage(setUp.driver);
        depositTransactionPage = new DepositTransactionPage(setUp.driver);
        depositTransactionListPage = new DepositTransactionListPage(setUp.driver);
    }
    @Test
    void createNewDeposit() {
        SoftAssert softAssert = new SoftAssert();

        //Navigate to Deposit Transaction Page from Dashboard
        assertTrue(homepage.welcomeUserTxt.isDisplayed());
        homepage.depositBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(homepage.newDepositBtn));
        homepage.newDepositBtn.click();

        //Create New Deposit Trx
        softAssert.assertTrue(depositTransactionPage.depositTransactionTitle.getText().equals("Deposit Transaction"),
                "Deposit Transaction Title Not Found");

        //Fill out form
        depositTransactionPage.fillOutForm("Cloud Strife", "1000");

        //Verify Deposit Transaction successfully posted
        assertTrue(depositTransactionListPage.depositTransactionListTitle.isDisplayed());
        depositTransactionListPage.verifyDepositTransactionExists(depositTransactionPage.trsansactionReference);

        softAssert.assertAll();
    }
    @Test
    void viewDepositTransaction(){
        SoftAssert softAssert = new SoftAssert();

        //Navigate to Deposit Transaction List Page from Dashboard
        assertTrue(homepage.welcomeUserTxt.isDisplayed());
        homepage.depositBtn.click();

        WebDriverWait wait = new WebDriverWait(setUp.driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(homepage.depositTransactionListBtn));
        homepage.depositTransactionListBtn.click();

        //View and Verify Deposit Transaction
        softAssert.assertTrue(depositTransactionListPage.depositTransactionListTitle.getText().equals("Deposit Transaction List"),
                "Deposit Transaction List Title Not Found");
        depositTransactionListPage.getDepositTransactionInList("2").click();
        softAssert.assertTrue(depositTransactionPage.depositTransactionTitle.getText().equals("Deposit Transaction"),
                "Deposit Transaction Title Not Found");

        depositTransactionPage.verifyDepositTrx("2024-04-01","2","DONE","Cloud Strife","1222.0","PHP");

        softAssert.assertAll();
    }

    @AfterTest
    void tearDown(){
        setUp.driver.quit();
    }
}
