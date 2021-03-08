/**--- Generated at Mon Mar 08 12:14:37 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.commands;
import generated.kino.*;
import commands.*;
public class Buchung_constructor_Command extends ServiceCommand<Buchung>{
   private static final long serialVersionUID = -1057459072L;
   private Integer  buchungsNummer;
   private Vorfuehrung  vorfuehrung;
   public Buchung_constructor_Command(Integer  buchungsNummer, Vorfuehrung  vorfuehrung){
      super();
      this.buchungsNummer = buchungsNummer;
      this.vorfuehrung = vorfuehrung;
   }
   public void execute(){
      try{this.result = Buchung.createFresh(buchungsNummer, vorfuehrung);
      }catch(Exception e){this.e = e;
      }finally{Kino.getInstance().notifyObservers(this);}
   }
}
