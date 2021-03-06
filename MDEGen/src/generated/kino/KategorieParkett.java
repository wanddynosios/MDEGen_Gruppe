/**--- Generated at Sat Mar 06 13:28:16 CET 2021 
 * --- Change only in Editable Sections!  
 * --- Do not touch section numbering!   
 */
package generated.kino;
//10 ===== GENERATED:      Import Section =========
import java.sql.SQLException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import db.connection.NoConnectionException;
import db.connection.TypeKeyManager;
import java.util.Optional;
import java.sql.ResultSet;
import db.executer.DBExecuterFactory;
import db.executer.PersistenceException;
import db.executer.PersistenceExecuterFactory;
import generated.kino.proxies.IKategorieParkett;
import exceptions.ConstraintViolation;
//20 ===== Editable : Your Import Section =========

//25 ===== GENERATED:      Header Section =========
public class KategorieParkett extends Kategorie implements java.io.Serializable, IKategorieParkett
{
   //30 ===== GENERATED:      Attribute Section ======
   private static Optional<KategorieParkett> theInstance = Optional.empty();
   //40 ===== Editable : Your Attribute Section ======

   //50 ===== GENERATED:      Constructor ============
   private KategorieParkett(Integer id, boolean objectOnly)
   {
      super(id, objectOnly);
      if(objectOnly) return;
   }
   private static KategorieParkett createAlreadyPersistent(Integer id){
      return new KategorieParkett(id, true);
   }
   //60 ===== Editable : Your Constructors ===========

   //70 ===== GENERATED:      Feature Access =========
   public static KategorieParkett getInstance() throws PersistenceException{
      if(!theInstance.isPresent())theInstance = Optional.of(load());
      return theInstance.get();
   }
   private static KategorieParkett load() throws PersistenceException {
      try{
         Integer typeKey = TypeKeyManager.getTheInstance().getTypeKey("Kino", "KategorieParkett");
         ResultSet rs = DBExecuterFactory.getConfiguredFactory().getDBDMLExecuter().selectEntriesOfTable("Kategorie", typeKey);
         rs.next();
         return KategorieParkett.createAlreadyPersistent(rs.getInt("id"));
      } catch (SQLException | NoConnectionException e) {
         throw new PersistenceException(e.getMessage());
      }
   }
   public KategorieParkett getTheObject(){
      return this;
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
