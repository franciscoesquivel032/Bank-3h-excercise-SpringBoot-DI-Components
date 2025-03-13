package org.dam2.bancoestudiarspring.configuracion;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // Cargar Beans para inyectar
    @Bean(initMethod = "inicializarBean", destroyMethod = "finalizarBean")
    DtoConverter dtoConverter() {
	    return new DtoConverter();
	}

}
