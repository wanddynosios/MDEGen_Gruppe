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
public class reiheKategorieSupervisor
{
   //30 ===== GENERATED:      Attribute Section ======
   private static reiheKategorieSupervisor theInstance = new reiheKategorieSupervisor();
   private Relation<IReihe,IKategorie> elements;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private reiheKategorieSupervisor(){
      this.elements = new Relation<>("reiheKategorie");
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public static reiheKategorieSupervisor getInstance(){return theInstance;}
   public IKategorie getKategorie(IReihe owner){
      return this.elements.getRelatedTargets(owner).isEmpty() ? null : this.elements.getRelatedTargets(owner).get(0);
   }
   public void set(IReihe owner, IKategorie target) throws PersistenceException{
      IKategorie targetOld = this.getKategorie(owner); IKategorie targetNew = target;
      this.elements.change(owner, targetOld, targetNew);
   }
   public void setAlreadyPersistent(IReihe owner, IKategorie target) {
      IKategorie targetOld = null; IKategorie targetNew = target;
      this.elements.setAlreadyPersistent(owner, targetNew);
   }
   public void change(IReihe owner, IKategorie targetOld, IKategorie targetNew) throws PersistenceException{
      this.elements.change(owner, targetOld, targetNew);
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
