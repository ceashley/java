import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage;  
import javafx.scene.shape.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.*;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

public class checkerboard extends Application { 
    @Override 
    public void start(Stage stage) {        
        GridPane grid = new GridPane();
        for(int row = 0; row < 10; row++){
           for(int col = 0; col < 10; col++){              
                Rectangle r = new Rectangle();
                r.setX(50);
                r.setY(50);
                r.setWidth(50);
                r.setHeight(50);
                if((col % 2 == 1 && row % 2 == 0) || (col% 2 == 0 && row % 2 == 1))
                    r.setFill(Color.WHITE);
                r.setOnMouseClicked(new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent t) {
                        r.setFill(Color.RED);
                    }
                });
                GridPane.setRowIndex(r, row);
                GridPane.setColumnIndex(r, col);
                grid.getChildren().addAll(r);
           }
        }   
        //Creating a Group object  
        Group root = new Group(grid); 
            
        //Creating a scene object 
        Scene scene = new Scene(root, 500, 500);  
        
        //Setting title to the Stage 
        stage.setTitle("Clickable Checkerboard"); 
            
        //Adding scene to the stage 
        stage.setScene(scene); 
        
        //Displaying the contents of the stage 
        stage.show(); 
    } 
    public static void main(String args[]) throws Exception
    {
       launch(args);
    }
}