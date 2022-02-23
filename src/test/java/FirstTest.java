import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class FirstTest {
    private WebDriver driver;
    private String baseUrl;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "https://sandbox.cardpay.com/MI/cardpayment2.html?orderXml=PE9SREVSIFdBTExFVF9JRD0nODI5OScgT1JERVJfTlVNQkVSPSc0NTgyMTEnIEFNT1VOVD0nMjkxLjg2JyBDVVJSRU5DWT0nRVVSJyAgRU1BSUw9J2N1c3RvbWVyQGV4YW1wbGUuY29tJz4KPEFERFJFU1MgQ09VTlRSWT0nVVNBJyBTVEFURT0nTlknIFpJUD0nMTAwMDEnIENJVFk9J05ZJyBTVFJFRVQ9JzY3NyBTVFJFRVQnIFBIT05FPSc4NzY5OTA5MCcgVFlQRT0nQklMTElORycvPgo8L09SREVSPg==&sha512=998150a2b27484b776a1628bfe7505a9cb430f276dfa35b14315c1c8f03381a90490f6608f0dcff789273e05926cd782e1bb941418a9673f43c47595aa7b8b0d";
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);



    }

    @Test
    public void testPervy() throws Exception {
        driver.get(baseUrl);
        WebElement cardNumber = driver.findElement(By.id("input-card-number"));
        cardNumber.click();
        cardNumber.clear();
        cardNumber.sendKeys("4000 0000 0000 0002");

        WebElement cardHolder = driver.findElement(By.id("input-card-holder"));
        cardHolder.click();
        cardHolder.clear();
        cardHolder.sendKeys("Oleg");

        driver.findElement(By.id("card-expires-month")).click();
        new Select(driver.findElement(By.id("card-expires-month"))).selectByVisibleText("04");
        driver.findElement(By.id("card-expires-year")).click();
        new Select(driver.findElement(By.id("card-expires-year"))).selectByVisibleText("2037");

        WebElement cardCvc = driver.findElement(By.id("input-card-cvc"));
        cardCvc.click();
        cardCvc.clear();
        cardCvc.sendKeys("123");

        driver.findElement(By.id("action-submit")).click();
        driver.findElement(By.id("success")).click();
        assertEquals("Success", driver.findElement(By.xpath("//div[@id='payment-status-title']/span")).getText());
        assertEquals("Payment Details:", driver.findElement(By.xpath("//section[@id='payment-details']/h2")).getText());
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}