/**--- Generated at Mon Mar 08 12:14:38 CET 2021 
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
public class buchungsBuchungseinheitenSupervisor
{
   //30 ===== GENERATED:      Attribute Section ======
   private static buchungsBuchungseinheitenSupervisor theInstance = new buchungsBuchungseinheitenSupervisor();
   private Relation<IBuchung,IBuchungsEinheit> elements;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private buchungsBuchungseinheitenSupervisor(){
      this.elements = new Relation<>("buchungsBuchungseinheiten");
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public static buchungsBuchungseinheitenSupervisor getInstance(){return theInstance;}
   public Set<IBuchungsEinheit> getBuchungsEinheiten(IBuchung owner){
      return this.elements.getRelatedTargets(owner).stream().collect(Collectors.toSet());
   }
   public void add(IBuchung owner, IBuchungsEinheit target) throws PersistenceException{
      this.elements.addElement(owner,target);
   }
   public void addAlreadyPersistent(IBuchung owner, IBuchungsEinheit target) throws PersistenceException{
      this.elements.addElementAlreadyPersistent(owner,target);
   }
   public boolean remove(IBuchung owner, IBuchungsEinheit target) throws PersistenceException{
      boolean loop = this.removeOnce(owner, target);
      boolean result = loop;
      while(loop) loop = this.removeOnce(owner, target);
      return result;
   }
   private boolean removeOnce(IBuchung owner, IBuchungsEinheit target) throws PersistenceException{
      return this.elements.removeElement(owner,target);
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
