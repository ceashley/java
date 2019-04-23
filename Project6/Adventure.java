import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.*; 
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.event.*;

public class Adventure extends Application{
    static String mapFile;
    @Override 
    public void start(Stage stage) throws Exception{
        GameChar character = new GameChar(mapFile);
        BorderPane pane = new BorderPane();
        TextField inputBox = new TextField();
        TextArea commandOutput = new TextArea();
        inputBox.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke)
            {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    
                    commandOutput.setText(character.goCommand(inputBox.getText()));
                }
            }
        });
        HBox bottomPane = new HBox();
        bottomPane.getChildren().add(inputBox);
        bottomPane.getChildren().add(commandOutput);
        pane.setBottom(bottomPane);
        Group root = new Group();
        root.getChildren().add(pane); 
        Scene scene = new Scene(root, 500, 500);    
        stage.setTitle("Adventure Game");         
        stage.setScene(scene); 
        stage.sizeToScene();
        stage.show();

    }
    public static void main(String args[]) throws Exception
    {
        
        if(args.length == 0)
        {
            print("no map file provided");
            return;
        }
        mapFile = args[0];
        Application.launch(args);
        /*GameChar character = new GameChar(args[0]);
        String in;
        do
        {
            if(in.charAt(0) == 'g')
            {
                character.goCommand(in);
            }
            else if(in.charAt(0) == 'i')
            {
                character.invintory();
            }
            else if(in.charAt(0) != 'q')
            {
                print("Invalid command: "+ in);
            }
            print("You are at location " + character.getY() + "," + character.getX() +" in terrain " + character.getTerrain());
        }while(!in.equals("quit") && !in.equals("q"));
        print("Farewell");*/
	}
    public static void print(String inStr)
    {
        System.out.println(inStr);
    }
}