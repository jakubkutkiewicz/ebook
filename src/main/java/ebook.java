import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.SecureRandom;
import java.util.List;

public class ebook extends AbstractPage {

    @FindBy(css = "input[name='password']")
    static WebElement password;

    @FindBy(css = "input[name='login']")
    static WebElement login;

    @FindBy(css = "button[id='login-btn']")
    static WebElement button;

    @FindBy(css = "button[id='register-btn']")
    static WebElement signUp;

    @FindBy(css = "input[id='password-repeat']")
    static WebElement passRepeat;

    @FindBy(css = "button[id='add-title-button']")
    static WebElement addNewTitle;

    @FindBy(css = "input[name='title']")
    static WebElement title;

    @FindBy(css = "input[name='author']")
    static WebElement author;

    @FindBy(css = "input[name='year']")
    static WebElement year;

    @FindBy(css = "button[name='submit-button']")
    static WebElement addTitleButton;

    @FindBy(css = "button[name='remove-btn btn--small btn btn--error']")
    static WebElement removeButton;


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

    public void login() {
        login.sendKeys("jakubk");
        password.sendKeys("jakubk");
        button.click();
    }

    public void registerNewUser() {
        signUp.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(passRepeat));
        login.sendKeys(randomString(5));
        password.sendKeys("sssss");
        passRepeat.sendKeys("sssss");
        signUp.click();
    }


    public void addNewTitle() {
        login();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(addNewTitle));
        addNewTitle.click();
        title.sendKeys("Nazwa Ksiazki");
        author.sendKeys("Autor Ksiazki");
        year.sendKeys("2020");
        addTitleButton.click();

    }

    public void remove() {
        login();
        List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"titles\"]/ul"));

        }
    }


