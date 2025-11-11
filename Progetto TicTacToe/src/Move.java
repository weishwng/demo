public class Move {
    private int row;
    private int col;

    public Move(int r, int c) {
        row = r;
        col = c;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int r) {
        row = r;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int c) {
        col = c;
    }

    @Override
    public String toString() {
    return "(" + row + "," + col + ")";
    }

}
