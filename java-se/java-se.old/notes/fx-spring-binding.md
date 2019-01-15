# Liaison JavaFX / Spring

Project créé avec Spring initializr:

    https://start.spring.io
    
Dans la classe principale:
    
    
    @SpringBootApplication
    class JavafxExperimentsApplication extends Application {
    
        static Logger logger = LoggerFactory.getLogger(JavafxExperimentsApplication.class)
        private ConfigurableApplicationContext springContext
        private Parent root
    
        @Override
        void init() throws Exception {
            springContext = SpringApplication.run(JavafxExperimentsApplication.class)
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/root.fxml"))
            fxmlLoader.setControllerFactory({ type ->
                logger.info("Factory asked for: ${type}")
                return springContext.getBean(type)
            })
            root = fxmlLoader.load()
        }
    
        @Override
        void start(Stage primaryStage) throws Exception {
            primaryStage.setTitle("Main window")
            Scene scene = new Scene(root, 800, 600)
            primaryStage.setScene(scene)
            primaryStage.setResizable(true)
            primaryStage.show()
        }
    
        @Override
        void stop() throws Exception {
            springContext.stop()
        }
    
        static void main(String[] args) {
            launch(JavafxExperimentsApplication.class, args)
        }
    
    }

Ou l'on récupère le contexte Spring, et ou on créé un ControllerFactory JavaFX:

      springContext = SpringApplication.run(JavafxExperimentsApplication.class)
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/root.fxml"))
            fxmlLoader.setControllerFactory({ type ->
                logger.info("Factory asked for: ${type}")
                return springContext.getBean(type)
            })
            root = fxmlLoader.load()
            
