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
public class saalReihenSupervisor
{
   //30 ===== GENERATED:      Attribute Section ======
   private static saalReihenSupervisor theInstance = new saalReihenSupervisor();
   private Relation<ISaal,IReihe> elements;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private saalReihenSupervisor(){
      this.elements = new Relation<>("saalReihen");
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public static saalReihenSupervisor getInstance(){return theInstance;}
   public List<IReihe> getReihen(ISaal owner){
      return this.elements.getRelatedTargets(owner);
   }
   public void add(ISaal owner, IReihe target) throws PersistenceException{
      this.elements.addElement(owner,target);
   }
   public void addAlreadyPersistent(ISaal owner, IReihe target) throws PersistenceException{
      this.elements.addElementAlreadyPersistent(owner,target);
   }
   public boolean remove(ISaal owner, IReihe target) throws PersistenceException{
      return this.elements.removeElement(owner,target);
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
