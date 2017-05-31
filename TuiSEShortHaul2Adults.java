package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TuiSEShortHaul2Adults {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://www.tui.se.uat.tuinordic.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testTuiSEShortHaul2Adults() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.cssSelector("span.icon-bubble-close.remove")).click();
    driver.findElement(By.cssSelector("#countries > div.form-box.clearfix > span.icon-bubble-close.remove")).click();
    driver.findElement(By.cssSelector("#durations > div.form-box.clearfix > span.icon-bubble-close.remove")).click();
    driver.findElement(By.cssSelector("#date > div.form-box.clearfix > span.icon-bubble-close.remove")).click();
    driver.findElement(By.cssSelector("div.form-box.clearfix > span.text")).click();
    driver.findElement(By.cssSelector("li > span")).click();
    driver.findElement(By.cssSelector("#countries > div.form-box.clearfix")).click();
    driver.findElement(By.xpath("//div[@id='destinations']/div/div[3]/ul[2]/li[9]/span/i")).click();
    driver.findElement(By.xpath("(//input[@type='checkbox'])[258]")).click();
    driver.findElement(By.xpath("(//input[@type='checkbox'])[258]")).click();
    driver.findElement(By.xpath("//div[@id='destinations']/div/div[3]/ul[2]/li[9]/ul/li[10]/span")).click();
    driver.findElement(By.xpath("//div[@id='destinations']/div/div[4]/span")).click();
    driver.findElement(By.cssSelector("#durations > div.form-box.clearfix")).click();
    driver.findElement(By.xpath("//div[@id='duration-lengths']/div/div[3]/ul[2]/li/span")).click();
    driver.findElement(By.cssSelector("#date > div.form-box.clearfix > span.text")).click();
    driver.findElement(By.linkText("13")).click();
    driver.findElement(By.cssSelector("#paxes > div.form-box.clearfix > span.text")).click();
    driver.findElement(By.cssSelector("#paxes > div.form-box.clearfix > span.text")).click();
    driver.findElement(By.id("search-btn")).click();
    driver.findElement(By.xpath("//button[@type='button']")).click();
    driver.findElement(By.xpath("//button[@type='button']")).click();
    driver.findElement(By.xpath("//button[@type='button']")).click();
    driver.findElement(By.xpath("//button[@type='button']")).click();
    driver.findElement(By.xpath("//button[@type='button']")).click();
    driver.findElement(By.xpath("//button[@type='button']")).click();
    driver.findElement(By.xpath("//button[@type='button']")).click();
    driver.findElement(By.xpath("//button[@type='button']")).click();
    driver.findElement(By.xpath("//button[@type='button']")).click();
    driver.findElement(By.xpath("//button[@type='button']")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
