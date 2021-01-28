package view;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// map function in order to see the qoordinates of the plane trip.
public class MapDisplayer extends Canvas {
	int[][] mapData;
	
	
    double mn = Double.MAX_VALUE;
    double mx = 0;

    // setting the map data for proccesing
    public void setMapData(int[][] mapData) {
        this.mapData = mapData;

        for(int i = 0; i < mapData.length; i++) {
            for (int j = 0; j < mapData[i].length; j++) {
                if(mn > mapData[i][j]) { mn = mapData[i][j]; }
                if(mx < mapData[i][j]) { mx = mapData[i][j]; }
            }
        }
		
                
				double new_min = 0;
        double new_max = 255;
        
        for (int i = 0; i < mapData.length; i++) {
			
            for (int j = 0; j < mapData[i].length; j++) {
                mapData[i][j] = (int)((mapData[i][j] - mn) / (mx - mn) * (new_max - new_min) + new_min);
            }
        }
        
        redraw();
    }

    // Drawing the output on the canvas.
    public void redraw() {
		
        if(mapData != null) {
            double H = getHeight();
            double W = getWidth();
			
            double h = H / mapData.length;
            double w = W / mapData[0].length;
            
            GraphicsContext gc = getGraphicsContext2D();

            for (int i = 0; i < mapData.length; i++) {
                for (int j = 0; j < mapData[i].length; j++) {
                    int tmp = mapData[i][j];
					
                    gc.setFill(Color.rgb((255 - tmp), (0 + tmp), 0));
                    gc.fillRect((j * w), (i * h), w, h);
                }
            }
        }
    }
	
}

