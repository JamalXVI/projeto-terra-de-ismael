package br.com.jamalxvi.farmaciadanatureza.rest;


import br.com.jamalxvi.farmaciadanatureza.models.dto.FormularioReceitaDto;
import br.com.jamalxvi.farmaciadanatureza.service.ReceitaService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @RequestMapping(method = POST, value = "/receita/")
    public void nova(HttpServletResponse response, @RequestBody FormularioReceitaDto dto) throws IOException, JRException {
        JasperPrint etiquetas = receitaService.cadastra(dto);
        response.setContentType("application/x-pdf");
        response.setHeader("Content-disposition", "inline; filename=etiqueta.pdf");

        final OutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(etiquetas, outStream);
    }
}
