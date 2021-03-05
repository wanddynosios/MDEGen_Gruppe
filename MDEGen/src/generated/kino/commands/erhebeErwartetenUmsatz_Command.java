/**--- Generated at Fri Mar 05 12:20:39 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.commands;
import generated.kino.*;
import commands.*;
public class erhebeErwartetenUmsatz_Command extends ServiceCommand<Integer>{
   private static final long serialVersionUID = 926846999L;
   private Vorfuehrung vorfuehrung;
   public erhebeErwartetenUmsatz_Command(Vorfuehrung vorfuehrung){
      super();
      this.vorfuehrung = vorfuehrung;
   }
   public void execute(){
      try{this.result = Kino.getInstance().erhebeErwartetenUmsatz(vorfuehrung);
      }catch(Exception e){this.e = e;
      }finally{Kino.getInstance().notifyObservers(this);}
   }
}
