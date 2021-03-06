/**--- Generated at Sat Mar 06 17:55:52 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.commands;
import generated.kino.*;
import commands.*;
public class BuchungsEinheit_constructor_Command extends ServiceCommand<BuchungsEinheit>{
   private static final long serialVersionUID = -77586756L;
   public BuchungsEinheit_constructor_Command(){
      super();
   }
   public void execute(){
      try{this.result = BuchungsEinheit.createFresh();
      }catch(Exception e){this.e = e;
      }finally{Kino.getInstance().notifyObservers(this);}
   }
}
