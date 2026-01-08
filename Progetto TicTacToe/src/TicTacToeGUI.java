import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame implements ActionListener {

    private JButton[][] buttons; // Array di bottoni per rappresentare la griglia
    private Game game; // Riferimento all'oggetto Game (il cervello logico)
    private JLabel statusLabel; // Etichetta per mostrare il turno o il risultato

    // Costruttore della GUI
    public TicTacToeGUI(Game game) {
        this.game = game;
        
        setTitle("Tris Minimax AI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Chiude l'applicazione quando si chiude la finestra
        setSize(400, 450); // Dimensioni della finestra
        setLayout(new BorderLayout()); // Layout principale (Nord, Sud, Est, Ovest, Centro)

        // 1. Creazione della griglia di bottoni (al centro)
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(3, 3)); // Layout 3x3 per i bottoni
        buttons = new JButton[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton btn = new JButton("");
                btn.setFont(new Font("Arial", Font.BOLD, 80)); // Grandezza del carattere per X/O
                btn.setFocusable(false); // Estetico, rimuove il focus
                btn.addActionListener(this); // Aggiunge l'ascoltatore di eventi (cioè la classe stessa)
                btn.setActionCommand(i + "," + j); // Assegna un comando che contiene le coordinate
                buttons[i][j] = btn;
                gridPanel.add(btn);
            }
        }
        add(gridPanel, BorderLayout.CENTER);

        // 2. Creazione dell'etichetta di stato (in basso)
        statusLabel = new JLabel("Inizio partita: tocca a X", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        add(statusLabel, BorderLayout.SOUTH);

        setVisible(true); // Rende la finestra visibile
    }

    // Metodo per aggiornare l'etichetta di stato
    public void updateStatus(String message) {
        statusLabel.setText(message);
    }

    // Metodo chiamato quando la logica di gioco ha aggiornato la Board
    public void redrawBoard() {
        Board currentBoard = game.getBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char cellValue = currentBoard.getCell(i, j);
                String text = (cellValue == ' ') ? "" : String.valueOf(cellValue);
                buttons[i][j].setText(text);
                
                // Disabilita il bottone se la cella è occupata
                buttons[i][j].setEnabled(cellValue == ' '); 
                
                // Colora X (rosso) e O (blu) per distinguerli
                if (cellValue == 'X') {
                    buttons[i][j].setForeground(Color.RED);
                } else if (cellValue == 'O') {
                    buttons[i][j].setForeground(Color.BLUE);
                }
            }
        }
    }

    // Metodo chiamato quando l'utente clicca su un bottone
    @Override
    public void actionPerformed(ActionEvent e) {
        // Estrai le coordinate dalla stringa di comando (es. "1,2")
        String command = e.getActionCommand();
        String[] parts = command.split(",");
        int r = Integer.parseInt(parts[0]);
        int c = Integer.parseInt(parts[1]);

        // Se la partita non è finita, passa la mossa al cervello logico
        if (!game.isGameOver()) {
            game.handleHumanMove(r, c);
        }
    }

    // Metodo per disabilitare tutti i bottoni a fine partita
    public void disableAllButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }
}