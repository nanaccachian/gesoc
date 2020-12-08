package com.testigos.gesoc.views.config;

import static java.util.Arrays.asList;

import java.util.Collection;

import com.testigos.gesoc.model.services.UsuarioService;
import com.testigos.gesoc.persistence.DAOUsuario;
import com.testigos.gesoc.persistence.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.testigos.gesoc.model.domain.usuarios.Usuario;
import org.springframework.stereotype.Service;

@Service
public class MiUserDetailsService implements UserDetailsService {

    @Autowired
    private DAOUsuario daoUsuario;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = daoUsuario.find(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("No existe el usuario.");
        }

        return new UserDetails() {
            private static final long serialVersionUID = 1L;

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return asList(new SimpleGrantedAuthority(usuario.getPermisos()));
            }

            @Override
            public String getPassword() {
                return usuario.getPassword();
            }

            @Override
            public String getUsername() {
                return usuario.getUsername();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
    }
}