package common.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author Windows
 */
public class MenuParameter {
    
    Scene scene;
    private static MenuParameter INSTANCE = null;
    private Pane root = new Pane();
    
    static MenuParameter getInstance() {           
        if (INSTANCE == null)  
            INSTANCE = new MenuParameter();
        return INSTANCE;
    }
    
    private MenuParameter() {
        this.scene = new Scene(root,JavaBobyIsYou.WIDTH,JavaBobyIsYou.HEIGHT);
        scene.getStylesheets().add(JavaBobyIsYou.THEME);
        //recuper les donner dans config
        root.setBackground(JavaFXMethode.addColoredBackGround(Color.BLACK));
        root.getChildren().add(JavaFXMethode.addTitle("PARAMETRE",Color.WHITE));
        addMenu();
    }

    private void addMenu() {
        VBox vbox = new VBox();

        Button buttonBack= new Button("Menu");            
        Button buttonUP= new Button("Move UP : "+Key.getInstance().getKeyUP());
        Button buttonDOWN= new Button("Move DOWN : "+Key.getInstance().getKeyDOWN());
        Button buttonLEFT= new Button("Move LEFT : "+Key.getInstance().getKeyLEFT());
        Button buttonRIGHT= new Button("Move RIGHT : "+Key.getInstance().getKeyRIGHT());
        Button buttonR= new Button("Reload : "+Key.getInstance().getKeyR());
        
        vbox.getChildren().addAll(
                buttonUP,
                buttonDOWN,
                buttonLEFT,
                buttonRIGHT,
                buttonR,
                buttonBack);
        vbox.getStyleClass().add("vbox");
        root.getChildren().addAll(vbox);
        vbox.setTranslateX((JavaBobyIsYou.WIDTH/2)-90);
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
        MenuInit.getInstance().getStage().setScene(MenuInit.getInstance().scene);
    }      
    
}
