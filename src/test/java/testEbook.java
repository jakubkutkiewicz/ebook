import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class testEbook {
    WebDriver driver;


    @Before
    public void testSetup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\jakub\\IdeaProjects\\kodilla-course\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://ta-ebookrental-fe.herokuapp.com/login");
        PageFactory.initElements(driver, ebook.class);
        WebDriverWait wait = new WebDriverWait(driver, 20);
    }



    @Test
    public void testLogin() {
        ebook ebook = new ebook(driver);
        ebook.loginCorrect();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"titles\"]/h2")));

    }

    @Test
    public void loginFailed() {
        ebook ebook = new ebook(driver);
        ebook.loginFail();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"app\"]/div/form/div[1]/p")));

    }

    @Test
    public void registerNewUser() {
        ebook ebook = new ebook(driver);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        ebook.registerNewUser();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"app\"]/div/form/div[1]/p")));

    }

    @Test
    public void registerNewUserFailedDifferPass() {
        ebook ebook = new ebook(driver);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        ebook.failRegisterNewUserDifferPass();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("alert__content")));

    }
    @Test
    public void registerNewUserFailedNoLogin(){
        ebook ebook = new ebook(driver);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        ebook.failRegisterNewUserNoLogin();
        WebElement text = driver.findElement(By.cssSelector("#app > div > form > div.alert.alert--error > p"));
        wait.until(ExpectedConditions.textToBePresentInElement(text,"You can't leave fields empty"));
    }
}

