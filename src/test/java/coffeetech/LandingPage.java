package coffeetech;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

public class LandingPage {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        
        
        driver = new ChromeDriver();
        
        // Establece la resolución de pantalla para que el botón "Descargar APK" sea visible
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(1024, 768));
        //driver.manage().window().maximize();
        
        // Navega a la página principal
        driver.get("https://coffeetech.netlify.app/");
    }

    @Test
    public void testLandingPageFunctionality() throws InterruptedException {
        // Seleccionar todos los botones del header usando XPath
        WebElement nosotrosButton = driver.findElement(By.xpath("//*[@id='root']/nav/div/div/div[2]/a[1]"));
        WebElement etapasButton = driver.findElement(By.xpath("//*[@id='root']/nav/div/div/div[2]/a[2]"));
        WebElement solucionesButton = driver.findElement(By.xpath("//*[@id='root']/nav/div/div/div[2]/a[3]"));
        WebElement documentacionButton = driver.findElement(By.xpath("//*[@id='root']/nav/div/div/div[2]/a[4]"));
        
        // Verificar que los botones están presentes
        
        Assert.assertTrue(nosotrosButton.isDisplayed(), "Botón 'Nosotros' no se encuentra");
        System.out.println("Botón 'Nosotros' presente");
        Assert.assertTrue(etapasButton.isDisplayed(), "Botón 'Etapas' no se encuentra");
        System.out.println("Botón 'Etapas' presente");
        Assert.assertTrue(solucionesButton.isDisplayed(), "Botón 'Soluciones' no se encuentra");
        System.out.println("Botón 'Soluciones' presente");
        Assert.assertTrue(documentacionButton.isDisplayed(), "Botón 'Documentación' no se encuentra");
        System.out.println("Botón 'Documentación' presente");
        
        // Navegar a la página de Documentación
        documentacionButton.click();
        Thread.sleep(2000); // Pausa para ver el cambio de página

        // Verificar que estamos en la página de Documentación
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("documentation"), "No se ha navegado correctamente a la página de Documentación");

        // Volver a la página principal desde Documentación
        WebElement inicioDesdeDocumentacion = driver.findElement(By.xpath("//*[@id='root']/nav/div/div/div[2]/a"));
        inicioDesdeDocumentacion.click();
        Thread.sleep(2000); // Pausa para regresar a la página principal

        // Navegar a la página de descarga
        WebElement descargarButton = driver.findElement(By.xpath("//*[@id='root']/nav/div/div/div[2]/a[5]"));
        descargarButton.click();
        Thread.sleep(2000); // Pausa para ver el cambio de página

        // Descargar el APK
        WebElement descargarApkButton = driver.findElement(By.xpath("//*[@id='root']/section/div/a"));
        descargarApkButton.click();
        Thread.sleep(3000); // Pausa para iniciar la descarga
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
