package cloudBankTestCases;

import cloudBankPages.CreateSystemUserPage;
import cloudBankPages.Homepage;
import cloudBankPages.LogInPage;
import cloudBankPages.SystemUserListPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertTrue;
import org.testng.asserts.SoftAssert;

public class NewUserCreationTestCase {

    WebDriver driver;

    LogInPage objLogInPage;
    Homepage objHomePage;
    CreateSystemUserPage objCreateSysUserPage;
    SystemUserListPage objSystemUserListPage;



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
    void create_new_user(){
        String username = "testuser0229";
        String password = "te$ting123";

        SoftAssert softAssert = new SoftAssert();

        objHomePage = new Homepage(driver);
        objCreateSysUserPage = new CreateSystemUserPage(driver);
        objSystemUserListPage = new SystemUserListPage(driver);

        //Navigate to Create System User Page from Dashboard
        assertTrue(objHomePage.welcomeUserTxt.isDisplayed());
        objHomePage.systemUserBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(objHomePage.newSystemUserBtn));
        objHomePage.newSystemUserBtn.click();

        //Create New User
        softAssert.assertTrue(objCreateSysUserPage.createUserTitle.getText().equals("Create System Users"),
                "Create System User Title Not Found");
        objCreateSysUserPage.createNewUser(username, password);

        //Verify user is successfully created
        assertTrue(objSystemUserListPage.systemUserListTitle.isDisplayed());
        objSystemUserListPage.verifyUserExists(username);

        softAssert.assertAll();

        driver.quit();
    }
}
