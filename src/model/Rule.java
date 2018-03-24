package model;

/**
 *
 * @author Glaskani
 */
public class Rule implements Observer {

    @Override
    public void update(Subject obsarvebel,TypeTypeElement te1,TypeTypeElement te2) { //doit text rule soit text text
        if (te2==TypeTypeElement.RULE) //text rule
            changeRule(te1,te2);
        else changeType(te1,te2);
    }
    
    private void changeType(TypeTypeElement te1,TypeTypeElement te2) {
        //get texte
        //get texte to
        
       //change
    }
    
    private void changeRule(TypeTypeElement te1,TypeTypeElement te2) {
        //get texte
        //get rule
        
        //delete if il y a la rule pour tout les elem
        
        //add dans le nouveau
    }
}
