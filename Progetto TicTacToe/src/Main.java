public class Main {
    public static void main(String[] args) {
        Game tris = new Game();
        // Avvia il gioco e mostra la GUI
        tris.startGame(); 
        
        // Nota: la logica di gioco si sposta interamente nella GUI
        // e nei metodi handleHumanMove/playBotMove
    }
}