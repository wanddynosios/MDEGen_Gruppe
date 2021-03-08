/**--- Generated at Mon Mar 08 12:14:37 CET 2021 
 * --- Change only in Editable Sections!  
 * --- Do not touch section numbering!   
 */
package generated.kino;
//10 ===== GENERATED:      Import Section =========
import java.sql.SQLException;
import db.connection.NoConnectionException;
import db.connection.TypeKeyManager;
import java.util.Optional;
import java.sql.ResultSet;
import db.executer.DBExecuterFactory;
import db.executer.PersistenceException;
import db.executer.PersistenceExecuterFactory;
import generated.kino.proxies.IKategorieLoge;
import exceptions.ConstraintViolation;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class KategorieLoge extends Kategorie implements java.io.Serializable, IKategorieLoge
{
   //30 ===== GENERATED:      Attribute Section ======
   private static Optional<KategorieLoge> theInstance = Optional.empty();
   //40 ===== Editable : Your Attribute Section ======
   
   //50 ===== GENERATED:      Constructor ============
   private KategorieLoge(Integer id, boolean objectOnly)
   {
      super(id, objectOnly);
      if(objectOnly) return;
   }
   private static KategorieLoge createAlreadyPersistent(Integer id){
      return new KategorieLoge(id, true);
   }
   //60 ===== Editable : Your Constructors ===========
   
   //70 ===== GENERATED:      Feature Access =========
   public static KategorieLoge getInstance() throws PersistenceException{
      if(!theInstance.isPresent())theInstance = Optional.of(load());
      return theInstance.get();
   }
   private static KategorieLoge load() throws PersistenceException {
      try{
         Integer typeKey = TypeKeyManager.getTheInstance().getTypeKey("Kino", "KategorieLoge");
         ResultSet rs = DBExecuterFactory.getConfiguredFactory().getDBDMLExecuter().selectEntriesOfTable("Kategorie", typeKey);
         rs.next();
         return KategorieLoge.createAlreadyPersistent(rs.getInt("id"));
      } catch (SQLException | NoConnectionException e) {
         throw new PersistenceException(e.getMessage());
      }
   }
   public KategorieLoge getTheObject(){
      return this;
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
