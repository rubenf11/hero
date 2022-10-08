import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element {

    public Monster(int x, int y) {
        super(x, y);
    }

    public Position getPosition() {
        return super.getPosition();
    }

    public void draw(TextGraphics graphics) {

        graphics.setForegroundColor(TextColor.Factory.fromString("#339933"));
        graphics.putString(new TerminalPosition(super.getPosition().get_x(), super.getPosition().get_y()), "M");

    }

    public Position move(Arena arena){
        Random random = new Random();
        while(true) {
            Position ret = new Position(this.getPosition().get_x() + random.nextInt(3) - 1,
                    this.getPosition().get_y() + random.nextInt(3) - 1);
            if(ret.get_x() > 0 && ret.get_x() < arena.get_width()-1 &&
                    ret.get_y() > 0 && ret.get_y() < arena.get_height()-1)
                return ret;
        }
    }

}
