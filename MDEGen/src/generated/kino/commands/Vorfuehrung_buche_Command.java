/**--- Generated at Fri Mar 05 12:20:39 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.commands;
import generated.kino.*;
import commands.*;
import java.util.Collection;
public class Vorfuehrung_buche_Command extends ObjectCommand<Vorfuehrung, Void>{
   private static final long serialVersionUID = 284901278L;
   private Resevierung reservierung;
   private Collection<BuchungsEinheit> buchungsEinheiten;
   public Vorfuehrung_buche_Command(Vorfuehrung receiver, Resevierung reservierung, Collection<BuchungsEinheit> buchungsEinheiten){
      super(receiver);
      this.reservierung = reservierung;
      this.buchungsEinheiten = buchungsEinheiten;
   }
   public void execute(){
      try{this.receiver.buche(reservierung, buchungsEinheiten);
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
