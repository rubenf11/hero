import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.List;

public class Game {
    private Screen screen;
    private Wall walls;

    private Arena arena = new Arena(60,20, (List) walls);

    private Hero hero = new Hero(10, 10);

    public Game(){

        try {
            TerminalSize terminalSize = new TerminalSize(60, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            this.screen = new TerminalScreen(terminal);
            this.screen.setCursorPosition(null); // we don't need a cursor
            this.screen.startScreen(); // screens must be started
            this.screen.doResizeIfNecessary(); // resize screen if necessary
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void draw() throws IOException{
        this.screen.clear();
        arena.draw(screen.newTextGraphics());
        this.screen.refresh();
    }

    private void processKey(KeyStroke key) throws IOException{
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){
            screen.close();
        }
        arena.processKey(key);
    }

    private void moveHero(Position position) {
        hero.setPosition(position);
    }

    public void run() throws IOException{
        while(true){
            draw();
            KeyStroke key = screen.readInput();
            processKey(key);
            if(key.getKeyType() == KeyType.EOF){
                break;
            }
            if(arena.verifyMonsterCollisions()){
                screen.close();
            }

            arena.moveMonsters();
            if(arena.verifyMonsterCollisions()){
                screen.close();
                break;
            }

        }
    }

}

