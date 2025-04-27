package com.tournament.management.enums;

public enum FormulaType {
    AVERAGE_ALL,          // Simple average of all judges
    DROP_HIGHEST_LOWEST,  // Discard min and max, average middle scores
    WEIGHTED,             // Weighted average based on rule weight
    SUM,                  // Sum of all scores
    BONUS_SINGLE,         // ➕ Single score
    PENALTY_SINGLE,       // ➖ Single score
    CUSTOM                // (future) admin-supplied formula
}
