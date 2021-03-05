/**--- Generated at Fri Mar 05 17:41:57 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.commands;
import generated.kino.*;
import commands.*;
public class holeVorfuehrung_Command extends ServiceCommand<Vorfuehrung>{
   private static final long serialVersionUID = -20193739L;
   private Integer vorfuehrungNummer;
   public holeVorfuehrung_Command(Integer vorfuehrungNummer){
      super();
      this.vorfuehrungNummer = vorfuehrungNummer;
   }
   public void execute(){
      try{this.result = Kino.getInstance().holeVorfuehrung(vorfuehrungNummer);
      }catch(Exception e){this.e = e;
      }finally{Kino.getInstance().notifyObservers(this);}
   }
}
