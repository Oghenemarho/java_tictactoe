
package mytictactoe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author user2
 */
class StartGame extends JFrame implements ActionListener{
    JButton startBtn;
    JTextField playerName2;
    JTextField playerName1;
    static Player Player1;
    static Player Player2;
    
    public StartGame(){
        super("Start");
        setSize(450, 350);
        //setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel bgCon = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        bgCon.setLayout(new GridBagLayout());
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        bgCon.add(new JLabel("Player 1:"), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipadx = 75;
        gbc.ipady = 20;
        gbc.gridwidth = 2;
        
        playerName1 = new JTextField();
        //playerName1.setPreferredSize(new Dimension(180, 40));
        bgCon.add(playerName1, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        bgCon.add(new JLabel("Player 2:"), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.ipadx = 75;
        gbc.ipady = 20;
        gbc.gridwidth = 2;
        
        playerName2 = new JTextField();
        //playerName2.setPreferredSize(new Dimension(180, 40));
        bgCon.add(playerName2, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        
        startBtn = new JButton("Start Game");
        startBtn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        startBtn.setPreferredSize(new Dimension(42, 25));
        startBtn.setBackground(Color.white);
        bgCon.add(startBtn, gbc);
        startBtn.addActionListener(this);

        
                    
        
        add(bgCon);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String player1 = playerName1.getText();
        String player2 = playerName2.getText();
        
        Player1 = new Player(player1);
        Player2 = new Player(player2);
        
        TicTacToeFrame tf = new TicTacToeFrame();
        tf.setVisible(true);
        setVisible(false);
                
    }
    
}

public class Main {

    public static void main(String[] args) {
        // TODO code application logic here
        new StartGame();
    }
    
}


