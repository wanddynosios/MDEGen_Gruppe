/**--- Generated at Sat Mar 06 15:22:39 CET 2021 
 * --- Change only in Editable Sections!  
 * --- Do not touch section numbering!   
 */
package generated.kino;
//10 ===== GENERATED:      Import Section =========
import java.sql.SQLException;
import db.connection.NoConnectionException;
import db.connection.TypeKeyManager;
import db.executer.PersistenceExecuterFactory;
import generated.kino.proxies.SaalProxy;
import observation.Observable;
import generated.kino.proxies.ISaal;
import generated.kino.relationControl.*;
import generated.kino.proxies.*;
import db.executer.PersistenceException;

import java.util.*;

import exceptions.ConstraintViolation;
import java.util.List;
import java.util.ArrayList;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class Saal extends Observable implements java.io.Serializable, ISaal
{
   //30 ===== GENERATED:      Attribute Section ======
   private Integer id;
   private Integer SaalNummer;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private Saal(Integer id, Integer SaalNummer, boolean objectOnly)
   {
      super();
      this.setId(id);
      this.SaalNummer = SaalNummer;
      if(objectOnly) return;
   }
   /** Caution: A Call to this Method Requires to add any newly instantiated Object to its Cache! */
   public static Saal createAlreadyPersistent(SaalProxy proxy, Integer SaalNummer){
      if(proxy.isObjectPresent()) return proxy.getTheObject();
      return new Saal(proxy.getId(), SaalNummer, true);
   }
   public static Saal createFresh(Integer SaalNummer)throws PersistenceException{
      db.executer.DBDMLExecuter dmlExecuter = PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter();
      Integer id = dmlExecuter.getNextId();
      try{
         dmlExecuter.insertInto("Saal", "id, typeKey, SaalNummer", 
         id.toString() + ", " + TypeKeyManager.getTheInstance().getTypeKey("Kino", "Saal").toString() + ", " + SaalNummer.toString());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
      Saal me = new Saal(id, SaalNummer, false);
      Kino.getInstance().addSaalProxy(new SaalProxy(me));
      return me;
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public Saal getTheObject(){
      return this;
   }
   public Integer getId(){
      return this.id;
   }
   protected void setId(Integer id){
      this.id = id;
   }
   public boolean equals(Object o) {
      if(!(o instanceof ISaal)) return false;
      return ((ISaal)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   public List<Reihe> getReihen() throws PersistenceException{
      List<Reihe> result = new ArrayList<>();
      for (IReihe i : saalReihenSupervisor.getInstance().getReihen(this)) result.add(i.getTheObject());
      return result;
   }
   public void addToReihen(Reihe arg) throws PersistenceException{
      saalReihenSupervisor.getInstance().add(this, arg);
   }
   public boolean removeFromReihen(Reihe arg) throws PersistenceException{
      return saalReihenSupervisor.getInstance().remove(this, arg);
   }
   public Integer getSaalNummer() {
      return this.SaalNummer;
   }
   public void setSaalNummer(Integer newSaalNummer) throws PersistenceException{
      this.SaalNummer = newSaalNummer;
      try{PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter().update("Saal", "SaalNummer", newSaalNummer.toString(), this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   //80 ===== Editable : Your Operations =============

/**
 * holt anzahl plaetze Parkett
 */
   public Integer getAnzahlPlaetzeParkett(){
      // TODO: Implement Operation getAnzahlPlaetzeParkett
      return null;
   }
/**
 * holt anzahl plaetze Loge
 */
   public Integer getAnzhalPlaetzeLoge(){
      // TODO: Implement Operation getAnzhalPlaetzeLoge
      return null;
   }
/**
 * holt anzahl plaetze Mitte
 */
   public Integer getAnzahlPlaetzeMitte(){
      // TODO: Implement Operation getAnzahlPlaetzeMitte
      return null;
   }
//90 ===== GENERATED: End of Your Operations ======
}
