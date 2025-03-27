package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class e_commerce {
    public static void  main(String[] args) throws InterruptedException, IOException {

        WebDriver driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.in/?&tag=googhydrabk1-21&ref=pd_sl_5szpgfto9i_e&adgrpid=155259813593&hvpone=&hvptwo=&hvadid=674893540034&hvpos=&hvnetw=g&hvrand=14990987437967305109&hvqmt=e&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=9301354&hvtargid=kwd-64107830&hydadcr=14452_2316413&gad_source=1");

        WebElement search= driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        search.sendKeys("mobile phones under 20000 ");
        search.submit();

        WebElement add1= driver.findElement(By.xpath("//button[@id='a-autoid-1-announce']"));
        add1.click();
        WebElement add2= driver.findElement(By.xpath("//button[@id='a-autoid-2-announce']"));
        add2.click();
        WebElement add3= driver.findElement(By.xpath("//button[@id='a-autoid-3-announce']"));
        add3.click();
        WebElement add4= driver.findElement(By.xpath("//button[@id='a-autoid-4-announce']"));
        add4.click();

        WebDriverWait cart0 = new WebDriverWait(driver, Duration.ofSeconds(10));
        Thread.sleep(1000);
        WebElement cart= cart0.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='nav-cart']")));
        cart.click();

        //        Thread.sleep(2000);

        ArrayList<Integer> numbers = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            String productPriceText = driver.findElement(By.xpath("//div[@data-csa-c-posx='" + i + "']//span[@data-a-size='b']//span[@aria-hidden='true']")).getText();
            System.out.println("Product Price (String): " + productPriceText);

            // Removing non-numeric characters and decimal values
            String cleanedPrice = productPriceText.replaceAll("[^0-9.]", ""); // Remove currency symbols
            int productPrice = (int) Double.parseDouble(cleanedPrice); // Convert to double then cast to int

            numbers.add(productPrice);
        }
        System.out.println("Prices List: " + numbers);

        int max = Collections.max(numbers);
        System.out.println("Maximum value: " + max);

        int maxindex = numbers.indexOf(max);
        System.out.println(maxindex);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

//        Thread.sleep(2000);

        for (int i = 1; i <= 4; i++) {
                    if(maxindex == (i-1)) {
                        continue;
                    }
            WebElement remove = driver.findElement(By.xpath("//div[@data-csa-c-posx='" + i + "']//button[@tabindex='-1']"));
            remove.click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            Thread.sleep(1000);
            }

        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Save the screenshot
        File destinationFile = new File("screenshot.png");
        Files.copy(screenshotFile.toPath(), destinationFile.toPath());

        System.out.println("Screenshot saved successfully!");


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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

//        Thread.sleep(3000);

        driver.quit();

    }
}
