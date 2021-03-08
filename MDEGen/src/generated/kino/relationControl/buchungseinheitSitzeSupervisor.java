/**--- Generated at Mon Mar 08 14:44:48 CET 2021 
 * --- Change only in Editable Sections!  
 * --- Do not touch section numbering!   
 */
package generated.kino.relationControl;
//10 ===== GENERATED:      Import Section =========
import relationManagement.Relation;
import db.executer.PersistenceException;
import generated.kino.proxies.*;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class buchungseinheitSitzeSupervisor
{
   //30 ===== GENERATED:      Attribute Section ======
   private static buchungseinheitSitzeSupervisor theInstance = new buchungseinheitSitzeSupervisor();
   private Relation<IBuchungsEinheit,ISitz> elements;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private buchungseinheitSitzeSupervisor(){
      this.elements = new Relation<>("buchungseinheitSitze");
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public static buchungseinheitSitzeSupervisor getInstance(){return theInstance;}
   public ISitz getSitz(IBuchungsEinheit owner){
      return this.elements.getRelatedTargets(owner).isEmpty() ? null : this.elements.getRelatedTargets(owner).get(0);
   }
   public void set(IBuchungsEinheit owner, ISitz target) throws PersistenceException{
      ISitz targetOld = this.getSitz(owner); ISitz targetNew = target;
      this.elements.change(owner, targetOld, targetNew);
   }
   public void setAlreadyPersistent(IBuchungsEinheit owner, ISitz target) {
      ISitz targetOld = null; ISitz targetNew = target;
      this.elements.setAlreadyPersistent(owner, targetNew);
   }
   public void change(IBuchungsEinheit owner, ISitz targetOld, ISitz targetNew) throws PersistenceException{
      this.elements.change(owner, targetOld, targetNew);
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
