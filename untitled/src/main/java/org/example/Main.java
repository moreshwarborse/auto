package org.example;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Alert;
import org.openqa.selenium.interactions.Actions;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.security.Key;

public class Main {
    public static void main(String[] args) throws Exception
    {
        //System.setProperty("web-driver.chrome.driver", "path_to_chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.get("https://demo.automationtesting.in/Register.html");

        driver.manage().window().maximize();
        //System.out.println("Page title is: " + driver.getTitle());
        //first name
        WebElement fname= driver.findElement(By.xpath("//input[@placeholder='First Name']"));
        fname.sendKeys("Mohit");
        //last name
        WebElement lname= driver.findElement(By.xpath("//input[@placeholder='Last Name']"));
        lname.sendKeys("Borse");
        //address
        WebElement address = driver.findElement(By.xpath("//textarea[@ng-model='Adress']"));
        address.sendKeys("Dhule- 424001 maharashtra");
        //email
        WebElement email = driver.findElement(By.xpath("//input[@ng-model='EmailAdress']"));
        email.sendKeys("moreshwarborse@gmail.com");
        //phone number
        WebElement phone = driver.findElement(By.xpath("//input[@type='tel']"));
        phone.sendKeys("9970xxxx56");

        //radio button
        WebElement gender_male = driver.findElement(By.xpath("//input[@value='Male']"));
        gender_male.click();

//        WebElement gender_female = driver.findElement(By.xpath("//input[@value='Female']"));
//        gender_female.click();
        //check box
        WebElement hobi = driver.findElement(By.xpath("//input[@value='Cricket']"));
        hobi.click();

        WebElement lang = driver.findElement(By.id("msdd"));
        lang.click();  // Click the dropdown to open options

        //Actions actions = new Actions(driver);
        WebElement option = driver.findElement(By.xpath("//a[contains(text(), 'Arabic')]"));
        //actions.moveToElement(option).click().perform();
        option.click();












        //drop down
        WebElement dropdown = driver.findElement(By.xpath("//select[@id='Skills']"));
        Select select= new Select(dropdown);
        select.selectByVisibleText("C");

        Actions action = new Actions(driver);
        action.scrollByAmount(0, 200).perform();

        WebElement pass = driver.findElement(By.xpath("//input[@id='firstpassword']"));
        pass.sendKeys("1234");

        WebElement cpass = driver.findElement(By.xpath("//input[@id='secondpassword']"));
        cpass.sendKeys("1234");

        /*Actions action = new Actions(driver);

        for (int i=0; i<5; i++) {
            action.scrollByAmount(0, -200).perform();
            Thread.sleep(100);
        }*/

        WebElement img= driver.findElement(By.xpath("//input[@id='imagesrc']"));
        img.sendKeys("/home/moreshwar.borse@apmosys.mahape/Pictures/Screenshots/shot.png");

        WebElement ref = driver.findElement(By.xpath("//button[@value='Refresh']"));
        ref.click();

//        Alert alert = driver.switchTo().alert();
//
//        alert.accept();
//        alert.dismiss();
//
        driver.quit();

        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Save the screenshot
        File destinationFile = new File("screenshot.png");
        FileUtils.copyFile(screenshotFile.toPath().toFile(), destinationFile.toPath().toFile());

        System.out.println("Screenshot saved successfully!");
    }
}

