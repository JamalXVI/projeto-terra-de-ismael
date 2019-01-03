package br.com.jamalxvi.farmaciadanatureza.service.impl;

import br.com.jamalxvi.farmaciadanatureza.models.Usuario;
import br.com.jamalxvi.farmaciadanatureza.repository.UsuarioRepository;
import br.com.jamalxvi.farmaciadanatureza.service.UsuarioService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Customização da implementação da classe User Details Service do próprio Spring
 * @author      Jamal XVI <henriquearantest@gmail.com>
 * @version     0.1
 * @since       0.1
 */
@Service
public class DetalhesDoUsuarioCustomizadoServiceImp implements UserDetailsService {

    protected final Log LOGGER = LogFactory.getLog(getClass());

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {

        LOGGER.info("Iniciando a pesquisa: "+usuario);
        Usuario user = usuarioService.encontrarPeloNomeDeUsuario(usuario);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("Nenhum usuário foi encontrado com" +
                    " estas credenciais: '%s'.", usuario));
        } else {
            return user;
        }
    }

    public void changePassword(String senhaAntiga, String novaSenha) {

        Authentication usuarioAtual = SecurityContextHolder.getContext().getAuthentication();
        String username = usuarioAtual.getName();

        if (authenticationManager != null) {
            LOGGER.debug("Reautenticando usuário '"+ username + "' com a nova senha");

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, senhaAntiga));
        } else {
            LOGGER.debug("Nenhum gerenciador de autenticação encontrado. Não podemos atualizar a senha");

            return;
        }

        LOGGER.debug("Mudando senha para o usuário '"+ username + "'");

        Usuario usuario = (Usuario) loadUserByUsername(username);

        usuario.setSenha(passwordEncoder.encode(novaSenha));
        usuarioRepository.save(usuario);

    }

}
