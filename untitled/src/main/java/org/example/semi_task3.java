package org.example;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.IOException;

public class semi_task3 {
    public static void main (String[] args) throws IOException, InterruptedException {

        WebDriver driver= new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://the-internet.herokuapp.com/upload");

        WebElement select= driver.findElement(By.xpath("//input[@id='file-upload']"));
        select.sendKeys("/home/moreshwar.borse@apmosys.mahape/Desktop/Auto/untitled/screenshot.png");

        WebElement upload= driver.findElement(By.xpath("//input[@id='file-submit']"));
        upload.click();

        WebElement check=driver.findElement(By.xpath("//div[@id='uploaded-files']"));
        if(check.getText().equals("screenshot.png")){
            System.out.println("Uploaded Successfully: "+ check.getText());
        }
        else {
            System.out.println("Error in file structure");
        }
        TakesScreenshot ts = (TakesScreenshot) driver;
        File uploadShot = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(uploadShot, new File("upload_success.png"));

        Thread.sleep(2000);

        driver.get("https://the-internet.herokuapp.com/download");

        Thread.sleep(2000);

        WebElement download= driver.findElement(By.xpath("//a[contains(@href, 'selenide')]"));
        download.click();


        Thread.sleep(2000);
        String path="/home/moreshwar.borse@apmosys.mahape/Downloads";
        String name=download.getText();
        System.out.println(name);
        File file= new File(path+"/"+name);

        int time=20;

        while(time > 0 && file.exists()){
                time --;
        }

        if(file.exists() && file.length() > 0){
            System.out.println("File is fully specify requirement");
        }
        else {
            System.out.println("File not specify the as requirement");
        }

        Thread.sleep(2000);
        File downlaodFile=ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(downlaodFile, new File("DownloadFile.png"));
        System.out.println("Download Successfully: Screen Shot");

        driver.quit();

    }


}
