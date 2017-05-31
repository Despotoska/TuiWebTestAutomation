import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TuiShortHaul2Adults1Child 
{
	static WebDriver driver;
	static WebElement w;
	static WebDriverWait wait;
	static Actions actions;
	static String mainWindowHandle;
	
	@SuppressWarnings("deprecation")
	@BeforeClass
	public static void setUp() throws Exception 
	{
		//Initialize driver
		File binaryFile = new File(TestDataWeb.FIREFOX_PATH);
		FirefoxBinary ffBinary = new FirefoxBinary(binaryFile);
		FirefoxProfile ffProfile = new FirefoxProfile();
		System.setProperty("webdriver.gecko.driver", TestDataWeb.GECKO_DRIVER_PATH);
		driver = new FirefoxDriver(ffBinary, ffProfile);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
	    driver.get(TestDataWeb.TUI_PROD_URL_SE);
		actions = new Actions(driver);
	    wait = new WebDriverWait(driver, 20);
		mainWindowHandle = driver.getWindowHandle();
	}
	@Test
	  public void TuiSEShortHaul2Adults1Child() throws Exception
	  {
//			Select departure place
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class, 'text') and text() = 'V‰lj avreseort']"))).click();
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='stations'] ul li span[data-itemid='"+TestDataWeb.DEPARTURE1+"'] input[type='checkbox']"))).click();
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='stations'] span[class='panel-select-close icon-x']"))).click();
	   
//			Select destination
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#countries > div.form-box.clearfix"))).click();
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='destinations'] input[type='text']"))).sendKeys(TestDataWeb.DESTINATION1);
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='destinations'] //ul[@class='search-filter-items'] //span[contains(text(),'"+TestDataWeb.DESTINATION1+"')]"))).click();
		    
//			Select trip duration
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#durations"))).click();
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='duration-lengths'] //ul //span[contains(text(),'"+TestDataWeb.DURATION+"')]"))).click();
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='duration-lengths'] span[class='panel-select-close icon-x']"))).click();
			
//		    Select Date from
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#date"))).click();
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@data-handler='selectDay' and @data-month='5' and @data-year='2017']/a[contains(text(),'11')]"))).click();
		    
//		    Choose how many passengers will travel(2+1)
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id=paxes]"))).click();
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='children clearfix'] i[class='icon icon-plus add']"))).click();
		    Select dropDownChildAges = new Select (wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ages clearfix'] //select[@data-index='1']"))));
		    dropDownChildAges.selectByValue("6");
		    
//		    Search
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-btn"))).click();
	    
//		    Check if the price for the hotel is reasonable
		    String hotelPriceString = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(),'"+TestDataWeb.HOTEL1+"')]/ancestor::div[2]//span[@class='price-current']"))).getText();
		    String hotelPriceStringTemp = hotelPriceString.replaceAll("[^0-9,]", "");
		    int hotelPrice = Integer.parseInt(hotelPriceStringTemp);
		    assertTrue(hotelPrice>TestDataWeb.HOTELPRICE1);
		    
//		    Select hotel (opens new window)
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@class='accommodation-name' and contains(text(), '"+TestDataWeb.HOTEL1+"')]"))).click();
		    Set<String> allWindowHandles = driver.getWindowHandles();
		    for( String s : allWindowHandles)
		    {
		    	if(!s.equals(mainWindowHandle))
		   			driver.switchTo().window(s);
		   	}

//		    Continues to the next step(choose a flight) in the booking
		    WebElement forwardButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[ng-show='forwardButton']")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", forwardButton);    
		    Thread.sleep(1500);
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[ng-show='forwardButton']"))).click();

	    
//		  	Tui recommended flight is by default selected and this code clicks on the continue button
		    WebElement forwardButtonFlight = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='row button-panel'] button[ng-show='forwardButton']")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", forwardButtonFlight);
		    Thread.sleep(1500);
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='row button-panel'] button[ng-show='forwardButton']"))).click();
		   
//		    Select room and meals
		    WebElement forwardToBookingButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[ng-show='forwardToBookingButton']")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", forwardToBookingButton); 
		    Thread.sleep(1500);
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[ng-show='forwardToBookingButton']"))).click();
		    
//		    Fill the name and surname for passenger 1
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-section'] //input[@type='text' and @placeholder='Resen‰r 1']"))).sendKeys(TestDataWeb.PASSENGERNAME1);
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-section'] //input[@data-ng-model='passenger.familyName' and @data-passenger-index='0']"))).sendKeys(TestDataWeb.PASSENGERSURNAME1);
		    
//		    Answer the question "Do all in the company have same surname? "
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='question clearfix'] //button[@data-ng-click='traveller.updateFamilyName($event, false)']"))).click();
//		    Date of birth passenger 1
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='≈≈≈≈-MM-DD' and @data-ng-model='passenger.dob' and @data-passenger-index='0']"))).sendKeys(TestDataWeb.DATEOFBIRTH1);
//		    Gender passenger 1
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-watch-model='passenger.gender' and @name='sex0' and @value='"+TestDataWeb.GENDER1+"']"))).click();
	    
//		    Fill the name and surname for passenger 2
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-section'] //input[@type='text' and @placeholder='Resen‰r 2' ]"))).sendKeys(TestDataWeb.PASSENGERNAME2);
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-section'] //input[@data-ng-model='passenger.familyName' and @data-passenger-index='1']"))).sendKeys(TestDataWeb.PASSENGERSURNAME2);
//		    Date of birth passenger 2
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='≈≈≈≈-MM-DD' and @data-ng-model='passenger.dob' and @data-passenger-index='1']"))).sendKeys(TestDataWeb.DATEOFBIRTH2);
//		    Gender passenger 2
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-watch-model='passenger.gender' and @name='sex1' and @value='"+TestDataWeb.GENDER2+"']"))).click();
		    
//		    Fill the info for the child
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-section'] //input[@type='text' and @placeholder='Resen‰r 3' ]"))).sendKeys(TestDataWeb.PASSENGERNAME3);
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-section'] //input[@data-ng-model='passenger.familyName' and @data-passenger-index='2']"))).sendKeys(TestDataWeb.PASSENGERSURNAME3);
//			Date of birth passenger 3
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='≈≈≈≈-MM-DD' and @data-ng-model='passenger.dob' and @data-passenger-index='2']"))).sendKeys(TestDataWeb.DATEOFBIRTH3);
//		    Gender passenger 3
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-watch-model='passenger.gender' and @name='sex2' and @value='"+TestDataWeb.GENDER3+"']"))).click();
		    
//		    Fill the address, phone, email for the booking(Passenger 1)
		    WebElement addressBookerStreet= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text' and @data-ng-model='address.booker.street']")));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addressBookerStreet);
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text' and @data-ng-model='address.booker.street']"))).sendKeys(TestDataWeb.ADDRESS1);
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='tel' and @data-ng-model='address.booker.zipCode']"))).sendKeys(TestDataWeb.POSTALNUMBER1);
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text' and @data-ng-model='address.booker.city']"))).sendKeys(TestDataWeb.POSTALADDRESS1);
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='tel' and @data-ng-model='address.booker.phone']"))).sendKeys(TestDataWeb.MOBILE1);
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email' and @data-ng-model='address.booker.email']"))).sendKeys(TestDataWeb.EMAIL1);
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email' and @data-custom-validation='confirmEmail']"))).sendKeys(TestDataWeb.EMAIL1);
		    
//		    Food onboard passenger 1
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@class='radio-inline ng-binding'] //input[@name='LIBO-0000012590' and @value='no']"))).click();
//		    Food onboard passenger 2
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@class='radio-inline ng-binding'] //input[@name='LIBO-0000012591' and @value='no']"))).click();
//		    Food onboard passenger 3
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@class='radio-inline ng-binding'] //input[@name='LIBO-0000012592' and @value='no']"))).click();
		    
//		    Book bus or private transfer(default No)
		    
//			Cancelation and rebooking protection passenger 1
		    WebElement cancelOrReebokProtection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='LIBO-TRANSFER0' and @value='false']")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cancelOrReebokProtection );
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='C-CANCELLATIONINSURANCE0' and @value='no']"))).click();
//		    Cancelation and rebooking protection passenger 1
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='C-CANCELLATIONINSURANCE1' and @value='no']"))).click();

//		    Travel insurance passenger 1
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='C-TRAVELINSURANCE0' and @value='no']"))).click();
//		    Travel insurance passenger 2
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='C-TRAVELINSURANCE1' and @value='no']"))).click();
//		    Travel insurance passenger 3
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='C-TRAVELINSURANCE2' and @value='no']"))).click();
		    
//		   	Scrool down the page to payment 
		    WebElement proceedToPay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-proceed")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", proceedToPay);
//		    Accept terms
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='checkbox'] //input[@data-ng-model='footerSummary.termsAgreed']"))).click();
//		    Check if the total price is the same as the hotel price(no additional expenses in this booking)
		    String totalPriceString = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='col-sm-6 total-price'] //span[@data-ng-bind='cartJSON.packagePrice.formattedValue']"))).getText();
		    totalPriceString = totalPriceString.replaceAll("[^0-9,]", "");
		    int totalPrice = Integer.parseInt(totalPriceString);
		    assertEquals(totalPrice,hotelPrice);
		  System.out.println(totalPriceString);
		  
//		    Proceed to payment
//		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-proceed"))).click();
		      
	  }  
	  
	  @AfterClass
		public static void tearDown() throws Exception
		{
			//driver.quit();
		}	

}
