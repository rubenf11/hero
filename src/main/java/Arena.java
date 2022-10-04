import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Arena {

    private Hero hero = new Hero(10,10);
    private Screen screen;

    private Position position;

    private int width;
    private int height;

    public Arena(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void draw(Screen screen) throws IOException {
        hero.draw(screen);
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
