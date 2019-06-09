package Framework.TestNG;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
public class Practice2 {

	String item1="HP LP3065";
	String item2="MacBook";
	String item3="Sony VAIO";
	
	static WebDriver driver;
	@BeforeClass
	public void setEnviroment() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	
	public static void goToLaptopsAndDesks() {
		driver.get("https://www.tekschoolofamerica.com/test-environment/");
		driver.findElement(By.xpath("//a[text()='Laptops & Notebooks']")).click();
		driver.findElement(By.xpath("//a[text()='Show All Laptops & Notebooks']")).click();
		//button[descendant::span[text()='Add to Cart']]
	}

	public static void addItemtoCart(String itemName) {
		String xpath="//a[text()='"+itemName+"']";
		driver.findElement(By.xpath(xpath)).click();
		driver.findElement(By.xpath("//button[text()='Add to Cart']")).click();
		boolean messageAppear = driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")).isDisplayed();
		Assert.assertTrue(messageAppear);
	}
	
	public static void verifyItemAdded(String itemName) {
		driver.findElement(By.xpath("//a[@title='Shopping Cart']")).sendKeys(Keys.ENTER);
		String xpath="//a[text()='"+itemName+"']";
		boolean itemExist = driver.findElement(By.xpath(xpath)).isDisplayed();
		Assert.assertTrue(itemExist);
	}
	

	
	@Test
	public void addFirstItem() {
		goToLaptopsAndDesks();
		addItemtoCart(item1);
		verifyItemAdded(item1);
	}
	@Test
	public void addSecondItem() {
		goToLaptopsAndDesks();
		addItemtoCart(item2);
		verifyItemAdded(item2);
	}
	@Test
	public void addThirdItem() {
		goToLaptopsAndDesks();
		addItemtoCart(item3);
		verifyItemAdded(item3);
	}
	
	@AfterClass
	public void closeTheEnviroment() {
		driver.close();
		driver.quit();
	}
}