import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {

    public static void main(String[] args) {
        exercise3();
    }

    public static void exercise3() {
        File file = new File("chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

        WebDriver driver = new ChromeDriver();
        driver.navigate().to("http://www.aliorbank.pl");
        driver.findElement(By.className("login")).click();

        ArrayList tabs = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1).toString());

        driver.findElement(By.name("p_alias")).sendKeys("1234567890");
        driver.findElement(By.xpath("//div[@id='contentTableDiv']/form/table[@id='contentTable']/tbody/tr[3]/td" +
                "/div[@id='buttonTr']/a[@class='button']/span")).click();

        driver.switchTo().frame("main");

        List<WebElement> fieldsToEnter = driver.findElements(By.className("logField"));
        for (WebElement field : fieldsToEnter) {
            field.sendKeys("x");
        }

        driver.findElement(By.id("submitButton")).click();

        WebElement loginFailed = null;
        try {
            loginFailed = driver.findElement(By.xpath("//*[contains(text(), 'Błąd podczas logowania')]"));
        } catch (NoSuchElementException e) {
            System.out.println("Login successful, because there was no element with text \"Błąd podczas logowania\" on site!");
        }
        if (loginFailed != null) {
            System.out.println("Login failed, because there was an element with text \"Błąd podczas logowania\" on site!");
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}