import org.openqa.selenium.By;
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

    @FindBy(css = "button[class='remove-btn btn--small btn btn--error']")
    static WebElement removeButton;

    @FindBy(css = "button[class='edit-btn btn--small btn btn--warning']")
    static WebElement editButton;

    @FindBy(css = "button[class='btn btn--primary']")
    static WebElement editTitleSubmitButton;

    @FindBy(css = "button[class='show-copies-btn btn--small btn btn--primary']")
    static WebElement showCopiesButton;

    @FindBy(css = "button[name='add-item-button']")
    static WebElement addNewCopiesButton;

    @FindBy(css = "button[name='submit-button']")
    static WebElement addCopySubmitButton;


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
        password.sendKeys("haslo");
        passRepeat.sendKeys("haslo");
        signUp.click();
    }


    public void addNewTitleToList() {
        login();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(addNewTitle));
        addNewTitle.click();
        title.sendKeys("tytul ksiazki");
        author.sendKeys("autor ksiazki");
        year.sendKeys("2020");
        addTitleButton.click();

    }

    public void removePositionFromList() {
        removeButton.click();
    }


    public void editPostion() {
        login();
        WebDriverWait wait1 = new WebDriverWait(driver, 5);
        wait1.until(ExpectedConditions.elementToBeClickable(addNewTitle));
        editButton.click();
        title.clear();
        title.sendKeys("edited title");
        author.clear();
        author.sendKeys("edited author");
        year.clear();
        year.sendKeys("2000");
        editTitleSubmitButton.click();

    }
/////// operacje na ksiazkach

    public void showCopies() {
        login();
        WebDriverWait wait1 = new WebDriverWait(driver, 5);
        wait1.until(ExpectedConditions.elementToBeClickable(addNewTitle));
        showCopiesButton.click();
    }

    public void addNewCopies() {
        login();
        WebDriverWait wait1 = new WebDriverWait(driver, 5);
        wait1.until(ExpectedConditions.elementToBeClickable(addNewTitle));
        showCopiesButton.click();
        wait1.until(ExpectedConditions.elementToBeClickable(addNewCopiesButton));
        addNewCopiesButton.click();
        addCopySubmitButton.click();
//        WebElement purchaseDate = driver.findElement(By.xpath("//*[@id='id']"));
//        purchaseDate.click();
//        WebElement yearCenter = driver.findElement(By.xpath("//*[@id=\"title-copies\"]"));
//        yearCenter.click();
//        purchaseDate.click();
//        yearCenter.sendKeys("20000101");
//        addNewCopiesButton.click();


    }
}
