package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import suporte.Web;
import static org.junit.Assert.*;

public class infromacoesUsuariosPageObjectsTest {
    private WebDriver navegador;

    @Before
    public void setUp() {
        navegador = Web.createChrome();
    }

    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario() {
        String textToast = new LoginPage(navegador)
                .clickSignIn()
                .typeLogin("julio0001")
                .typePassword("123456")
                .clickInSignIn()
                .clickMe()
                .clickAbaMoreDataAboutYou()
                .clickButtonAddMoreDataAboutYou()
                .chooseContactType("phone")
                .typeContact("+551199999999")
                .clickSave()
                .captTextoToast();

        assertEquals("Your contact has been added!", textToast);
    }

    @After
    public void tearDown() {
        navegador.quit();
    }
}
