public class Move{
    private int row;
    private int col;

    public Move(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public void setCow(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }
}