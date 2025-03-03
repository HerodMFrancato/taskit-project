package tests;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Generator;
import suporte.Screenshot;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class informacoesUsuarioTest {
    private WebDriver navegador;

    @Rule
    public TestName test = new TestName();

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/herodfrancato/drivers/chromedriver");
        WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        navegador.get("http:juliodelima.com.br/taskit");
    }
    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario() {
        navegador.findElement(By.linkText("Sign in")).click();
        WebElement formularioSignInBox = navegador.findElement(By.id("Sign in"));
        formularioSignInBox.findElement(By.name("login")).sendKeys("julio001");
        formularioSignInBox.findElement(By.name("password")).sendKeys("123456");
        navegador.findElement(By.linkText("SIGN IN")).click();
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();
        WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));
        WebElement campoType = popupAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText("phone");
        popupAddMoreData.findElement(By.name("contact")).sendKeys("+5511999999999");
        popupAddMoreData.findElement(By.linkText("SAVE")).click();
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals("Your contact has been added", mensagem);
    }
    @Test
    public void removerUmContatoDeumUsuario() {
        navegador.findElement(By.xpath("//span[text()=\"+55113334444\"]following-sibling::a")).click();
        navegador.switchTo().alert().accept();
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals("Rest in peace, dear phone!", mensagem);
        String screenshotArquivo = "/Users/herodfrancato/test-reports/taskit/" + Generator.datahoraParaArquivo() + test.getMethodName() + ".png";
        Screenshot.tirar(navegador, screenshotArquivo);
        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));
        navegador.findElement(By.linkText("Logout")).click();
    }

    @After
    public void tearDown() {
        navegador.quit();
    }
}
