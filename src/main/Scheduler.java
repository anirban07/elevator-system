package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Anirban on 10/25/2016.
 */

/**
 * This class represents a scheduler for elevators.
 * It can take requests from users outside elevators (from different floors)
 * and from users inside the elevators.
 * It also moves every elevator based on the common outer requests and individual
 * inner requests.
 */
public class Scheduler {
    public static final int MAX_ELEVATORS = 16;
    private List<Integer> requestList;
    private List<Elevator> elevators;

    public Scheduler(int numElevators) {
        if (numElevators > MAX_ELEVATORS || numElevators < 1) {
            throw new IllegalArgumentException();
        }
        elevators = new ArrayList<>(numElevators);
        for (int i = 0; i < numElevators; i++) {
            elevators.add(new Elevator());
        }
        requestList = new ArrayList<>();
    }

    public void makeInnerRequest(int elevatorId, int floor) {
        if (floor < 1 || elevatorId < 0 || elevatorId > elevators.size() - 1) {
            throw new IllegalArgumentException();
        }
        elevators.get(elevatorId).addDestination(floor);
    }

    public void makeOuterRequest(int floor) {
        if (floor < 1) {
            throw new IllegalArgumentException();
        }
        requestList.add(floor);
    }

    public void step() {
        for (Elevator elevator : elevators) {
            if (elevator.getDirection() == 0) {
                // The elevator reached destination and/or its destination list is empty
                elevator.popDestination();
            }
            // if the elevator is standing still, then its destination list is empty
            if (elevator.getDirection() == 0) {
                // assign it its nearest requested floor
                int nextClosestRequest = getClosestRequest(elevator);
                if (nextClosestRequest != -1) {
                    elevator.addDestination(nextClosestRequest);
                }
            } else {  // elevator moving. Add any requests that are in its path
                for (int requestFloor : requestList) {
                    // add every request floor which is in between curr and next
                    if ((requestFloor - elevator.getCurrentFloor()) *
                            (requestFloor - elevator.getNextFloor()) < 0) {
                        elevator.addDestination(requestFloor);
                    }
                }
            }
            // destination list of this elevator updated
            // move the elevator if nextFloor != currFloor
            elevator.move();
        }
    }

    private int getClosestRequest(Elevator elevator) {
        if (requestList.isEmpty()) return -1;
        int min = requestList.get(0);
        int minDist = Math.abs(elevator.getCurrentFloor() - min);
        for (int requestFloor : requestList) {
            min = Math.abs(requestFloor - elevator.getCurrentFloor()) < minDist ? requestFloor : min;
        }
        return min;
    }
}
