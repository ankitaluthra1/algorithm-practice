package dp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NQueenProblem {
    int n;

    class Row {
        int occupiedPos;

        void place(int i) {
            this.occupiedPos = i;
        }

        @Override
        public String toString() {
            StringBuilder sg = new StringBuilder();
            for (int k = 0; k < n; k++) {
                if (k == occupiedPos)
                    sg.append("Q");
                else
                    sg.append(".");
            }
            return sg.toString();
        }
    }

    List<List<String>> solution;

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        solution = new ArrayList<>();
        canBePlaced(new ArrayList<>());
        return solution;
    }

    private void canBePlaced(List<Row> rows) {
        if (rows.size() == n) {
            solution.add(rows.stream().map(r -> r.toString()).collect(Collectors.toList()));
        }

        for (int i = 0; i < n; i++) {
            boolean possible = true;
            for (Row curr : rows) {
                if (curr.occupiedPos == i) {
                    possible = false;
                    break;
                }
            }
                if (isNeighbor(rows, i))
                    possible = false;
            if (possible) {
                List<Row> newRows = new ArrayList<>(rows);
                Row currentRow = new Row();
                currentRow.place(i);
                newRows.add(currentRow);
                canBePlaced(newRows);
            }
        }
    }

    private boolean isNeighbor(List<Row> rows, int col) {
        int diff = 1;
        for (int i = rows.size() - 1; i >= 0; i--) {
            if (col - diff < 0)
                break;
            if(rows.get(i).occupiedPos == col - diff)
                return true;
            diff++;
        }

        int sum = 1;
        for (int i = rows.size() - 1; i >= 0; i--) {
            if (col + sum > n)
                break;
            if(rows.get(i).occupiedPos == col + sum)
                return true;
            sum++;
        }

        return false;
    }

    public static void main(String[] args) {

        NQueenProblem np = new NQueenProblem();
        List<List<String>> lists = np.solveNQueens(4);

        for (List<String> s : lists) {
            for (String s1 : s) {
                System.out.println(s1);
            }
            System.out.println("--------------------------");
        }

    }
}
