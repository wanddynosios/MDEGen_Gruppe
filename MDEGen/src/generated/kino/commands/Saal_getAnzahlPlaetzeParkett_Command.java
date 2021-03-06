/**--- Generated at Sat Mar 06 14:53:51 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.commands;
import generated.kino.*;
import commands.*;
public class Saal_getAnzahlPlaetzeParkett_Command extends ObjectCommand<Saal, Integer>{
   private static final long serialVersionUID = 1871597117L;
   public Saal_getAnzahlPlaetzeParkett_Command(Saal receiver){
      super(receiver);
   }
   public void execute(){
      try{this.result = this.receiver.getAnzahlPlaetzeParkett();
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
