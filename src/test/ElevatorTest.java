package test;

import main.Elevator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Anirban on 10/25/2016.
 */
public class ElevatorTest {
    private Elevator elevator;

    private static final int FLOOR_1 = 1;
    private static final int FLOOR_5 = 5;
    private static final int FLOOR_9 = 9;

    @Before
    public void setUp() {
        elevator = new Elevator();
    }

    @Test
    public void testMoveUp() {
        int oldFloor = elevator.getCurrentFloor();
        elevator.moveUp();
        assertEquals(oldFloor + 1, elevator.getCurrentFloor());
    }

    @Test
    public void testMoveDown() {
        int oldFloor = elevator.getCurrentFloor();
        elevator.moveDown();
        assertEquals(oldFloor - 1, elevator.getCurrentFloor());
    }

    @Test
    public void addDestination() {
        elevator.addDestination(FLOOR_5);
        assertEquals(FLOOR_5, elevator.getNextFloor());
    }

    @Test
    public void popDestination() {
        elevator.addDestination(FLOOR_5);
        elevator.popDestination();
        assertEquals(elevator.getCurrentFloor(), elevator.getNextFloor());
    }

    @Test
    public void testGetDirectionUp() {
        elevator.addDestination(FLOOR_5);
        assertTrue(elevator.getDirection() > 0);
    }

    @Test
    public void testGetDirectionDown() {
        elevator.moveUp();
        elevator.addDestination(FLOOR_1);
        assertTrue(elevator.getDirection() < 0);
    }

    @Test
    public void testGetDirectionStand() {
        elevator.addDestination(FLOOR_1);
        assertEquals(0, elevator.getDirection());
    }

    @Test
    public void testMultiDestination() {
        elevator.addDestination(FLOOR_9);
        elevator.addDestination(FLOOR_5);
        assertEquals(FLOOR_5, elevator.getNextFloor());
    }
}
