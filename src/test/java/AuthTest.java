


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObject.MainPage;

import java.time.Duration;


public class AuthTest {

     MainPage mainPage;
     WebDriver driver;

    @BeforeEach
    public void setup(){
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://online.mkb.ru/");

        mainPage=new MainPage(driver);
    }
    @Test
    public void auth_blocker_check() throws InterruptedException {
    mainPage.authorization("Avtotest","123456");

    //По правилам тестирования должна делаться 1 проверка на тест кейс,
    // но в нашем случае у нас есть действия после проверки, смысл которых неясен
    mainPage.auth_error_message_check();

    //В цикле ниже я сделал проверку отправки пароля 3 дополнительных раза.
    //Смысл этого неясен, так как всего дается 3 попытки ввода и после 3 появляется ограничение авторизации
        for (int i = 0; i < 3; i++) {
            mainPage.authorization("123456");
        }



    //Здесь в закоменченном коде я предлагаю сделать 2 дополнительных попытки авторизоваться вместо 3 дополнительных
    //а затем проверить, что появилось сообщение ограничения входа в систему
/*        for (int i = 0; i < 2; i++) {
            mainPage.authorization("123456");
        }
        mainPage.auth_block_error_message_check();*/
    }

    @AfterEach
    public void tear_down(){
            driver.close();
    }
}
