/**--- Generated at Fri Mar 05 18:16:22 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.commands;
import generated.kino.*;
import commands.*;
public class Film_constructor_Command extends ServiceCommand<Film>{
   private static final long serialVersionUID = -847927523L;
   private String  filmName;
   public Film_constructor_Command(String  filmName){
      super();
      this.filmName = filmName;
   }
   public void execute(){
      try{this.result = Film.createFresh(filmName);
      }catch(Exception e){this.e = e;
      }finally{Kino.getInstance().notifyObservers(this);}
   }
}
