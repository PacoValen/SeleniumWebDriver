package coffeetech;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.time.Duration;

public class LoginTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Configuración de WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Navega a la página de inicio de sesión
        driver.get("https://opensource-demo.orangehrmlive.com/");

        // Verifica que la página se haya cargado correctamente
        System.out.println("Título de la página: " + driver.getTitle());
        System.out.println("URL de la página: " + driver.getCurrentUrl());
    }

    @Test
    public void testLogin() {
        // Localizar los elementos de usuario, contraseña y botón de inicio de sesión
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".oxd-button.oxd-button--main.orangehrm-login-button")));

        // Ingresa las credenciales y hace clic en el botón de login
        usernameField.sendKeys("Admin");
        passwordField.sendKeys("admin123");
        loginButton.click();

        // Verifica si el login fue exitoso comprobando la presencia del texto "Dashboard"
        WebElement dashboardHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".oxd-topbar-header-breadcrumb-module")));
        Assert.assertTrue(dashboardHeader.isDisplayed(), "Inicio de sesión fallido");
        System.out.println("Inicio de sesión exitoso en OrangeHRM");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
