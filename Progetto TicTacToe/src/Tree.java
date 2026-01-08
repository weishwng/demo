public class Tree {
    private TreeNode root; // La radice, cioè la situazione ATTUALE del gioco

    public Tree(Board rootBoard, char rootPlayer) {
        this.root = new TreeNode(rootBoard, rootPlayer);
        
        // APPENA CREO L'ALBERO, CALCOLO TUTTO IL FUTURO!
        // Così non devo rifarlo ogni volta che muovo.
        System.out.println("Sto calcolando tutte le mosse possibili dell'universo...");
        generateChildren(this.root);
        
        // Assegno i punteggi a tutti i nodi creati
        minimax(this.root);
        System.out.println("Calcolo finito. AI pronta.");
    }

    public TreeNode getRoot() { return root; }

    // --- NUOVO METODO FONDAMENTALE ---
    // Serve per aggiornare la radice senza buttare via l'albero.
    // In pratica "scendo" nel ramo che corrisponde alla mossa fatta.
    public void changeRoot(Move moveMade) {
        if (root.getNumChildren() == 0) return; // Se non ci sono figli, non posso scendere

        // Controllo tutti i figli del nodo attuale
        for (int i = 0; i < root.getNumChildren(); i++) {
            TreeNode child = root.getChildren()[i];
            Move m = child.getMoveFromParent(); // Che mossa ha generato questo figlio?
            
            // Se le coordinate sono uguali alla mossa fatta dal giocatore (o dal bot)
            if (m.getRow() == moveMade.getRow() && m.getCol() == moveMade.getCol()) {
                this.root = child; // Trovato! Il figlio diventa la nuova radice
                return; // Esco subito
            }
        }
        System.out.println("Errore: Non ho trovato la mossa nell'albero (strano...)");
    }

    // Questo crea tutto l'albero delle possibilità in modo ricorsivo
    public void generateChildren(TreeNode node) { 
        Board b = node.getBoard();

        // Se la partita è finita in questo nodo, mi fermo, non creo figli
        if (b.isFull() || b.checkWin('X') || b.checkWin('O')){
            return; 
        }

        char player = node.getPlayer(); 
        // Capisco chi sarà il prossimo a giocare nei figli
        char nextPlayer = (player == 'X') ? 'O' : 'X';

        // Provo a mettere il simbolo in ogni casella vuota
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (b.isCellEmpty(i, j)) { 
                    // Copio la griglia attuale
                    Board childBoard = new Board(b); 
                    // Faccio la mossa sulla copia
                    childBoard.setCell(i, j, player); 
                    
                    // Creo il nuovo nodo figlio
                    TreeNode child = new TreeNode(childBoard, nextPlayer); 
                    child.setMoveFromParent(new Move(i, j)); // Mi segno che mossa era
                    
                    // Attacco il figlio al padre
                    node.addChild(child); 

                    // RICORSIONE: Faccio la stessa cosa per il figlio appena creato
                    generateChildren(child); 
                }
            }
        }
    }

    // Assegna un punteggio base: 1 se vince X, -1 se vince O, 0 pareggio
    private int valutazione(Board b) { 
        if (b.checkWin('X')) return 1;
        if (b.checkWin('O')) return -1;
        return 0; 
    }

    // Algoritmo Minimax: risale l'albero per capire quale strada è la migliore
    public int minimax(TreeNode node) {
        int n = node.getNumChildren();

        // Caso base: se è un nodo foglia (partita finita), calcolo il valore
        if (n == 0) {
            int val = valutazione(node.getBoard());
            node.setValue(val); 
            return val;
        }

        char player = node.getPlayer();

        if (player == 'X') { // Se tocca a X, vuole massimizzare il punteggio (cerca 1)
            int bestVal = -2; // Parto da un valore bassissimo
            for (int i = 0; i < n; i++) {
                // Chiamo minimax sui figli (ricorsione)
                int val = minimax(node.getChildren()[i]); 
                if (val > bestVal) bestVal = val; // Prendo il massimo
            }
            node.setValue(bestVal); // Salvo il valore nel nodo
            return bestVal;

        } else { // Se tocca a O, vuole minimizzare (cerca -1)
            int bestVal = 2; // Parto da un valore altissimo
            for (int i = 0; i < n; i++) {
                int val = minimax(node.getChildren()[i]);
                if (val < bestVal) bestVal = val; // Prendo il minimo
            }
            node.setValue(bestVal);
            return bestVal;
        }
    }

    // Decide qual è la mossa migliore guardando SOLO i figli diretti (perché i calcoli sono già fatti)
    public Move bestMove() {
        int n = root.getNumChildren();
        if (n == 0) return null; // Non ci sono mosse possibili

        char player = root.getPlayer(); // Chi deve muovere adesso?
        Move best = null;

        if (player == 'X') {
            int bestVal = -2;
            // Cerco tra i figli quello col valore più alto
            for (int i = 0; i < n; i++) {
                TreeNode child = root.getChildren()[i];
                if (child.getValue() > bestVal) {
                    bestVal = child.getValue();
                    best = child.getMoveFromParent(); 
                }
            }
        } else { // Se tocca a O
            int bestVal = 2;
            // Cerco tra i figli quello col valore più basso
            for (int i = 0; i < n; i++) {
                TreeNode child = root.getChildren()[i];
                if (child.getValue() < bestVal) {
                    bestVal = child.getValue();
                    best = child.getMoveFromParent();
                }
            }
        }
        return best; // Restituisco la mossa vincente
    }   
}