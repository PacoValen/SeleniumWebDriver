package coffeetech;

// Importaciones necesarias para trabajar con Selenium WebDriver y TestNG
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CoffeeTechTest {

    // Declaración de una variable 'driver' de tipo WebDriver
    // 'driver' será utilizada para controlar el navegador
    private WebDriver driver;

    // Configuración inicial que se ejecuta antes de cada método de prueba
    @BeforeMethod
    public void setUp() {
        // Configura WebDriverManager para manejar automáticamente el driver de Chrome
        WebDriverManager.chromedriver().setup();

        // Inicializa 'driver' con una nueva instancia de ChromeDriver
        driver = new ChromeDriver();

        // Maximiza la ventana del navegador para una mejor visualización
        driver.manage().window().maximize();
    }

    // Método de prueba que navega a la página de CoffeeTech
    @Test
    public void navigateToCoffeeTechPage() throws InterruptedException {
        // Navega a la URL de la página de CoffeeTech
        driver.get("https://coffeetech.netlify.app");

        // Pausa de 5 segundos para permitir que la página cargue completamente
        // y para ver visualmente el resultado de la navegación
        Thread.sleep(5000);

        // Obtiene el título de la página actual y lo imprime en la consola
        // Esto permite verificar que se haya navegado correctamente a la página
        String title = driver.getTitle();
        System.out.println("El título de la página es: " + title);
    }

    // Finalización de la prueba, que se ejecuta después de cada método de prueba
    @AfterMethod
    public void tearDown() {
        // Verifica que 'driver' no sea nulo y cierra el navegador si está abierto
        if (driver != null) {
            driver.quit();
        }
    }
}
