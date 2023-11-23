package seleniumScript;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.stream.Collectors;
import java.util.List;


public class PruebasNoticias {
	ChromeDriver driver;
	String url = "http://127.0.0.1:8000/login";
	@BeforeMethod
	//método para invocar al navegador Chrome
	public void invocarNavegador() {
		//ruta donde se encuentra el ejecutable de chrome driver
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Usuario\\Desktop\\New folder\\"
				+ "Selenium testing\\chromedriver-win32\\chromedriver.exe");
		
		driver = new ChromeDriver(); //inicializando el objeto 
		driver.manage().window().maximize(); //maximizando la ventana
		driver.get(url); 
		
		driver.findElement(By.id("correo")).sendKeys("tt54321@ues.edu.sv");
		driver.findElement(By.id("password")).sendKeys("Minerva.23");
		driver.findElement(By.id("btnLogin")).click();
		try {
            Thread.sleep(3000); // Sleep for 10 seconds (10,000 milliseconds)
        } catch (InterruptedException e) {
            // Handle the exception (e.g., interrupted sleep)
        }
	}
	
	@Test
	public void crearNoticia() {
		//verificar que el login funcione
		//webelement
		
		driver.findElement(By.id("btnGestionNoticias")).click();
		driver.findElement(By.id("inputT")).sendKeys("Titulo de prueba");
		
		  // Hacer clic en el botón "Subir imagen de portada"
        driver.findElement(By.id("botonSubir")).click();

        // Esperar un momento para que aparezca el cuadro de diálogo para seleccionar archivos
        try {
            Thread.sleep(2000); // Ajusta el tiempo según sea necesario
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Ruta de la imagen que deseas subir
        String imagePath = "\"C:\\Users\\Usuario\\Downloads\\guppy icon.png\"";

        // Copiar la ruta de la imagen al portapapeles
        StringSelection stringSelection = new StringSelection(imagePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        // Usar la clase Robot para simular Ctrl+V (pegar)
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        } catch (AWTException e) {
            e.printStackTrace();
        }

        // Presionar Enter para confirmar la selección de la imagen
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        
     // Localiza el elemento por su clase
        WebElement editor = driver.findElement(By.className("ck-editor__editable"));

        // Envía el texto "Lorem Ipsum" al elemento
        editor.sendKeys("Lorem Ipsum");
		
     // Esperar un momento para que aparezca el cuadro de diálogo para seleccionar archivos
        try {
            Thread.sleep(5000); // Ajusta el tiempo según sea necesario
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        driver.findElement(By.id("btnCrearNoticias")).click();
        
        try {
            Thread.sleep(3000); // Ajusta el tiempo según sea necesario
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        driver.quit();
        
	}
	
	
	@Test 
	public void verMasInfo() {
		driver.findElement(By.id("btnNoticiasPublicadas")).click();
		WebElement boton = driver.findElement(By.cssSelector(".botonCurso.botonFiltroActivoCurso"
				+ ".btnVerMasNoticia[data-id-noticia='54']"));
		boton.click();
		driver.quit();
	}
	
	@Test 
	public void editarNoticia() {
		driver.findElement(By.id("btnNoticiasPublicadas")).click();
		WebElement boton = driver.findElement(By.cssSelector(".botonCurso.botonFiltroActivoCurso.btnVerMasNoticia[data-id-noticia='54']"));
		boton.click();
		WebElement botonEditar = driver.findElement(By.cssSelector(".btn-editar-noticia[data-idnoticia='54']"));

	        // Realiza clic en el botón
		WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(botonEditar));
	    botonEditar.click();
	    
	    try {
	          Thread.sleep(2000); // Ajusta el tiempo según sea necesario
	      } catch (InterruptedException e) {
	          e.printStackTrace();
	      }
	    driver.findElement(By.id("inputT")).clear();
	    driver.findElement(By.id("inputT")).sendKeys("Titulo de prueba edicion");
		
		  // Hacer clic en el botón "Subir imagen de portada"
      driver.findElement(By.id("botonSubir")).click();

      // Esperar un momento para que aparezca el cuadro de diálogo para seleccionar archivos
      try {
          Thread.sleep(2000); // Ajusta el tiempo según sea necesario
      } catch (InterruptedException e) {
          e.printStackTrace();
      }

      // Ruta de la imagen que deseas subir
      String imagePath = "\"C:\\Users\\Usuario\\Downloads\\guppy icon.png\"";

      // Copiar la ruta de la imagen al portapapeles
      StringSelection stringSelection = new StringSelection(imagePath);
      Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

      // Usar la clase Robot para simular Ctrl+V (pegar)
      try {
          Robot robot = new Robot();
          robot.keyPress(KeyEvent.VK_CONTROL);
          robot.keyPress(KeyEvent.VK_V);
          robot.keyRelease(KeyEvent.VK_V);
          robot.keyRelease(KeyEvent.VK_CONTROL);
      } catch (AWTException e) {
          e.printStackTrace();
      }

      // Presionar Enter para confirmar la selección de la imagen
      try {
          Robot robot = new Robot();
          robot.keyPress(KeyEvent.VK_ENTER);
          robot.keyRelease(KeyEvent.VK_ENTER);
      } catch (AWTException e) {
          e.printStackTrace();
      }
      
      WebElement parrafo = driver.findElement(By.cssSelector(".ck-editor__editable"));

      // Borra el contenido actual del párrafo
      parrafo.clear();

      // Ingresa el nuevo texto (Lorem Ipsum en este caso) en el párrafo
      parrafo.sendKeys("Lorem Ipsum");
      
      driver.findElement(By.id("btnActualizarNoticia")).click();
      
      try {
          Thread.sleep(3000); // Ajusta el tiempo según sea necesario
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
      
      driver.quit();
	}
	
	
	@Test
	/*
	 * Esta funcion evita eliminar la noticia con id 54 porque es la que se usa con las pruebas de editar
	 * */
	public void eliminarNoticiaNo54() {
		driver.findElement(By.id("btnNoticiasPublicadas")).click();
		
        // Localiza todos los botones de eliminación de noticias
        List<WebElement> botonesEliminar = driver.findElements(By.cssSelector(".botonCurso.botonFiltro"
        		+ "DesactivoCurso.btnEliminarNoticia"));

        // Verifica que haya al menos un botón de eliminación
        if (!botonesEliminar.isEmpty()) {
            // Filtra los botones para excluir aquellos asociados a la noticia con ID 54
            List<WebElement> botonesNo54 = botonesEliminar.stream()
                    .filter(boton -> !boton.getAttribute("data-id-noticia").equals("54"))
                    .filter(boton -> !boton.getAttribute("data-id-noticia").equals("78"))
                    .collect(Collectors.toList());

            // Verifica que haya al menos un botón no asociado a la noticia 54
            if (!botonesNo54.isEmpty()) {
                // Selecciona el primer botón no asociado a la noticia 54 y haz clic en él
                WebElement primerBotonNo54 = botonesNo54.get(0);
                primerBotonNo54.click();
                try {
                    Thread.sleep(2000); // Ajusta el tiempo según sea necesario
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                driver.findElement(By.id("btnEliminarNoticia")).click();
            } 
           }
        driver.quit();
        } 
    
	@Test
	public void buscarNoticia() {
		driver.findElement(By.id("btnNoticiasPublicadas")).click();
		driver.findElement(By.id("inputBusqueda")).sendKeys("Buscar");
	}
	
}
