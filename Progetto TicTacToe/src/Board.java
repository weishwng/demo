public class Board {
    private char[][] griglia;

    public Board() {
        griglia = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                griglia[i][j] = ' ';
            }
        }
    }

    public Board(Board other) {
        griglia = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                griglia[i][j] = other.getCell(i, j);
            }
        }
    }

    public char getCell(int r, int c) { return griglia[r][c]; }
    public void setCell(int r, int c, char val) { griglia[r][c] = val; }

    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (griglia[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean applyMove(Move move, char player) {
        if (move == null) return false;
        int r = move.getRow();
        int c = move.getCol();
        if (r < 0 || r > 2 || c < 0 || c > 2) return false;
        if (getCell(r, c) == ' ') {
            setCell(r, c, player);
            return true;
        }
        return false;
    }

    public boolean checkWin(char player) {
        for (int i = 0; i < 3; i++) {
            if ((getCell(i, 0) == player && getCell(i, 1) == player && getCell(i, 2) == player)) return true;
            if ((getCell(0, i) == player && getCell(1, i) == player && getCell(2, i) == player)) return true;
        }
        if ((getCell(0, 0) == player && getCell(1, 1) == player && getCell(2, 2) == player)) return true;
        if ((getCell(0, 2) == player && getCell(1, 1) == player && getCell(2, 0) == player)) return true;
        return false;
    }

    public boolean isCellEmpty(int r, int c) {
        if (r < 0 || r > 2 || c < 0 || c > 2) return false;
        if (getCell(r, c) == ' ') return true;
        return false;
    }

    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(getCell(i, j) + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }
}
