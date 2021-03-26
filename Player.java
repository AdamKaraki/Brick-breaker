package brick;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Player extends JPanel implements KeyListener,ActionListener {

	public boolean play = false,
	EndGame = false;
	private int playerScore = 0;

	private int totalBricks = 24;

	private Timer timer;
	private int delay = 6;
    private int levels=0;


	private int playerX = 310;

	Random rand = new Random();
	private int ballPosX = 310 + rand.nextInt(130);
	private int ballPosY = 500;
	private int ballDirX = -1;
	private int ballDirY = -2;

	private Map map;

	public Player() {
		map = new Map(3, 8);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}

	public void paint(Graphics g) {
		// game background
		g.setColor(Color.WHITE);
		g.fillRect(-1, -1, 692, 592);
		
		g.setColor(Color.BLACK);
        g.setFont(new Font("Times", Font.BOLD, 6));
		g.drawString("Made by R.K 2020", 10, 10);

		// the score board
		g.setColor(Color.BLACK);
		g.setFont(new Font("Times", Font.BOLD, 20));
		g.drawString(playerScore+" points", 560, 30);

		//the paddle
		g.setColor(Color.BLUE);
		g.fillRect(playerX, 550, 100,25);

		//the ball
		g.setColor(Color.RED);
		g.fillOval(ballPosX, ballPosY, 20, 20);
		map.draw((Graphics2D) g);

		

		if (totalBricks <= 0) {
			play = false;
			ballDirX = 0;
			ballDirY = 0;
			playerX = 310;
			EndGame = true;

			g.setColor(Color.BLACK);
			g.setFont(new Font("Times", Font.BOLD, 25));
			g.drawString("You Won!, Score: " + playerScore+", Level : " +levels, 190, 300);

			g.setFont(new Font("Times", Font.BOLD, 20));
			g.drawString("Press ENTER to play again !", 230, 350);
			

		}

		if (ballPosY > 570) {
			play = false;
			ballDirX = 0;
			ballDirY = 0;
			playerX = 310;
			EndGame = true;
			playerScore=0;
			g.setColor(Color.BLACK);
			g.setFont(new Font("Times", Font.BOLD, 25));
			g.drawString("Game Over, Your Score: " + playerScore, 190, 300);

			g.setFont(new Font("Times", Font.BOLD, 20));
			g.drawString("Press ENTER to play again !", 230, 350);

		}

		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.restart();

		if (play) {
			if (new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
				ballDirY = -ballDirY;
			}

			All: for (int i = 0; i < map.map.length; i++) {
				for (int j = 0; j < map.map[0].length; j++) {
					if (map.map[i][j] > 0) {
						int brickX = j * map.brickwidth + 80;
						int brickY = i * map.brickheight + 50;
						int brickWidth = map.brickwidth;
						int brickHeight = map.brickheight;

						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 20, 20);
						Rectangle brickRect = rect;

						if (ballRect.intersects(brickRect)) {
							map.destroyBrick(i, j);
							totalBricks--;
							playerScore += 5;

							if (ballPosX + 19 <= brickRect.x || ballPosX + 1 >= brickRect.x + brickRect.width) {
								ballDirX = -ballDirX;
							} else {
								ballDirY = -ballDirY;
							}
							break All;
						}
			
					}
				}
			}

			ballPosX += ballDirX;
			ballPosY += ballDirY;
			if (ballPosX < 0) {
				ballDirX = -ballDirX;
			}
			if (ballPosY < 0) {
				ballDirY = -ballDirY;
			}
			if (ballPosX > 670) {
				ballDirX = -ballDirX;
			}
			if(totalBricks<=0) {
				levels++;


			}



		}

		repaint();

	}

	
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (playerX > 600) {
				playerX = 600;
			} else if (EndGame == false) {
				moveRight();
			}
			
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (playerX < 10) {
				playerX = 10;
			} else if (EndGame == false) {
				moveLeft();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!play) {
				play = true;
				ballPosX = 310 + rand.nextInt(80);
				ballPosY = 500;
				ballDirX = -1;
				ballDirY = -2;
				playerX = 310;

				totalBricks = 24;
				map = new Map(3, 8);
				EndGame = false;

				repaint();
			}
		}
	}
	public void moveRight() {
		play = true;
		if (playerX + 20 <= 600) {
			playerX += 20;
		}
	}
	public void moveLeft() {
		play = true;
		if (playerX - 20 >= 0) playerX -= 20;
	}@Override
	public void keyReleased(KeyEvent e) {}

}