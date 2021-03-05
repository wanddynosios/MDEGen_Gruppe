/**--- Generated at Fri Mar 05 18:16:22 CET 2021 
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
public class reservierungKategorieSupervisor
{
   //30 ===== GENERATED:      Attribute Section ======
   private static reservierungKategorieSupervisor theInstance = new reservierungKategorieSupervisor();
   private Relation<IResevierung,IKategorie> elements;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private reservierungKategorieSupervisor(){
      this.elements = new Relation<>("reservierungKategorie");
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public static reservierungKategorieSupervisor getInstance(){return theInstance;}
   public IKategorie getKategorie(IResevierung owner){
      return this.elements.getRelatedTargets(owner).isEmpty() ? null : this.elements.getRelatedTargets(owner).get(0);
   }
   public void set(IResevierung owner, IKategorie target) throws PersistenceException{
      IKategorie targetOld = this.getKategorie(owner); IKategorie targetNew = target;
      this.elements.change(owner, targetOld, targetNew);
   }
   public void setAlreadyPersistent(IResevierung owner, IKategorie target) {
      IKategorie targetOld = null; IKategorie targetNew = target;
      this.elements.setAlreadyPersistent(owner, targetNew);
   }
   public void change(IResevierung owner, IKategorie targetOld, IKategorie targetNew) throws PersistenceException{
      this.elements.change(owner, targetOld, targetNew);
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
