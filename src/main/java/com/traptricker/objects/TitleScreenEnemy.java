package com.traptricker.objects;

import com.traptricker.Game;
import java.awt.Color;
import java.awt.Graphics;

public class TitleScreenEnemy extends GameObject {

  private final Color color;
  private final int[][] trailArray;

  public TitleScreenEnemy(Game game, int x, int y, int xVelocity, int yVelocity,
      int radius, ID id, Color color) {
    super(game, x, y, xVelocity, yVelocity, radius, id);
    this.color = color;
    trailArray = new int[][]{{x, y}, {x, y}, {x, y}, {x, y}, {x, y}, {x, y}, {x, y}, {x, y}, {x, y},
        {x, y}, {x, y}, {x, y}, {x, y}, {x, y}, {x, y}, {x, y}, {x, y}, {x, y}, {x, y}, {x, y}};
  }

  @Override
  public void tick() {
    // Moves the trail down one
    if (game.getTick() % 15 == 0) {
      for (int i = 0; i < trailArray.length - 1; i++) {
        trailArray[i + 1] = trailArray[i];
      }
      trailArray[0] = new int[]{x, y};
    }

    // Keeps the enemy on screen
    if (x <= 0 || x >= game.getWidth() - radius * 2) {
      xVelocity *= -1;
    }
    if (y <= 0 || y >= game.getHeight() - radius * 2) {
      yVelocity *= -1;
    }

    x += xVelocity;
    y += yVelocity;
  }

  @Override
  public void render(Graphics g) {
    g.setColor(color);
    g.fillRect(x, y, radius * 2, radius * 2);
    int i = 1;
    for (int[] position : trailArray) {
      // Adjusts the opacity of the trail
      Color trailColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), 255);
      i++;
      g.setColor(trailColor);
      g.fillRect(position[0] + i, position[1] + i, (radius - i) * 2, (radius - i) * 2);
    }
  }
}
