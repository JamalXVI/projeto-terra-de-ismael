package br.com.jamalxvi.farmaciadanatureza.util;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe reponsável por conversões e trabalhos com datas.
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
public class AjudandeDeData {
  /**
   * Converte LocalDate para String no formato yyyy-MM-dd
   * @param data a data de entrada
   * @return a data convertida para String
   */
  public static String localDateParaString(LocalDate data) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    return dateTimeFormatter.format(data.atStartOfDay());
  }
}
