import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTests {
    WebDriver webDriver;
    LoginPage loginPage ;

    @BeforeTest
    public void OpenBrowser() throws InterruptedException {
        webDriver = new ChromeDriver();
        loginPage = new LoginPage(webDriver);
        webDriver.manage().window().maximize();
        Thread.sleep(3000);
    }


    @Test
    public void ValidUserAndPassword()
    {
        webDriver.get("http://apps.iti.gov.eg/ManagementSystem/intlogin.aspx?returnURL=http://apps.iti.gov.eg/ManagementSystem/Student/CourseEval.aspx");
        loginPage.usernameEle().clear();
        loginPage.usernameEle().sendKeys("AB38276");
        loginPage.passwordEle().clear();
        loginPage.passwordEle().sendKeys("iti5");
        loginPage.loginEle().click();

        SoftAssert softAssert = new SoftAssert();
         //First Assertion
        softAssert.assertEquals(webDriver.getCurrentUrl(),"http://apps.iti.gov.eg/ManagementSystem/Student/CourseEval.aspx");
        //Second Assertion
        softAssert.assertEquals(webDriver.findElement(By.className("pageTitle")).getText(),"Course Evaluation");
        //Third Assertion
        softAssert.assertEquals(webDriver.findElement(By.id("lblusername")).getText(),"Abdelrahman Arafa Osman Mohamed");
        softAssert.assertAll();
    }


    @Test
    public void ValidUserAndInvalidPassword()
    {
        webDriver.get("http://apps.iti.gov.eg/ManagementSystem/intlogin.aspx?returnURL=http://apps.iti.gov.eg/ManagementSystem/Student/CourseEval.aspx");
        loginPage.usernameEle().clear();
        loginPage.usernameEle().sendKeys("AB38276");
        loginPage.passwordEle().clear();
        loginPage.passwordEle().sendKeys("iti");
        loginPage.loginEle().click();

       SoftAssert softAssert = new SoftAssert();
        //First Assertion
        softAssert.assertEquals(webDriver.getCurrentUrl(),"http://apps.iti.gov.eg/ManagementSystem/intlogin.aspx?returnURL=http://apps.iti.gov.eg/ManagementSystem/Student/CourseEval.aspx");
        //Second Assertion
        softAssert.assertEquals(webDriver.findElement(By.xpath("//*[text()='Invalid Password']")).getText(),"Invalid Password");
        softAssert.assertAll();
    }

    @Test
    public void InvalidUserAndValidPassword()
    {
        webDriver.get("http://apps.iti.gov.eg/ManagementSystem/intlogin.aspx?returnURL=http://apps.iti.gov.eg/ManagementSystem/Student/CourseEval.aspx");
        loginPage.usernameEle().clear();
        loginPage.usernameEle().sendKeys("AB");
        loginPage.passwordEle().clear();
        loginPage.passwordEle().sendKeys("iti5");
        loginPage.loginEle().click();

        SoftAssert softAssert = new SoftAssert();
        //First Assertion
        softAssert.assertEquals(webDriver.getCurrentUrl(),"http://apps.iti.gov.eg/ManagementSystem/intlogin.aspx?returnURL=http://apps.iti.gov.eg/ManagementSystem/Student/CourseEval.aspx");
        //Second Assertion
        softAssert.assertEquals(webDriver.findElement(By.xpath("//*[text()='Invalid Username']")).getText(),"Invalid Username");
        softAssert.assertAll();
    }

    @Test
    public void ValidUserAndBlankPassword()
    {
        webDriver.get("http://apps.iti.gov.eg/ManagementSystem/intlogin.aspx?returnURL=http://apps.iti.gov.eg/ManagementSystem/Student/CourseEval.aspx");
        loginPage.usernameEle().clear();
        loginPage.usernameEle().sendKeys("AB38276");
        loginPage.passwordEle().clear();
        loginPage.passwordEle().sendKeys("");
        loginPage.loginEle().click();

        SoftAssert softAssert = new SoftAssert();
        //First Assertion
        softAssert.assertEquals(webDriver.getCurrentUrl(),"http://apps.iti.gov.eg/ManagementSystem/intlogin.aspx?returnURL=http://apps.iti.gov.eg/ManagementSystem/Student/CourseEval.aspx");
        //Second Assertion
        softAssert.assertEquals(webDriver.findElement(By.xpath("//*[text()='Required Field Password']")).getText(),"Required Field Password");
        softAssert.assertAll();
    }

    @Test
    public void BlankUserAndValidPassword()
    {
        webDriver.get("http://apps.iti.gov.eg/ManagementSystem/intlogin.aspx?returnURL=http://apps.iti.gov.eg/ManagementSystem/Student/CourseEval.aspx");
        loginPage.usernameEle().clear();
        loginPage.usernameEle().sendKeys("");
        loginPage.passwordEle().clear();
        loginPage.passwordEle().sendKeys("iti5");
        loginPage.loginEle().click();

        SoftAssert softAssert = new SoftAssert();
        //First Assertion
        softAssert.assertEquals(webDriver.getCurrentUrl(),"http://apps.iti.gov.eg/ManagementSystem/intlogin.aspx?returnURL=http://apps.iti.gov.eg/ManagementSystem/Student/CourseEval.aspx");
        //Second Assertion
        softAssert.assertEquals(webDriver.findElement(By.xpath("//*[text()='Required Field UserName']")).getText(),"Required Field UserName");
        softAssert.assertAll();
    }

    @AfterTest
    public void CloseBrowser() throws InterruptedException {
        Thread.sleep(3000);
        webDriver.quit();
    }
}
