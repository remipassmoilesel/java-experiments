package org.remipassmoilesel.javafxexperiments

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.layout.BorderPane
import javafx.stage.Stage
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ConfigurableApplicationContext

@SpringBootApplication
class JavafxExperimentsApplication extends Application {

    static Logger logger = LoggerFactory.getLogger(JavafxExperimentsApplication.class)
    private ConfigurableApplicationContext springContext
    private Parent mainView

    @Override
    void init() throws Exception {
        springContext = SpringApplication.run(JavafxExperimentsApplication.class)
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/mainView.fxml"))
        fxmlLoader.setControllerFactory({ type ->
            logger.info("Factory asked for: ${type}")
            return springContext.getBean(type)
        })
        mainView = fxmlLoader.load()
    }

    @Override
    void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Main window")
        BorderPane root = new BorderPane();
        root.setStyle("""
                -fx-padding: 5;
                -fx-border-style: solid inside;
                -fx-border-width: 1;
                -fx-border-insets: 5;
                -fx-border-radius: 5;
                -fx-border-color: darkgray;
        """)
        root.setCenter(mainView)
        Scene scene = new Scene(root)
        primaryStage.setScene(scene)
        primaryStage.setWidth(1080);
        primaryStage.setHeight(720);
        primaryStage.setFullScreen(false);
        primaryStage.setResizable(true)
        primaryStage.show()
    }

    @Override
    void stop() throws Exception {
        logger.info("Stopping Spring context ...")
        springContext.stop()
    }

    static void main(String[] args) {
        launch(JavafxExperimentsApplication.class, args)
    }

}
