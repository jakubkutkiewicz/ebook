import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ebook extends AbstractPage {

    @FindBy(css = "input[name='password']")
    static WebElement password;

    @FindBy(css = "input[name='login']")
    static WebElement login;

    @FindBy(css = "button[id='login-btn']")
    static WebElement button;

    @FindBy(css = "button[id='register-btn']")
    static WebElement singUp;

    @FindBy(css = "input[id='password-repeat']")
    static WebElement passRepeat;

    @FindBy(css = "input[id='login']")
    static WebElement loginSingUp;

    public ebook(WebDriver driver) {
        super(driver);
    }

    public void loginCorrect() {
        login.sendKeys("demo");
        password.sendKeys("demo");
        button.click();
    }

    public void loginFail() {
        login.sendKeys("demooo");
        password.sendKeys("demo");
        button.click();
    }

    public void registerNewUser() {
        singUp.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(passRepeat));
        login.sendKeys("kubakuab");
        password.sendKeys("sssss");
        passRepeat.sendKeys("ssss");

    }
}