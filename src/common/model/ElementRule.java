package common.model;

/**
 *
 * @author Glaskani
 */
class ElementRule {
    
    private TypeElement te;
    private Property p;
    
    ElementRule(TypeElement te,Property p) {
        this.te =te;
        this.p =p;
    }
    
    Property getProperty() {
        return this.p;
    }
    
    TypeElement getTypeElement() {
        return this.te;
    }
    
}
