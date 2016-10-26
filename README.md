# elevator-system

<p>
This is a system representing elevators in a building. There can be up to 16 elevators, and the task is to efficiently manage the movement of the elevators.
</p>

<p>
Every elevator has a inner work list which contains the floors requested by users from inside that elevator. All the elevators also have a shared outer work list which contains the floors requested by users from outside the elevators. Changes to these are made by the Scheduler.
</p>

<p>
I have improved upon the FCFS (First-Come-First-Serve) algorithm by adding those requests in the outer request list that are in the current path of some elevator to that elevator's inner work list.
I have also made sure that every elevator goes to the next closest requested floor on its inner request list after it has completed a request. This ensures that the elevator does not follow the FCFS rule.
</p>
