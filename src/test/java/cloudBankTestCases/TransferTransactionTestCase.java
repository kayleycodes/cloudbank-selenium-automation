package cloudBankTestCases;

import cloudBankPages.Homepage;
import cloudBankPages.LogInPage;
import cloudBankPages.TransferTransactionListPage;
import cloudBankPages.TransferTransactionPage;
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

public class TransferTransactionTestCase {
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
    void createNewTransfer() {
        SoftAssert softAssert = new SoftAssert();

        objHomePage = new Homepage(driver);
        objTransferTrxPage = new TransferTransactionPage(driver);
        objTransferTrxListPage = new TransferTransactionListPage(driver);

        //Navigate to Transfer Transaction Page from Dashboard
        assertTrue(objHomePage.welcomeUserTxt.isDisplayed());
        objHomePage.transferBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(objHomePage.newTransferBtn));
        objHomePage.newTransferBtn.click();

        //Create New Transfer Trx
        softAssert.assertTrue(objTransferTrxPage.transferTransactionTitle.getText().equals("Transfer Transaction"),
                "Transfer Transaction Title Not Found");

        //Fill out form
        objTransferTrxPage.fillOutForm("Cloud Strife","Tifa Lockheart", "1000");

        //Verify Transfer Transaction successfully posted
        assertTrue(objTransferTrxListPage.transferTrxListTitle.isDisplayed());
        objTransferTrxListPage.verifyTransferTrxExists(objTransferTrxPage.trxRef);

        softAssert.assertAll();

        driver.quit();
    }
}
