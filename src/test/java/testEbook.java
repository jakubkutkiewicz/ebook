import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Objects;


public class testEbook {
    WebDriver driver;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        testEbook testEbook = (testEbook) o;
        return driver.equals(testEbook.driver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(driver);
    }

    @Before
    public void testSetup() {
        System.setProperty("webdriver.chrome.driver", "C:\\selenium-drivers\\Chrome\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://ta-ebookrental-fe.herokuapp.com/login");
        PageFactory.initElements(driver, ebook.class);
        WebDriverWait wait = new WebDriverWait(driver, 20);
    }

//    @After
//    public void closeDriver() {
//        driver.close();
//    }

    @Test
    public void testLoginPass() {
        ebook ebook = new ebook(driver);
        ebook.login();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"titles\"]")));

    }

    @Test
    public void testloginFail() {
        ebook ebook = new ebook(driver);
        ebook.login.sendKeys("jakubk");
        ebook.password.sendKeys("blednehaslo");
        ebook.button.click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"app\"]/div/form/div[1]/p")));

    }

    @Test
    public void registerNewUserPass() {
        ebook ebook = new ebook(driver);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        ebook.registerNewUser();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"app\"]/div/form/div[1]/p")));

    }

    @Test
    public void registerNewUserFailedDifferPassword() {
        ebook ebook = new ebook(driver);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        ebook.signUp.click();
        wait.until(ExpectedConditions.elementToBeClickable(ebook.passRepeat));
        ebook.login.sendKeys(ebook.randomString(5));
        ebook.password.sendKeys("sssss");
        ebook.passRepeat.sendKeys("ssss");
        ebook.signUp.click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("alert__content")));

    }

    @Test
    public void registerNewUserFailedNoLogin() {
        ebook ebook = new ebook(driver);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        ebook.signUp.click();
        wait.until(ExpectedConditions.elementToBeClickable(ebook.passRepeat));
        ebook.password.sendKeys("sssss");
        ebook.passRepeat.sendKeys("ssss");
        ebook.signUp.click();
        WebElement text = driver.findElement(By.cssSelector("#app > div > form > div.alert.alert--error > p"));
        wait.until(ExpectedConditions.textToBePresentInElement(text, "You can't leave fields empty"));
    }

    @Test
    public void addNewTitleTest() {
        ebook ebook = new ebook(driver);
        ebook.addNewTitleToList();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"titles\"]")));
    }

    @Test
    public void addNewTitleTestFailNoData() {
        ebook ebook = new ebook(driver);
        ebook.login();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(ebook.addNewTitle));
        ebook.addNewTitle.click();
        ebook.title.sendKeys("");
        ebook.author.sendKeys("");
        ebook.year.sendKeys("");
        ebook.addTitleButton.click();
        WebElement text = driver.findElement(By.className("alert__content"));
        wait.until(ExpectedConditions.textToBePresentInElement(text, "\"title\" field shouldn't be empty..."));
    }


    @Test
    public void addNewTitleTestFailNoTitle() {
        ebook ebook = new ebook(driver);
        ebook.login();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(ebook.addNewTitle));
        ebook.addNewTitle.click();
        ebook.title.sendKeys("");
        ebook.author.sendKeys("Autor Ksiazki");
        ebook.year.sendKeys("2020");
        ebook.addTitleButton.click();
        WebElement text = driver.findElement(By.className("alert__content"));
        wait.until(ExpectedConditions.textToBePresentInElement(text, "\"title\" field shouldn't be empty..."));
    }

    @Test
    public void addNewTitleTestFailNoWriter() {
        ebook ebook = new ebook(driver);
        ebook.login();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(ebook.addNewTitle));
        ebook.addNewTitle.click();
        ebook.title.sendKeys("Tytul Ksiazki");
        ebook.author.sendKeys("");
        ebook.year.sendKeys("2020");
        ebook.addTitleButton.click();
        WebElement text = driver.findElement(By.className("alert__content"));
        wait.until(ExpectedConditions.textToBePresentInElement(text, "\"author\" field shouldn't be empty..."));
    }

    @Test
    public void addNewTitleTestFailNoYear() {
        ebook ebook = new ebook(driver);
        ebook.login();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(ebook.addNewTitle));
        ebook.addNewTitle.click();
        ebook.title.sendKeys("Tytul Ksiazki");
        ebook.author.sendKeys("Autor Ksiazki");
        ebook.year.sendKeys("");
        ebook.addTitleButton.click();
        WebElement text = driver.findElement(By.className("alert__content"));
        wait.until(ExpectedConditions.textToBePresentInElement(text, "\"year\" field shouldn't be empty..."));
    }


    @Test
    public void removeTitleFromList() {
        ebook ebook = new ebook(driver);
        ebook.login();
        WebDriverWait wait1 = new WebDriverWait(driver, 5);
        wait1.until(ExpectedConditions.elementToBeClickable(ebook.addNewTitle));
        List<WebElement> listBefore = driver.findElements(By.xpath("//li[contains(@id,'title-')]"));
        int listBeforesize = listBefore.size();
        for (int i = 0; i < listBeforesize; i++)
            System.out.println("size list before add/remove position is:" + listBeforesize);
        ebook.addNewTitle.click();
        ebook.title.sendKeys("ksiazka do usuniecia");
        ebook.author.sendKeys("Autor Ksiazki do usuniecia ");
        ebook.year.sendKeys("2020");
        ebook.addTitleButton.click();
        WebDriverWait wait2 = new WebDriverWait(driver, 5);
        wait2.until(ExpectedConditions.elementToBeClickable(ebook.addNewTitle));
        ebook.removePositionFromList();
        driver.navigate().refresh();

        WebDriverWait wait3 = new WebDriverWait(driver, 5);
        wait3.until(ExpectedConditions.elementToBeClickable(ebook.button));
        ebook.login();

        WebDriverWait wait4 = new WebDriverWait(driver, 5);
        wait4.until(ExpectedConditions.elementToBeClickable(ebook.addNewTitle));
        List<WebElement> listAfter = driver.findElements(By.xpath("//li[contains(@id,'title-')]"));
        int listAftersize = listAfter.size();
        for (int i = 0; i < listAftersize; i++)
            System.out.println("size list after add/remove position is:" + listAftersize);
        Assert.assertEquals(listBeforesize, listAftersize);


    }

    @Test
    public void editAllFieldsInPostion() {
        ebook ebook = new ebook(driver);
        ebook.editPostion();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement text1 = driver.findElement(By.xpath("//div[@class=\"flex flex-grow--1 w-100\"]"));
        wait.until(ExpectedConditions.textToBePresentInElement(text1, "edited"));
        wait.until(ExpectedConditions.textToBePresentInElement(text1, "2000"));

    }

    @Test
    public void showCopiesListTest(){
        ebook ebook = new ebook(driver);
        ebook.showCopies();
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='return-button']")));

    }

    @Test
    public void addNewCopyTest(){
        ebook ebook = new ebook(driver);
        ebook.login();
        WebDriverWait wait1 = new WebDriverWait(driver, 5);
        wait1.until(ExpectedConditions.elementToBeClickable(ebook.addNewTitle));
        ebook.showCopiesButton.click();
        List<WebElement> listBefore = driver.findElements(By.xpath("//li[@id]"));
        int listBeforeSize = listBefore.size();
        for (int i = 0; i <listBeforeSize; i++);
        System.out.println(listBeforeSize);
//        ebook.addNewCopies();
//        WebDriverWait wait = new WebDriverWait(driver, 5);
//        wait.until(ExpectedConditions.)
    }
}







