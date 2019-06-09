package Framework.TestNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {

	WebDriver driver;

	@BeforeMethod
	public void setEnviroment() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
	}

	@Test(invocationCount = 1, priority = 1)
	public void testLogIn() {
		driver.get("https://www.tekschoolofamerica.com/test-environment/");
		driver.findElement(By.xpath("//span[text() ='My Account']")).click();
		driver.findElement(By.xpath("//a[text() ='Login']")).click();
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("test001@test.com");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("test001");
		driver.findElement(By.xpath("//input[@value='Login']")).click();

		String title = driver.getTitle();
		// System.out.println(title);
		Assert.assertEquals(title, "My Account");

	}

	@Test(priority = 2)
	public void testLogIn2() {
		driver.get("https://www.tekschoolofamerica.com/test-environment/");
		driver.findElement(By.xpath("//span[text() ='My Account']")).click();
		driver.findElement(By.xpath("//a[text() ='Login']")).click();
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("test002@test.com");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("test002");
		driver.findElement(By.xpath("//input[@value='Login']")).click();

	}

	@Test(priority = 3)
	public void infinitFailure() {

		int i = 0;
		while (i >= 5) {
			System.out.println("jack ma return " + i++);
		}
	}

	@AfterMethod
	public void closeTheEnviroment() {
		driver.close();
		driver.quit();
	}

}