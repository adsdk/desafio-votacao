package com.br.vote.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "client")
public class ClientProperties {

    private String validationUrl;
}
