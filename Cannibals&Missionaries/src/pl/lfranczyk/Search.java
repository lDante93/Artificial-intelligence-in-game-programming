package pl.lfranczyk;

import java.util.ArrayList;

class Search {

    ArrayList<State> list, historyList;


    Search(State choosenState) {
        list = new ArrayList<>();
        historyList = new ArrayList<>();
        list.add(choosenState);
        historyList.add(choosenState);


    }

    void lookup() {
        final Long timeStart = System.currentTimeMillis();
        while (true) {
            //Selecting first state
            State state = selectState();
            if (isEndState(state)) {
                System.out.println("Found correct State: " + state.printState() + "\n");

                System.out.println("Tree of parents: " + state.toString());
                break;
            }
            //expands selected state removed from list
            ArrayList<State> tmpList = expandState(state);

            //filters states for matching boundaries and if is new
            tmpList = filter(tmpList);

            //adding filtered states to list and historyList
            actualVariables(tmpList);

        }
        final Long timeEnd = System.currentTimeMillis();
        System.out.println("Time of processed program: " + (timeEnd - timeStart) + "ms");
    }

    State selectState() {
        try {
            return list.remove(0);
        } catch (Exception e) {
            System.err.println("\n" +
                    "It is not possible to find a solution for a given case.");
            System.exit(1);
            return null;
        }
    }

    boolean isEndState(State state) {

        if ((state.getLKL() == 0) && (state.getLML() == 0) && (!state.isBoatOnLeft())) {
            return true;
        } else {
            return false;
        }
    }

    ArrayList<State> filter(ArrayList<State> actualList) {
        ArrayList<State> tmpList = new ArrayList<>();

        for (State state : actualList) {
            //check if actual state is new and match boundaries
            if (isNew(state) && checkBoundaries(state)) {
                tmpList.add(state);
            }

        }
        return tmpList;
    }

    boolean checkBoundaries(State state) {

        if ((state.getLKL() <= state.getLML() || state.getLML() == 0) &&
                (state.getLKP() <= state.getLMP() || state.getLMP() == 0)) {
            return true;
        } else {
            return false;
        }
    }


    boolean isNew(State state) {

        for (State historyState : historyList) {
            if (state.getLKL() == historyState.getLKL() &&
                    state.getLKP() == historyState.getLKP() &&
                    state.getLML() == historyState.getLML() &&
                    state.getLMP() == historyState.getLMP() &&
                    state.isBoatOnLeft() == historyState.isBoatOnLeft()
                    ) {
                return false;
            }
        }
        return true;
    }


    void actualVariables(ArrayList<State> tmpList) {

        list.addAll(tmpList);
        historyList.addAll(tmpList);

    }


    ArrayList<State> expandState(State actualState) {
        ArrayList<State> tmp = new ArrayList<>();
        if (actualState.isBoatOnLeft()) {

            if (actualState.getLKL() > 0) {
                tmp.add(new State(
                        actualState.getLKL() - 1,
                        actualState.getLML(),
                        actualState.getLKP() + 1,
                        actualState.getLMP(),
                        !actualState.isBoatOnLeft(),
                        actualState
                ));
            }

            if (actualState.getLML() > 0) {
                tmp.add(new State(
                        actualState.getLKL(),
                        actualState.getLML() - 1,
                        actualState.getLKP(),
                        actualState.getLMP() + 1,
                        !actualState.isBoatOnLeft(),
                        actualState
                ));
            }

            if (actualState.getLKL() > 1) {
                tmp.add(new State(
                        actualState.getLKL() - 2,
                        actualState.getLML(),
                        actualState.getLKP() + 2,
                        actualState.getLMP(),
                        !actualState.isBoatOnLeft(),
                        actualState
                ));
            }

            if (actualState.getLML() > 1) {
                tmp.add(new State(
                        actualState.getLKL(),
                        actualState.getLML() - 2,
                        actualState.getLKP(),
                        actualState.getLMP() + 2,
                        !actualState.isBoatOnLeft(),
                        actualState
                ));
            }

            if (actualState.getLKL() > 0 && actualState.getLML() > 0) {
                tmp.add(new State(
                        actualState.getLKL() - 1,
                        actualState.getLML() - 1,
                        actualState.getLKP() + 1,
                        actualState.getLMP() + 1,
                        false,
                        actualState
                ));
            }


        } else {

            if (actualState.getLKP() > 0) {
                tmp.add(new State(
                        actualState.getLKL() + 1,
                        actualState.getLML(),
                        actualState.getLKP() - 1,
                        actualState.getLMP(),
                        !actualState.isBoatOnLeft(),
                        actualState
                ));
            }

            if (actualState.getLMP() > 0) {
                tmp.add(new State(
                        actualState.getLKL(),
                        actualState.getLML() + 1,
                        actualState.getLKP(),
                        actualState.getLMP() - 1,
                        !actualState.isBoatOnLeft(),
                        actualState
                ));
            }

            if (actualState.getLKP() > 1) {
                tmp.add(new State(
                        actualState.getLKL() + 2,
                        actualState.getLML(),
                        actualState.getLKP() - 2,
                        actualState.getLMP(),
                        !actualState.isBoatOnLeft(),
                        actualState
                ));
            }

            if (actualState.getLMP() > 1) {
                tmp.add(new State(
                        actualState.getLKL(),
                        actualState.getLML() + 2,
                        actualState.getLKP(),
                        actualState.getLMP() - 2,
                        !actualState.isBoatOnLeft(),
                        actualState
                ));
            }

            if (actualState.getLKP() > 0 && actualState.getLMP() > 0) {
                tmp.add(new State(
                        actualState.getLKL() + 1,
                        actualState.getLML() + 1,
                        actualState.getLKP() - 1,
                        actualState.getLMP() - 1,
                        true,
                        actualState
                ));
            }

        }

        return tmp;
    }
}