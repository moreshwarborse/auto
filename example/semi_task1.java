package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class semi_task1 {

    public static void main(String[] args) throws InterruptedException {
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();

        webDriver.get("https://www.moneycontrol.com/markets/indian-indices/");

        WebElement cancel= webDriver.findElement(By.xpath("//button[@id='wzrk-cancel']"));
        cancel.click();

        TreeMap<String, Double> uu=new TreeMap<>();

        WebElement table = webDriver.findElement(By.xpath("//table[@id='indicesTable']"));
        List<WebElement> rows = table.findElements(By.xpath(".//tbody[@aria-live='polite']//tr"));

        double maxPrice=0;

        for (WebElement row : rows) {
            // Get the company name from <a> tag inside the row
            WebElement nameElement = row.findElement(By.xpath(".//a[@target='_blank']"));
            String companyName = nameElement.getText().trim();

            // Get the price (LTP) from <td> with id containing 'ltp' inside the row
            WebElement priceElement = row.findElement(By.xpath(".//td[contains(@id,'ltp')]"));
            String price = priceElement.getText().replace(",","").trim();

            System.out.print(companyName + " " + price + " | ");

            double intPrise=Double.parseDouble(price);
            if(intPrise > maxPrice){
                maxPrice=intPrise;
            }
            System.out.println();
        }
        System.out.println(maxPrice);

        webDriver.quit();
    }
}
