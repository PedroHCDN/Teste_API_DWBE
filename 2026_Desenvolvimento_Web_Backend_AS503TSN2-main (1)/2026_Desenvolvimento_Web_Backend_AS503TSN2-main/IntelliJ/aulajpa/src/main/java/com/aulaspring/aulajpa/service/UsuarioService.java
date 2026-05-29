package com.aulaspring.aulajpa.service;

import com.aulaspring.aulajpa.configs.JWTService;
import com.aulaspring.aulajpa.dto.*;
import com.aulaspring.aulajpa.model.Usuario;
import com.aulaspring.aulajpa.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;



import java.util.List;

@Service
public class UsuarioService {
    private UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JWTService jwtService){
        this.repository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    private Usuario toEntity(UsuarioRequestDTO usuarioRequestDTO){
        Usuario user = new Usuario();
        user.setNome(usuarioRequestDTO.getNome());
        user.setEmail(usuarioRequestDTO.getEmail());
        user.setSenha(usuarioRequestDTO.getSenha());
        user.setCpf(usuarioRequestDTO.getCpf());
        user.setPerfil(usuarioRequestDTO.getPerfil());
        return user;
    }
    private UsuarioResponseDTO toDTO(Usuario usuario){
        return UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .perfil(usuario.getPerfil())
                .build();
    }
    public UsuarioResponseDTO salvar(UsuarioRequestDTO usuarioRequestDTO){
        // 🔎 verifica se já existe email
//        if (repository.findByEmail(usuarioRequestDTO.getEmail()).isPresent()) {
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST,
//                    "Email já cadastrado!"
//            );
//        }

        Usuario usuario = toEntity(usuarioRequestDTO);
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        Usuario usuarioSalvo = this.repository.save(usuario);
        return toDTO(usuarioSalvo);
    }
    public List<UsuarioResponseDTO> todos(){
        return this.repository.findAll()
                .stream()
                .map(this :: toDTO)
                .toList();
    }
    public UsuarioResponseDTO porId(Long id){
        Usuario usuario = this.repository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado"));
        return toDTO(usuario);
    }

    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto){
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado"));
        usuario.setCpf(dto.getCpf());
        usuario.setEmail(dto.getEmail());
        usuario.setNome(dto.getNome());
        usuario.setSenha(dto.getSenha());
        Usuario atualizado = repository.save(usuario);
        return toDTO(atualizado);
    }
    public String exclui(Long id){
        Usuario usuario = this.repository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("usuário não encontrado"));
        this.repository.delete(usuario);
        return "Excluído com sucesso";
    }
    //Implementado para autenticação
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = this.repository.findByEmail(username);

        String[] roles = usuario.getPerfil() == "Administrador" ? new String[] { "ADMIN", "USER" }
                : new String[] { "USER" };
        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }

    public UserDetails autenticarTeste(Usuario usuario) {
        UserDetails user = loadUserByUsername(usuario.getEmail());
        boolean senhaOK = passwordEncoder.matches(usuario.getSenha(), user.getPassword());
        if (senhaOK) {
            return user;
        }
        throw new RegraNegocioException("Senha inválida");
    }


    public TokenDTO autenticar(AutenticacaoDTO autenticacao) {
        UserDetails user = loadUserByUsername(autenticacao.getEmail());
        boolean senhaOK = passwordEncoder.matches(autenticacao.getSenha(), user.getPassword());
        if (senhaOK) {
            Usuario usuario = new Usuario("", autenticacao.getEmail(), autenticacao.getSenha(), "", "");
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(autenticacao.getEmail(), token);
        }
        throw new RegraNegocioException("Senha inválida");
    }
}
