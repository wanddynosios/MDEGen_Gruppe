/**--- Generated at Sat Mar 06 13:28:16 CET 2021 
 * --- Change only in Editable Sections!  
 * --- Do not touch section numbering!   
 */
package generated.kino;
//10 ===== GENERATED:      Import Section =========
import java.sql.SQLException;
import db.connection.NoConnectionException;
import db.connection.TypeKeyManager;
import db.executer.PersistenceExecuterFactory;
import generated.kino.proxies.FilmProxy;
import observation.Observable;
import generated.kino.proxies.IFilm;
import db.executer.PersistenceException;
import exceptions.ConstraintViolation;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class Film extends Observable implements java.io.Serializable, IFilm
{
   //30 ===== GENERATED:      Attribute Section ======
   private Integer id;
   private String filmName;
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private Film(Integer id, String filmName, boolean objectOnly)
   {
      super();
      this.setId(id);
      this.filmName = filmName;
      if(objectOnly) return;
   }
   /** Caution: A Call to this Method Requires to add any newly instantiated Object to its Cache! */
   public static Film createAlreadyPersistent(FilmProxy proxy, String filmName){
      if(proxy.isObjectPresent()) return proxy.getTheObject();
      return new Film(proxy.getId(), filmName, true);
   }
   public static Film createFresh(String filmName)throws PersistenceException{
      db.executer.DBDMLExecuter dmlExecuter = PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter();
      Integer id = dmlExecuter.getNextId();
      try{
         dmlExecuter.insertInto("Film", "id, typeKey, filmName", 
         id.toString() + ", " + TypeKeyManager.getTheInstance().getTypeKey("Kino", "Film").toString() + ", " + "'" + filmName + "'");
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
      Film me = new Film(id, filmName, false);
      Kino.getInstance().addFilmProxy(new FilmProxy(me));
      return me;
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public Film getTheObject(){
      return this;
   }
   public Integer getId(){
      return this.id;
   }
   protected void setId(Integer id){
      this.id = id;
   }
   public boolean equals(Object o) {
      if(!(o instanceof IFilm)) return false;
      return ((IFilm)o).getId().equals(this.getId());
   }
   public int hashCode() {return this.getId().hashCode();}
   public String getFilmName() {
      return this.filmName;
   }
   public void setFilmName(String newFilmName) throws PersistenceException{
      this.filmName = newFilmName;
      try{PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter().update("Film", "filmName", "'" + newFilmName + "'", this.getId());
      }catch(SQLException|NoConnectionException e){throw new PersistenceException(e.getMessage());}
   }
   //80 ===== Editable : Your Operations =============
   //90 ===== GENERATED: End of Your Operations ======
}
