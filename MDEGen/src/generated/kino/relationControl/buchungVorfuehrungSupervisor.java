/**--- Generated at Sat Mar 06 17:46:50 CET 2021 
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
public class buchungVorfuehrungSupervisor
{
   //30 ===== GENERATED:      Attribute Section ======
   private static buchungVorfuehrungSupervisor theInstance = new buchungVorfuehrungSupervisor();
   private Relation<IBuchung,IVorfuehrung> elements;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private buchungVorfuehrungSupervisor(){
      this.elements = new Relation<>("buchungVorfuehrung");
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public static buchungVorfuehrungSupervisor getInstance(){return theInstance;}
   public IVorfuehrung getVorfuehrung(IBuchung owner){
      return this.elements.getRelatedTargets(owner).isEmpty() ? null : this.elements.getRelatedTargets(owner).get(0);
   }
   public void set(IBuchung owner, IVorfuehrung target) throws PersistenceException{
      IVorfuehrung targetOld = this.getVorfuehrung(owner); IVorfuehrung targetNew = target;
      this.elements.change(owner, targetOld, targetNew);
   }
   public void setAlreadyPersistent(IBuchung owner, IVorfuehrung target) {
      IVorfuehrung targetOld = null; IVorfuehrung targetNew = target;
      this.elements.setAlreadyPersistent(owner, targetNew);
   }
   public void change(IBuchung owner, IVorfuehrung targetOld, IVorfuehrung targetNew) throws PersistenceException{
      this.elements.change(owner, targetOld, targetNew);
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
