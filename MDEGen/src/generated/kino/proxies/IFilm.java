/**--- Generated at Sat Mar 06 17:55:52 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.proxies;
import idManagement.Identifiable;
import db.executer.PersistenceException;
import generated.kino.Film;
public interface IFilm extends Identifiable{
   public Film getTheObject();
   public Integer getId();
   public String getFilmName() ;
   public void setFilmName(String newFilmName) throws PersistenceException;
}
