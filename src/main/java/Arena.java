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
import java.util.Random;

public class Arena {

    private Hero hero = new Hero(10,10);
    private Screen screen;
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;

    private Position position = new Position(hero.getPosition().get_x(), hero.getPosition().get_y());

    private int width;
    private int height;

    public Arena(int width, int height, List walls){
        this.width = width;
        this.height = height;
        this.walls = createWalls();
        coins = createCoins();
        monsters = createMonsters();
    }

    public int get_width(){
        return width;
    }

    public int get_height(){
        return height;
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

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Coin newcoin = new Coin(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1);
            if (!coins.contains(newcoin) && !newcoin.getPosition().equals(hero.getPosition())){
                coins.add(newcoin);
            }
        }
        return coins;
    }

    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Monster newmonster = new Monster(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1);
            if (!monsters.contains(newmonster) && !newmonster.getPosition().equals(hero.getPosition())){
                monsters.add(newmonster);
            }
        }
        return monsters;
    }

    public void draw(TextGraphics graphics) throws IOException {

        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width * 2, height * 2), ' ');
        hero.draw(graphics);
        for (Wall wall : walls)
            wall.draw(graphics);

        for(Coin coin : coins){
            coin.draw(graphics);
        }
        for(Monster monster : monsters){
            monster.draw(graphics);
        }
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
        retrieveCoins();
    }

    public void retrieveCoins(){
        for(Coin coin : coins){
            if(coin.getPosition().equals(hero.getPosition())){
                coins.remove(coin);
                break;
            }
        }

    }

    public void moveMonsters(){
        for(Monster monster : monsters){
            monster.setPosition(monster.move(this));
        }
    }

    public boolean verifyMonsterCollisions(){
        for(Monster monster : monsters){
            if(monster.getPosition().equals(hero.getPosition())){
                System.out.println("Your hero is dead!");
                return true;
            }
        }
        return false;
    }

}
