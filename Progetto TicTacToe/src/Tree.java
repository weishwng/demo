public class Tree {
    private TreeNode root;

    public Tree(Board rootBoard, char rootPlayer) {
        this.root = new TreeNode(rootBoard, rootPlayer);
    }

    public TreeNode getRoot() { return root; }
}