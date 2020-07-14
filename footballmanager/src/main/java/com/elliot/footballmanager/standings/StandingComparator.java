package com.elliot.footballmanager.standings;

import com.elliot.footballmanager.entity.Standing;
import java.util.Comparator;
import java.util.List;

public class StandingComparator {

    public static List<Standing> orderTableByPoints(List<Standing> table) {
        Comparator.comparing(Standing::getPoints)
                .thenComparing(Standing::getGoalDifference)
                .thenComparing(Standing::getFootballTeamId);
        updateTablePositions(table);
        return table;
    }

    private static void updateTablePositions(List<Standing> table) {
        for (int i = 0; i < table.size(); i++) {
            table.get(i).setTablePosition(i + 1);
        }
    }
}
