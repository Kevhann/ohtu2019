package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
// import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import java.util.Random;

public class Tester {

    public static void main(String[] args) {
        // WebDriver driver = new ChromeDriver();
        WebDriver driver = new HtmlUnitDriver();
        Random r = new Random();

        driver.get("http://localhost:4567");

        System.out.println(driver.getPageSource());

        sleep(0.5);

        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        System.out.println(driver.getPageSource());

        sleep(0.5);

        element = driver.findElement(By.name("username"));
        element.sendKeys("arto" + r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("artomies123");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("artomies123");
        element = driver.findElement(By.name("signup"));

        sleep(0.5);

        System.out.println(driver.getPageSource());

        element.submit();

        sleep(0.5);
        System.out.println(driver.getPageSource());

        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        sleep(0.5);
        System.out.println(driver.getPageSource());

        element = driver.findElement(By.linkText("logout"));
        element.click();
        System.out.println(driver.getPageSource());

        driver.quit();
    }

    private static void sleep(double n) {
        try {
            Thread.sleep((long) (n * 1000));
        } catch (Exception e) {
        }
    }
}
