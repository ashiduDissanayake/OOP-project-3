package Player;

// Importing required classes
import java.util.HashMap;
import Character.Character;
import Character.*;

// Player class implementing Cloneable interface
public class Player implements Cloneable{
    // Declaring private and public variables
    private String name;
    public static String[] userNames = {};
    private String userName;
    private static int userID = 0;
    private int XP;
    private int goldCoins;
    private String homeGround;
    private  int armoursUsed = 0;
    private  int artefactsUsed = 0;
    public HashMap<String, Character> characterList = new HashMap<String, Character>();

    // Overriding clone method from Cloneable interface
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // Default constructor
    public Player(){
        this("", "", 0, 500, "");
        // Initializing characterList with default characters
        characterList.put("Archer", new Archer());
        characterList.put("Knight", new Knight());
        characterList.put("Mage", new Mage());
        characterList.put("Healer", new Healer());
        characterList.put("MythicalCreature", new MythicalCreature());
    }

    // Constructor with name, userName, and homeGround parameters
    public Player(String name, String userName, String homeGround){
        this.name = name;
        this.userName = userName;
        this.homeGround = homeGround;
        Player.userID += 1;
    }

    // Constructor with name, userName, XP, goldCoins, and homeGround parameters
    public Player(String name, String userName, int XP, int goldCoins, String homeGround){
        this.name = name;
        this.userName = userName;
        this.XP = XP;
        this.goldCoins = goldCoins;
        this.homeGround = homeGround;
        Player.userID += 1;
    }

    // Constructor with name, userName, XP, goldCoins, homeGround, and characterList parameters
    public Player(String name, String userName, int XP, int goldCoins, String homeGround, HashMap<String, Character> characterList){
        this.name = name;
        this.userName = userName;
        this.XP = XP;
        this.goldCoins = goldCoins;
        this.homeGround = homeGround;
        this.characterList = characterList;
        Player.userID += 1;
    }

    // Getter and setter methods for the class variables
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        userID = userID;
    }

    public int getGoldCoins() {
        return goldCoins;
    }

    public void setGoldCoins(int goldCoins) {
        this.goldCoins = goldCoins;
    }

    public String getHomeGround() {
        return homeGround;
    }

    public void setHomeGround(String homeGround) {
        this.homeGround = homeGround;
    }

    public HashMap getCharacterList() {
        return characterList;
    }

    // Method to get Archer details
    public String getArcher(){
        Character archer = this.characterList.get("Archer");
        if( archer.getName() != ""){
            String archerName = archer.getName();
            String armourName = archer.getArmour().getName();
            String artefactName = archer.getArtefact().getName();
            if(armourName != "" || artefactName != ""){
                return archerName +" with "+armourName+" "+artefactName;
            }
            return archerName;
        }else{
            return "Not yet bought!";
        }
    }

    // Method to get Knight details
    public String getKnight(){
        Character knight = this.characterList.get("Knight");
        if(knight.getName() != ""){
            String knightName = knight.getName();
            String armourName = knight.getArmour().getName();
            String artefactName = knight.getArtefact().getName();

            if(!armourName.isEmpty() || !artefactName.isEmpty()){
                return knightName + " with " + armourName + " " + artefactName;
            }
            return knightName;
        } else {
            return "Not yet bought!";
        }
    }

    // Method to get Mage details
    public String getMage(){
        Character mage = this.characterList.get("Mage");
        if(mage.getName() != ""){
            String mageName = mage.getName();
            String armourName = mage.getArmour().getName();
            String artefactName = mage.getArtefact().getName();

            if(!armourName.isEmpty() || !artefactName.isEmpty()){
                return mageName + " with " + armourName + " " + artefactName;
            }
            return mageName;
        } else {
            return "Not yet bought!";
        }
    }

    // Method to get Healer details
    public String getHealer(){
        Character healer = this.characterList.get("Healer");
        if(healer.getName() != ""){
            String healerName = healer.getName();
            String armourName = healer.getArmour().getName();
            String artefactName = healer.getArtefact().getName();

            if(!armourName.isEmpty() || !artefactName.isEmpty()){
                return healerName + " with " + armourName + " " + artefactName;
            }
            return healerName;
        } else {
            return "Not yet bought!";
        }
    }

    // Method to get MythicalCreature details
    public String getMythicalCreature(){
        Character mythicalCreature = this.characterList.get("MythicalCreature");
        if(mythicalCreature.getName() != ""){
            String creatureName = mythicalCreature.getName();
            String armourName = mythicalCreature.getArmour().getName();
            String artefactName = mythicalCreature.getArtefact().getName();

            if(!armourName.isEmpty() || !artefactName.isEmpty()){
                return creatureName + " with " + armourName + " " + artefactName;
            }
            return creatureName;
        } else {
            return "Not yet bought!";
        }
    }

    public void setCharacterList(HashMap characterList) {
        this.characterList = characterList;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public int getArmoursUsed() {
        return armoursUsed;
    }

    public void setArmoursUsed(int armoursUsed) {
        this.armoursUsed = armoursUsed;
    }

    public int getArtefactsUsed() {
        return artefactsUsed;
    }

    public void setArtefactsUsed(int artefactsUsed) {
        this.artefactsUsed = artefactsUsed;
    }
}
