package flogin;

import static org.testng.AssertJUnit.assertTrue;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class OnMouseOverTest {
	WebDriver driver;
	
	@BeforeClass
	public void initBroswer() {
		driver = new FirefoxDriver();
		driver.get("http://skiva.com.au");
		driver.manage().window().maximize();
	}
	
	public boolean verifyText(WebDriver driver, String expText) {
		if (expText.equals("notext"))
			return true;
		
		if (driver.getPageSource().contains(expText)) {
			System.out.println("Returning true "+expText);
			return true;
		} else {
			System.out.println("Returning false "+expText);
			return false;
		}
	}
	
	
	public boolean verifyTitle(String actTitle,String expTitle) {
		System.out.println("Expected title "+expTitle);
		System.out.println("Actual title "+actTitle);
		return actTitle.equals(expTitle);
		
	}
	
	@Test
	public void selectAfterMouseOver() {
		//To identify web element 'Training' on skiva home page using xpath (use firepath to find xpath)
		// and assign it to mou WebElement
		WebElement mou = driver.findElement(By.xpath(".//*[@id='menu']/li[4]/a/span"));
		
		//Use Actions Class to perform mouse overaction
		//Supply 'driver' object created in initBrowser to the new instance of Actions class and save in builder
		 
		Actions builder = new Actions(driver);
		//To put mouseOver on 'Training' on skiva.com.au
		//builder will not give you moveToElement method which is used for mouseOver
		//pass 'mou' WebElement to moveToElement element and invoke perform as follows for mouseover effect
		builder.moveToElement(mou).perform();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Identify web element 'Cloud Training' inside Training using xpath and store it in 'trang'
		WebElement trang = driver.findElement(By.xpath(".//*[@id='menu-item-4769']/a/span"));
		//Perform click operation by passing WebElement 'trang' and invoking perform as follows: 
		builder.click(trang).build().perform();
		String expectedTitle="Cloud Training - AWS (Amazon Web Services) - Skiva";
		String expectedText="LEARN CLOUD COMPUTING";
		
		//verifyTitle and verifyText has been used together to decide if test case is passed
		//Similarly other methods like pageNotFoundErr, exeptionOnPage etc could be
		//used in one assertTrue to decide if test is passed or failed
		Assert.assertTrue(verifyTitle(driver.getTitle(),expectedTitle) & verifyText(driver,expectedText));
					
		
	}
	
	//you can get the result of your test in your @AfterTest method via ITestResult result
        //ITestResult provides many methods like getName and getStatus. 
	//These can be used for better logging and reporting of executed test cases
	@AfterMethod
	public void tearDown(ITestResult result) {
		//getName provides the test method name
		String tName = result.getName();
		System.out.println("Invoking Teardown for Test = "+tName);
		if(result.getStatus()==ITestResult.FAILURE)
		{
			System.out.println("Test Case "+tName+" FAILED");
		} else {
			System.out.println("Test Case "+tName+" PASSED");
		}
		
	}
	
	@AfterClass
	public void closeBroswer() {
		driver.close();
	}

}
