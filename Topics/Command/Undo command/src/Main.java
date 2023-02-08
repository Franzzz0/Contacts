import java.awt.*;
import java.util.ArrayList;
import java.util.List;

interface Movable {
    int getX();
    int getY();
    void setX(int newX);
    void setY(int newY);
}

interface Storable {
    int getInventoryLength();
    String getInventoryItem(int index);
    void setInventoryItem(int index, String item);
}

interface Command {
    void execute();
    void undo();
}

class CommandMove implements Command{
    Movable entity;
    int xMovement;
    int yMovement;
    private final List<Point> moves;

    CommandMove() {
        this.moves = new ArrayList<>();
    }

    @Override
    public void execute() {
        entity.setX(entity.getX() + xMovement);
        entity.setY(entity.getY() + yMovement);
        this.moves.add(new Point(xMovement, yMovement));
    }

    @Override
    public void undo() {
        if (!moves.isEmpty()) {
            entity.setX(entity.getX() - moves.get(moves.size() - 1).x);
            entity.setY(entity.getY() - moves.get(moves.size() - 1).y);
            moves.remove(moves.size() - 1);
        }
    }
}

class CommandPutItem implements Command{
    Storable entity;
    String item;
    private final List<Integer> list;

    CommandPutItem() {
        this.list = new ArrayList<>();
    }

    @Override
    public void execute() {
        int index = -1;
        for (int i = 0; i < entity.getInventoryLength(); i++) {
            if (entity.getInventoryItem(i) == null) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            entity.setInventoryItem(index, item);
            list.add(index);
        }
    }

    @Override
    public void undo() {
        if (!list.isEmpty()) {
            entity.setInventoryItem(list.get(list.size() - 1), null);
            list.remove(list.size() - 1);
        }
    }
}