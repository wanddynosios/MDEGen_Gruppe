/**--- Generated at Fri Mar 05 15:18:57 CET 2021 
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
public class vorfuehrungSaalSupervisor
{
   //30 ===== GENERATED:      Attribute Section ======
   private static vorfuehrungSaalSupervisor theInstance = new vorfuehrungSaalSupervisor();
   private Relation<IVorfuehrung,ISaal> elements;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private vorfuehrungSaalSupervisor(){
      this.elements = new Relation<>("vorfuehrungSaal");
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public static vorfuehrungSaalSupervisor getInstance(){return theInstance;}
   public ISaal getSaal(IVorfuehrung owner){
      return this.elements.getRelatedTargets(owner).isEmpty() ? null : this.elements.getRelatedTargets(owner).get(0);
   }
   public void set(IVorfuehrung owner, ISaal target) throws PersistenceException{
      ISaal targetOld = this.getSaal(owner); ISaal targetNew = target;
      this.elements.change(owner, targetOld, targetNew);
   }
   public void setAlreadyPersistent(IVorfuehrung owner, ISaal target) {
      ISaal targetOld = null; ISaal targetNew = target;
      this.elements.setAlreadyPersistent(owner, targetNew);
   }
   public void change(IVorfuehrung owner, ISaal targetOld, ISaal targetNew) throws PersistenceException{
      this.elements.change(owner, targetOld, targetNew);
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
