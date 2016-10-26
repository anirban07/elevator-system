package main;

import main.enums.ElevatorDirection;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Anirban on 10/25/2016.
 */

/**
 * This class stores the state of an elevator
 */
public class Elevator {
    public static final int DEFAULT_FLOOR = 1;

    private static int elevatorCount;

    private int id;
    private int direction;
    private int currentFloor;
    private int nextFloor;
    private PriorityQueue<Integer> destinationList;

    static {
        elevatorCount = 0;
    }

    public Elevator() {
        this.id = elevatorCount;
        elevatorCount++;
        this.direction = 0;
        this.currentFloor = DEFAULT_FLOOR;
        this.nextFloor = DEFAULT_FLOOR;
        this.destinationList = new PriorityQueue<Integer>();
    }

    public int getDirection() {
        return nextFloor - currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    /*public void setDirection(ElevatorDirection direction) {
        this.direction = direction;
    }*/

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getNextFloor() {
        return this.nextFloor;
    }

    public void moveUp() {
        currentFloor++;
    }

    public void moveDown() {
        currentFloor--;
    }

    public void addDestination(int floor) {
        if (nextFloor != floor && !destinationList.contains(floor)) {
            if ((floor - this.getCurrentFloor()) *
                    (floor - this.getNextFloor()) < 0) {
                destinationList.add(nextFloor);
                nextFloor = floor;
            } else {
                destinationList.add(floor);
            }
        }
    }

    public void move() {
        if (getDirection() > 0) {
            currentFloor++;
        } else if (getDirection() < 0) {
            currentFloor--;
        }
    }

    public void popDestination() {
        if (destinationList.isEmpty()) {
            nextFloor = currentFloor;
        }
        else {
            nextFloor = destinationList.poll();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Elevator)) {
            return false;
        }
        return this.id == ((Elevator) o).id;
    }
}
