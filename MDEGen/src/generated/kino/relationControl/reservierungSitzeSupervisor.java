/**--- Generated at Fri Mar 05 15:18:57 CET 2021 
 * --- Change only in Editable Sections!  
 * --- Do not touch section numbering!   
 */
package generated.kino.relationControl;
//10 ===== GENERATED:      Import Section =========
import relationManagement.Relation;
import db.executer.PersistenceException;
import generated.kino.proxies.*;
import java.util.List;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class reservierungSitzeSupervisor
{
   //30 ===== GENERATED:      Attribute Section ======
   private static reservierungSitzeSupervisor theInstance = new reservierungSitzeSupervisor();
   private Relation<IResevierung,ISitz> elements;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private reservierungSitzeSupervisor(){
      this.elements = new Relation<>("reservierungSitze");
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public static reservierungSitzeSupervisor getInstance(){return theInstance;}
   public List<ISitz> getSitze(IResevierung owner){
      return this.elements.getRelatedTargets(owner);
   }
   public void add(IResevierung owner, ISitz target) throws PersistenceException{
      this.elements.addElement(owner,target);
   }
   public void addAlreadyPersistent(IResevierung owner, ISitz target) throws PersistenceException{
      this.elements.addElementAlreadyPersistent(owner,target);
   }
   public boolean remove(IResevierung owner, ISitz target) throws PersistenceException{
      return this.elements.removeElement(owner,target);
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
