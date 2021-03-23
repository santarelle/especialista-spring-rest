package com.algaworks.algafood.core.modelmapper;

import com.algaworks.algafood.api.model.EnderecoModel;
import com.algaworks.algafood.api.model.EstadoModel;
import com.algaworks.algafood.domain.model.Endereco;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Endereco.class, EnderecoModel.class).<String>addMapping(
                enderecoSource -> enderecoSource.getCidade().getEstado().getNome(),
                (enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(value));

        return modelMapper;
    }
}
