/**--- Generated at Mon Mar 08 14:44:48 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.commands;
import generated.kino.*;
import commands.*;
public class BuchungsEinheit_constructor_Command extends ServiceCommand<BuchungsEinheit>{
   private static final long serialVersionUID = -667785980L;
   private Sitz sitz;
   public BuchungsEinheit_constructor_Command(Sitz sitz){
      super();
      this.sitz = sitz;
   }
   public void execute(){
      try{this.result = BuchungsEinheit.createFresh(sitz);
      }catch(Exception e){this.e = e;
      }finally{Kino.getInstance().notifyObservers(this);}
   }
}
