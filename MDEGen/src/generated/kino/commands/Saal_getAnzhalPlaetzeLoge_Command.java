/**--- Generated at Sat Mar 06 14:53:51 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.commands;
import generated.kino.*;
import commands.*;
public class Saal_getAnzhalPlaetzeLoge_Command extends ObjectCommand<Saal, Integer>{
   private static final long serialVersionUID = -1556429615L;
   public Saal_getAnzhalPlaetzeLoge_Command(Saal receiver){
      super(receiver);
   }
   public void execute(){
      try{this.result = this.receiver.getAnzhalPlaetzeLoge();
      }catch(Exception e){this.e = e;
      }finally{this.receiver.notifyObservers(this);}
   }
}
