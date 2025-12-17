package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void contextLoads() {
        // FIX: Assert that the context is actually created and not null
        assertThat(context).isNotNull();
    }

    @Test
    void testMain() {
        // FIX: Use assertDoesNotThrow to confirm main() runs without crashing
        // This satisfies SonarQube's requirement for an assertion
        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> {
            DependabilityProjectApplication.main(new String[]{});
        });
    }
}