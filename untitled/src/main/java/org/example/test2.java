package org.example;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class test2 {

    public static void main(String[] args) throws InterruptedException, IOException {
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();

        webDriver.get("https://www.angelone.in/stocks");

        Thread.sleep(1000);

        WebElement cancel= webDriver.findElement(By.xpath("//button[@id='wzrk-confirm']"));
        cancel.click();

        TreeMap<String, Double> uu=new TreeMap<>();

        WebElement table = webDriver.findElement(By.xpath("//table[@class='MuiTable-root css-1owb465']"));
        List<WebElement> rows = table.findElements(By.xpath(".//tbody[@class='MuiTableBody-root css-1xnox0e']//tr"));

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0, 500)");

        Thread.sleep(1000);
        JavascriptExecutor mm = (JavascriptExecutor) webDriver;
        for (WebElement row : rows) {
            // Get the company name from <a> tag inside the row
            WebElement nameElement = row.findElement(By.xpath(".//div[@class='A0jEHm']"));
            String companyName = nameElement.getText().trim();

            // Get the price (LTP) from <td> with id containing 'ltp' inside the row
            WebElement priceElement = row.findElement(By.xpath(". //div[@class='_cJd_o']"));
            String price = priceElement.getText().replace("₹","").trim();

            String[] lines = price.split("\n");
            String finalPrice = lines.length > 1 ? lines[0].trim() : "";

            double intPrise=Double.parseDouble(finalPrice);

            uu.put(companyName,intPrise);

            System.out.print(companyName + " " + intPrise + " | ");

            if (intPrise > 2000) {
                js.executeScript("arguments[0].style.border='3px solid red'", row);
            }
        }
        JavascriptExecutor ss = (JavascriptExecutor) webDriver;
        ss.executeScript("window.scrollBy(0, 400)");
        Thread.sleep(3000);
        System.out.println();
        System.out.println(uu);
        double bb =Collections.max(uu.values());
        System.out.println("Max Stock Value: "+bb);


        for(Map.Entry<String,Double> entry: uu.entrySet()){
            if (entry.getValue().equals(bb)){
                System.out.println("Stock Name: "+entry.getKey());
                WebElement clickOnStock=webDriver.findElement(By.xpath("//a[text()='"+entry.getKey()+"']"));
                clickOnStock.click();
            }
        }

        Thread.sleep(2000);

        File screenshotFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File destinationFile = new File("screenshot1_"+timestamp+".png");
        FileUtils.copyFile(screenshotFile.toPath().toFile(), destinationFile.toPath().toFile());
        System.out.println("Screenshot saved successfully!");
        Thread.sleep(1000);

        webDriver.quit();
    }
}
