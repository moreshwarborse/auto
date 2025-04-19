package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import javax.swing.*;
import java.util.List;
//import org.openqa.selenium.support.ui.Select;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        System.out.println("Hello, World!");
        WebDriver driver = new ChromeDriver();
        driver.get("https://demo.automationtesting.in/Register.html");
        //first name
        WebElement fname = driver.findElement(By.xpath("//input[@placeholder='First Name']"));
        fname.sendKeys("Mohit");
        //last name
        WebElement lname = driver.findElement(By.xpath("//input[@placeholder='Last Name']"));
        lname.sendKeys("Borse");
        //address
        WebElement address = driver.findElement(By.xpath("//textarea[@rows='3']"));
        address.sendKeys("Dhule Maharashtra");
        //email
        WebElement email = driver.findElement(By.xpath("//input[@type='email']"));
        email.sendKeys("moreshwarborse@gmail.com");
        //phone
        WebElement phone = driver.findElement(By.xpath("//input[@type='tel']"));
        phone.sendKeys("9970xxxx56");
        //radio button
        WebElement gender = driver.findElement(By.xpath("//input[@value='Male']"));
        //gender.sendKeys("moreshwarborse@gmail.com");
        gender.click();
        //check box
        WebElement hobi= driver.findElement(By.xpath("//input[@value='Cricket']"));
        //gender.sendKeys("moreshwarborse@gmail.com");
        hobi.click();

        //dropdown (language)
        //WebElement lang = driver.findElement(By.xpath("//select[@id='msdd']"));
        WebElement skill = driver.findElement(By.xpath("//select[@id='Skills']"));
        Select select = new Select(skill);
        select.selectByVisibleText("Java");
        WebElement cld = driver.findElement(By.xpath("//select[@ng-model='yearbox']"));
        Select date = new Select(cld);
        date.selectByVisibleText("1916");

        WebElement pass = driver.findElement(By.xpath("//input[@id='firstpassword']"));
        pass.sendKeys("9970xxxx56");
        WebElement cpass = driver.findElement(By.xpath("//input[@id='secondpassword']"));
        cpass.sendKeys("9970xxxx56");

        WebElement ref = driver.findElement(By.xpath("//button[@id='submitbtn']"));
        ref.submit();

        Thread.sleep(2000);
        driver.close();
    }
}