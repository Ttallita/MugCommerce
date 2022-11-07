package selenium.pageModels.components;

import org.openqa.selenium.WebDriver;
import selenium.pageModels.perfilCliente.PerfilClientePage;

public class HeaderUsuarioDeslogado extends HeaderComponentAbstract{

    public HeaderUsuarioDeslogado(WebDriver driver) {
        super(driver);
    }

    public PerfilClientePage acessarPagina(String pagina) {
        throw new RuntimeException("Usuário não está logado para acessar sua página de perfil");
    }
}
