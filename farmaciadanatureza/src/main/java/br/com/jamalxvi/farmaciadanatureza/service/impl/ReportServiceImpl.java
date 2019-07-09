package br.com.jamalxvi.farmaciadanatureza.service.impl;

import br.com.jamalxvi.farmaciadanatureza.service.ReportService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ReportServiceImpl implements ReportService {
    @Autowired
    DataSource dataSource;

    @Value("${diretorio.relatorio}")
    private String caminho;

    @Override
    public JasperPrint gerarRelatorio(String nome, Map<String, Object> params) {
        try {
            Connection connection = dataSource.getConnection();
            JasperReport jasperReport = JasperCompileManager.compileReport(caminho+nome+".jrxml");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, connection);
            return jasperPrint;
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
