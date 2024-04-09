package cloudBankTestCases;

import cloudBankPages.ClientListPage;
import cloudBankPages.CreateClientPage;
import cloudBankPages.Homepage;
import cloudBankPages.LogInPage;
import org.apache.commons.lang3.RandomStringUtils;
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

public class UpdateClientDetailsTestCase {
    WebDriver driver;

    LogInPage objLogInPage;
    Homepage objHomePage;
    CreateClientPage objCreateClientPage;
    ClientListPage objClientListPage;

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
    void updateClientDetails() {

        String randomAlphabet = RandomStringUtils.randomAlphabetic(8);
        String randomNumeric = RandomStringUtils.randomNumeric(8);
        String randomAlphaNumeric = RandomStringUtils.randomAlphanumeric(8);

        SoftAssert softAssert = new SoftAssert();


        String firstName = "FOR UPDATE "+randomAlphabet;
        String lastName = "Test "+randomAlphabet;
        String address = "Manila "+randomAlphaNumeric;
        String mobileNum = "12"+randomNumeric;
        String emailAddress = "testabc"+randomAlphaNumeric+"@gmail.com";


        objHomePage = new Homepage(driver);
        objCreateClientPage = new CreateClientPage(driver);
        objClientListPage = new ClientListPage(driver);

        //Navigate to Client list from Dashboard
        assertTrue(objHomePage.welcomeUserTxt.isDisplayed());
        objHomePage.clientsBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(objHomePage.clientListBtn));
        objHomePage.clientListBtn.click();

        //Update Client Details
        softAssert.assertTrue(objClientListPage.clientListTitle.getText().equals("Client List"),
                "Client List Title Not Found");
        objClientListPage.getClientNameLocator("FOR UPDATE").click();
        softAssert.assertTrue(objCreateClientPage.updateClientTitle.getText().equals("Update Client"),
                "Update Client Title Not Found");
        objCreateClientPage.clearAllTextFieldValue();
        objCreateClientPage.fillOutForm(firstName, lastName, address, mobileNum, emailAddress);

        //Verify Client details is successfully updated
        assertTrue(objClientListPage.clientListTitle.isDisplayed());
        objClientListPage.getClientNameLocator("FOR UPDATE").click();

        softAssert.assertTrue(objCreateClientPage.updateClientTitle.getText().equals("Update Client"),
                "Update Client Title Not Found");

        softAssert.assertTrue(objCreateClientPage.firstNameTxt.getAttribute("value").equals(firstName),
                "First Name is not updated");
        softAssert.assertTrue(objCreateClientPage.lastNameTxt.getAttribute("value").equals(lastName),
                "Last Name is not updated");
        softAssert.assertTrue(objCreateClientPage.addressTxt.getAttribute("value").equals(address),
                "Address is not updated");
        softAssert.assertTrue(objCreateClientPage.mobileNumTxt.getAttribute("value").equals(mobileNum),
                "Mobile Number is not updated");
        softAssert.assertTrue(objCreateClientPage.emailAddTxt.getAttribute("value").equals(emailAddress),
                "Email Address is not updated");

        softAssert.assertAll();

        driver.quit();

    }
}
