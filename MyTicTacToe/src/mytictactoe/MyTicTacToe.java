
package mytictactoe;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author user2
 */

class MyTableModel extends DefaultTableModel{
    public MyTableModel(Object[][] data, String[] columns){
        super(data, columns);
    }
    
    @Override
    public boolean isCellEditable(int row, int column){
        return false;
    }
}

class Player{
    int Wins;
    int Losses;
    int Draws;
    String playerName;
    boolean isX = false;
    
    public Player(String playerName){
       this(playerName, 0, 0, 0);
    }
    
    public Player(String playerName, int Wins, int Losses, int Draws){
        this.Wins = Wins;
        this.Losses = Losses;
        this.Draws = Draws;
        this.playerName = playerName;
    }
    
    @Override
    public String toString(){
        return "[ playerName : " + playerName + ", wins : " + Wins + ", losses : " + Losses + ", draws : " + Draws + " ]";
    }
    
}

class TicTacToeFrame extends JFrame{
    JPanel container = new JPanel();
    JPanel gamePanel = new JPanel();
    JPanel scorePanel = new JPanel();
    static GameBox[] boxes = new GameBox[9];
    static JLabel playerOneTurn;
    static JLabel playerTwoTurn;
    static JTable table;
    static JLabel roundIndicator;
    static int roundCounter = 1;
    
// Add an indicator to show the players that are both X and O        
    
    public TicTacToeFrame(){
        super("TicTacToe");
        this.setSize(950, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        StartGame.Player1.isX = true;
        
        gamePanel.setPreferredSize(new Dimension(600, 600));
        gamePanel.setLayout(new GridLayout(3, 3));
        
        
        int i;
        for(i = 0; i < 9; i++){
            boxes[i] = new GameBox();
            gamePanel.add(boxes[i]);
            boxes[i].setBackground(new Color(255, 255, 255));
        }
        
        scorePanel.setPreferredSize(new Dimension(350, 600));
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        
        
        
        // Score Board        
        
        String[] columns = {"","Wins","Losses","Draws"};
        
        Object[][] data = {
            {
                StartGame.Player1.playerName, StartGame.Player1.Wins, StartGame.Player1.Losses, StartGame.Player1.Draws
            },
            {
                StartGame.Player2.playerName, StartGame.Player2.Wins, StartGame.Player2.Losses, StartGame.Player2.Draws
            }
        };
              
        MyTableModel tableModel = new MyTableModel(data, columns);
        table = new JTable(tableModel);
        
        JScrollPane board1 = new JScrollPane(table);
        JPanel board2 = new JPanel();
        JPanel board3 = new JPanel();
        JPanel board4 = new JPanel();
        
        //table.setFillsViewportHeight(true);
        table.setRowHeight(35);
        
        playerOneTurn = new JLabel(StartGame.Player1.playerName);
        playerTwoTurn = new JLabel(StartGame.Player2.playerName);
        playerOneTurn.setHorizontalAlignment(SwingConstants.CENTER);
        playerOneTurn.setForeground(Color.green);
        playerTwoTurn.setHorizontalAlignment(SwingConstants.CENTER);
        playerOneTurn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        playerTwoTurn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        
        roundIndicator = new JLabel("Round " + roundCounter);
        roundIndicator.setHorizontalAlignment(SwingConstants.CENTER);
        roundIndicator.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        
        
        JButton newgameBtn = new JButton("New Game");
        JButton instructionsBtn = new JButton("Instructions");
        JButton winPercentageBtn = new JButton("Check Win Percentage");
        JButton quitBtn = new JButton("Quit");
        
        newgameBtn.setPreferredSize(new Dimension(155, 35));
        instructionsBtn.setPreferredSize(new Dimension(155, 35));
        winPercentageBtn.setPreferredSize(new Dimension(155, 35));
        quitBtn.setPreferredSize(new Dimension(155, 35));
        
        newgameBtn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        instructionsBtn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        winPercentageBtn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        quitBtn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        
        
        
        board1.setPreferredSize(new Dimension(350, 80));   
        board1.setBackground(Color.white);
        board2.setBackground(Color.white);
        board2.setPreferredSize(new Dimension(350, 60));
        board2.setLayout(new GridLayout(1, 2));
        board2.add(playerOneTurn);
        board2.add(playerTwoTurn);
        board4.setLayout(new GridLayout(1, 1));
        board4.setPreferredSize(new Dimension(350, 50));
        board4.setBackground(Color.white);
        board4.add(roundIndicator);
        board3.add(newgameBtn);
        board3.add(instructionsBtn);
        board3.add(winPercentageBtn);
        board3.add(quitBtn);
        board3.setPreferredSize(new Dimension(350, 200));
        
        
        
        
        scorePanel.add(board1);
        scorePanel.add(board4);
        scorePanel.add(board2);
        scorePanel.add(board3);

        
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));        
        container.add(gamePanel);
        container.add(scorePanel);
        
           

        System.out.println(Arrays.deepToString(data));
        add(container);
        
        setVisible(true);
        JOptionPane.showMessageDialog(this, StartGame.Player1.playerName + " is X");
                
    }  
}

class GameBox extends JButton implements ActionListener{
    ImageIcon X, O;
    static int Counter;
    boolean Occupied;
    int Mark;
    
    public GameBox(){
        Counter = 0;
        Occupied = false;
        Mark  = -1;
        
        X = new ImageIcon(this.getClass().getResource("../images/close1.png"));
        O = new ImageIcon(this.getClass().getResource("../images/circle-ring.png"));
        
        this.addActionListener(this);
    }
    
    public static boolean checkForDraw(){
        GameBox[] gb = TicTacToeFrame.boxes;
        return gb[0].Mark != -1 && gb[1].Mark != -1 && gb[2].Mark != -1 && gb[3].Mark != -1 && gb[4].Mark != -1 && gb[5].Mark != -1
               && gb[6].Mark != -1 && gb[7].Mark != -1 && gb[8].Mark != -1;            
    }
    
    public static void checkForWin(){
        GameBox[] boxes = TicTacToeFrame.boxes;
        if(boxes[0].Mark == boxes[1].Mark && boxes[1].Mark == boxes[2].Mark && 
           boxes[0].Mark != -1)
        {
            boxes[0].setBackground(Color.green);
            boxes[1].setBackground(Color.green);
            boxes[2].setBackground(Color.green);
            
            endRound(0, 1, 2);
        }
        else if(boxes[3].Mark == boxes[4].Mark && boxes[4].Mark == boxes[5].Mark && 
           boxes[3].Mark != -1)
        {
            boxes[3].setBackground(Color.green);
            boxes[4].setBackground(Color.green);
            boxes[5].setBackground(Color.green);
            
            endRound(3, 4, 5);
        }
        else if(boxes[6].Mark == boxes[7].Mark && boxes[7].Mark == boxes[8].Mark && 
           boxes[6].Mark != -1)
        {
            boxes[6].setBackground(Color.green);
            boxes[7].setBackground(Color.green);
            boxes[8].setBackground(Color.green);
            
            endRound(6, 7, 8);
        }
        else if(boxes[0].Mark == boxes[3].Mark && boxes[3].Mark == boxes[6].Mark && 
           boxes[0].Mark != -1)
        {
            boxes[0].setBackground(Color.green);
            boxes[3].setBackground(Color.green);
            boxes[6].setBackground(Color.green);
            
            endRound(0, 3, 6);
        }
        else if(boxes[1].Mark == boxes[4].Mark && boxes[4].Mark == boxes[7].Mark && 
           boxes[1].Mark != -1)
        {
            boxes[1].setBackground(Color.green);
            boxes[4].setBackground(Color.green);
            boxes[7].setBackground(Color.green);
            
            endRound(1, 4, 7);
        }
        else if(boxes[2].Mark == boxes[5].Mark && boxes[5].Mark == boxes[8].Mark && 
           boxes[2].Mark != -1)
        {
            boxes[2].setBackground(Color.green);
            boxes[5].setBackground(Color.green);
            boxes[8].setBackground(Color.green);
            
            endRound(2, 5, 8);
        }
        else if(boxes[0].Mark == boxes[4].Mark && boxes[4].Mark == boxes[8].Mark && 
           boxes[0].Mark != -1)
        {
            boxes[0].setBackground(Color.green);
            boxes[4].setBackground(Color.green);
            boxes[8].setBackground(Color.green);
            
            endRound(0, 4, 8);
        }
        else if(boxes[2].Mark == boxes[4].Mark && boxes[4].Mark == boxes[6].Mark && 
           boxes[2].Mark != -1)
        {
            boxes[2].setBackground(Color.green);
            boxes[4].setBackground(Color.green);
            boxes[6].setBackground(Color.green);
            
            endRound(2, 4, 6);
        }
        else{
            endRound(2, 4, 6);
        }
    }
    
    public static void endRound(int box1, int box2, int box3){
        GameBox[] boxes = TicTacToeFrame.boxes;
        JTable table = TicTacToeFrame.table;
        Player Player1 = StartGame.Player1;
        Player Player2 = StartGame.Player2;
        
        // If Player 1 is X and Player 1 wins
        if(boxes[box1].Mark == boxes[box2].Mark && boxes[box2].Mark == boxes[box3].Mark && 
            boxes[box1].Mark == 1 && StartGame.Player1.isX){
            
            table.setValueAt((int) table.getValueAt(0, 1) + 1, 0, 1);
            Player1.Wins = (int) table.getValueAt(0, 1);
            table.setValueAt((int) table.getValueAt(1, 2) + 1, 1, 2);
            Player2.Losses = (int) table.getValueAt(1, 2);
            JOptionPane.showMessageDialog(null, StartGame.Player1.playerName + " wins");
            JOptionPane.showMessageDialog(null, StartGame.Player2.playerName + " is now X");
            TicTacToeFrame.playerTwoTurn.setForeground(Color.green);
            TicTacToeFrame.playerOneTurn.setForeground(Color.black);
            for(GameBox btn : boxes){
                btn.setIcon(null);
                btn.setBackground(Color.white);
                btn.Occupied = false;
                btn.Mark = -1;
            }
            GameBox.Counter = 0;
            StartGame.Player1.isX = !StartGame.Player1.isX;
            StartGame.Player2.isX = !StartGame.Player2.isX;
            TicTacToeFrame.roundIndicator.setText("Round " + ++TicTacToeFrame.roundCounter);
            System.out.println(Player1 + " " + Player2);

        }
        // If Player 1 is O and Player 1 Loses
        else if(boxes[box1].Mark == boxes[box2].Mark && boxes[box2].Mark == boxes[box3].Mark && 
                boxes[box1].Mark == 1 && !StartGame.Player1.isX){
            
            table.setValueAt((int) table.getValueAt(1, 1) + 1, 1, 1);
            Player2.Wins = (int) table.getValueAt(1, 1);
            table.setValueAt((int) table.getValueAt(0, 2) + 1, 0, 2);
            Player1.Losses = (int) table.getValueAt(0, 2);
            JOptionPane.showMessageDialog(null, StartGame.Player2.playerName + " wins");
            JOptionPane.showMessageDialog(null, StartGame.Player1.playerName + " is now X");
            TicTacToeFrame.playerTwoTurn.setForeground(Color.black);
            TicTacToeFrame.playerOneTurn.setForeground(Color.green);
            for(GameBox btn : boxes){
                btn.setIcon(null);
                btn.setBackground(Color.white);
                btn.Occupied = false;
                btn.Mark = -1;
            }
            GameBox.Counter = 0;
            StartGame.Player1.isX = !StartGame.Player1.isX;
            StartGame.Player2.isX = !StartGame.Player2.isX;
            TicTacToeFrame.roundIndicator.setText("Round " + ++TicTacToeFrame.roundCounter);
            System.out.println(Player1 + " " + Player2);

        }
        // If Player 1 is O and Player 1 Wins
        else if(boxes[box1].Mark == boxes[box2].Mark && boxes[box2].Mark == boxes[box3].Mark && 
                boxes[box1].Mark == 0 && !StartGame.Player1.isX){
            
            table.setValueAt((int) table.getValueAt(0, 1) + 1, 0, 1);
            Player1.Wins = (int) table.getValueAt(0, 1);
            table.setValueAt((int) table.getValueAt(1, 2) + 1, 1, 2);
            Player2.Losses = (int) table.getValueAt(1, 2);
            JOptionPane.showMessageDialog(null, StartGame.Player1.playerName + " wins");
            JOptionPane.showMessageDialog(null, StartGame.Player1.playerName + " is now X");
            TicTacToeFrame.playerTwoTurn.setForeground(Color.black);
            TicTacToeFrame.playerOneTurn.setForeground(Color.green);
            for(GameBox btn : boxes){
                btn.setIcon(null);
                btn.setBackground(Color.white);
                btn.Occupied = false;
                btn.Mark = -1;
            }
            GameBox.Counter = 0;
            StartGame.Player1.isX = !StartGame.Player1.isX;
            StartGame.Player2.isX = !StartGame.Player2.isX;
            TicTacToeFrame.roundIndicator.setText("Round " + ++TicTacToeFrame.roundCounter);
            System.out.println(Player1 + " " + Player2);

        }
        // If Player 1 is X and Player 1 Loses
        else if(boxes[box1].Mark == boxes[box2].Mark && boxes[box2].Mark == boxes[box3].Mark && 
                boxes[box1].Mark == 0 && StartGame.Player1.isX){
            
            table.setValueAt((int) table.getValueAt(1, 1) + 1, 1, 1);
            Player2.Wins = (int) table.getValueAt(1, 1);
            table.setValueAt((int) table.getValueAt(0, 2) + 1, 0, 2);
            Player1.Losses = (int) table.getValueAt(0, 2);
            JOptionPane.showMessageDialog(null, StartGame.Player2.playerName + " wins");
            JOptionPane.showMessageDialog(null, StartGame.Player2.playerName + " is now X");
            TicTacToeFrame.playerTwoTurn.setForeground(Color.green);
            TicTacToeFrame.playerOneTurn.setForeground(Color.black);
            for(GameBox btn : boxes){
                btn.setIcon(null);
                btn.setBackground(Color.white);
                btn.Occupied = false;
                btn.Mark = -1;
            }
            GameBox.Counter = 0;
            StartGame.Player1.isX = !StartGame.Player1.isX;
            StartGame.Player2.isX = !StartGame.Player2.isX;
            TicTacToeFrame.roundIndicator.setText("Round " + ++TicTacToeFrame.roundCounter);
            System.out.println(Player1 + " " + Player2);

        }
        else if(checkForDraw() && !StartGame.Player1.isX){
            table.setValueAt((int) table.getValueAt(0, 3) + 1, 0, 3);
            Player1.Draws = (int) table.getValueAt(0, 3);
            table.setValueAt((int) table.getValueAt(1, 3) + 1, 1, 3);
            Player2.Draws = (int) table.getValueAt(1, 3);
            JOptionPane.showMessageDialog(null, "STALEMATE!!!");
            JOptionPane.showMessageDialog(null, StartGame.Player1.playerName + " is now X");
            TicTacToeFrame.playerTwoTurn.setForeground(Color.black);
            TicTacToeFrame.playerOneTurn.setForeground(Color.green);
            for(GameBox btn : boxes){
                btn.setIcon(null);
                btn.setBackground(Color.white);
                btn.Occupied = false;
                btn.Mark = -1;
            }
            GameBox.Counter = 0;
            StartGame.Player1.isX = !StartGame.Player1.isX;
            StartGame.Player2.isX = !StartGame.Player2.isX;
            TicTacToeFrame.roundIndicator.setText("Round " + ++TicTacToeFrame.roundCounter);
            System.out.println(Player1 + " " + Player2);
        }
        else if(checkForDraw() && StartGame.Player1.isX){
            table.setValueAt((int) table.getValueAt(0, 3) + 1, 0, 3);
            Player1.Draws = (int) table.getValueAt(0, 3);
            table.setValueAt((int) table.getValueAt(1, 3) + 1, 1, 3);
            Player2.Draws = (int) table.getValueAt(1, 3);
            JOptionPane.showMessageDialog(null, "STALEMATE!!!");
            JOptionPane.showMessageDialog(null, StartGame.Player2.playerName + " is now X");
            TicTacToeFrame.playerTwoTurn.setForeground(Color.green);
            TicTacToeFrame.playerOneTurn.setForeground(Color.black);
            for(GameBox btn : boxes){
                btn.setIcon(null);
                btn.setBackground(Color.white);
                btn.Occupied = false;
                btn.Mark = -1;
            }
            GameBox.Counter = 0;
            StartGame.Player1.isX = !StartGame.Player1.isX;
            StartGame.Player2.isX = !StartGame.Player2.isX;
            TicTacToeFrame.roundIndicator.setText("Round " + ++TicTacToeFrame.roundCounter);
            System.out.println(Player1 + " " + Player2);
        }else{
            
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(Counter == 0 && !Occupied){
            setIcon(X);
            Counter++;
            Occupied = true;
            Mark = 1;
            if(StartGame.Player1.isX){
                TicTacToeFrame.playerTwoTurn.setForeground(Color.green);
                TicTacToeFrame.playerOneTurn.setForeground(Color.black);
            }
            else{
                TicTacToeFrame.playerTwoTurn.setForeground(Color.black);
                TicTacToeFrame.playerOneTurn.setForeground(Color.green);
            }
        }
        else if(Counter > 0 && !Occupied){
            setIcon(O);
            Counter = 0;
            Occupied = true;
            Mark = 0;
            if(StartGame.Player1.isX){
                TicTacToeFrame.playerTwoTurn.setForeground(Color.black);
                TicTacToeFrame.playerOneTurn.setForeground(Color.green);
            }
            else{
                TicTacToeFrame.playerTwoTurn.setForeground(Color.green);
                TicTacToeFrame.playerOneTurn.setForeground(Color.black);
            }
            
        }
        
        checkForWin();
    }   
}


