package main;


import java.util.ArrayList;
import java.util.LinkedList;
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
    private int currentFloor;
    private int nextFloor;
    private List<Integer> destinationList;

    static {
        elevatorCount = 0;
    }

    public Elevator() {
        this.id = elevatorCount;
        elevatorCount++;
        this.currentFloor = DEFAULT_FLOOR;
        this.nextFloor = DEFAULT_FLOOR;
        this.destinationList = new LinkedList<Integer>();
    }

    public int getDirection() {
        return nextFloor - currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

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
            if (getDirection() == 0 && destinationList.isEmpty()) {
                nextFloor = floor;
            } else if ((floor - this.getCurrentFloor()) *
                        (floor - this.getNextFloor()) < 0) {
                // The requested floor is on the way to the current destination.
                // Update the current destination to the requested floor
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
            nextFloor = popClosestRequest();
        }
    }

    private int popClosestRequest() {
        if (destinationList.isEmpty()) return currentFloor;
        int closest = destinationList.get(0);
        int minDist = Math.abs(currentFloor - closest);
        for (int floor : destinationList) {
            closest = Math.abs(floor - currentFloor) < minDist ? floor : closest;
        }
        destinationList.remove(Integer.valueOf(closest));
        return closest;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Elevator)) {
            return false;
        }
        return this.id == ((Elevator) o).id;
    }
}
