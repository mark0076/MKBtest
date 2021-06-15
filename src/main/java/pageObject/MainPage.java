package pageObject;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private WebDriver driver;
    public MainPage(WebDriver driver){
        this.driver=driver;
    }

    private By loginInput= By.xpath("//input[@name='txtLogin']");
    private By passwordInput= By.xpath("//input[@name='txtPassword']");
    private By enterButton=By.cssSelector("#btnLoginStandard");
    private By errorAuthMessage=By.xpath("//div[@id='errMessage' and contains(text(), 'Ошибка аутентификации.')]");
    private By errorAuthBlockerMessage=By.xpath("//div[@id='errMessage' and contains(text(), 'В целях безопасности вход в систему ограничен.')]");

    public void authorization(String login, String password){
        driver.findElement(loginInput).sendKeys(login);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(enterButton).click();
    }
    public void authorization( String password){
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(enterButton).click();
    }
    public void auth_error_message_check() throws InterruptedException {
        Assertions.assertTrue(driver.findElement(errorAuthMessage).isDisplayed(),"Error message is not visible but should be visible");
    }
    public void auth_block_error_message_check(){
        Assertions.assertTrue(driver.findElement(errorAuthBlockerMessage).isDisplayed(),"Error message limit of attempts is not visible but should be visible");
    }


}
