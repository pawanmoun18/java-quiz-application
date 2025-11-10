package quiz.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Quiz extends JFrame implements ActionListener {

    String questions[][] = new String[10][5];
    String answers[][] = new String[10][2];
    String useranswers[][] = new String[10][1];

    JLabel qno, question;
    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup groupoptions;
    JButton next, submit;
    public static int timer = 15;
    public static int ans_given = 0;
    public static int count = 0;
    public static int score = 0;
    String name;

    Quiz(String name) {
        this.name = name;
        setBounds(250, 100, 1100, 600);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        qno = new JLabel();
        qno.setBounds(100, 100, 50, 30);
        qno.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(qno);

        question = new JLabel();
        question.setBounds(150, 100, 900, 30);
        question.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(question);

        questions[0][0] = "What is the size of int in Java?";
        questions[0][1] = "16 bits";
        questions[0][2] = "32 bits";
        questions[0][3] = "64 bits";
        questions[0][4] = "Depends on the system";

        questions[1][0] = "Which keyword is used to inherit a class in Java?";
        questions[1][1] = "implement";
        questions[1][2] = "extends";
        questions[1][3] = "super";
        questions[1][4] = "this";

        questions[2][0] = "Which of the following is not a primitive data type?";
        questions[2][1] = "char";
        questions[2][2] = "String";
        questions[2][3] = "boolean";
        questions[2][4] = "int";

        questions[3][0] = "Which method is the entry point for a Java program?";
        questions[3][1] = "start()";
        questions[3][2] = "main()";
        questions[3][3] = "init()";
        questions[3][4] = "system()";

        questions[4][0] = "Which OOP concept binds data and code together?";
        questions[4][1] = "Polymorphism";
        questions[4][2] = "Encapsulation";
        questions[4][3] = "Abstraction";
        questions[4][4] = "Inheritance";

        questions[5][0] = "Which of the following is used to define a constant in Java?";
        questions[5][1] = "static";
        questions[5][2] = "final";
        questions[5][3] = "constant";
        questions[5][4] = "define";

        questions[6][0] = "Which of these is a type of exception in Java?";
        questions[6][1] = "Syntax Error";
        questions[6][2] = "Runtime Error";
        questions[6][3] = "Logical Error";
        questions[6][4] = "None";

        questions[7][0] = "Which operator is used for comparison?";
        questions[7][1] = "=";
        questions[7][2] = "==";
        questions[7][3] = "equals()";
        questions[7][4] = "!=";

        questions[8][0] = "Which of these does not support multiple inheritance?";
        questions[8][1] = "Class";
        questions[8][2] = "Interface";
        questions[8][3] = "Object";
        questions[8][4] = "Package";

        questions[9][0] = "Which keyword is used to prevent inheritance?";
        questions[9][1] = "static";
        questions[9][2] = "super";
        questions[9][3] = "final";
        questions[9][4] = "private";

        answers[0][1] = "32 bits";
        answers[1][1] = "extends";
        answers[2][1] = "String";
        answers[3][1] = "main()";
        answers[4][1] = "Encapsulation";
        answers[5][1] = "final";
        answers[6][1] = "Runtime Error";
        answers[7][1] = "==";
        answers[8][1] = "Class";
        answers[9][1] = "final";

        opt1 = new JRadioButton();
        opt1.setBounds(150, 200, 700, 30);
        opt1.setBackground(Color.WHITE);
        add(opt1);

        opt2 = new JRadioButton();
        opt2.setBounds(150, 240, 700, 30);
        opt2.setBackground(Color.WHITE);
        add(opt2);

        opt3 = new JRadioButton();
        opt3.setBounds(150, 280, 700, 30);
        opt3.setBackground(Color.WHITE);
        add(opt3);

        opt4 = new JRadioButton();
        opt4.setBounds(150, 320, 700, 30);
        opt4.setBackground(Color.WHITE);
        add(opt4);

        groupoptions = new ButtonGroup();
        groupoptions.add(opt1);
        groupoptions.add(opt2);
        groupoptions.add(opt3);
        groupoptions.add(opt4);

        next = new JButton("Next");
        next.setBounds(800, 400, 200, 40);
        next.setBackground(new Color(30, 144, 254));
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        add(next);

        submit = new JButton("Submit");
        submit.setBounds(800, 460, 200, 40);
        submit.setBackground(new Color(30, 144, 254));
        submit.setForeground(Color.WHITE);
        submit.setEnabled(false);
        submit.addActionListener(this);
        add(submit);

        start(count);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            ans_given = 1;
            storeAnswer();
            count++;
            if (count == 9) {
                next.setEnabled(false);
                submit.setEnabled(true);
            }
            start(count);
        } else if (ae.getSource() == submit) {
            storeAnswer();
            calculateScore();
            setVisible(false);
            new Score(name, score);
        }
    }

    public void storeAnswer() {
        if (groupoptions.getSelection() != null) {
            useranswers[count][0] = groupoptions.getSelection().getActionCommand();
        } else {
            useranswers[count][0] = "";
        }
    }

    public void start(int c) {
        groupoptions.clearSelection();
        timer = 15;

        qno.setText("" + (c + 1) + ". ");
        question.setText(questions[c][0]);

        opt1.setText(questions[c][1]);
        opt1.setActionCommand(questions[c][1]);

        opt2.setText(questions[c][2]);
        opt2.setActionCommand(questions[c][2]);

        opt3.setText(questions[c][3]);
        opt3.setActionCommand(questions[c][3]);

        opt4.setText(questions[c][4]);
        opt4.setActionCommand(questions[c][4]);

        repaint();
    }

    public void calculateScore() {
        score = 0;
        for (int i = 0; i < useranswers.length; i++) {
            if (useranswers[i][0].equals(answers[i][1])) {
                score++;
            }
        }
    }

    public static void main(String[] args) {
        new Quiz("User");
    }
}
