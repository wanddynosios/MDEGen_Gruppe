/**--- Generated at Sat Mar 06 17:55:52 CET 2021 
 * --- Change only in Editable Sections!  
 * --- Do not touch section numbering!   
 */
package generated.kino.relationControl;
//10 ===== GENERATED:      Import Section =========
import relationManagement.Relation;
import db.executer.PersistenceException;
import generated.kino.proxies.*;
import java.util.Set;
import java.util.stream.Collectors;
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
   public Set<ISitz> getSitz(IBuchungsEinheit owner){
      return this.elements.getRelatedTargets(owner).stream().collect(Collectors.toSet());
   }
   public void add(IBuchungsEinheit owner, ISitz target) throws PersistenceException{
      this.elements.addElement(owner,target);
   }
   public void addAlreadyPersistent(IBuchungsEinheit owner, ISitz target) throws PersistenceException{
      this.elements.addElementAlreadyPersistent(owner,target);
   }
   public boolean remove(IBuchungsEinheit owner, ISitz target) throws PersistenceException{
      boolean loop = this.removeOnce(owner, target);
      boolean result = loop;
      while(loop) loop = this.removeOnce(owner, target);
      return result;
   }
   private boolean removeOnce(IBuchungsEinheit owner, ISitz target) throws PersistenceException{
      return this.elements.removeElement(owner,target);
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
