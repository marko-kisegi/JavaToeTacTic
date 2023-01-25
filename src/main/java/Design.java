import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



class Design implements ActionListener {
    static int wins = 0 ;
    static int losses = 0;
    static int draws = 0;
    JFrame main;

    // Panel for keeping score
    JPanel topPanel;
    static JLabel currentPlayer;
    JPanel playg;
    JButton[] buttons = new JButton[9];;

    JPanel bottomPanel;
    JButton start;
    JButton score;
    Design(){
        // main fsetup
        main = new JFrame("Toe Tac Tic");
        main.setBackground(Color.black);
        main.setVisible(true);
        BorderLayout borderLayout = new BorderLayout();
        main.setLayout(borderLayout);
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Top panel
        JTextPane textChange = new JTextPane();
        JPanel topPanel = new JPanel();
        JLabel currentPlayer = new JLabel();
        textChange.setText("Press Start to begin!");
        // middle panel
        topPanel.add(textChange);
        topPanel.add(currentPlayer);
        GridLayout grid = new GridLayout(3, 3);

        playg = new JPanel();
        playg.setLayout(grid);
        for (int i = 0 ; i < 9 ; i++){
            buttons[i] = new JButton();
            buttons[i].setText( String.valueOf(i+1));
            buttons[i].addActionListener(this::actionPerformed);
            playg.add(buttons[i]);
        }
        // Bottom panel

        bottomPanel = new JPanel();
        start = new JButton("start");
        score = new JButton("score");
        start.addActionListener(e -> add2(buttons, textChange));
        score.addActionListener(e -> add1(textChange));
        bottomPanel.add(start);
        bottomPanel.add(score);

        // Ending and adding all panels

        main.add(topPanel, BorderLayout.NORTH);
        main.add(playg, BorderLayout.CENTER);
        main.add(bottomPanel, BorderLayout.SOUTH);
        main.setSize(1000, 1000);
        main.pack();
    }

    static void add1(JTextPane textPane){
        textPane.setText("Losses: " + losses + " Draws: "  + draws);
        System.out.println(count);
    }
    static int count = 0;
    static void add2(JButton[] buttons, JTextPane textPane){
        for (int i = 0 ; i < 9 ; i++){
            buttons[i].setText(String.valueOf(i+1));
            buttons[i].setBackground(Color.white);
            buttons[i].setEnabled(true);
        }
        textPane.setText("Start:");
        count = 0;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        count++;
        for (int i = 0 ; i < 9 ; i++){
            if (buttons[i] == e.getSource()){
                String s = (count%2 == 0) ? "X" : "O";
                setPieces(buttons[i], s);
            }
        }
        if(count > 3){
            checkScore(buttons);
        }

    }

    private void checkScore(JButton[] buttons) {
        // 0 1 2
        // 3 4 5
        // 6 7 8
        String s = (count%2 == 0) ? "X" : "O";
        boolean win = false;
        for (int i = 0 ; i < 3 ; i++){
            //check columns
            if(buttons[i].getText().equals(s) &&
            buttons[i+3].getText().equals(s) &&
            buttons[i+6].getText().equals(s))
            {
                win = true;
                break;
            }
            int j = i*3 ;
            // check rows
            if(buttons[i*3 + 0].getText().equals(s) &&
                    buttons[i*3 + 1].getText().equals(s) &&
                    buttons[i+3 + 2].getText().equals(s))
            {
                win = true;
                break;
            }
        }
        if (buttons[0].getText().equals(s) &&
        buttons[4].getText().equals(s) &&
        buttons[8].getText().equals(s)){
            win = true;
        }
        if (buttons[2].getText().equals(s) &&
                buttons[4].getText().equals(s) &&
                buttons[6].getText().equals(s)){
            win = true;
        }
        if (win) {
            System.out.println( s + " wins");
            wins++;
            for (int i = 0 ; i < 9 ; i++){
                buttons[i].setEnabled(false);
            }
        }
        if (!win && count == 9){
            draws++;
            for (int i = 0 ; i < 9 ; i++){
                buttons[i].setEnabled(false);
            }
        }
    }

    public void setPieces(JButton button, String s){
        button.setBackground( count % 2 == 0  ? Color.cyan : Color.BLUE);
        button.setText(s);
        button.setForeground(Color.black);
        button.setEnabled(false);
    }
}
