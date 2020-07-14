package com.elliot.footballmanager.entity;

import com.elliot.footballmanager.standings.StandingComparator;
import java.util.ArrayList;
import java.util.List;

/**
 * Collection of individual standings creating the table
 * of all FootballTeams for a given league.
 * @author Elliot
 */
public class LeagueTable {

    List<Standing> table;

    //TODO: Replace calls for the list of standings with the table object holding the list itself

    public LeagueTable() {

    }

    public LeagueTable(List<Standing> table) {
        this.table = table;
        sortTable();
    }

    public void addStandingToTable(Standing standing) {
        if (table == null) {
            table = new ArrayList<Standing>();
        }
        table.add(standing);
        sortTable();
    }

    private void sortTable() {
        StandingComparator.orderTableByPoints(table);
    }

    public List<Standing> getTable() {
        return table;
    }
}
