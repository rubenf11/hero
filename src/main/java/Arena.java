import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arena {

    private Hero hero = new Hero(10,10);
    private Screen screen;
    private List<Wall> walls;

    private Position position = new Position(hero.getPosition().get_x(), hero.getPosition().get_y());

    private int width;
    private int height;

    public Arena(int width, int height, List walls){
        this.width = width;
        this.height = height;
        this.walls = createWalls();
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }

    public void draw(TextGraphics graphics) throws IOException {

        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width * 2, height * 2), ' ');
        hero.draw(graphics);
        for (Wall wall : walls)
            wall.draw(graphics);

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
        for (Wall wall : walls){
            if (wall.getPosition().equals(position)){
                return false;
            }
        }
        return true;
    }


    public void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }

}
