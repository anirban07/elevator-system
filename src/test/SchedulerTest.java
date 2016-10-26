package test;

import main.Elevator;
import main.Scheduler;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by Anirban on 10/25/2016.
 */
public class SchedulerTest {
    private Scheduler scheduler;

    private static final int FLOOR_1 = 1;
    private static final int FLOOR_5 = 5;
    private static final int FLOOR_9 = 9;

    @Before
    public void setUp() {
        scheduler = new Scheduler(5);
    }

    @Test
    public void testRegisteringInnerRequest() {
        scheduler.makeInnerRequest(0, FLOOR_5);
        assertEquals(FLOOR_5, scheduler.getElevators().get(0).getNextFloor());
    }

    @Test
    public void testRegisteringOuterRequest() {
        scheduler.makeOuterRequest(FLOOR_9);
        assertEquals(1, scheduler.getRequestList().size());
        assertEquals(FLOOR_9, scheduler.getRequestList().get(0).intValue());
    }

    @Test
    public void testMakingOuterRequest() {
        scheduler.makeOuterRequest(FLOOR_5);
        for (int i = 0; i < 5; i++) {
            scheduler.step();
        }
        assertEquals(0, scheduler.getRequestList().size());
    }

    @Test
    public void testMakingInnerRequest() {
        Elevator elevator = scheduler.getElevators().get(0);
        elevator.setCurrentFloor(7);
        scheduler.makeInnerRequest(0, FLOOR_5);
        for (int i = 0; i < 2; i++) {
            scheduler.step();
        }
        assertEquals(5, elevator.getCurrentFloor());
        assertEquals(0, elevator.getDirection());
    }

    @Test
    public void testInnerRequestIndependence() {
        Elevator elevator = scheduler.getElevators().get(0);
        elevator.setCurrentFloor(7);
        scheduler.makeInnerRequest(0, FLOOR_5);
        for (int i = 0; i < 2; i++) {
            scheduler.step();
        }
        List<Elevator> elevatorList = scheduler.getElevators();
        for (int i = 1; i < 5; i++) {
            assertEquals(FLOOR_1, elevatorList.get(i).getCurrentFloor());
        }
    }

}
