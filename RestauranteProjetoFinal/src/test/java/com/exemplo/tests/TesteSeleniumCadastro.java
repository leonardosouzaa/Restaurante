package com.exemplo.tests;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteSeleniumCadastro {
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
  public void testCadastroProdutos() throws Exception {
    driver.get(baseUrl + "/RestauranteProjetoFinal/");
    driver.findElement(By.name("bt_cadastro")).click();
    driver.findElement(By.id("produto")).clear();
    driver.findElement(By.id("produto")).sendKeys("Produto selenium");
    driver.findElement(By.id("quantidade")).clear();
    driver.findElement(By.id("quantidade")).sendKeys("Descricao produto selenium");
    driver.findElement(By.name("preco")).clear();
    driver.findElement(By.name("preco")).sendKeys("10");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
  }
}
