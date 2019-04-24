import java.util.Scanner;
import java.io.*; 
import java.util.*;
import javafx.application.Application;
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.*; 
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.event.*;
import javafx.scene.control.Alert.AlertType; 

public class Adventure extends Application{
    static String mapFile;
    ArrayList<String> miniMap;
    GridPane worldMap;
    TextArea mapDisplay;
    public void updateMiniMap()
    {
        String md = "";
        for(String row : miniMap)
        {
           md += row + "\n";
        }
        mapDisplay.setText(md);
    }
    @Override 
    public void start(Stage stage) throws Exception{
        GameChar character = new GameChar(mapFile);
        miniMap = character.getMiniMap();
        updateMiniMap();
        BorderPane pane = new BorderPane();
        TextField inputBox = new TextField();
        TextArea commandOutput = new TextArea();
        commandOutput.setEditable(false);
        inputBox.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke)
            {
                if (ke.getCode().equals(KeyCode.ENTER))
                {                
                    String result = character.processCommand(inputBox.getText());  
                    if(result.equals("Farewell"))
                    {
                        Alert a = new Alert(AlertType.NONE);
                        a.setAlertType(AlertType.CONFIRMATION); 
                        a.setContentText(commandOutput.getText()); 
                        a.setHeaderText("");
                        a.setOnCloseRequest(event -> System.exit(0));
                        a.show(); 
                    }  
                    String location = "\nYou are at location " + character.getY() + "," + character.getX() +" in terrain " + character.getTerrain();
                    commandOutput.setText(inputBox.getText() + "\n" + result + location);
                    inputBox.setText("");
                    miniMap = character.getMiniMap();  
                    updateMiniMap();                  
                }
            }
        });

        Button quitGame = new Button("Quit");
        EventHandler<ActionEvent> quitBtn = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            {
                Alert a = new Alert(AlertType.NONE);
                a.setAlertType(AlertType.CONFIRMATION); 
                a.setContentText("Farewell"); 
                a.setHeaderText("");
                a.setOnCloseRequest(event -> System.exit(0));
                a.show();
            }
        };        
        quitGame.setOnAction(quitBtn);

        Button saveGame = new Button("Save");
        EventHandler<ActionEvent> saveBtn = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            {
                Alert a = new Alert(AlertType.NONE);
                a.setAlertType(AlertType.CONFIRMATION); 
                a.setContentText("Save Pressed"); 
                a.setHeaderText("");
                a.show();
            }
        };        
        saveGame.setOnAction(saveBtn);

        Button openGame = new Button("Open");
        EventHandler<ActionEvent> openBtn = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            {
                Alert a = new Alert(AlertType.NONE);
                a.setAlertType(AlertType.CONFIRMATION); 
                a.setContentText("Open Pressed"); 
                a.setHeaderText("");
                a.show();
            }
        };        
        openGame.setOnAction(openBtn);

        HBox bottomPane = new HBox();
        bottomPane.getChildren().add(inputBox);
        bottomPane.getChildren().add(saveGame);
        bottomPane.getChildren().add(quitGame);
        bottomPane.getChildren().add(openGame);
        
        pane.setCenter(mapDisplay);
        pane.setRight(commandOutput);
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