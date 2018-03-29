package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Glaskani
 */
public class Placement {
    
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
    public List<Element> getListeContenu() {
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
    void removeElement(TypeElement e){
        listeContenu.remove(getElements(e));
    }
       
    /**
     * Revois true si on peux push un element.
     * @return true ou false
     */
    boolean canPush() {
        return findRule(Property.PUSH) && !findRule(Property.STOP);
    }
        
    /**
     * Revois true si on peux add un element.
     * @return true ou false
     */
    boolean canAdd() {
        return !findRule(Property.STOP) && !findRule(Property.PUSH);
    }
    
    /**
     * Revois true si tr est bien dans la liste des régles de l'element.
     * @param tr Property
     * @return true ou false
     */
    boolean findRule(Property tr){
        for(Element e:this.listeContenu)
            if(e.getTypeRule().contains(tr))
                return true; 
        return false;
    }
    
    /**
     * 
     * @param te
     * @return 
     */
    boolean findElements(TypeElement te){
        for(Element e:this.listeContenu)
            if(e.getTypeElements()==te)
                return true;
        return false;
    }
    
    /**
     * 
     * @param ty
     * @return 
     */
    boolean findTypeType(TypeTypeElement ty) {
        for(Element e:this.listeContenu)
            if(e.getTypeTypeElements()==ty)
                return true;
        return false;
    }
    
    /**
     * 
     * @param ty
     * @return 
     */
    TypeElement findTypeElement(TypeTypeElement ty) {
        for(Element e:this.listeContenu)
            if(e.getTypeTypeElements()==ty)
                return e.getTypeElements();
        return null;
    }
    
    /**
     * 
     * @param te
     * @return 
     */
    Element getElements(TypeElement te){
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
