import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class HomePage {
    WebDriver webDriver;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public WebElement myscheduleEle() {
        By myschedule = By.xpath("//*[@id=\"general\"]/ul/li[1]/a");
        WebElement myscheduleEle = webDriver.findElement(myschedule);
        return myscheduleEle;
    }

    public WebElement gradesEle() {
        By grades = By.xpath("//*[@id=\"general\"]/ul/li[2]/a");
        WebElement gradesEle = webDriver.findElement(grades);
        return gradesEle;
    }

    public WebElement evaluationsEle() {
        By evaluations = By.xpath("//*[@id=\"general\"]/ul/li[3]/a");
        WebElement evaluationEle = webDriver.findElement(evaluations);
        return evaluationEle;
    }

    public WebElement drop_downEle() {
        By dropDown = By.xpath("//*[@id=\"ContentPlaceHolder1_UcCourseEval1_ddlCourseName_chzn\"]/a/div/b");
        WebElement drop_downEle = webDriver.findElement(dropDown);
        return drop_downEle;
    }

    public WebElement select_courseEle() {
        By select = By.id("ContentPlaceHolder1_UcCourseEval1_ddlCourseName_chzn_o_1");
        WebElement select_courseEle = webDriver.findElement(select);
        return select_courseEle;
    }

    public WebElement rate_lc(int i) {
        By rate_lc_star = By.id("ContentPlaceHolder1_UcCourseEval1_DataListLectureEval_GrdViewinstEval_0_Rating1_" + i + "_Star_5");
        WebElement rate_lc_Ele = webDriver.findElement(rate_lc_star);
        return rate_lc_Ele;
    }

    public WebElement rate_lab(int i) {
        By rate_lab_star = By.id("ContentPlaceHolder1_UcCourseEval1_GrdViewcourseEval_Rating1_" + i + "_Star_5");
        WebElement rate_lab = webDriver.findElement(rate_lab_star);
        return rate_lab;
    }

public WebElement finishEle() {
    By finish = By.id("ContentPlaceHolder1_UcCourseEval1_btnSave");
    WebElement finishEle = webDriver.findElement(finish);
    return finishEle;
        }

}

