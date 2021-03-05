/**--- Generated at Fri Mar 05 15:18:57 CET 2021 
 * --- Change only in Editable Sections!  
 * --- Do not touch section numbering!   
 */
package generated.kino.relationControl;
//10 ===== GENERATED:      Import Section =========
import relationManagement.Relation;
import db.executer.PersistenceException;
import generated.kino.proxies.*;
import exceptions.ConstraintViolation;
import java.util.List;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class reihenSitzeSupervisor
{
   //30 ===== GENERATED:      Attribute Section ======
   private static reihenSitzeSupervisor theInstance = new reihenSitzeSupervisor();
   private Relation<IReihe,ISitz> elements;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private reihenSitzeSupervisor(){
      this.elements = new Relation<>("reihenSitze");
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public static reihenSitzeSupervisor getInstance(){return theInstance;}
   public List<ISitz> getSitze(IReihe owner){
      return this.elements.getRelatedTargets(owner);
   }
   public void add(IReihe owner, ISitz target) throws ConstraintViolation, PersistenceException{
      this.elements.willViolateInjectivity(owner, target);
      this.elements.addElement(owner,target);
   }
   public void addAlreadyPersistent(IReihe owner, ISitz target) throws ConstraintViolation, PersistenceException{
      this.elements.willViolateInjectivity(owner, target);
      this.elements.addElementAlreadyPersistent(owner,target);
   }
   public boolean remove(IReihe owner, ISitz target) throws ConstraintViolation, PersistenceException{
      this.elements.willViolateSurjectivity(owner, target);
      return this.elements.removeElement(owner,target);
   }
   public IReihe getReihe(ISitz target){
      return this.elements.getRelatedSources(target).get(0);
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
