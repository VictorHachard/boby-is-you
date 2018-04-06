package common.view;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Windows
 */
public class Parameter {
    
    Scene scene;
    private static Parameter INSTANCE = null;
    private Stage primaryStage = MenuInit.getInstance().getStage();
    private Pane root = new Pane();
    
    static Parameter getInstance() {           
        if (INSTANCE == null)  
            INSTANCE = new Parameter();
        return INSTANCE;
    }
    
    Parameter() {
        this.scene = new Scene(root,JavaBobyIsYou.WIDTH,JavaBobyIsYou.HEIGHT);
        //recuper les donner dans config
        addTitle();
        addMenu();
    }
    
    private void addTitle() {
        Title title = new Title("PARAMETRE");
        title.setTranslateX(JavaBobyIsYou.WIDTH / 2 - title.getTitleWidth() / 2);
        title.setTranslateY(JavaBobyIsYou.HEIGHT / 3);

        root.getChildren().add(title);
    }

    private void addMenu() {
        VBox vbox = new VBox();

        Button buttonBack= new Button("Continuer");
            buttonBack.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
            buttonBack.setMaxWidth(Double.MAX_VALUE);
        
        TextField textFieldUp = new TextField();
	
        vbox.getChildren().addAll(
                textFieldUp,
                buttonBack);
        
        vbox.setSpacing(20);
        vbox.setMinWidth(200);
        
        root.getChildren().addAll(vbox);
        vbox.setTranslateX((JavaBobyIsYou.WIDTH/2)-100);
	vbox.setTranslateY((JavaBobyIsYou.HEIGHT/2)-70);
	
        /*textFieldUp.setOnAction(e -> {
            textFieldUp.setText();
        });*/
        
        textFieldUp.setOnKeyPressed(new EventHandler<KeyEvent>(){
        @Override
        public void handle(KeyEvent ke){
            //textFieldUp.setText(ke.getText());
        }
    });
        
        buttonBack.setOnAction(event -> {
                       
        });
        /*scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ESCAPE:
                    Logger.getLogger(MenuInit.class.getName()).log(Level.INFO, "Exit of the application");
                    primaryStage.close();
                    break;
                    default :
                    //NE RIEN FAIRE
            }
            e.consume();
        });*/
    }        
    
}
