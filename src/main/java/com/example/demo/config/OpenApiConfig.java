package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração de documentação OpenAPI (SWAGGER)
 */

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Api de Gerenciamento de Tarefas")
                        .version("1.0")
                        .description("API REST desenvolvida para criação, leitura, alteração de status de remoção de tarefas")
                        .contact(new Contact()
                                .name("Suporte Técnico")
                                .email("mateus_mathias@estudante.sesisenai.org.br")
                        )
                );
    }
}
