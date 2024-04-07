package cloudBankTestCases;

import cloudBankPages.DepositTransactionListPage;
import cloudBankPages.DepositTransactionPage;
import cloudBankPages.Homepage;
import cloudBankPages.LogInPage;
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

public class ViewDepositTransactionTestCase {
    WebDriver driver;

    LogInPage objLogInPage;
    Homepage objHomePage;
    DepositTransactionPage objDepositTrxPage;
    DepositTransactionListPage objDepositTrxListPage;

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
    void viewDepositTransaction(){
        SoftAssert softAssert = new SoftAssert();

        objHomePage = new Homepage(driver);
        objDepositTrxPage = new DepositTransactionPage(driver);
        objDepositTrxListPage = new DepositTransactionListPage(driver);

        //Navigate to Deposit Transaction List Page from Dashboard
        assertTrue(objHomePage.welcomeUserTxt.isDisplayed());
        objHomePage.depositBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(objHomePage.depositTrxListBtn));
        objHomePage.depositTrxListBtn.click();

        //View and Verify Deposit Transaction
        objDepositTrxListPage.getDepositTrxInList("2").click();
        softAssert.assertTrue(objDepositTrxPage.depositTransactionTitle.getText().equals("Deposit Transaction"),
                "Deposit Transaction Title Not Found");

        objDepositTrxPage.verifyDepositTrx("2024-04-01","2","DONE","Cloud Strife","1222.0","PHP");

        softAssert.assertAll();
        driver.quit();
    }
}
