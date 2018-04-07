package common.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
        root.getChildren().add(JavaFXMethode.addTitle("PARAMETRE"));
        addMenu();
    }

    private void addMenu() {
        VBox vbox = new VBox();

        Button buttonBack= new Button("Menu");
            buttonBack.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
            buttonBack.setMaxWidth(Double.MAX_VALUE);
            
        Button buttonUP= new Button("Move UP : "+Key.getInstance().getKeyUP());
            buttonUP.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
            buttonUP.setMaxWidth(Double.MAX_VALUE);
        Button buttonDOWN= new Button("Move DOWN : "+Key.getInstance().getKeyDOWN());
            buttonDOWN.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
            buttonDOWN.setMaxWidth(Double.MAX_VALUE);
        Button buttonLEFT= new Button("Move LEFT : "+Key.getInstance().getKeyLEFT());
            buttonLEFT.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
            buttonLEFT.setMaxWidth(Double.MAX_VALUE);
        Button buttonRIGHT= new Button("Move RIGHT : "+Key.getInstance().getKeyRIGHT());
            buttonRIGHT.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
            buttonRIGHT.setMaxWidth(Double.MAX_VALUE);
        Button buttonR= new Button("Reload : "+Key.getInstance().getKeyR());
            buttonR.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
            buttonR.setMaxWidth(Double.MAX_VALUE);
        
	
        vbox.getChildren().addAll(
                buttonUP,
                buttonDOWN,
                buttonLEFT,
                buttonRIGHT,
                buttonR,
                buttonBack);
        
        vbox.setSpacing(20);
        vbox.setMinWidth(200);
        
        root.getChildren().addAll(vbox);
        vbox.setTranslateX((JavaBobyIsYou.WIDTH/2)-100);
	vbox.setTranslateY((JavaBobyIsYou.HEIGHT/2)-70);
	
        buttonUP.setOnAction(event -> {
            root.setOnKeyPressed((KeyEvent ke) -> {
                buttonUP.setText("Move UP : "+ke.getCode());
                Key.getInstance().setKeyUP(ke.getCode());
                ke.consume();
            });
        });
        buttonDOWN.setOnAction(event -> {
            root.setOnKeyPressed((KeyEvent ke) -> {
                buttonDOWN.setText("Move DOWN : "+ke.getCode());
                Key.getInstance().setKeyDOWN(ke.getCode());
                ke.consume();
            });
        });
        buttonLEFT.setOnAction(event -> {
            root.setOnKeyPressed((KeyEvent ke) -> {
                buttonLEFT.setText("Move LEFT : "+ke.getCode());
                Key.getInstance().setKeyLEFT(ke.getCode());
                ke.consume();
            });
        });
        buttonRIGHT.setOnAction(event -> {
            root.setOnKeyPressed((KeyEvent ke) -> {
                buttonRIGHT.setText("Move RIGHT : "+ke.getCode());
                Key.getInstance().setKeyRIGHT(ke.getCode());
                ke.consume();
            });
        });
        buttonR.setOnAction(event -> {
            root.setOnKeyPressed((KeyEvent ke) -> {
                buttonR.setText("Reload : "+ke.getCode());
                Key.getInstance().setKeyR(ke.getCode());
                ke.consume();
            });
        });
        buttonBack.setOnAction(event -> {
            saveKey();
        });
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE)
                saveKey();
            e.consume();
        });
    }
    private void saveKey() {
        JavaBobyIsYou.save();
        this.primaryStage.setScene(MenuInit.getInstance().scene);
    }      
    
}
