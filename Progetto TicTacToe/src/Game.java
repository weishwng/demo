import javax.swing.SwingUtilities; // Necessario per lavorare con la GUI

public class Game {
    private Board board;
    private char currentPlayer;
    private Tree tree; // Il cervello AI
    private TicTacToeGUI gui; // L'interfaccia grafica
    private boolean gameOver; // Stato della partita

    // Costruttore
    public Game() {
        this.board = new Board();
        this.currentPlayer = 'X';
        this.gameOver = false;
        
        // La creazione dell'Albero (il calcolo Minimax) avviene nel costruttore
        // Qui viene fatto tutto il lavoro pesante di calcolo iniziale!
        this.tree = new Tree(board, currentPlayer);
    }

    // Metodi pubblici per l'accesso dalla GUI
    public Board getBoard() { return board; }
    public boolean isGameOver() { return gameOver; }

    // *NUOVO* Metodo per avviare la partita con la GUI
    public void startGame() {
        // La GUI viene creata sulla "Event Dispatch Thread" di Swing
        SwingUtilities.invokeLater(() -> {
            gui = new TicTacToeGUI(this);
            gui.redrawBoard();
            gui.updateStatus("Inizio partita: tocca a X");
        });
    }

    // *NUOVO* Metodo chiamato dalla GUI dopo che l'utente (X) ha cliccato
    public void handleHumanMove(int r, int c) {
        if (gameOver || currentPlayer != 'X') return; // Se non è il turno di X o la partita è finita, esci

        Move humanMove = new Move(r, c);
        
        // 1. Applica la mossa dell'utente
        if (board.applyMove(humanMove, 'X')) {
            // 2. Aggiorna l'albero per "scorrere" nel nuovo stato
            tree.changeRoot(humanMove);
            
            // 3. Controlla lo stato dopo la mossa di X
            checkGameStatus();
            if (gameOver) return;

            // 4. Cambia giocatore e passa al turno del Bot
            currentPlayer = 'O';
            gui.updateStatus("Tocca a O (AI)...");
            
            // 5. Avvia il turno del Bot con un piccolo ritardo per rendere visibile la mossa di X
            // Usiamo un Timer per evitare di bloccare la GUI
            Timer timer = new Timer(500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playBotMove();
                    ((Timer)e.getSource()).stop(); // Ferma il timer dopo un'esecuzione
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    // *NUOVO* Metodo per eseguire la mossa dell'AI (Bot O)
    private void playBotMove() {
        if (gameOver || currentPlayer != 'O') return;

        // 1. Il Bot trova la mossa migliore (che è già pre-calcolata nell'albero)
        Move botMove = tree.bestMove();
        
        if (botMove != null) {
            // 2. Applica la mossa
            board.applyMove(botMove, 'O');
            
            // 3. Aggiorna l'albero per "scorrere" nel nuovo stato
            tree.changeRoot(botMove);
            
            // 4. Aggiorna la GUI
            gui.redrawBoard();
            
            // 5. Controlla lo stato dopo la mossa di O
            checkGameStatus();
            
            if (!gameOver) {
                // 6. Cambia giocatore e torna a X
                currentPlayer = 'X';
                gui.updateStatus("Tocca a X");
            }
        }
    }

    // Metodo per controllare lo stato e aggiornare la GUI
    private void checkGameStatus() {
        gui.redrawBoard(); // Disegna i cambiamenti

        if (board.checkWin('X')) {
            gui.updateStatus("X ha vinto!");
            gameOver = true;
        } else if (board.checkWin('O')) {
            gui.updateStatus("O (AI) ha vinto!");
            gameOver = true;
        } else if (board.isFull()) {
            gui.updateStatus("Pareggio!");
            gameOver = true;
        }
        
        if (gameOver) {
            gui.disableAllButtons();
        }
    }
}