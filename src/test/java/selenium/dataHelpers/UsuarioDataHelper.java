package selenium.dataHelpers;

import selenium.dataHelpers.VOs.UsuarioVO;

public class UsuarioDataHelper {

    private static Object[][] usuariosInvalidos() {
        UsuarioVO usuario1 = UsuarioVO.createUsuarioClientePadrao();
        usuario1.setSenha("Senha123456");
        usuario1.setSenhaConfirmacao("Senha123456");

        UsuarioVO usuario2 = UsuarioVO.createUsuarioClientePadrao();
        usuario2.setEmail("emailNaoCadastrado@email.com");

        return new Object[][]{
                {usuario1},
                {usuario2}
        };
    }

}
