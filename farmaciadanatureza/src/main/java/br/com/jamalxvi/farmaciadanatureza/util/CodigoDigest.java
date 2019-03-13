package br.com.jamalxvi.farmaciadanatureza.util;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

public class CodigoDigest {
    private final static String CHAVE="tuntankamonreidoegito362";
    private final static String INITVETOR = "rainhavittorinha";

    /**
     * Ofusca uma string
     * @param valor
     * @return
     */
    public static String digerir(String valor) {
        try {
            IvParameterSpec iv = new IvParameterSpec(INITVETOR.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(CHAVE.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(valor.getBytes());

            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * Devolve a string original a partir da encryptada
     * @param digerida
     * @return
     */
    public static String reverter(String digerida) {
        try {
            IvParameterSpec iv = new IvParameterSpec(INITVETOR.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(CHAVE.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.decodeBase64(digerida));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
