package br.com.jamalxvi.farmaciadanatureza.enums;

/**
 * Enum que define o tipo de perfil de permissões que o usuário pode ter.
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
public enum EnumAutorizacaoUsuario {
    ROLE_USUARIO,
    ROLE_ADMIN;
    public static EnumAutorizacaoUsuario getEnum(String valor){
        EnumAutorizacaoUsuario[] enums = values();
        for (EnumAutorizacaoUsuario en : enums) {
            if(en.name().equals(valor)){
                return en;
            }
        }
        return null;
    }
}
