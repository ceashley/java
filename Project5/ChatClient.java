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
import javafx.scene.text.*;

public class ChatClient extends Application{
    BorderPane pane;
    double WIDTH = 500;
    @Override 
    public void start(Stage stage) throws Exception{         
        

        VBox vbox = new VBox(8); // spacing = 8
        TextField textField = new TextField ();
        Button send = new Button("Send");
        EventHandler<ActionEvent> sendBtn = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            {
                Text t = new Text();
                t.setWrappingWidth(WIDTH);
                t.setFont(new Font(20));
                t.setText(textField.getText());
                vbox.getChildren().addAll(t);
            } 
        };  
        send.setOnAction(sendBtn);

        Button disc = new Button("Disconnect");
        EventHandler<ActionEvent> discBtn = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            {
                Text t = new Text();
                t.setFont(new Font(20));
                t.setText("Disconnect Pressed");
                vbox.getChildren().addAll(t);
            }
        };        
        disc.setOnAction(discBtn);

        ScrollPane sp = new ScrollPane();
        sp.setPrefSize(WIDTH, 450);
        sp.setContent(vbox);        
        Group root = new Group();
        pane = new BorderPane();
        HBox bottomPane = new HBox();
        pane.setCenter(sp);
        bottomPane.getChildren().add(textField);
        bottomPane.getChildren().add(send);
        bottomPane.getChildren().add(disc);
        pane.setBottom(bottomPane);
        root.getChildren().add(pane);     
        Scene scene = new Scene(root, WIDTH, 500);
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