import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Arena {

    private Hero hero = new Hero(10,10);
    private Screen screen;

    private Position position = new Position(hero.get_x(), hero.get_y());

    private int width;
    private int height;

    public Arena(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void draw(TextGraphics graphics) throws IOException {

        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width * 2, height * 2), ' ');
        graphics.putString(new TerminalPosition(position.get_x() * 2, position.get_y() * 2), "\\/");
        graphics.putString(new TerminalPosition(position.get_x() * 2, position.get_y() * 2 + 1), "/\\");
        hero.draw(graphics);
    }
    public void processKey(KeyStroke key) throws IOException{
        if(key.getKeyType() == KeyType.ArrowUp){
            moveHero(hero.moveUp());
        }
        if(key.getKeyType() == KeyType.ArrowDown){
            moveHero(hero.moveDown());
        }
        if(key.getKeyType() == KeyType.ArrowLeft){
            moveHero(hero.moveLeft());
        }
        if(key.getKeyType() == KeyType.ArrowRight){
            moveHero(hero.moveRight());
        }


        System.out.println(key);
    }

    public boolean canHeroMove(Position position){
        if(width >= position.get_x()+1 || position.get_x()-1 >= 0){
            return true;
        }
        if(height >= position.get_y()+1 || position.get_y()-1 >= 0){
            return true;
        }

        return false;
    }

    public void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }

}
