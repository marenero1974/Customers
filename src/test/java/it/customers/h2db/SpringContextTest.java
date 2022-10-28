package it.customers.h2db;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;


@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
@WebAppConfiguration
public class SpringContextTest {

    @Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }

    @Configuration
    public static class ContextConfiguration {

    }
}
