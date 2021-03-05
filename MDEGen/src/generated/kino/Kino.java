/**--- Generated at Fri Mar 05 18:16:22 CET 2021 
 * --- Change only in Editable Sections!  
 * --- Do not touch section numbering!   
 */
package generated.kino;
//10 ===== GENERATED: Import Section ==============
import db.executer.PersistenceException;
import generated.kino.relationControl.*;
import utilities.InitialProxyLoader;
import utilities.InitialRelationLoader;
import utilities.InitialRelationLoader.IntegerPair;
import exceptions.ConstraintViolation;
import generated.kino.proxies.*;
import observation.Observable;
import db.executer.PersistenceExecuterFactory;
import db.executer.DBDMLExecuter;
import db.connection.TypeKeyManager;
import db.connection.DBConnectionManager;
import db.connection.DBConnectionData;

import java.util.HashMap;
import java.util.Map;
//20 ===== Editable : Your import section =========
//30 ===== GENERATED: Main Section ================
public class Kino extends Observable{
   DBDMLExecuter dmlExecuter = PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter();
   private Map<Integer,VorfuehrungProxy> vorfuehrungCache;
   private Map<Integer,ResevierungProxy> resevierungCache;
   private Map<Integer,SaalProxy> saalCache;
   private Map<Integer,ReiheProxy> reiheCache;
   private Map<Integer,SitzProxy> sitzCache;
   private Map<Integer,FilmProxy> filmCache;
   private Map<Integer,BuchungsEinheitProxy> buchungsEinheitCache;
   private static Kino theInstance = new Kino();
   private Kino(){
      try{DBConnectionManager.getTheInstance().openDBConnection(new DBConnectionData("jdbc:mysql://localhost:3306", "Kino", "root" , ""));
         TypeKeyManager.getTheInstance().initializeFor("Kino");
         this.loadProxies();
         this.loadRelations();
      }catch(Exception e){assert false : "Failed to establish initial database connection. Program aborted: " + e.getMessage();}
   }
   public static Kino getInstance(){return theInstance;}
   
// The methods loadProxies/Relations will be replaced by more intelligent lazy-load-strategies!
   private void loadProxies() throws PersistenceException{
      this.vorfuehrungCache = new InitialProxyLoader<VorfuehrungProxy>("generated", "Kino", "kino", "Vorfuehrung", "Vorfuehrung").perform();
      this.resevierungCache = new InitialProxyLoader<ResevierungProxy>("generated", "Kino", "kino", "Resevierung", "Resevierung").perform();
      this.saalCache = new InitialProxyLoader<SaalProxy>("generated", "Kino", "kino", "Saal", "Saal").perform();
      this.reiheCache = new InitialProxyLoader<ReiheProxy>("generated", "Kino", "kino", "Reihe", "Reihe").perform();
      this.sitzCache = new InitialProxyLoader<SitzProxy>("generated", "Kino", "kino", "Sitz", "Sitz").perform();
      this.filmCache = new InitialProxyLoader<FilmProxy>("generated", "Kino", "kino", "Film", "Film").perform();
      this.buchungsEinheitCache = new InitialProxyLoader<BuchungsEinheitProxy>("generated", "Kino", "kino", "BuchungsEinheit", "BuchungsEinheit").perform();
   }
   private void loadRelations() throws PersistenceException{
      for(IntegerPair pair : new InitialRelationLoader("vorfuehrungReservierungen").perform().values()){
         String className1 = this.dmlExecuter.getNameOfConcreteType(pair.getP1(), "Vorfuehrung", "Kino");
         String className2 = this.dmlExecuter.getNameOfConcreteType(pair.getP2(), "Resevierung", "Kino");
         try{this.addvorfuehrungReservierungenElement(pair.getP1(), className1, pair.getP2(), className2);}catch(ConstraintViolation cv){throw new PersistenceException(cv.getMessage());}
      }
      for(IntegerPair pair : new InitialRelationLoader("vorfuehrungFilm").perform().values()){
         String className1 = this.dmlExecuter.getNameOfConcreteType(pair.getP1(), "Vorfuehrung", "Kino");
         String className2 = this.dmlExecuter.getNameOfConcreteType(pair.getP2(), "Film", "Kino");
         try{this.addvorfuehrungFilmElement(pair.getP1(), className1, pair.getP2(), className2);}catch(ConstraintViolation cv){throw new PersistenceException(cv.getMessage());}
      }
      for(IntegerPair pair : new InitialRelationLoader("vorfuehrungSaal").perform().values()){
         String className1 = this.dmlExecuter.getNameOfConcreteType(pair.getP1(), "Vorfuehrung", "Kino");
         String className2 = this.dmlExecuter.getNameOfConcreteType(pair.getP2(), "Saal", "Kino");
         try{this.addvorfuehrungSaalElement(pair.getP1(), className1, pair.getP2(), className2);}catch(ConstraintViolation cv){throw new PersistenceException(cv.getMessage());}
      }
      for(IntegerPair pair : new InitialRelationLoader("saalReihen").perform().values()){
         String className1 = this.dmlExecuter.getNameOfConcreteType(pair.getP1(), "Saal", "Kino");
         String className2 = this.dmlExecuter.getNameOfConcreteType(pair.getP2(), "Reihe", "Kino");
         try{this.addsaalReihenElement(pair.getP1(), className1, pair.getP2(), className2);}catch(ConstraintViolation cv){throw new PersistenceException(cv.getMessage());}
      }
      for(IntegerPair pair : new InitialRelationLoader("reihenSitze").perform().values()){
         String className1 = this.dmlExecuter.getNameOfConcreteType(pair.getP1(), "Reihe", "Kino");
         String className2 = this.dmlExecuter.getNameOfConcreteType(pair.getP2(), "Sitz", "Kino");
         try{this.addreihenSitzeElement(pair.getP1(), className1, pair.getP2(), className2);}catch(ConstraintViolation cv){throw new PersistenceException(cv.getMessage());}
      }
      for(IntegerPair pair : new InitialRelationLoader("reiheKategorie").perform().values()){
         String className1 = this.dmlExecuter.getNameOfConcreteType(pair.getP1(), "Reihe", "Kino");
         String className2 = this.dmlExecuter.getNameOfConcreteType(pair.getP2(), "Kategorie", "Kino");
         try{this.addreiheKategorieElement(pair.getP1(), className1, pair.getP2(), className2);}catch(ConstraintViolation cv){throw new PersistenceException(cv.getMessage());}
      }
      for(IntegerPair pair : new InitialRelationLoader("reservierungKategorie").perform().values()){
         String className1 = this.dmlExecuter.getNameOfConcreteType(pair.getP1(), "Resevierung", "Kino");
         String className2 = this.dmlExecuter.getNameOfConcreteType(pair.getP2(), "Kategorie", "Kino");
         try{this.addreservierungKategorieElement(pair.getP1(), className1, pair.getP2(), className2);}catch(ConstraintViolation cv){throw new PersistenceException(cv.getMessage());}
      }
   }
   private void addvorfuehrungReservierungenElement(Integer id1, String className1, Integer id2, String className2) throws ConstraintViolation, PersistenceException{
      IVorfuehrung proxy1 = null; IResevierung proxy2 = null; 
      if(className1.equals("Vorfuehrung"))  proxy1 = this.vorfuehrungCache.get(id1);
      if(className2.equals("Resevierung"))  proxy2 = this.resevierungCache.get(id2);
      vorfuehrungReservierungenSupervisor.getInstance().addAlreadyPersistent(proxy1, proxy2);
   }
   private void addvorfuehrungFilmElement(Integer id1, String className1, Integer id2, String className2) throws ConstraintViolation, PersistenceException{
      IVorfuehrung proxy1 = null; IFilm proxy2 = null; 
      if(className1.equals("Vorfuehrung"))  proxy1 = this.vorfuehrungCache.get(id1);
      if(className2.equals("Film"))  proxy2 = this.filmCache.get(id2);
      vorfuehrungFilmSupervisor.getInstance().setAlreadyPersistent(proxy1, proxy2);
   }
   private void addvorfuehrungSaalElement(Integer id1, String className1, Integer id2, String className2) throws ConstraintViolation, PersistenceException{
      IVorfuehrung proxy1 = null; ISaal proxy2 = null; 
      if(className1.equals("Vorfuehrung"))  proxy1 = this.vorfuehrungCache.get(id1);
      if(className2.equals("Saal"))  proxy2 = this.saalCache.get(id2);
      vorfuehrungSaalSupervisor.getInstance().setAlreadyPersistent(proxy1, proxy2);
   }
   private void addsaalReihenElement(Integer id1, String className1, Integer id2, String className2) throws ConstraintViolation, PersistenceException{
      ISaal proxy1 = null; IReihe proxy2 = null; 
      if(className1.equals("Saal"))  proxy1 = this.saalCache.get(id1);
      if(className2.equals("Reihe"))  proxy2 = this.reiheCache.get(id2);
      saalReihenSupervisor.getInstance().addAlreadyPersistent(proxy1, proxy2);
   }
   private void addreihenSitzeElement(Integer id1, String className1, Integer id2, String className2) throws ConstraintViolation, PersistenceException{
      IReihe proxy1 = null; ISitz proxy2 = null; 
      if(className1.equals("Reihe"))  proxy1 = this.reiheCache.get(id1);
      if(className2.equals("Sitz"))  proxy2 = this.sitzCache.get(id2);
      reihenSitzeSupervisor.getInstance().addAlreadyPersistent(proxy1, proxy2);
   }
   private void addreiheKategorieElement(Integer id1, String className1, Integer id2, String className2) throws ConstraintViolation, PersistenceException{
      IReihe proxy1 = null; IKategorie proxy2 = null; 
      if(className1.equals("Reihe"))  proxy1 = this.reiheCache.get(id1);
      if(className2.equals("KategorieParkett"))  proxy2 = KategorieParkett.getInstance();
      if(className2.equals("KategorieMitte"))  proxy2 = KategorieMitte.getInstance();
      if(className2.equals("KategorieLoge"))  proxy2 = KategorieLoge.getInstance();
      reiheKategorieSupervisor.getInstance().setAlreadyPersistent(proxy1, proxy2);
   }
   private void addreservierungKategorieElement(Integer id1, String className1, Integer id2, String className2) throws ConstraintViolation, PersistenceException{
      IResevierung proxy1 = null; IKategorie proxy2 = null; 
      if(className1.equals("Resevierung"))  proxy1 = this.resevierungCache.get(id1);
      if(className2.equals("KategorieParkett"))  proxy2 = KategorieParkett.getInstance();
      if(className2.equals("KategorieMitte"))  proxy2 = KategorieMitte.getInstance();
      if(className2.equals("KategorieLoge"))  proxy2 = KategorieLoge.getInstance();
      reservierungKategorieSupervisor.getInstance().setAlreadyPersistent(proxy1, proxy2);
   }
   public Vorfuehrung getVorfuehrung(Integer id){
      return this.vorfuehrungCache.get(id).getTheObject();
   }
   public void addVorfuehrungProxy(VorfuehrungProxy p) throws PersistenceException{
      this.vorfuehrungCache.put(p.getId(), p);
   }
   public Map<Integer,VorfuehrungProxy> getVorfuehrungCache(){
      return this.vorfuehrungCache;
   }
   public Resevierung getResevierung(Integer id){
      return this.resevierungCache.get(id).getTheObject();
   }
   public void addResevierungProxy(ResevierungProxy p) throws PersistenceException{
      this.resevierungCache.put(p.getId(), p);
   }
   public Map<Integer,ResevierungProxy> getResevierungCache(){
      return this.resevierungCache;
   }
   public Saal getSaal(Integer id){
      return this.saalCache.get(id).getTheObject();
   }
   public void addSaalProxy(SaalProxy p) throws PersistenceException{
      this.saalCache.put(p.getId(), p);
   }
   public Map<Integer,SaalProxy> getSaalCache(){
      return this.saalCache;
   }
   public Reihe getReihe(Integer id){
      return this.reiheCache.get(id).getTheObject();
   }
   public void addReiheProxy(ReiheProxy p) throws PersistenceException{
      this.reiheCache.put(p.getId(), p);
   }
   public Map<Integer,ReiheProxy> getReiheCache(){
      return this.reiheCache;
   }
   public Sitz getSitz(Integer id){
      return this.sitzCache.get(id).getTheObject();
   }
   public void addSitzProxy(SitzProxy p) throws PersistenceException{
      this.sitzCache.put(p.getId(), p);
   }
   public Map<Integer,SitzProxy> getSitzCache(){
      return this.sitzCache;
   }
   public Film getFilm(Integer id){
      return this.filmCache.get(id).getTheObject();
   }
   public void addFilmProxy(FilmProxy p) throws PersistenceException{
      this.filmCache.put(p.getId(), p);
   }
   public Map<Integer,FilmProxy> getFilmCache(){
      return this.filmCache;
   }
   public BuchungsEinheit getBuchungsEinheit(Integer id){
      return this.buchungsEinheitCache.get(id).getTheObject();
   }
   public void addBuchungsEinheitProxy(BuchungsEinheitProxy p) throws PersistenceException{
      this.buchungsEinheitCache.put(p.getId(), p);
   }
   public Map<Integer,BuchungsEinheitProxy> getBuchungsEinheitCache(){
      return this.buchungsEinheitCache;
   }
   public void closeDBConnection() throws java.sql.SQLException{
      db.connection.DBConnectionManager.getTheInstance().close();
   }
   //80 ===== Editable : Your Operations =============
/**
 * Gibt den bisher durch Buchungen und Reservierungen zu erwarteten Umsatz pro Vorstellung zurück
 */
   public Integer erhebeErwartetenUmsatz(Vorfuehrung vorfuehrung){
      // TODO: Implement Operation erhebeErwartetenUmsatz
      return null;
   }
/**
 * holt den aktuellen Aufbau des Saals
 */
   public Saal holeSaal(Integer saalNummer){
         Map<Integer, SaalProxy> saele = Kino.getInstance().getSaalCache();
         Map<Integer, Integer> saalNummernUndSaalIds = new HashMap<>();
         saele.forEach((saalId, saalProxy) ->
                 saalNummernUndSaalIds.put(saalProxy.getSaalNummer(), saalId));
         Integer saalId = saalNummernUndSaalIds.get(saalNummer);
         return Kino.getInstance().getSaal(saalId);
   }
/**
 * holt die Vorfuehrung
 */
   public Vorfuehrung holeVorfuehrung(Integer vorfuehrungNummer){
      Map<Integer, VorfuehrungProxy> voerfuherungen = Kino.getInstance().getVorfuehrungCache();
      Map<Integer, Integer> vorfuherungsNummernUndVorfIds = new HashMap<>();
      voerfuherungen.forEach((vorfuehrungId, vorfuehrungProxy) ->
              vorfuherungsNummernUndVorfIds.put(vorfuehrungProxy.getVorfuehrungsNummer(), vorfuehrungId));
      Integer vorfuehrungId = vorfuherungsNummernUndVorfIds.get(vorfuehrungNummer);
      return Kino.getInstance().getVorfuehrung(vorfuehrungId);
   }
//90 ===== GENERATED: End of Your Operations ======
}
