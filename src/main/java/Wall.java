import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;
import java.util.List;

public class Wall extends Element {

    private Hero hero = new Hero(10,10);
    private Position position = new Position(10,10);

    public Position getPosition(){
        return super.getPosition();
    }

    public Wall(int x, int y) {
        super(x,y);
    }

    public void draw(TextGraphics graphics) {

        graphics.setForegroundColor(TextColor.Factory.fromString("#FC5203"));
        graphics.putString(new TerminalPosition(super.getPosition().get_x(), super.getPosition().get_y()), "#");

    }


}
