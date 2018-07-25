package br.com.jamalxvi.farmaciadanatureza.enums;

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
