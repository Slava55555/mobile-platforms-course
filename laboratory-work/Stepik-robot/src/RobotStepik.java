/*
Author: Buchnev Vyacheslav 02421
tg: @SlavaBuchnev
project: https://github.com/Slava55555/mobile-platforms-course
*/

public class RobotStepik {

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public abstract class Robot {

        public abstract Direction getDirection();

        public abstract int getX();

        public abstract int getY();

        public abstract void turnLeft();

        public abstract void turnRight();

        public abstract void stepForward();
    }


    public static void moveRobot(Robot robot, int toX, int toY) {

        int dx = toX - robot.getX();
        if (dx != 0) {
            Direction neededDirectionX = (dx > 0) ? Direction.RIGHT : Direction.LEFT;
            turnToDirection(robot, neededDirectionX);
            moveSteps(robot, Math.abs(dx));
        }

        int dy = toY - robot.getY();
        if (dy != 0) {
            Direction neededDirectionY = (dy > 0) ? Direction.UP : Direction.DOWN;
            turnToDirection(robot, neededDirectionY);
            moveSteps(robot, Math.abs(dy));
        }
    }

    private static void turnToDirection(Robot robot, Direction targetDirection) {
        while (robot.getDirection() != targetDirection) {
            robot.turnRight();
        }
    }

    private static void moveSteps(Robot robot, int steps) {
        for (int i = 0; i < steps; i++) {
            robot.stepForward();
        }
    }
}