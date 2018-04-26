package common.model;

/**
 *
 * @author Glaskani
 */
public enum Achievement {
    
    MOVE("Deplacement", "Deplaces toi"),
    WIN("Gagner", "Gagne"),
    VICTORY("Victory", "Fini le jeu."),
    RELOAD("Recomence","Reload le niveux");
    
    private String name;
    private String description;

    Achievement(String name, String description) {
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
