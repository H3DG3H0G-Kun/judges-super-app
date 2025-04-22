package com.tournament.scoring.enums;

public enum WebSocketTopic {
    SCORES("/topic/scores/%s"),
    SCORE_SUBMITTED("/topic/score-submitted/%s"),
    JUDGE_LOCKED("/topic/judge-locked/%s"),
    PANEL_STATUS("/topic/panel-status/%s"),
    CURRENT_SPORTSMAN("/topic/current-sportsman/%s"),
    SPORTSMAN_FINISHED("/topic/sportsman-finished/%s"),
    JUDGE_READY("/topic/judge-ready/%s"),
    SESSION_STARTED("/topic/session-started/%s"),
    SESSION_ENDED("/topic/session-ended/%s"),
    MAIN_JUDGE_OVERRIDE("/topic/main-judge-override/%s"),
    SCORE_RECALCULATED("/topic/score-recalculated/%s"),
    ALERT("/topic/alert/%s");

    private final String topic;

    WebSocketTopic(String topic) {
        this.topic = topic;
    }

    public String format(Object id) {
        return String.format(this.topic, id);
    }
}
