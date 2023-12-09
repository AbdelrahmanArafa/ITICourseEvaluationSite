import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver webDriver ;
    public LoginPage(WebDriver webDriver)
    {
        this.webDriver = webDriver;
    }

    public WebElement usernameEle()
    {
        By username = By.id("txtUsername");
        WebElement usernameEle = webDriver.findElement(username);
        return usernameEle;
    }

    public WebElement passwordEle()
    {
        By password = By.id("txtpassword");
        WebElement passwordEle = webDriver.findElement(password);
        return passwordEle;
    }

    public WebElement loginEle()
    {
        By loginbtn = By.id("btnlogin");
        WebElement loginEle = webDriver.findElement(loginbtn);
        return loginEle;
    }
}
