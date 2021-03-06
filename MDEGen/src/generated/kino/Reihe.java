/**--- Generated at Sat Mar 06 17:55:51 CET 2021 
 * --- Change only in Editable Sections!  
 * --- Do not touch section numbering!   
 */
package generated.kino;
//10 ===== GENERATED:      Import Section =========
import java.sql.SQLException;
import db.connection.NoConnectionException;
import db.connection.TypeKeyManager;
import db.executer.PersistenceExecuterFactory;
import generated.kino.proxies.ReiheProxy;
import observation.Observable;
import generated.kino.proxies.IReihe;
import generated.kino.relationControl.*;
import generated.kino.proxies.*;
import db.executer.PersistenceException;
import java.util.List;
import java.util.ArrayList;
import exceptions.ConstraintViolation;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class Reihe extends Observable implements java.io.Serializable, IReihe
{
   //30 ===== GENERATED:      Attribute Section ======
   private Integer id;
   private Integer reihenNummer;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private Reihe(Integer id, Kategorie kategorie, Integer reihenNummer, boolean objectOnly)
   throws PersistenceException{
      super();
      this.setId(id);
      reiheKategorieSupervisor.getInstance().set(this, kategorie);
      this.reihenNummer = reihenNummer;
      if(objectOnly) return;
   }
   /** Caution: A Call to this Method Requires to add any newly instantiated Object to its Cache! */
   public static Reihe createAlreadyPersistent(ReiheProxy proxy, Kategorie kategorie, Integer reihenNummer)throws PersistenceException{
      if(proxy.isObjectPresent()) return proxy.getTheObject();
      return new Reihe(proxy.getId(), kategorie, reihenNummer, true);
   }
   public static Reihe createFresh(Kategorie kategorie, Integer reihenNummer)throws PersistenceException{
      db.executer.DBDMLExecuter dmlExecuter = PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter();
      Integer id = dmlExecuter.getNextId();
      try{
         dmlExecuter.insertInto("Reihe", "id, typeKey, reihenNummer", 
         id.toString() + ", " + TypeKeyManager.getTheInstance().getTypeKey("Kino", "Reihe").toString() + ", " + reihenNummer.toString());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
      Reihe me = new Reihe(id, kategorie, reihenNummer, false);
      Kino.getInstance().addReiheProxy(new ReiheProxy(me));
      return me;
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public Reihe getTheObject(){
      return this;
   }
   public Integer getId(){
      return this.id;
   }
   protected void setId(Integer id){
      this.id = id;
   }
   public boolean equals(Object o) {
      if(!(o instanceof IReihe)) return false;
      return ((IReihe)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   public List<Sitz> getSitze() throws PersistenceException{
      List<Sitz> result = new ArrayList<>();
      for (ISitz i : reihenSitzeSupervisor.getInstance().getSitze(this)) result.add(i.getTheObject());
      return result;
   }
   public void addToSitze(Sitz arg) throws ConstraintViolation, PersistenceException{
      reihenSitzeSupervisor.getInstance().add(this, arg);
   }
   public boolean removeFromSitze(Sitz arg) throws ConstraintViolation, PersistenceException{
      return reihenSitzeSupervisor.getInstance().remove(this, arg);
   }
   public Kategorie getKategorie() throws PersistenceException{
      return reiheKategorieSupervisor.getInstance().getKategorie(this).getTheObject();
   }
   public void setKategorie(Kategorie newKategorie)throws PersistenceException{
      reiheKategorieSupervisor.getInstance().change(this, this.getKategorie(), newKategorie);
   }
   public Integer getReihenNummer() {
      return this.reihenNummer;
   }
   public void setReihenNummer(Integer newReihenNummer) throws PersistenceException{
      this.reihenNummer = newReihenNummer;
      try{PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter().update("Reihe", "reihenNummer", newReihenNummer.toString(), this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   //80 ===== Editable : Your Operations =============

   @lombok.SneakyThrows
   @Override
   public String toString() {
      return "Reihe{" +
              "reihenNummer=" + reihenNummer +
              ", kategorie=" + getKategorie().getTheObject() +
              ", sitze=" + getSitze().get(0) +
              '}';
   }

//90 ===== GENERATED: End of Your Operations ======
}
