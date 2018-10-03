package maze;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Path extends Block{
	
	public Path(){
		Rectangle bg = new Rectangle(Block.SIZE,Block.SIZE);
		bg.setFill(Color.YELLOW);
		this.getChildren().add(bg);
	}

}
