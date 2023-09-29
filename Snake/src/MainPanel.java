import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class MainPanel extends JPanel implements KeyListener, ActionListener {
    ImageIcon title = new ImageIcon("title.png");
    ImageIcon body = new ImageIcon("body.png");
    ImageIcon up = new ImageIcon("up.png");
    ImageIcon down = new ImageIcon("down.png");
    ImageIcon left = new ImageIcon("left.png");
    ImageIcon right = new ImageIcon("right.png");
    ImageIcon food = new ImageIcon("food.png");
    int length;
    int score =0;
    int[] snake_x = new int[816];
    int[] snake_y = new int[816];
    String direction = "R";
    boolean isStarted = false;
    boolean isFailed = false;
    Timer timer = new Timer(100, this);
    int foodx, foody;
    Random rand = new Random();
    //constructor
    public MainPanel(){
        this.initSnake();
        this.setFocusable(true);
        this.addKeyListener(this);
        this.timer.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(Color.white);
        title.paintIcon(this,g,25,11);
        g.fillRect(25,75,850,600);
        g.drawString("Length: "+ length, 750, 35);
        g.drawString("Score: "+ score, 750,50);


        if(direction.equals("R")){
            right.paintIcon(this, g, snake_x[0], snake_y[0]);
        }

        else if(direction.equals("L")){
            left.paintIcon(this, g, snake_x[0], snake_y[0]);
        }

        else if(direction.equals("D")){
            down.paintIcon(this, g, snake_x[0], snake_y[0]);
        }

        else if(direction.equals("U")){
            up.paintIcon(this, g, snake_x[0], snake_y[0]);
        }

        for(int i=1; i<length; i++){
            body.paintIcon(this, g, snake_x[i], snake_y[i]);
            if(snake_x[i] == snake_x[0] && snake_y[i] == snake_y[0]){
                isFailed = true;
            }
        }

        food.paintIcon(this, g, foodx, foody);

        if(isStarted == false){
            g.setFont(new Font("arial", Font.BOLD,35));
            g.setColor(Color.white);
            g.drawString("Press Space to Start",275,350);
        }

        if(isFailed == true){
            g.setColor(Color.BLUE);
            g.setFont(new Font("arial", Font.BOLD,35));
            g.drawString("Failed: Press Space to Start",250,350);
            g.drawString("Your Score is: " + score, 325, 425);
        }

    }

    public void initSnake(){
        score = 0;
        direction = "R";
        length = 3;
        snake_x[0] = 100;
        snake_y[0] = 100;
        snake_x[1] = 75;
        snake_y[1] = 100;
        snake_x[2] = 50;
        snake_y[2] = 100;
        foodx = 25 + 25 * rand.nextInt(34);
        foody = 75 + 25 * rand.nextInt(24);
    }

    public void keyTyped(KeyEvent e){}

    public void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_SPACE){
            if(isFailed == true){
                isFailed = false;
                initSnake();
            }
            else{
                isStarted = !isStarted;
            }
            repaint();
        }

        if(keyCode == KeyEvent.VK_W){
            direction = "U";
        }
        else if(keyCode == KeyEvent.VK_S){
            direction = "D";
        }
        else if(keyCode == KeyEvent.VK_A){
            direction = "L";
        }
        else if(keyCode == KeyEvent.VK_D){
            direction = "R";
        }
    }

    public void keyReleased(KeyEvent e){}

    public void actionPerformed(ActionEvent e){
        if(isStarted == true && isFailed == false) {
            for(int i=length-1; i>0; i--){
                snake_x[i] = snake_x[i-1];
                snake_y[i] = snake_y[i-1];
            }

            if (direction.equals("U")) {
                snake_y[0] = snake_y[0] - 25;
                if (snake_y[0] < 75) {
                    snake_y[0] = 650;
                }
            } else if (direction.equals("D")) {
                snake_y[0] = snake_y[0] + 25;
                if(snake_y[0] > 650) {
                    snake_y[0] = 75;
                }

            } else if(direction.equals("L")) {
                snake_x[0] = snake_x[0] - 25;
                if (snake_x[0] < 25) {
                    snake_x[0] = 850;
                }

            } else if(direction.equals("R")) {
                snake_x[0] = snake_x[0] + 25;
                if (snake_x[0] > 850) {
                    snake_x[0] = 25;
                }
            }
            else if(direction.equals("M")) {
            }

            if(snake_x[0] == foodx && snake_y[0] == foody){
                length++;
                snake_x[length-1] = snake_x[length-2];
                snake_y[length-1] = snake_y[length-2];
                score += 5;
                foodx = 25 + 25 * rand.nextInt(34);
                foody = 75 + 25 * rand.nextInt(24);
            }

            repaint();
            //timer.start();
        }
    }


}
