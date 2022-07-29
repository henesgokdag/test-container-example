package org.example;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.couchbase.BucketDefinition;
import org.testcontainers.couchbase.CouchbaseContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;

import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@Testcontainers
@ActiveProfiles("container")
@SpringBootTest
public class CouchbaseTestContainer {
    private static final DockerImageName COUCHBASE_IMAGE_ENTERPRISE = DockerImageName.parse("couchbase:enterprise")
            .asCompatibleSubstituteFor("couchbase/server")
            .withTag("7.1.0");

    @ClassRule
    public final static CouchbaseContainer container = new CouchbaseContainer(COUCHBASE_IMAGE_ENTERPRISE)
            .withBucket(new BucketDefinition("Message"))
            .withCredentials("enes","enesenes")
            .withStartupTimeout(Duration.ofSeconds(90))
            .waitingFor(Wait.defaultWaitStrategy());

    @BeforeClass
    public static void beforeClass(){ container.start();}

    @DynamicPropertySource
    static void bindCouchbaseProperties(DynamicPropertyRegistry registry){
        container.start();
        await().until(container::isRunning);
        registry.add("spring.couchbase.host",container::getConnectionString);
    }
}
