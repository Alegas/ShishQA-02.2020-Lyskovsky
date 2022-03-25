import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class P3{
    private WebDriver driver;
    private String baseUrl;



    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "https://sandbox.cardpay.com/MI/cardpayment2.html?orderXml=PE9SREVSIFdBTExFVF9JRD0nODI5OScgT1JERVJfTlVNQkVSPSc0NTgyMTEnIEFNT1VOVD0nMjkxLjg2JyBDVVJSRU5DWT0nRVVSJyAgRU1BSUw9J2N1c3RvbWVyQGV4YW1wbGUuY29tJz4KPEFERFJFU1MgQ09VTlRSWT0nVVNBJyBTVEFURT0nTlknIFpJUD0nMTAwMDEnIENJVFk9J05ZJyBTVFJFRVQ9JzY3NyBTVFJFRVQnIFBIT05FPSc4NzY5OTA5MCcgVFlQRT0nQklMTElORycvPgo8L09SREVSPg==&sha512=998150a2b27484b776a1628bfe7505a9cb430f276dfa35b14315c1c8f03381a90490f6608f0dcff789273e05926cd782e1bb941418a9673f43c47595aa7b8b0d";
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        System.out.println("Start Test");

        }
    private static final String[][] Cards = {
            {"4000 0000 0000 0002", "Success"},
            {"5555 5555 5555 4444", "Decline"},
            {"4000000000000044", "Info"},
            {"4000000000000036", "Success"},
            {"4000000000000093", "Success"},
            {"4000000000000069", "Success"},
            {"4000000000000085", "Success"},
            {"4000000000000077", "Success"},
            {"5555555555554477", "Decline"},
            {"4000000000000051", "Info"}


    };


    @Test
    public void CardTest1() throws Exception {
        CardTestForm1(1);
    }

    @Test
    public void CardTest2() throws Exception {
        CardTestForm1(2);
    }

    @Test
    public void CardTest3() throws Exception {
        CardTestForm1(3);
    }

    @Test
    public void CardTest4() throws Exception {
        CardTestForm2(4);
    }

    @Test
    public void CardTest5() throws Exception {
        CardTestForm2(5);
    }

    @Test
    public void CardTest6() throws Exception {
        CardTestForm2(6);
    }

    @Test
    public void CardTest7() throws Exception {
        CardTestForm2(7);
    }

    @Test
    public void CardTest8() throws Exception {
        CardTestForm2(8);
    }

    @Test
    public void CardTest9() throws Exception {
        CardTestForm2(9);
    }

    @Test
    public void CardTest10() throws Exception {
        CardTestForm2(10);
    }



    private void CardTestForm1(int n){
        driver.get(baseUrl);
        FillCard(Cards[n-1][0]);
        FillForm();
        driver.findElement(By.id("action-submit")).click();
        driver.findElement(By.id("success")).click();
        assertEquals(Cards[n-1][1], driver.findElement(By.xpath("//div[@id='payment-status-title']/span")).getText());
        assertEquals("Payment Details:", driver.findElement(By.xpath("//section[@id='payment-details']/h2")).getText());
    }
    private void CardTestForm2(int n){
        driver.get(baseUrl);
        FillCard(Cards[n-1][0]);
        FillForm();
        driver.findElement(By.id("action-submit")).click();
        assertEquals(Cards[n-1][1], driver.findElement(By.xpath("//div[@id='payment-status-title']/span")).getText());
        assertEquals("Payment Details:", driver.findElement(By.xpath("//section[@id='payment-details']/h2")).getText());
    }

    private void FillCard(String Number) {
        WebElement cardNumber = driver.findElement(By.id("input-card-number"));
        cardNumber.click();
        cardNumber.clear();
        cardNumber.sendKeys(Number);
    }
    private void FillForm() {
        WebElement cardHolder = driver.findElement(By.id("input-card-holder"));
        WebElement monthSelect = driver.findElement(By.id("card-expires-month"));
        WebElement yearSelect = driver.findElement(By.id("card-expires-year"));
        WebElement cardCvc = driver.findElement(By.id("input-card-cvc"));

        cardHolder.click();
        cardHolder.clear();
        cardHolder.sendKeys("Oleg");

        monthSelect.click();
        new Select(monthSelect).selectByVisibleText("04");
        yearSelect.click();
        new Select(yearSelect).selectByVisibleText("2037");

        cardCvc.click();
        cardCvc.clear();
        cardCvc.sendKeys("123");
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        System.out.println("End Test");
    }
}
