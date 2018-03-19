package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Glaskani
 */
public class Placement extends Subject {
    
    private List<Element> listeContenu;
    
    /**
     * 
     * @param e Element
     */
    public Placement(Element e) {
        listeContenu = new ArrayList<>();
        listeContenu.add(new Element(e));
    }
    
    //Getters
 
    /**
     * Revois une liste d'Element.
     * @return ArrayListElement
     */
    public ArrayList<Element> getListeContenu() {
        return new ArrayList(this.listeContenu);
    }
    
    /**
     * Ajoute e si il n'est pas déja dans la liste d'Element
     * @param e Element
     */
    void addElement(Element e){
        if(this.listeContenu.contains(e))
            return;
        this.listeContenu.add(e);
        
        Collections.sort(listeContenu, new Comparator<Element>() {
            @Override
            public int compare(Element e1, Element e2) {
                return e1.getTypeElements().getPriority() - e2.getTypeElements().getPriority();
            }
        });
    }
    
    /**
     * 
     * @param e 
     */
    void removeElement(TypeElements e){
            listeContenu.remove(getElements(e));
    }
    
    /**
     * Revois l'Element ??
     * @param te TypeElements
     * @return Element ou null
     */
    Element get(TypeElements te) {
        for(Element e:this.listeContenu)
            if(e.getTypeElements()==te)
                return e;
        return null;
    }
    
    /**
     * Revois true si on peux push un element.
     * @return true ou false
     */
    boolean canPush() {
        return findRule(Property.PUSH);
    }
        
    /**
     * Revois true si on peux add un element.
     * @return true ou false
     */
    boolean canAdd() {
<<<<<<< HEAD
        return !findRule(Property.STOP) || findRule(Property.PUSH); //demander makoto si le PUSH et utile
=======
        return !findRule(Property.STOP) && !findRule(Property.PUSH);
>>>>>>> 5918d435494464f1f460897a45525efd0f90e6d3
    }
    
    /**
     * Revois true si tr est bien dans la liste des régles de l'element.
     * @param tr Property
     * @return true ou false
     */
    private boolean findRule(Property tr){
        for(Element e:this.listeContenu)
            if(e.getTypeRule().contains(tr))
                return true; 
        return false;
    }
    
    /**
     * 
     * @return List<Element>
     */
    List<Element> getAllElement(){
        List<Element> le = new ArrayList<>();
        
        for(Element e:this.listeContenu)
            le.add(new Element(e));
        
        return le;
    }
    
    /**
     * 
     * @param te
     * @return 
     */
    boolean findElements(TypeElements te){
        for(Element e:this.listeContenu)
            if(e.getTypeElements()==te)
                return true;
        return false;
    }
    
    /**
     * 
     * @param te
     * @return 
     */
    Element getElements(TypeElements te){
        for(Element e:this.listeContenu)
            if(e.getTypeElements()==te)
                return e;
        return null;
    }
    
    /**
     * 
     * @param tr
     * @return 
     */
    List<Element> getElementsOf(Property tr){
        List<Element> ret = new ArrayList<>();
        
        for(Element e:this.listeContenu)
            if(e.getTypeRule().contains(tr))
                ret.add(e);
        
        return ret;
    }
}
