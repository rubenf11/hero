import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.List;

public class Coin extends Element{

    public Coin(int x, int y){
        super(x,y);
    }

    public Position getPosition(){
        return super.getPosition();
    }

    public void draw(TextGraphics graphics) {

        graphics.setForegroundColor(TextColor.Factory.fromString("#999933"));
        graphics.putString(new TerminalPosition(super.getPosition().get_x(), super.getPosition().get_y()), "$");

    }


}
