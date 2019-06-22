package br.com.jamalxvi.farmaciadanatureza.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jamalxvi.farmaciadanatureza.models.PrincipioAtivo;
import br.com.jamalxvi.farmaciadanatureza.repository.PrincipioAtivoRepository;
import br.com.jamalxvi.farmaciadanatureza.service.PrincipioAtivoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PrincipioAtivoServiceImpl extends
    BaseServiceImpl<PrincipioAtivo, PrincipioAtivoRepository> implements PrincipioAtivoService {
  @Autowired
  PrincipioAtivoRepository principioAtivoRepository;

  @Override
  void config() {
    this.repository = principioAtivoRepository;
  }

}
