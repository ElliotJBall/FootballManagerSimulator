package com.elliot.footballmanager.match.model;

import com.elliot.footballmanager.match.engine.MatchEngine;
import com.elliot.footballmanager.match.engine.MatchEngineMediator;

/**
 *
 * @author Elliot
 */
public abstract class MatchEvent {

    protected void doesEventNeedToBeLogged() {
        if (MatchEngine.isLoggingGameEvents()) {
            outputMatchEventString(buildMatchEventString());
        }
    }

    protected String getCurrentGameTime() {
        return "[" + MatchEngine.getCurrentTimeInGame() + "]";
    }

    protected abstract String buildMatchEventString();

    protected void outputMatchEventString(String message) {
        MatchEngineMediator.printMessageToConsole(message);
    }
}
