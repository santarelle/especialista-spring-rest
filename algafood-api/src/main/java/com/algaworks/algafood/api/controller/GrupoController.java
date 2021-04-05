package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.algaworks.algafood.api.assembler.GrupoInputDisassembler;
import com.algaworks.algafood.api.assembler.GrupoModelAssembler;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.api.model.input.GrupoInput;
import com.algaworks.algafood.domain.exception.GrupoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.repository.GrupoRepository;
import com.algaworks.algafood.domain.service.CadastroGrupoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GrupoInputDisassembler grupoInputDisassembler;

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @Autowired
    private CadastroGrupoService cadastroGrupoService;

    @Autowired
    private GrupoRepository grupoRepository;

    @GetMapping
    public List<GrupoModel> listar() {
        List<Grupo> grupos = grupoRepository.findAll();
        return grupoModelAssembler.toCollectionModel(grupos);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoModel adicionar(@RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);
        grupo = cadastroGrupoService.salvar(grupo);
        GrupoModel grupoModel = grupoModelAssembler.toModel(grupo);
        return grupoModel;
    }

    @GetMapping("/{grupoId}")
    public GrupoModel buscar(@PathVariable Long grupoId) {
        try {
            Grupo grupo = cadastroGrupoService.buscarPorId(grupoId);
            GrupoModel grupoModel = grupoModelAssembler.toModel(grupo);
            return grupoModel;
        } catch (GrupoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{grupoId}")
    public GrupoModel atualizar(@RequestBody @Valid GrupoInput grupoInput, @PathVariable Long grupoId) {
        try {
            Grupo grupoAtual = cadastroGrupoService.buscarPorId(grupoId);
            grupoInputDisassembler.copyToDomainObject(grupoInput, grupoAtual);
            grupoAtual = cadastroGrupoService.salvar(grupoAtual);
            return grupoModelAssembler.toModel(grupoAtual);
        } catch (GrupoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long grupoId) {
        try {
            cadastroGrupoService.excluir(grupoId);
        } catch (GrupoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }
    }
}
