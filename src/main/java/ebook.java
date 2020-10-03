import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.SecureRandom;

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


    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }


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
        login.sendKeys(randomString(5));
        password.sendKeys("sssss");
        passRepeat.sendKeys("sssss");
        singUp.click();
    }
    public void failRegisterNewUserDifferPass() {
        singUp.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(passRepeat));
        login.sendKeys(randomString(5));
        password.sendKeys("sssss");
        passRepeat.sendKeys("ssss");
        singUp.click();
    }

    public void failRegisterNewUserNoLogin(){
        singUp.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(passRepeat));
        password.sendKeys("sssss");
        passRepeat.sendKeys("ssss");
        singUp.click();
    }

}
