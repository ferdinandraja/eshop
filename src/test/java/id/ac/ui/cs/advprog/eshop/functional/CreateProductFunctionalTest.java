package id.ac.ui.cs.advprog.eshop.functional;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.openqa.selenium.WebElement;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    private String ProductListUrl;

    @BeforeEach
    void setupTest(){
        baseUrl = String.format("%s:%d/product/create", testBaseUrl, serverPort);
        ProductListUrl = String.format("%s:%d/product/list", testBaseUrl, serverPort);
    }
    @Test
    void CreateProductTest(ChromeDriver driver) throws Exception{
        String TempItemName = "Aqmal Andityo Arham";
        int TempItemQuantity = 69;

        driver.get(baseUrl);

        WebElement inputProductName = driver.findElement(By.id("nameInput"));
        inputProductName.clear();
        inputProductName.sendKeys(TempItemName);

        WebElement inputProductQuantity = driver.findElement(By.id("quantityInput"));
        inputProductQuantity.clear();
        inputProductQuantity.sendKeys(String.valueOf(TempItemQuantity));

        WebElement button = driver.findElement(By.id("SubmitButton"));
        button.click();

        assertEquals(ProductListUrl, driver.getCurrentUrl());

        WebElement ResultName = driver.findElement(By.xpath("//td[text()='"+ TempItemName +"']"));
        WebElement ResultQuantity = driver.findElement(By.xpath("//td[text()='"+ TempItemQuantity +"']"));

        assertEquals(TempItemName, ResultName.getText());
        assertEquals(ResultQuantity.getText(), String.valueOf(TempItemQuantity) );
    }
}
