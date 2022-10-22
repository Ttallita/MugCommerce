package selenium.pageModels.components;

import org.openqa.selenium.WebDriver;

public class HeaderUsuarioDeslogado extends HeaderComponentAbstract{

    public HeaderUsuarioDeslogado(WebDriver driver) {
        super(driver);
    }

    @Override
    public void acessarPagina(String pagina) {
        throw new RuntimeException("Usuário não está logado para acessar sua página de perfil");
    }
}
