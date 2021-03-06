/**--- Generated at Sat Mar 06 12:51:23 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.commands;
import generated.kino.*;
import commands.*;
public class erstelleSaal_Command extends ServiceCommand<Void>{
   private static final long serialVersionUID = -1331134073L;
   private Saal saal;
   public erstelleSaal_Command(Saal saal){
      super();
      this.saal = saal;
   }
   public void execute(){
      try{Kino.getInstance().erstelleSaal(saal);
      }catch(Exception e){this.e = e;
      }finally{Kino.getInstance().notifyObservers(this);}
   }
}
