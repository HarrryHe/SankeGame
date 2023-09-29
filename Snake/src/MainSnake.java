import javax.swing.*;

public class MainSnake {
    public static void main(String[] args) {
        //Create a jframe for 900*700 background window
        JFrame frame = new JFrame();
        frame.setBounds(10,10,900,720);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new MainPanel());
        frame.setVisible(true);


    }
}
