package br.com.jamalxvi.farmaciadanatureza.service;

import net.sf.jasperreports.engine.JasperPrint;

import java.util.Map;

public interface ReportService {
    public JasperPrint gerarRelatorio(String nome, Map<String, Object> params);
}
