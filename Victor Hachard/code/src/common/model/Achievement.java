package common.model;

/**
 *
 * @author Glaskani
 */
public enum Achievement {
    
    MOVE("Deplacement", "Deplaces toi"),
    WIN("Gagner", "Gagne"),
    RELOAD("Recomence","Reload le niveux");
    
    private final String name;
    private final String description;
    
    Achievement(String name, String description) {
        this.description = description;
        this.name = name;
    }

    /**
     * @return le nom du succe
     */
    public String getName() {
        return name;
    }

    /**
     * @return la description du succe
     */
    public String getDescription() {
        return description;
    }
}
