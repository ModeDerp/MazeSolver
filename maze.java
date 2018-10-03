package maze;
import java.io.File;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class maze extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {

		Map map = MapInterpreter.interpretMap(new File("map.txt"));
		Scene scene = new Scene(map,map.getWidth(),map.getHeight());

		primaryStage.setScene(scene);
		primaryStage.show();

		MapSolver.solveMap(map);

		AnimationTimer at = new AnimationTimer() {

			private long timer = 0;

			int i = 0;

			@Override
			public void handle(long now) {

				if(now - timer >= 5_000_000_0) {

					if(i >= MapSolver.visited.size()) {
						this.stop();
						return;
					}

					String[] split = MapSolver.visited.get(i).split(",",2);

					int x = Integer.parseInt(split[0]);
					int y = Integer.parseInt(split[1]);

					Circle cir = new Circle(Block.SIZE/2-5);
					cir.setFill(Color.CYAN);
					cir.setTranslateX((x*Block.SIZE)+Block.SIZE/2);
					cir.setTranslateY((y*Block.SIZE)+Block.SIZE/2);
					map.getChildren().add(cir);	
					i++;
					timer = now;
				}
			}
		};
		
		at.start();
	}

	public static void main(String[] args) {
		launch();
	}
}
