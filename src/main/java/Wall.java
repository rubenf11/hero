import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;
import java.util.List;

public class Wall {

    private int x;
    private int y;

    private Hero hero = new Hero(10,10);
    private Position position = new Position(hero.get_x(), hero.get_y());

    public Position getPosition(){
        return this.position;
    }

    public Wall(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int get_x(){
        return position.get_x();
    }
    public int get_y(){
        return position.get_y();
    }

    public void draw(TextGraphics graphics) throws IOException{

        graphics.setForegroundColor(TextColor.Factory.fromString("#FC5203"));
        graphics.putString(new TerminalPosition(position.get_x(), position.get_y()), "=");

    }


}
