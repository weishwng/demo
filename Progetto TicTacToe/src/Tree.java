public class Tree {
    private TreeNode root;
    private final int MAX_DEPTH = 4; // profondità massima per evitare blocco

    public Tree(Board rootBoard, char rootPlayer) {
        root = new TreeNode(rootBoard, rootPlayer);
    }

    public TreeNode getRoot() { return root; }

    public void generateChildren(TreeNode node, int depth) {
        if (depth >= MAX_DEPTH) return; // stop per profondità
        Board b = node.getBoard();
        if (b.isFull() || b.checkWin('X') || b.checkWin('O')) return; // stop se nodo terminale

        node.setNumChildren(0);
        char player = node.getPlayer();
        char nextPlayer = (player == 'X') ? 'O' : 'X';

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (b.isCellEmpty(i, j)) {
                    Board childBoard = new Board(b);
                    childBoard.setCell(i, j, player);
                    TreeNode child = new TreeNode(childBoard, nextPlayer);
                    child.setMoveFromParent(new Move(i, j));
                    node.addChild(child);
                    generateChildren(child, depth + 1);
                }
            }
        }
    }

    private int evaluate(Board b) {
        if (b.checkWin('X')) return 1;
        if (b.checkWin('O')) return -1;
        return 0;
    }

    public int minimax(TreeNode node, int depth) {
        int n = node.getNumChildren();

        if (n == 0 || depth >= MAX_DEPTH) {
            int val = evaluate(node.getBoard());
            node.setValue(val);
            return val;
        }

        char player = node.getPlayer();

        if (player == 'X') { // Max
            int bestVal = -2;
            for (int i = 0; i < n; i++) {
                TreeNode child = node.getChildren()[i];
                int val = minimax(child, depth + 1);
                if (val > bestVal) bestVal = val;
            }
            node.setValue(bestVal);
            return bestVal;
        } else { // Min
            int bestVal = 2;
            for (int i = 0; i < n; i++) {
                TreeNode child = node.getChildren()[i];
                int val = minimax(child, depth + 1);
                if (val < bestVal) bestVal = val;
            }
            node.setValue(bestVal);
            return bestVal;
        }
    }

    public Move bestMove() {
        generateChildren(root, 0);
        minimax(root, 0);

        int n = root.getNumChildren();
        if (n == 0) return null;

        char player = root.getPlayer();
        Move best = null;

        if (player == 'X') {
            int bestVal = -2;
            for (int i = 0; i < n; i++) {
                TreeNode child = root.getChildren()[i];
                int val = child.getValue();
                if (val > bestVal) {
                    bestVal = val;
                    best = child.getMoveFromParent();
                }
            }
        } else {
            int bestVal = 2;
            for (int i = 0; i < n; i++) {
                TreeNode child = root.getChildren()[i];
                int val = child.getValue();
                if (val < bestVal) {
                    bestVal = val;
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
