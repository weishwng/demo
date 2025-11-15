public class Tree {
    private TreeNode root;

    public Tree(Board rootBoard, char rootPlayer) {
        root = new TreeNode(rootBoard, rootPlayer);
    }

    public TreeNode getRoot() { return root; }

    public void generateChildren(TreeNode node) {
        Board b = node.getBoard();

        // nodo terminale
        if (b.isFull() || b.checkWin('X') || b.checkWin('O'))
            return;

        node.setNumChildren(0);

        char player = node.getPlayer();
        char nextPlayer;
        if (player == 'X') {
            nextPlayer = 'O';
        } else {
            nextPlayer = 'X';
        }


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (b.isCellEmpty(i, j)) {
                    Board childBoard = new Board(b);
                    childBoard.setCell(i, j, player);
                    TreeNode child = new TreeNode(childBoard, nextPlayer);
                    child.setMoveFromParent(new Move(i, j));
                    node.addChild(child);

                    generateChildren(child);
                }
            }
        }
    }

    private int evaluate(Board b) {
        if (b.checkWin('X')) return 1;
        if (b.checkWin('O')) return -1;
        return 0; // pareggio
    }

    public int minimax(TreeNode node) {
        int n = node.getNumChildren();

        // se è foglia → valuta
        if (n == 0) {
            int val = evaluate(node.getBoard());
            node.setValue(val);
            return val;
        }

        char player = node.getPlayer();

        if (player == 'X') { // MAX
            int bestVal = -2;
            for (int i = 0; i < n; i++) {
                int val = minimax(node.getChildren()[i]);
                if (val > bestVal) bestVal = val;
            }
            node.setValue(bestVal);
            return bestVal;

        } else { // MIN (O)
            int bestVal = 2;
            for (int i = 0; i < n; i++) {
                int val = minimax(node.getChildren()[i]);
                if (val < bestVal) bestVal = val;
            }
            node.setValue(bestVal);
            return bestVal;
        }
    }

    public Move bestMove() {
        generateChildren(root);
        minimax(root);

        int n = root.getNumChildren();
        if (n == 0) return null;

        char player = root.getPlayer();
        Move best = null;

        if (player == 'X') {
            int bestVal = -2;
            for (int i = 0; i < n; i++) {
                TreeNode child = root.getChildren()[i];
                if (child.getValue() > bestVal) {
                    bestVal = child.getValue();
                    best = child.getMoveFromParent();
                }
            }
        } else {
            int bestVal = 2;
            for (int i = 0; i < n; i++) {
                TreeNode child = root.getChildren()[i];
                if (child.getValue() < bestVal) {
                    bestVal = child.getValue();
                    best = child.getMoveFromParent();
                }
            }
        }

        return best;
    }

    public static Move computeBestMove(Board b, char player) {
        Tree t = new Tree(b, player);
        return t.bestMove();
    }
}
