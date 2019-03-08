import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage;  
import javafx.scene.shape.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.input.MouseEvent;
import javafx.event.*;
import javafx.scene.image.*;
import javafx.scene.control.*;

public class JIMachine extends Application{
    double height,width;
    @Override 
    public void start(Stage stage) {        
           
        Image image = new Image("Thinking112x122.png",false);      
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

        Group root = new Group();        
        Scene scene = new Scene(root, 500, 500);     
        HBox box = new HBox();
        box.getChildren().add(iv1);
        box.getChildren().add(zoom);
        box.getChildren().add(defaultSize);
        box.getChildren().add(zoomOut);
        box.getChildren().add(exit);
        root.getChildren().add(box);        
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