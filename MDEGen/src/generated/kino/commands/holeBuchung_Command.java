/**--- Generated at Mon Mar 08 11:46:28 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.commands;
import generated.kino.*;
import commands.*;
public class holeBuchung_Command extends ServiceCommand<Buchung>{
   private static final long serialVersionUID = 1798834690L;
   private Integer buchungsNummer;
   public holeBuchung_Command(Integer buchungsNummer){
      super();
      this.buchungsNummer = buchungsNummer;
   }
   public void execute(){
      try{this.result = Kino.getInstance().holeBuchung(buchungsNummer);
      }catch(Exception e){this.e = e;
      }finally{Kino.getInstance().notifyObservers(this);}
   }
}
