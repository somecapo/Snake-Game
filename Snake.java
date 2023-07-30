package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Snake extends GameObject {
    final private static String HEAD_SIGN = "\uD83D\uDE0E";
    final private static String BODY_SIGN = "\u26AB";
    private List<GameObject> snakeParts = new ArrayList<>();
    public boolean isAlive = true;
    private Direction direction = Direction.LEFT;

    public Snake(int x, int y) {
        super(x, y);
        snakeParts.addAll(Arrays.asList(new GameObject(x, y), new GameObject(x + 1, y), new GameObject(x + 2, y)));
    }

    public void draw(Game game) {
        for (GameObject o : snakeParts) {
            if (isAlive) {
                if (o == snakeParts.get(0)) {
                    game.setCellValueEx(o.x, o.y, Color.NONE, HEAD_SIGN, Color.BLACK, 75);
                } else {
                    game.setCellValueEx(o.x, o.y, Color.NONE, BODY_SIGN, Color.BLACK, 50);
                }
            } else {
                if (o == snakeParts.get(0)) {
                    game.setCellValueEx(o.x, o.y, Color.NONE, HEAD_SIGN, Color.RED, 75);
                } else {
                    game.setCellValueEx(o.x, o.y, Color.NONE, BODY_SIGN, Color.RED, 50);
                }
            }
        }
    }

    public void setDirection(Direction direction) {
        if (this.direction == Direction.LEFT && snakeParts.get(0).x == snakeParts.get(1).x) {
        } else if (this.direction == Direction.RIGHT && snakeParts.get(0).x == snakeParts.get(1).x) {
        } else if (this.direction == Direction.UP && snakeParts.get(0).y == snakeParts.get(1).y) {
        } else if (this.direction == Direction.DOWN && snakeParts.get(0).y == snakeParts.get(1).y) {
        } else if (this.direction == Direction.UP && direction == Direction.DOWN) {
        } else if (this.direction == Direction.DOWN && direction == Direction.UP) {
        } else if (this.direction == Direction.RIGHT && direction == Direction.LEFT) {
        } else if (this.direction == Direction.LEFT && direction == Direction.RIGHT) {
        } else {
            this.direction = direction;
        }
    }

    public void move(Apple apple) {
        GameObject head = createNewHead();
        if (head.x >= SnakeGame.WIDTH
                || head.x < 0
                || head.y >= SnakeGame.HEIGHT
                || head.y < 0) {
            isAlive = false;
        } else if (checkCollision(head)) {
            isAlive = false;
        } else if (isAlive) {
            snakeParts.add(0, head);
        }
        if (head.x == apple.x && head.y == apple.y) {
            apple.isAlive = false;
        } else if (isAlive) {
            removeTail();
        }
    }

    public GameObject createNewHead() {
        int x = snakeParts.get(0).x;
        int y = snakeParts.get(0).y;
        if (direction == Direction.LEFT) {
            x--;
        } else if (direction == Direction.DOWN) {
            y++;
        } else if (direction == Direction.UP) {
            y--;
        } else if (direction == Direction.RIGHT) {
            x++;
        }
        return new GameObject(x, y);
    }

    public void removeTail() {
        snakeParts.remove(snakeParts.get(snakeParts.size() - 1));
    }

    public boolean checkCollision(GameObject gameObject) {
        for (GameObject el : snakeParts) {
            if (el.x == gameObject.x && el.y == gameObject.y) {
                return true;
            }
        }
        return false;
    }

    public int getLength() {
        return snakeParts.size();
    }
}
