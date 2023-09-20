import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class MercuryToursTestCases {
    private WebDriver driver;


    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void goToRegisterPage() {
        String registerUrl = "https://demo.guru99.com/test/newtours/register.php";
        driver.get("https://demo.guru99.com/test/newtours/");
        driver.findElement(By.linkText("REGISTER")).click();
        if (driver.getCurrentUrl().equals(registerUrl)) {
            System.out.println("Caso de prueba 1: Navegación exitosa.");
        } else {
            System.out.println("Caso de prueba 1: Navegación fallida.");
        }
        assertEquals( registerUrl, driver.getCurrentUrl());
    }

    @Test
    public void loginWithInvalidPassword() {
        driver.get("https://demo.guru99.com/test/newtours/");
        driver.findElement(By.name("userName")).sendKeys("validuser");
        driver.findElement(By.name("password")).sendKeys("1");
        driver.findElement(By.name("submit")).click();
        assertTrue(driver.findElement(By.xpath("//span[contains(text(), 'Enter your userName and password correct')]")).isDisplayed());
    }

    @Test
    public void selectCountryByIndex() {
        driver.get("https://demo.guru99.com/test/newtours/register.php");
        Select countryDropdown = new Select(driver.findElement(By.name("country")));
        countryDropdown.selectByIndex(5); // Seleccionar el sexto país en la lista
        String selectedCountry = countryDropdown.getFirstSelectedOption().getText();
        if (selectedCountry.equals("ANGUILLA")) {
            System.out.println("Caso de prueba 3: Selección de país por índice exitosa.");
        } else {
            System.out.println("Caso de prueba 3: Selección de país por índice fallida.");
        }
        assertTrue(selectedCountry.equals("ANGUILLA"));
    }

    @Test
    public void registerNewUser() {
        driver.get("https://demo.guru99.com/test/newtours/register.php");
        driver.findElement(By.name("firstName")).sendKeys("John");
        driver.findElement(By.name("lastName")).sendKeys("Doe");
        driver.findElement(By.name("phone")).sendKeys("1234567890");
        driver.findElement(By.name("userName")).sendKeys("john.doe@example.com");
        driver.findElement(By.name("address1")).sendKeys("123 Main St");
        driver.findElement(By.name("city")).sendKeys("New York");
        driver.findElement(By.name("state")).sendKeys("NY");
        driver.findElement(By.name("postalCode")).sendKeys("10001");

        WebElement countryDropdown = driver.findElement(By.name("country"));
        countryDropdown.sendKeys("UNITED STATES");

        driver.findElement(By.name("email")).sendKeys("testuser");
        driver.findElement(By.name("password")).sendKeys("testpass");
        driver.findElement(By.name("confirmPassword")).sendKeys("testpass");
        driver.findElement(By.name("submit")).click();
        WebElement successMessage = driver.findElement(By.xpath("//font[contains(text(),'Thank you for registering')]"));
        assertTrue(successMessage.isDisplayed());
        assertEquals("https://demo.guru99.com/test/newtours/register_sucess.php", driver.getCurrentUrl());
    }

    @Test
    public void makeReservation(){
        driver.get("https://demo.guru99.com/test/newtours/reservation.php");
        driver.findElement(By.xpath("//input[contains(@value, 'oneway')]")).click();
        driver.findElement(By.name("passCount")).sendKeys("2");
        driver.findElement(By.name("fromPort")).sendKeys("Paris");
        driver.findElement(By.name("fromMonth")).sendKeys("April");
        driver.findElement(By.name("fromDay")).sendKeys("5");
        driver.findElement(By.name("toPort")).sendKeys("London");
        driver.findElement(By.name("toMonth")).sendKeys("April");
        driver.findElement(By.name("toDay")).sendKeys("10");
        driver.findElement(By.xpath("//input[contains(@value,'Business')]")).click();
        driver.findElement(By.name("airline")).sendKeys("Blue Skies Airlines");
        driver.findElement(By.name("findFlights")).click();
        assertEquals("https://demo.guru99.com/test/newtours/reservation2.php",driver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}