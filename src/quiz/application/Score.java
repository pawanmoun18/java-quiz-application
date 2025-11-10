package quiz.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Score extends JFrame implements ActionListener{
    JButton playAgain;
    String name;
    
    Score(String name, int score){
        this.name = name;
        setBounds(400, 150, 750, 550);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/score.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 200, 300, 250);
        add(image);
        
        JLabel heading = new JLabel("Thank you " + name + " for playing Simple Minds!");
        heading.setBounds(45, 30, 700, 30);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(heading);
        
        JLabel scores = new JLabel("Your score is: " + score + "/10");
        scores.setBounds(350, 200, 300, 30);
        scores.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(scores);
        
        playAgain = new JButton("Play Again");
        playAgain.setBounds(380, 270, 150, 40);
        playAgain.setBackground(new Color(30, 144, 254));
        playAgain.setForeground(Color.WHITE);
        playAgain.addActionListener(this);
        add(playAgain);
        
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()== playAgain){
            setVisible(false);
            Quiz.count = 0;
            Quiz.score = 0;
            new Quiz(name);
        }
    }
    
    public static void main(String[] args){
        new Score("User", 0);
    }
}
