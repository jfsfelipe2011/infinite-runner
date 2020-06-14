import java.awt.*;

public class Tile {
    public int x, y;

    public void tick() {
        if (x - Camera.x < - 32) {
            World.tiles.remove(this);
        }
    }

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void render(Graphics g) {
        /*
        g.setColor(Color.BLUE);
        g.fillRect(x - Camera.x, y - Camera.y, World.blockSize, World.blockSize);

        g.setColor(Color.BLACK);
        g.drawRect(x - Camera.x, y - Camera.y, World.blockSize, World.blockSize);

         */

        g.drawImage(Game.floorSprite, x - Camera.x, y - Camera.y, 32, 32, null);
    }
}
