package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTest {

    @Test
    void contextLoads() {
        // This test ensures the Spring Context loads successfully.
    }

    @Test
    void testMain() {
        // Explicitly call main to satisfy coverage
        DependabilityProjectApplication.main(new String[]{});
    }
}