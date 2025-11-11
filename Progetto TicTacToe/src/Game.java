import java.util.Scanner;

public class Game {
    private Board board;          // la griglia di gioco
    private char currentPlayer;   // X = umano, O = computer
    private Scanner sc;           // per input dell'utente

    // Costruttore
    public Game() {
        board = new Board();
        currentPlayer = 'X'; // X inizia per primo
        sc = new Scanner(System.in);
    }

    // Metodo principale per far partire il gioco
    public void play() {
        while (!board.isFull() && !board.checkWin('X') && !board.checkWin('O')) {
            board.printBoard();

            if (currentPlayer == 'X') {
                // suggerimento mossa migliore per X
                Move suggestedMove = Tree.computeBestMove(board, 'X');
                if (suggestedMove != null) {
                    System.out.println("Suggerimento mossa migliore per X: " + suggestedMove.toString());
                }

                // turno umano
                System.out.println("Tocca a X (umano). Inserisci riga e colonna (0-2):");
                int r = sc.nextInt();
                int c = sc.nextInt();
                Move m = new Move(r, c);

                if (!board.applyMove(m, 'X')) {
                    System.out.println("Mossa non valida, riprova.");
                    continue; // rimane il turno di X
                }
            } else {
                // turno computer
                System.out.println("Tocca a O (computer). Calcolo mossa migliore...");
                Move bestMove = Tree.computeBestMove(board, 'O');

                if (bestMove != null) {
                    System.out.println("Mossa migliore per O: " + bestMove.toString());
                    board.applyMove(bestMove, 'O'); // applica mossa
                } else {
                    System.out.println("Nessuna mossa disponibile per O.");
                }
            }

            // cambio turno
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }

        // gioco finito
        board.printBoard();

        if (board.checkWin('X')) System.out.println("Vince X!");
        else if (board.checkWin('O')) System.out.println("Vince O!");
        else System.out.println("Pareggio!");
    }
}
