package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class e_com{
    public static void main(String[] args) {
//        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver"); // Update with your chromedriver path
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://www.amazon.in/?&tag=googhydrabk1-21&ref=pd_sl_5szpgfto9i_e&adgrpid=155259813593&hvpone=&hvptwo=&hvadid=674893540034&hvpos=&hvnetw=g&hvrand=4971954754473181455&hvqmt=e&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=9301354&hvtargid=kwd-64107830&hydadcr=14452_2316413&gad_source=1"); // Replace with actual website

            // ✅ (1) Search for a Product
            WebElement searchBox = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
            searchBox.sendKeys("Laptop");
            searchBox.submit();

            // ✅ (2) Compare Products (Based on Price & Ratings)
            List<WebElement> products = driver.findElements(By.xpath("//div[@class='product-item']"));
            WebElement bestProduct = null;
            double lowestPrice = Double.MAX_VALUE;

            for (WebElement product : products) {
                String priceText = product.findElement(By.xpath(".//span[@class='price']")).getText().replace("$", "");
                double price = Double.parseDouble(priceText);

                String ratingText = product.findElement(By.xpath(".//span[@class='rating']")).getText();
                double rating = Double.parseDouble(ratingText);

                if (price < lowestPrice && rating >= 4.0) {  // Select best value product
                    lowestPrice = price;
                    bestProduct = product;
                }
            }


            Thread.sleep(5000);

            // ✅ (3) Add Selected Product to Cart (Handle Out-of-Stock Alerts)
            if (bestProduct != null) {
                bestProduct.findElement(By.xpath(".//button[contains(text(), 'Add to Cart')]")).click();
                try {
                    WebElement outOfStockAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Out of Stock')]")));
                    System.out.println("Selected product is out of stock!");
                } catch (Exception e) {
                    System.out.println("Product added to cart successfully.");
                }
            }

            // ✅ (4) Validate Cart Contents
            driver.findElement(By.xpath("//a[@id='cart']")).click();  // Go to cart
            WebElement cartItem = driver.findElement(By.xpath("//div[@class='cart-item']//span[@class='product-name']"));
            if (cartItem.isDisplayed()) {
                System.out.println("Cart validation successful: " + cartItem.getText());
            } else {
                System.out.println("Cart validation failed!");
            }

            // ✅ (5) Remove Items from Cart & Confirm Update
            driver.findElement(By.xpath("//button[contains(text(), 'Remove')]")).click();
            WebElement emptyCartMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(), 'Your cart is empty')]")));

            if (emptyCartMessage.isDisplayed()) {
                System.out.println("Cart updated successfully after item removal.");
            }

        } catch (Exception e) {
            System.out.println("Test case failed: " + e.getMessage());
        } finally {
            driver.quit(); // Close browser
        }
    }
}

