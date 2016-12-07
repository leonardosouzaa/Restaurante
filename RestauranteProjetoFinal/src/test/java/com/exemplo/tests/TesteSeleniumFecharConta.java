package com.exemplo.tests;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteSeleniumFecharConta{
  private WebDriver driver;
  private String baseUrl;

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  @Ignore
  public void testeFecharConta() throws Exception {
    driver.get(baseUrl + "/RestauranteProjetoFinal/");
    driver.findElement(By.name("bt_compra")).click();
    new Select(driver.findElement(By.name("produto"))).selectByVisibleText("Produto selenium");
    driver.findElement(By.id("quantidade")).clear();
    driver.findElement(By.id("quantidade")).sendKeys("2");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    driver.findElement(By.id("quantidade")).clear();
    driver.findElement(By.id("quantidade")).sendKeys("4");
    new Select(driver.findElement(By.name("produto"))).selectByVisibleText("2");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    new Select(driver.findElement(By.name("produto"))).selectByVisibleText("cadastrado");
    driver.findElement(By.id("quantidade")).clear();
    driver.findElement(By.id("quantidade")).sendKeys("6");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    driver.findElement(By.id("mesa")).clear();
    driver.findElement(By.id("mesa")).sendKeys("10");
    driver.findElement(By.cssSelector("form[name=\"finalizar\"] > input[type=\"submit\"]")).click();
  }
}
