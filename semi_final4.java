package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class semi_final4 {

    public static void main(String[] arg) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://vinothqaacademy.com/iframe/");
        driver.manage().window().maximize();
        Thread.sleep(1000);
//        Actions actions = new Actions(driver);
//
//        for(int i = 0; i <= 5; ++i) {
//            actions.scrollByAmount(0, 100).perform();
//        }
        driver.switchTo().frame("registeruser");
        WebElement firstname = driver.findElement(By.xpath("//input[@name='vfb-5']"));
        firstname.sendKeys("Mohit");
        WebElement lastname = driver.findElement(By.xpath("//input[@name='vfb-7']"));
        lastname.sendKeys("Arora");

        Thread.sleep(2000);
        WebElement gender = driver.findElement(By.xpath(".//input[@value='Male']"));
        gender.click();
        WebElement course1 = driver.findElement(By.xpath("//input[@value='Selenium WebDriver']"));
        course1.click();
        WebElement address = driver.findElement(By.xpath("//input[@name='vfb-13[address]']"));
        address.sendKeys("1st, Home");
        WebElement city = driver.findElement(By.xpath("//input[@name='vfb-13[zip]']"));
        city.sendKeys("Dhule");

        Thread.sleep(2000);
        WebElement country = driver.findElement(By.xpath("//span[@role='presentation']"));
        country.click();
        Select select = new Select(country);
        select.selectByContainsVisibleText("Afghanistan");
        WebElement Email = driver.findElement(By.xpath("//input[@type='email']"));
        Email.sendKeys("mohit@gmail.com");
        WebElement Date = driver.findElement(By.xpath("//input[@name='vfb-18']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='2003-09-25';", Date);
        WebElement Number = driver.findElement(By.xpath(""));
        WebElement verify = driver.findElement(By.xpath(""));
    
    }
}
