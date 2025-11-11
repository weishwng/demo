public class TreeNode {
    private Board board;
    private char player;
    private TreeNode[] children;
    private int numChildren;
    private int value;
    private Move moveFromParent;

    public TreeNode(Board b, char player) {
        this.board = b;
        this.player = player;
        this.children = new TreeNode[9];
        this.numChildren = 0;
        this.value = 0;
        this.moveFromParent = null;
    }

    public Board getBoard() { return board; }
    public void setBoard(Board b) { this.board = b; }

    public char getPlayer() { return player; }
    public void setPlayer(char p) { this.player = p; }

    public TreeNode[] getChildren() { return children; }

    public int getNumChildren() { return numChildren; }
    public void setNumChildren(int n) { this.numChildren = n; }

    public void addChild(TreeNode child) {
        if (numChildren < 9) {
            children[numChildren++] = child;
        }
    }

    public int getValue() { return value; }
    public void setValue(int v) { this.value = v; }

    public Move getMoveFromParent() { return moveFromParent; }
    public void setMoveFromParent(Move m) { this.moveFromParent = m; }
}
