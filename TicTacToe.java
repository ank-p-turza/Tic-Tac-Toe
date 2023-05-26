import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TicTacToe extends JFrame{
    int click_counter=0;
    ArrayList<Integer> list = new ArrayList<>();
    JPanel contentPanel;
    JButton [][]buttons;
    int [][]arr;
    Font ft_bold = new Font("Times New Roman", Font.BOLD, 20);


    public TicTacToe(){
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(335, 360);
        setResizable(false);

        arr = new int [3][3];

        contentPanel = new JPanel();
        contentPanel.setPreferredSize(new Dimension(335, 360));
        contentPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        
        buttons = new JButton[3][3];

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                arr[i][j] = 10;
                buttons[i][j] = new JButton(" ");
                buttons[i][j].setPreferredSize(new Dimension(100, 100));
                buttons[i][j].setFocusable(false);
                buttons[i][j].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                buttons[i][j].setFont(ft_bold);
                contentPanel.add(buttons[i][j]);
                action(buttons[i][j], i, j);
            }
        }

        add(contentPanel);

        setVisible(true); 
    }


    public void mechanics(){
        int m_diagonal=0;
        int l_diagonal=0;
        for(int i=0; i<3; i++){
            int sum_row = 0;
            int sum_column =0;
            for(int j=0; j<3; j++){
                sum_row += arr[i][j];
                sum_column+=arr[j][i];
                if(i == j){
                    m_diagonal +=arr[i][j];
                }
                if(i+j==2){
                    l_diagonal += arr[i][j];
                }
            }
            list.add(sum_row);
            list.add(sum_column);
            if(sum_row == 6 || sum_column==6){
                showMessage("THE WINNER IS \"O\"", 1);
                return;
            }
            if(sum_row == 9 || sum_column == 9){
                showMessage("THE WINNER IS \"X\"", 1);
                return;
            }
        }
        list.add(m_diagonal);
        list.add(l_diagonal);
        if(m_diagonal == 6|| l_diagonal ==6){
            showMessage("THE WINNER IS \"O\"", 1);
            return;
        }
        if(m_diagonal == 9|| l_diagonal == 9){
            showMessage("THE WINNER IS \"X\"", 1);
            return;
        }
        if(!winnable())showMessage("THIS MATCH IS A DRAW!", 0);
        list.clear();
    }


    public boolean winnable(){
        boolean x= false;
        if(list.contains(30)) x = true;
        if(list.contains(23)) x= true;
        if(list.contains(22)) x= true;
        if(list.contains(14)) x= true;
        if(list.contains(16)) x= true;
        return x;
    }

    public void showMessage(String str, int x){
        JFrame frame = new JFrame();
        JLabel textLabel; 
        if(x==1){
            frame.setTitle("Winner");
        }
        else{
            frame.setTitle("Draw");
        }
        frame.setSize(335, 360);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        textLabel = new JLabel(str, null, JLabel.CENTER);
        textLabel.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 25));
        textLabel.setForeground(Color.red);
        textLabel.setBounds(0, 50, 335, 50);
        frame.add(textLabel);


        frame.setVisible(true);
        setFocusable(false);
    }


    public void action(JButton jb, int i, int j){
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(click_counter%2 ==0){
                    jb.setText("O");
                    jb.setEnabled(false);
                    jb.setBackground(Color.black);
                    jb.setForeground(Color.white);
                    click_counter++;
                    arr[i][j] =2;
                    mechanics();
                }
                else{
                    jb.setText("X");
                    jb.setEnabled(false);
                    jb.setBackground(Color.white);
                    jb.setForeground(Color.black);
                    click_counter++;
                    arr[i][j] =3;
                    mechanics();
                }
            }
        });
    }
    public static void main(String[] args) {
        new TicTacToe();
    }
}

