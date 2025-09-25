public class Robot {

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