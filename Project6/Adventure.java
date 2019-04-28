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
import javafx.scene.image.*;

public class Adventure extends Application{
    static String mapFile;
    ArrayList<String> miniMap = new ArrayList<String>();
    GridPane worldMap = new GridPane();
    private int tileHeight = 10;
    private int tileWidth = 10;
    private int currentTile = 0;
    private int currentTileRow = 0;
    private HashMap<String,List<String>> icons = new HashMap<String,List<String>>();
    GameChar character;
    private void updateMiniMap()
    {
        for(int row = 0; row < 5; row++)
        {
            for(int col = 0; col < 5; col++)
            {
                ImageView tileImage = new ImageView();
                tileImage.setImage(getTileImage(row,col));
                tileImage.setFitHeight(tileHeight);
                tileImage.setFitWidth(tileWidth);
                GridPane.setRowIndex(tileImage, row);
                GridPane.setColumnIndex(tileImage, col);
                worldMap.getChildren().addAll(tileImage);
            }
        }
        currentTileRow = 0;
        currentTile = 0;
    }
    private Image getTileImage(int row, int col)
    {
        Image image;// = new Image("MapPics/forest.png",false);
        String tile = getTile(row, col);
        //System.out.print(icons.get(tile) + "\n");
        image = new Image(icons.get(tile).get(1),false);
        return image;
    }
    private String getTile(int row, int col)
    {
        String tile;
        if(currentTile >= 5)
        {
            currentTileRow++;
            currentTile = 0;
        }  
        if(row == 2 && col == 2) //if we are getting the tile for the center of the map then its the player
        {
            tile = character.getPlayerIcon();
        }   
        else
        {
            tile = Character.toString(miniMap.get(currentTileRow).charAt(currentTile));
        }          
        currentTile++;
        return tile;
    }
    private void updateTileSize(String tileSize)
    {
        String[] ts = tileSize.split(" ",2);
        tileWidth = Integer.parseInt(ts[0]);
        tileHeight = Integer.parseInt(ts[1]);
    }
    @Override 
    public void start(Stage stage) throws Exception{
        character = new GameChar(mapFile);
        updateTileSize(character.getTileSize());
        miniMap = character.getMiniMap();
        icons = character.getMapIcons();
        updateMiniMap();
        BorderPane pane = new BorderPane();
        TextField inputBox = new TextField();
        TextArea commandOutput = new TextArea();
        commandOutput.setEditable(false);
        commandOutput.setPrefHeight(tileHeight * 5);
        commandOutput.setPrefWidth(tileWidth * 5);
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
                        a.setContentText("Farewell"); 
                        a.setHeaderText("");
                        a.setOnCloseRequest(event -> System.exit(0));
                        a.show(); 
                    }  
                    String location = "\nYou are at location " + character.getY() + "," + character.getX() +" in terrain " + character.getTerrain();
                    commandOutput.appendText(inputBox.getText() + "\n" + result + location+ "\n");
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
        
        pane.setCenter(worldMap);
        pane.setRight(commandOutput);
        pane.setBottom(bottomPane);
        Group root = new Group();
        root.getChildren().add(pane); 
        Scene scene = new Scene(root, tileWidth * 10 + 10, tileHeight * 5 +30);    
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
            String wait = System. console(). readLine();
            System.exit(1);
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