import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Hero extends Element{

    private Position position;

    public Hero(int x, int y) {
        super(x,y);
    }

    public Position getPosition(){
        return super.getPosition();
    }
    public void setPosition(Position p){
        super.getPosition().set_x(p.get_x());
        super.getPosition().set_y(p.get_y());
    }

    public Position moveUp() {
        return new Position(super.getPosition().get_x(), super.getPosition().get_y() - 1);
    }

    public Position moveDown() {
        return new Position(super.getPosition().get_x(), super.getPosition().get_y() + 1);
    }

    public Position moveLeft() {
        return new Position(super.getPosition().get_x() - 1, super.getPosition().get_y());
    }

    public Position moveRight() {
        return new Position(super.getPosition().get_x() + 1, super.getPosition().get_y());
    }


    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(super.getPosition().get_x(), super.getPosition().get_y()), "X");
        graphics.setCharacter(super.getPosition().get_x(), super.getPosition().get_y(), TextCharacter.fromCharacter('X')[0]);
    }

}