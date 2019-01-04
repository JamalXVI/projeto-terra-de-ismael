package br.com.jamalxvi.farmaciadanatureza.service.impl;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumAutorizacaoUsuario;
import br.com.jamalxvi.farmaciadanatureza.enums.EnumExcecaoDto;
import br.com.jamalxvi.farmaciadanatureza.exception.MensagemExcecao;
import br.com.jamalxvi.farmaciadanatureza.models.Autoridade;
import br.com.jamalxvi.farmaciadanatureza.repository.AutoridadeRepository;
import br.com.jamalxvi.farmaciadanatureza.service.AutoridadeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static br.com.jamalxvi.farmaciadanatureza.enums.EnumMesagens.ERRO_BUSCAR_AUTORIDADE;
import static br.com.jamalxvi.farmaciadanatureza.enums.EnumMesagens.ERRO_IMPOSSIVEL_SALVAR_AUTORIDADE;

/**
 * Implementação do Serviço de autoridade
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Service
@AllArgsConstructor
@NoArgsConstructor
public class AutorizadadeServiceImpl implements AutoridadeService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AutoridadeRepository autoridadeRepository;

    @Override
    public List<Autoridade> encontrarPeloId(Long id) {
        Autoridade autoridade = this.autoridadeRepository.findById(id).orElseThrow(() -> new
                MensagemExcecao(ERRO_BUSCAR_AUTORIDADE.getMensagem(), EnumExcecaoDto.NAO_ENCONTRADO));
        List<Autoridade> autoridades = new ArrayList<>();
        autoridades.add(autoridade);
        return autoridades;
    }

    @Override
    public List<Autoridade> encontrarPelaAutorizacao(String autorizacao) {
        log.info("Iniciando a autorizacao: " + autorizacao);
        EnumAutorizacaoUsuario enumAutorizacaoUsuario = EnumAutorizacaoUsuario.getEnum(autorizacao);
        return getAutoridades(enumAutorizacaoUsuario);
    }

    public List<Autoridade> encontrarPelaAutorizacao(EnumAutorizacaoUsuario autorizacao) {
        return getAutoridades(autorizacao);
    }

    private List<Autoridade> getAutoridades(EnumAutorizacaoUsuario enumAutorizacaoUsuario) {
        List<Autoridade> auths = new ArrayList<>();
        if (enumAutorizacaoUsuario != null) {
            Autoridade auth = this.autoridadeRepository.findByAutorizacao(enumAutorizacaoUsuario);
            if (auth == null) {
                auth = Autoridade.builder().autorizacao(enumAutorizacaoUsuario).build();
                this.autoridadeRepository.save(auth);
            }
            auths.add(auth);
        }
        return auths;
    }

    @Override
    public Autoridade salvar(EnumAutorizacaoUsuario autorizacaoUsuario) {
        return criarAutoridade(autorizacaoUsuario);
    }

    @Override
    public Autoridade salvar(String autorizacao) {

        EnumAutorizacaoUsuario enumRole = EnumAutorizacaoUsuario.getEnum(autorizacao);
        return criarAutoridade(enumRole);
    }

    private Autoridade getAutoridade(Autoridade autoridade) {
        return autoridadeRepository.findByAutorizacao(
                autoridade.getAutorizacao());
    }

    private Autoridade criarAutoridade(EnumAutorizacaoUsuario role) {
        if (role != null) {
            Autoridade autoridade = Autoridade.builder().autorizacao(role).build();
            Autoridade encontrada = getAutoridade(autoridade);
            if (encontrada == null) {
                autoridadeRepository.save(autoridade);
            } else {
                autoridade = encontrada;
            }
            return autoridade;
        } else {
            throw new MensagemExcecao(ERRO_IMPOSSIVEL_SALVAR_AUTORIDADE.getMensagem(), EnumExcecaoDto
                    .NAO_ENCONTRADO);
        }
    }
}
