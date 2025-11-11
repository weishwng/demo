public class TreeNode {
    private Board board;
    private char player;
    private TreeNode[] children;
    private int numChildren;
    private int value;
    private Move moveFromParent;

    public TreeNode(Board b, char player) {
        board = b;
        this.player = player;
        children = new TreeNode[9];
        numChildren = 0;
        value = 0;
        moveFromParent = null;
    }

    public Board getBoard() { return board; }
    public char getPlayer() { return player; }
    public TreeNode[] getChildren() { return children; }
    public int getNumChildren() { return numChildren; }
    public int getValue() { return value; }
    public Move getMoveFromParent() { return moveFromParent; }

    public void setBoard(Board b) { board = b; }
    public void setPlayer(char p) { player = p; }
    public void setNumChildren(int n) { numChildren = n; }
    public void setValue(int v) { value = v; }
    public void setMoveFromParent(Move m) { moveFromParent = m; }

    public void addChild(TreeNode child) {
        if (numChildren < 9) {
            children[numChildren] = child;
            numChildren++;
        }
    }
}
