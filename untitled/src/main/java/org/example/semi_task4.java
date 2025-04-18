package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class semi_task4 {
    public static void main(String[] arg)throws InterruptedException{
        WebDriver driver= new ChromeDriver();
        driver.get("https://vinothqaacademy.com/iframe/");
        driver.manage().window().maximize();

        Thread.sleep(1000);

        Actions actions= new Actions(driver);
        for(int i=0; i<=5;i++){
        actions.scrollByAmount(0,100).perform();}

        WebElement firstname= driver.findElement(By.xpath("//input[@type='text']"));
        firstname.sendKeys("Mohit");
        WebElement lastname= driver.findElement(By.xpath(""));
        WebElement gender= driver.findElement(By.xpath(""));
        WebElement course1= driver.findElement(By.xpath(""));
        WebElement Address= driver.findElement(By.xpath(""));
        WebElement city= driver.findElement(By.xpath(""));
        WebElement country= driver.findElement(By.xpath(""));
        WebElement Email= driver.findElement(By.xpath(""));
        WebElement Date= driver.findElement(By.xpath(""));
        WebElement Number= driver.findElement(By.xpath(""));
        WebElement verify= driver.findElement(By.xpath(""));
    }

}
