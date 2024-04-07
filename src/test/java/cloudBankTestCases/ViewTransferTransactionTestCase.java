package cloudBankTestCases;

import cloudBankPages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class ViewTransferTransactionTestCase {
    WebDriver driver;

    LogInPage objLogInPage;
    Homepage objHomePage;
    TransferTransactionPage objTransferTrxPage;
    TransferTransactionListPage objTransferTrxListPage;
    @BeforeTest
    void setUp() {

        //chrome driver setup
        String projectPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", projectPath+ "/src/test/resources/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\Kamille\\Chrome For Testing\\chrome-win64\\chrome.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.get("http://localhost:8000/bank/login");

        //login
        String loginUsername = "admin";
        String loginPassword = "password";

        objLogInPage = new LogInPage(driver);
        objLogInPage.verifyHeader();
        objLogInPage.logIn(loginUsername, loginPassword);
    }

    @Test
    void viewWithdrawTransaction(){
        SoftAssert softAssert = new SoftAssert();

        objHomePage = new Homepage(driver);
        objTransferTrxPage = new TransferTransactionPage(driver);
        objTransferTrxListPage = new TransferTransactionListPage(driver);

        //Navigate to Withdraw Transaction List Page from Dashboard
        assertTrue(objHomePage.welcomeUserTxt.isDisplayed());
        objHomePage.transferBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(objHomePage.transferTrxListBtn));
        objHomePage.transferTrxListBtn.click();

        //View and Verify Deposit Transaction
        objTransferTrxListPage.getTransferTrxInList("1").click();
        softAssert.assertTrue(objTransferTrxPage.transferTransactionTitle.getText().equals("Transfer Transaction"),
                "Transfer Transaction Title Not Found");

        objTransferTrxPage.verifyTransferTrx("2024-04-01","1","DONE","Cloud Strife", "Tifa Lockheart","2000.0","PHP");

        driver.quit();
    }
}
