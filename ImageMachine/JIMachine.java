import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.*; 
import javafx.stage.FileChooser.ExtensionFilter; 
import javafx.scene.layout.*;
import javafx.scene.input.MouseEvent;
import javafx.event.*;
import javafx.scene.image.*;
import javafx.scene.control.*;
import java.io.*; 
import java.util.*;
import java.net.MalformedURLException;

public class JIMachine extends Application{
    double height,width;
    Image image;
    BorderPane pane;
    @Override 
    public void start(Stage stage) throws Exception{        
           
        image = new Image("Thinking112x122.png",false);      
        ImageView iv1 = new ImageView();
        iv1.setImage(image);
        height = image.getHeight();
        width = image.getWidth();       
        Button zoom = new Button("Zoom In");
        EventHandler<ActionEvent> eventZoom = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            {
                width = width + width * .25;
                height = height + height * .25;
                iv1.setFitWidth(width);
                iv1.setFitHeight(height);
            } 
        };        
        zoom.setOnAction(eventZoom);

        Button defaultSize = new Button("100%");
        EventHandler<ActionEvent> eventDefault = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            {
                width = image.getWidth();
                height = image.getHeight();
                iv1.setFitWidth(width);
                iv1.setFitHeight(height);
            } 
        };
        defaultSize.setOnAction(eventDefault);

        Button zoomOut = new Button("Zoom Out");
        EventHandler<ActionEvent> eventZoomOut = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            {
                width = width - width * .25;
                height = height - height * .25;
                iv1.setFitWidth(width);
                iv1.setFitHeight(height);
            } 
        };        
        zoomOut.setOnAction(eventZoomOut);

        Button exit = new Button("Quit");
        EventHandler<ActionEvent> exitBtn = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            {
                System.exit(0);
            } 
        };        
        exit.setOnAction(exitBtn);

        Button open = new Button("Open");
        EventHandler<ActionEvent> openBtn = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            {
                try{
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Open Resource File");
                    fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
                    File selectedFile = fileChooser.showOpenDialog(stage);
                    if (selectedFile != null) {
                        image = new Image(selectedFile.toURI().toURL().toExternalForm(),false);
                        iv1.setImage(image);
                        height = image.getHeight();
                        width = image.getWidth();
                        iv1.setFitWidth(width);
                        iv1.setFitHeight(height);
                        stage.setWidth(width + 50);
                        stage.setHeight(height + 50);
                        pane.setCenter(iv1);
                    }
                }
                catch(MalformedURLException ex){
                    System.out.print(ex);
                    System.exit(1);
                }
            } 
        };        
        open.setOnAction(openBtn);

        Group root = new Group();        
        Scene scene = new Scene(root, 500, 500);     
        pane = new BorderPane();
        HBox bottomPane = new HBox();
        pane.setCenter(iv1);
        bottomPane.getChildren().add(open);
        bottomPane.getChildren().add(zoom);
        bottomPane.getChildren().add(defaultSize);
        bottomPane.getChildren().add(zoomOut);
        bottomPane.getChildren().add(exit);
        pane.setTop(bottomPane);
        root.getChildren().add(pane);        
        stage.setTitle("Image Machine"); 
        stage.setScene(scene); 
        stage.sizeToScene();
        stage.show();

    }
    public static void main(String[] args) throws Exception
    {
         Application.launch(args);
    }
}