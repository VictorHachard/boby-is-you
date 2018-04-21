package common.model;

/**
 *
 * @author Glaskani
 */
public enum Achievement {
    
    MOVE("Deplacment", "Move"),
    WIN("Gagner", "Gagner"),
    VICTORY("Victory", "Win a game."),
    DEATHMATCH("Deathmatch", "Reach the deathmatch."),
    SOCLOSE("So Close...", "Get killed in the deathmatch."),
    FASTIRON("That was fast!", "Get a iron sword within 5 minutes of game start."),
    FIRSTKILL("First Kill", "Get the first kill of the round."),
    KILLSTREAK("Killstreak", "Get 5+ kills in one game."),
    OINK("Oink", "Kill a pig."),
    ARCHERHERO("Archer Hero", "Get 2+ bowkills in one game.");
    
    private String name;
    private String description;

    Achievement(String description, String name) {
        this.description = description;
        this.name = name;
    }

    /**
     * @return the nicename
     */
    public String getName() {
        return name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
}
