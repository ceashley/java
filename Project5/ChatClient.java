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
import java.net.*;
import javafx.application.Platform;

public class ChatClient extends Application{
    BorderPane pane;
    static double WIDTH = 500;
    static String userName;
    static String port;
    static Socket s;
    static VBox vbox;
    Thread t1;
    static class textThread extends Thread {        
        public void run(){
            try{
                while(true){
                    InputStream inStream = s.getInputStream();
                    Scanner in = new Scanner(inStream);
                    String reply = in.nextLine();
                    Text t = new Text();
                    t.setWrappingWidth(WIDTH);
                    t.setFont(new Font(20));
                    t.setText(reply);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            vbox.getChildren().addAll(t);
                        }
                    });       
                }             
            }
            catch (Exception e) 
            { 
                // Throwing an exception 
                System.out.println ("In textThread: " + e); 
            }            
        }
    }
    @Override 
    public void start(Stage stage) throws Exception{
        OutputStream outStream = s.getOutputStream();
        PrintWriter out = new PrintWriter(outStream, true);
        InputStream inStream = s.getInputStream();
        Scanner in = new Scanner(inStream);
        out.println("connect "+userName);
        String reply = in.nextLine();
        System.out.println(reply);        
        TextField textField = new TextField();
        
        Button send = new Button("Send");
        EventHandler<ActionEvent> sendBtn = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            {
                Text t = new Text();
                t.setWrappingWidth(WIDTH);
                t.setFont(new Font(20));
                t.setText(textField.getText());                
                out.println(t.getText());
                vbox.getChildren().addAll(t);
            } 
        };  
        send.setOnAction(sendBtn);

        Button disc = new Button("Disconnect");
        EventHandler<ActionEvent> discBtn = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            {
                out.println("disconnect "+userName);
                System.exit(1);
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
        t1 = new textThread();
        t1.start();
        Scene scene = new Scene(root, WIDTH, 500);    
        stage.setTitle("Chat Client");         
        stage.setScene(scene); 
        stage.sizeToScene();
        stage.show();

    }
    public static void main(String[] args) throws Exception
    {
        if(args.length == 0){
            userName = "Anonymous";
            port = "4688";
        }
        else if(args.length == 1){
            userName = args[0];
            port = "4688";
        }
        else{
            userName = args[0];
            port = args[1];
        }
        s = new Socket("localhost", Integer.parseInt(port));
        vbox = new VBox(8); // spacing = 8
        Application.launch(args);
    }
}