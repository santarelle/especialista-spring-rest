package com.algaworks.algafood.domain.service;

import java.util.Optional;

import javax.transaction.Transactional;

import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.UsuarioComEmailDuplicadoException;
import com.algaworks.algafood.domain.exception.UsuarioNaoEncontradoException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CadastroGrupoService cadastroGrupo;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        usuarioRepository.detach(usuario);

        Optional<Usuario> usuarioPorEmail = usuarioRepository.findByEmail(usuario.getEmail());

        if (usuarioPorEmail.isPresent() && !usuarioPorEmail.get().equals(usuario)) {
            throw new UsuarioComEmailDuplicadoException(usuario.getEmail());
        }

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
        Usuario usuario = buscarOuFalhar(usuarioId);

        if (usuario.senhaNaoCoincideCom(senhaAtual)) {
            throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
        }

        usuario.setSenha(novaSenha);
    }

    public Usuario buscarOuFalhar(Long usuarioId) {
        return usuarioRepository.findById(usuarioId).orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
    }

    @Transactional
    public void desassociarGrupo(Long usuarioId, Long grupoId) {
        Usuario usuario = buscarOuFalhar(usuarioId);
        Grupo grupo = cadastroGrupo.buscarPorId(grupoId);

        usuario.removerGrupo(grupo);
    }

    @Transactional
    public void associarGrupo(Long usuarioId, Long grupoId) {
        Usuario usuario = buscarOuFalhar(usuarioId);
        Grupo grupo = cadastroGrupo.buscarPorId(grupoId);

        usuario.adicionarGrupo(grupo);
    }
}