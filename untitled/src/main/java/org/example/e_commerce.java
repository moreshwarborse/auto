package org.example;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;

public class e_commerce {
    public static void  main(String[] args) throws InterruptedException, IOException {

        WebDriver driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.in/?&tag=googhydrabk1-21&ref=pd_sl_5szpgfto9i_e&adgrpid=155259813593&hvpone=&hvptwo=&hvadid=674893540034&hvpos=&hvnetw=g&hvrand=14990987437967305109&hvqmt=e&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=9301354&hvtargid=kwd-64107830&hydadcr=14452_2316413&gad_source=1");

        //(1).....Product Search........
        WebElement search= driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        search.sendKeys("laptop");
        search.submit();

        //(2).....Add to cart........
        for(int i=1;i<=4;i++){
        WebElement add1= driver.findElement(By.xpath("//button[@id='a-autoid-"+i+"-announce']"));
        add1.click();}

        //(3).....Click in cart........
        Thread.sleep(3000);
        WebElement cart= driver.findElement(By.xpath("//a[@id='nav-cart']"));
        cart.click();

        //(4).....Get the Price of Items and Compare........
        ArrayList<Integer> numbers = new ArrayList<>();

        for (int i = 1; i <= 4; i++)
        {
            //Extract value
            String productPriceText = driver.findElement(By.xpath("//div[@data-csa-c-posx='" + i + "']//span[@data-a-size='b']//span[@aria-hidden='true']")).getText();
            System.out.println("Product Price (String): " + productPriceText);

            // Removing non-numeric characters and decimal values
            String cleanedPrice = productPriceText.replaceAll("[^0-9.]", "");
            int productPrice = (int) Double.parseDouble(cleanedPrice);

            numbers.add(productPrice);
        }

        System.out.println("Prices List: " + numbers);

        int max = Collections.max(numbers);
        System.out.println("Maximum value: " + max);
        int maxindex = numbers.indexOf(max);

        //(5).....Remove items from cart, accept high price items........
        for (int i = 1; i <= 4; i++)
        {
            if(maxindex == (i-1))
            {
                continue;
            }
            WebElement remove = driver.findElement(By.xpath("//div[@data-csa-c-posx='" + i + "']//button[@tabindex='-1']"));
            remove.click();

            Thread.sleep(1000);
        }


        //(6).....Take screenshot for Validation........
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // Save the screenshot
        File destinationFile = new File("screenshot.png");
        FileUtils.copyFile(screenshotFile.toPath().toFile(), destinationFile.toPath().toFile());
        System.out.println("Screenshot saved successfully!");
        Thread.sleep(1000);

        //(7).....out-of-stock alerts........
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        WebElement inStockMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'In stock')]")));

        String stock_Status =inStockMessage.getText();
        if(stock_Status.equals("In stock"))
        {
            System.out.println("The Product is in Stock");
        }
        else{
            System.out.println("The Product is out of Stock");
            driver.close();
        }

        driver.quit();
    }
}