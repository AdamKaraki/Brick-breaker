package brick;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Map {
	public int map[][];
	public int brickwidth,
	brickheight;

	public Map(int row, int col) {
		map = new int[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				map[i][j] = 1;
			}
		}

		brickwidth = 540 / col;
		brickheight = 150 / row;
	}
	public void draw(Graphics2D g) { // drawing the bricks
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] > 0) {
					g.setColor(Color.red);
					g.fillRect(j * brickwidth + 80, i * brickheight + 50, brickwidth, brickheight);

					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j * brickwidth + 80, i * brickheight + 50, brickwidth, brickheight);
				}
			}
		}
	}
	public void destroyBrick(int row, int col) {
		map[row][col] = 0;
	}
}
