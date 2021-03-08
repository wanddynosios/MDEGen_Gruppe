/**--- Generated at Mon Mar 08 12:14:37 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.commands;
import generated.kino.*;
import commands.*;
public class Saal_constructor_Command extends ServiceCommand<Saal>{
   private static final long serialVersionUID = -785429229L;
   private Integer  SaalNummer;
   public Saal_constructor_Command(Integer  SaalNummer){
      super();
      this.SaalNummer = SaalNummer;
   }
   public void execute(){
      try{this.result = Saal.createFresh(SaalNummer);
      }catch(Exception e){this.e = e;
      }finally{Kino.getInstance().notifyObservers(this);}
   }
}
