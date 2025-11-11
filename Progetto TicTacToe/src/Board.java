public class Board{
    private char[][] griglia;

    public Board(){
        griglia = new char[3][3];
        for(int i =0; i < 3; i++){
            for(int j = 0; i< 3; j++){
                griglia[i][j] = ' ';
            }
        }
    }
    public Board(Board other){
        griglia = new char[3][3];
        for(int i =0; i < 3; i++){
            for(int j = 0; j< 3; j++){
                griglia[i][j] = other.griglia[i][j];
            }
        }
    }
    public void stampa(){
        System.out.println("+---+---+---+");
        for(int i =0; i < 3; i++){
            for(int j = 0; j< 3; j++){
                System.out.print("|");
                System.out.print(griglia[i][j]);
            }
            System.out.println("+---+---+---+");
            System.out.println();
        }
    }

    public boolean isFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (griglia[i][j] == ' '){
                    return false;
                }
        return true;
    }

    public boolean Mossa(Move move, char player) {
        if (move == null){ //controlla se non c'è nessuna mossa
            return false;
        }
        if (move.getRow() < 0 || move.getRow() > 2 || move.getCol() < 0 || move.getCol() > 2){
            return false; //controlla se la mossa è maggiore o minore delle caselle della griglia
        }
        if (griglia[move.getRow()][move.getCol()] == ' ') { //contorlla se è vuoto
            griglia[move.getRow()][move.getCol()] = player; //aggiunge in quella posizioni
            return true;
        }
        return false;
    }
    public boolean controlloVincitore(char player) {
        for (int i = 0, j = 3;i<3 && j>0; i++, j--){
            if ((griglia[i][0] == player && griglia[i][1] == player && griglia[i][2] == player) || //controllo riga
                (griglia[0][i] == player && griglia[1][i] == player && griglia[2][i] == player) || //controllo colonna
                (griglia[i][i] == player)||(griglia[i][j] == player)){ //controllo diagonali
                return true;
            }
        }
        return false;
    }


    public int CaselleVuote(){
        int v = 0;
        for(int i = 0;i<3;i++){
            for(int j= 0;j<3;j++){
                if(griglia[i][j]== ' '){
                    v++;
                }
            }
        }
        return v;
    }
}
