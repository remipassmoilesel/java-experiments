# Mettre en place des tests avec SpringBoot

Ajouter les dépendances au pom.xml:

    <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
    </dependency>
    
Créer une classe de test:

    @SpringBootTest                 // configuration de spring
    @RunWith(SpringRunner.class)    // configuration de JUnit. Essentiel, ou les tests seront inefficaces
    public class SharedResourceServiceTest{
    
        private final Logger logger = LoggerFactory.getLogger(SharedResourceServiceTest.class);
    
        @Autowired
        private SharedResourceService sharedResourceService;
    
        @Test
        public void test() {
    
            System.out.println(sharedResourceService);
    
        }
    
    }

"Mocker" un service:

    @RunWith(SpringRunner.class)
    @SpringBootTest
    public class MyTests {
    
        @MockBean
        private RemoteService remoteService;
    
        @Autowired
        private Reverser reverser;
    
        @Test
        public void exampleTest() {
            // RemoteService has been injected into the reverser bean
            given(this.remoteService.someCall()).willReturn("mock");
            String reverse = reverser.reverseSomeCall();
            assertThat(reverse).isEqualTo("kcom");
        }
    
    }
    
Tests JSON:

    @RunWith(SpringRunner.class)
    @JsonTest
    public class MyJsonTests {
    
        @Autowired
        private JacksonTester<VehicleDetails> json;
    
        @Test
        public void testSerialize() throws Exception {
            VehicleDetails details = new VehicleDetails("Honda", "Civic");
            // Assert against a `.json` file in the same package as the test
            assertThat(this.json.write(details)).isEqualToJson("expected.json");
            // Or use JSON path based assertions
            assertThat(this.json.write(details)).hasJsonPathStringValue("@.make");
            assertThat(this.json.write(details)).extractingJsonPathStringValue("@.make")
                    .isEqualTo("Honda");
        }
    
        @Test
        public void testDeserialize() throws Exception {
            String content = "{\"make\":\"Ford\",\"model\":\"Focus\"}";
            assertThat(this.json.parse(content))
                    .isEqualTo(new VehicleDetails("Ford", "Focus"));
            assertThat(this.json.parseObject(content).getMake()).isEqualTo("Ford");
        }
    
    }
    
Source: https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html