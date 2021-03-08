/**--- Generated at Mon Mar 08 14:44:48 CET 2021 
 * --- No Change Allowed!  
 */
package generated.kino.commands;
import generated.kino.*;
import commands.*;
public class Reihe_constructor_Command extends ServiceCommand<Reihe>{
   private static final long serialVersionUID = -1312834364L;
   private Kategorie kategorie;
   private Integer  reihenNummer;
   public Reihe_constructor_Command(Kategorie kategorie, Integer  reihenNummer){
      super();
      this.kategorie = kategorie;
      this.reihenNummer = reihenNummer;
   }
   public void execute(){
      try{this.result = Reihe.createFresh(kategorie, reihenNummer);
      }catch(Exception e){this.e = e;
      }finally{Kino.getInstance().notifyObservers(this);}
   }
}
