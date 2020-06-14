import java.awt.*;
import java.util.ArrayList;

public class Player {
    public static int x;
    public static double y;

    public double vspd = 0;
    public double gravity = 0.4;
    public static boolean jump = false;
    public int jumpHeight = -10;
    public int speedHorizontal = 4;

    public Player(int x, int y) {
        Player.x = x;
        Player.y = y;
    }

    public void tick() {
        vspd+=gravity;
        if(!World.isFree((int)x,(int)(y+1)) && jump) {
            vspd = jumpHeight;
            jump = false;
        }

        if(!World.isFree((int)x,(int)(y+vspd))) {

            int signVsp = 0;
            if(vspd >= 0) {
                signVsp = 1;
            }else  {
                signVsp = -1;
            }

            while(World.isFree((int)x,(int)(y+signVsp))) {
                y = y+signVsp;
            }
            vspd = 0;
        }

        y = y + vspd;

        if (World.isFree(x + speedHorizontal, (int) y)) {
            x+= speedHorizontal;
        }

        Camera.x = x - Game.WIDTH / 2;

        if (y >= Game.HEIGHT + 300) {
            x = 0;
            Game.world = new World();
            World.lastPos = 0;
            World.lastWidth = 0;
            Game.pontos = 0;
            World.tiles = new ArrayList<>();
            Game.player = new Player(0, 448 - 32);
        }

        if ((Game.pontos / 3) + 2 > 4) {
            speedHorizontal = (Game.pontos / 3) + 2;
        }
    }

    public void render(Graphics g) {
        /*
        g.setColor(Color.RED);
        g.fillRect(x - Camera.x, (int) y - Camera.y, 32, 32);

         */

        g.drawImage(Game.playerSprite, x - Camera.x, (int) y - Camera.y, 32, 32, null);
    }
}
