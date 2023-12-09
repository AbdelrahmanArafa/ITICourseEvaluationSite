import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class HomeTests {
    WebDriver webDriver;
    LoginPage loginPage ;
    HomePage homePage ;

    @BeforeTest
    public void OpenBrowser() throws InterruptedException {
        webDriver = new ChromeDriver();
        loginPage = new LoginPage(webDriver);
        homePage = new HomePage(webDriver);
        webDriver.manage().window().maximize();
        Thread.sleep(3000);
        webDriver.get("http://apps.iti.gov.eg/ManagementSystem/intlogin.aspx?returnURL=http://apps.iti.gov.eg/ManagementSystem/Student/CourseEval.aspx");
        loginPage.usernameEle().clear();
        loginPage.usernameEle().sendKeys("AB38276");
        loginPage.passwordEle().clear();
        loginPage.passwordEle().sendKeys("iti5");
        loginPage.loginEle().click();
    }


    @Test
    public void MySchedule()
    {
        homePage.myscheduleEle().click();
        SoftAssert softAssert = new SoftAssert();
        webDriver.findElement(By.className("fc-button-content")).click();
        //First Assertion
        softAssert.assertEquals(webDriver.getCurrentUrl(),"http://apps.iti.gov.eg/ManagementSystem/Schedule/StudentView.aspx");
        //Second Assertion
        softAssert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_UpdatePanel1\"]/div/div/div[1]/h6")).getText(),"Calendar ");
    }

    @Test
    public void Grades()
    {
        homePage.gradesEle().click();
        SoftAssert softAssert = new SoftAssert();
        //First Assertion
        softAssert.assertEquals(webDriver.getCurrentUrl(),"http://apps.iti.gov.eg/ManagementSystem/Student/StudentGrade.aspx");
        //Second Assertion
        softAssert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"general\"]/ul/li[2]/a")).getText(),"Grades");
        //Third Assertion
        softAssert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_UcViewGrade1_UpdatePanel1\"]/div/div/div/h6")).getText(),"\n" +
                "                     Course Grade");
        //Fourth Assertion
        softAssert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_UcViewGrade1_GrdViewcourseEval\"]/tbody/tr[1]/th[1]")).getText(),"Course");
        //Fifth Assertion
        softAssert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_UcViewGrade1_GrdViewcourseEval\"]/tbody/tr[1]/th[2]")).getText(),"Grade");

    }

    @Test
    public void Evaluatios()
    {
        homePage.evaluationsEle().click();
        SoftAssert softAssert = new SoftAssert();
        //First Assertion
        softAssert.assertEquals(webDriver.getCurrentUrl(),"http://apps.iti.gov.eg/ManagementSystem/Student/CourseEval.aspx");
        //Second Assertion
        softAssert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/span")).getText(),"Course Evaluation");
        //Third Assertion
        softAssert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"breadcrumbs\"]/li/a")).getText(),"Course Evaluation");

    }

    @Test
    public void DoCourseEvaluationWithValidRate() throws InterruptedException {
//        homePage.evaluationsEle().click();
        homePage.drop_downEle().click();
        homePage.select_courseEle().click();
        Thread.sleep(3000);



        for (int i = 0; i <= 14; i++) {
            WebElement currentElement = homePage.rate_lc(i);
            try {
                Thread.sleep(1500);
                currentElement.click();
            } catch (StaleElementReferenceException e) {
                // Element is stale, retry the operation
                currentElement = homePage.rate_lc(i); // Re-locate the element
                currentElement.click();
            }
        }

        Thread.sleep(1000);
        for (int i = 0; i <= 13; i++) {
            WebElement currentElement = homePage.rate_lab(i);
            try {
                Thread.sleep(1500);
                currentElement.click();
            } catch (StaleElementReferenceException e) {
                // Element is stale, retry the operation
                currentElement = homePage.rate_lc(i); // Re-locate the element
                currentElement.click();
            }
        }

        SoftAssert softAssert = new SoftAssert();
        //First Assertion
        softAssert.assertEquals(webDriver.getCurrentUrl(),"http://apps.iti.gov.eg/ManagementSystem/Student/CourseEval.aspx");
        //Second Assertion
        softAssert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/span")).getText(),"Course Evaluation");
        //Third Assertion
        softAssert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"breadcrumbs\"]/li/a")).getText(),"Course Evaluation");


        homePage.finishEle().click();
        Thread.sleep(7000);
        //Fourth Assertion
        softAssert.assertEquals(webDriver.findElement(By.id("ContentPlaceHolder1_UcCourseEval1_lblMsg")).getText(),"Your data saved sucessfully");

        softAssert.assertAll();
    }

    @Test
    public void DoCourseEvaluationWithInvalidRate() throws InterruptedException {
        homePage.evaluationsEle().click();
        homePage.drop_downEle().click();
        homePage.select_courseEle().click();
        Thread.sleep(1000);

        homePage.finishEle().click();
        Thread.sleep(1000);
        SoftAssert softAssert = new SoftAssert();
        //First Assertion
        softAssert.assertEquals(webDriver.getCurrentUrl(),"http://apps.iti.gov.eg/ManagementSystem/Student/CourseEval.aspx");
        //Second Assertion
        softAssert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/span")).getText(),"Course Evaluation");
        //Third Assertion
        softAssert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"breadcrumbs\"]/li/a")).getText(),"Course Evaluation");
        //Fourth Assertion
        softAssert.assertEquals(webDriver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_UcCourseEval1_lblMsg\"]")).getText(),"Oops sorry.Please select rating");
        softAssert.assertAll();
    }


    @AfterTest
    public void CloseBrowser() throws InterruptedException {
        Thread.sleep(3000);
        webDriver.quit();
    }
}
