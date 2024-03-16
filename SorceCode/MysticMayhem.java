import java.util.*;
import Character.*;
import Character.Character;
import Equipment.*;
import Player.Player;
//Main class file of the game
public class MysticMayhem {
    static int battled = 0;
    static HashMap<String, int[] > army = new HashMap<>();
    static Player playerG = new Player();
    //main method of the game
    public static void main(String [] args) throws CloneNotSupportedException{
        //Following are the enemies we are going to face in this game
        // Player 1
        Player player1 = createPlayer("Geralt_of_Rivia", "whitewolf", 32, 215, "Marshland", new Ranger(), new Chainmail(), new Squire(), new Warlock(), new Amulet(),new Dragon(),  new Medic());

        // Player 2
        Player player2 = createPlayer("Luna Shadowbane", "moonhowler", 28, 180, "Desert",
                new Ranger(), new Regalia(), new Cavalier(), new Illusionist(), new Excalibur(), new Pegasus(), new Soother());

        // Player 3
        Player player3 = createPlayer("Blaze Furyheart", "infernodragon", 40, 300, "Hillcrest",
                new Sunfire(), new Chainmail(), new Cavalier(), new Illusionist(), new Amulet(), new Dragon(), new Medic());

        // Player 4
        Player player4 = createPlayer("Luna Frostwhisper", "frostmystic", 18, 150, "Marshland",
                new Shooter(), new Fleece(), new Zoro(), new Enchanter(), new Amulet(), new Hydra(), new Alchemist());

        // Player 5
        Player player5 = createPlayer("Orion Stormrider", "thunderstorm", 22, 180, "Desert",
                new Zing(), new Regalia(), new Templar(), new Eldritch(), new Crystal(), new Pegasus(), new Saint());

        // Player 6
        Player player6 = createPlayer("Aria Moonlight", "lunarsong", 30, 220, "Arcane",
                new Saggitarius(), new Fleece(), new Swiftblade(), new Conjurer(), new Excalibur(), new Hydra(), new Lightbringer());

        // Player 7
        Player player7 = createPlayer("Frostbite Fang", "frostyfangs", 15, 120, "Hillcrest",
                new Ranger(), new Chainmail(), new Cavalier(), new Eldritch(), new Amulet(), new Basilisk(), new Saint());

        // Player 8
        Player player8 = createPlayer("Stormy Skydancer", "windwhisperer", 35, 260, "Marshland",
                new Shooter(), new Regalia(), new Squire(), new Warlock(), new Amulet(), new Dragon(), new Saint());

        // Player 9
        Player player9 = createPlayer("Luna Celestial", "starweaver", 28, 200, "Desert",
                new Zing(), new Chainmail(), new Templar(), new Eldritch(), new Excalibur(), new Phoenix(), new Medic());

        List<Player> enemyList = new ArrayList<Player>(Arrays.asList(player1, player2, player3, player4, player5, player6, player7, player8, player9));

        //this is the main game loop which allows user inputs
        try (Scanner scanner = new Scanner(System.in)){
            //creating a player profile
            Player user = createUser(scanner);
            int flag = 1;
            while (flag != 0) {
                String input = scanner.nextLine();  // Read the input

                if (input.equalsIgnoreCase("1")) {
                    displayPlayerStats(user);
                }
                else if (input.equalsIgnoreCase("2")){
                    displayStore();
                }
                else if (input.equalsIgnoreCase("3")){
                    buyCharacters(scanner, user);
                }
                else if (input.equalsIgnoreCase("4")){
                    upgradeCharacters(scanner, user);
                }
                else if (input.equalsIgnoreCase("5")){
                    sellCharacters(scanner, user);
                }
                else if (input.equalsIgnoreCase("6")){
                    Player enemy = selectEnemy(user, enemyList, scanner);
                    if(enemy != null){
                        challengePlayer(user, enemy, scanner);
                    }else{
                        flag = 0;
                    }
                }
                else if (input.equalsIgnoreCase("7")){
                    user = createUserAccount(scanner);
                }
                else if (input.equalsIgnoreCase("0")){
                    flag = 0;
                }
                else{
                    System.out.println();
                    System.out.println("*****************************************");
                    System.out.println("*            Menu Options               *");
                    System.out.println("*****************************************");
                    System.out.println("*   1) Display your stats.              *\n" +
                                       "*   2) Display store.                   *\n" +
                                       "*   3) Buy characters.                  *\n" +
                                       "*   4) Upgrade characters.              *\n" +
                                       "*   5) Sell characters.                 *\n" +
                                       "*   6) Battle.                          *\n" +
                                       "*   7) Create a new user account.       *\n" +
                                       "*   0) Quit                             *");
                    System.out.println("*****************************************");
                }
            }
        }
    }

    private static Player createUserAccount(Scanner scanner) {
        //in her i want to implement a new user profile creation in the exact same way of createuser() method without having much that welcoming

        Player user = createUser(scanner);
        System.out.println("Enter the name of new user: ");
        String name = scanner.nextLine();
        user.setName(name);
        System.out.println("Enter the username of new user: ");
        String userName = scanner.nextLine();
        user.setUserName(userName);
        // Display the list of options
        System.out.println("Select your Home Ground: ");
        System.out.println("1. Hilcrist");
        System.out.println("2. Marshland");
        System.out.println("3. Desert");
        System.out.println("4. Arcane");

        // Prompt the user to enter their choice
        System.out.print("Enter the number of your home ground: ");
        int choice = scanner.nextInt();

        String homeGround = "";
        // Process the user's choice
        switch (choice) {
            case 1:
                System.out.println("You chose Hilcrist");
                homeGround = "Hilcrist";
                break;
            case 2:
                System.out.println("You chose Marshland");
                homeGround = "Marshland";
                break;
            case 3:
                System.out.println("You chose Desert");
                homeGround = "Desert";
                break;
            case 4:
                System.out.println("You chose Arcane");
                homeGround = "Arcane";
                break;
            default:
                System.out.println("Your choice is not recognized");
        }
        user.setHomeGround(homeGround);
        return user;
    }

    //Creating a user profile for the game to play
    public static Player createUser(Scanner scanner){
        //Player.Player creation
        Player user = new Player();
        String messege = "Welcome to the Mystical Mayhem!\n";
        printMessege(messege);
        printMessege("Hey! Warrior!!! \nwho are you?");
        String name = scanner.nextLine();
        user.setName(name);
        printMessege("Hail, mighty " + name+"\n");

        printMessege("Choose a Heroic Title: ");
        String userName = scanner.nextLine();
        while (Arrays.asList(Player.userNames).contains(userName)) {
            printMessege("Heroic Title already claimed! \nChoose another Heroic Title: ");
            userName = scanner.nextLine();
        }
        user.setUserName(userName);

        // Display the list of options
        System.out.println("Select your Home Ground: ");
        System.out.println("1. Hilcrist");
        System.out.println("2. Marshland");
        System.out.println("3. Desert");
        System.out.println("4. Arcane");

        // Prompt the user to enter their choice
        System.out.print("Enter the number of your home ground: ");
        int choice = scanner.nextInt();

        String homeGround = "";
        // Process the user's choice
        switch (choice) {
            case 1:
                System.out.println("You chose Hilcrist");
                homeGround = "Hilcrist";
                break;
            case 2:
                System.out.println("You chose Marshland");
                homeGround = "Marshland";
                break;
            case 3:
                System.out.println("You chose Desert");
                homeGround = "Desert";
                break;
            case 4:
                System.out.println("You chose Arcane");
                homeGround = "Arcane";
                break;
            default:
                System.out.println("Your choice is not recognized");
        }
        user.setHomeGround(homeGround);
        System.out.println("Huzzah!!! \n      You are now a legendary warrior in the Mayhem of the Ancients\n");
        return user;
    }

    //this method is used to buy characters
    private static void buyCharacters(Scanner scanner,Player user) {
        // Display the list of character types
        System.out.println("Welcome to the Heroic Emporium! Browse and Choose Your Essence: ");
        System.out.println("1. Archers            ");
        System.out.println("2. Knights            ");
        System.out.println("3. Mage               ");
        System.out.println("4. Healers            ");
        System.out.println("5. Mythical Creatures ");
        System.out.println("Press other key to go back!");

        // Prompt the user to enter their choice
        printMessege("\nEnter the number corresponding to your chosen Essence: \n");
        int choice = scanner.nextInt();

        // Process the user's choice
        switch (choice) {
            case 1:
                boolean haveI = user.getArcher() != "Not yet bought!";
                if(haveI){
                    System.out.printf("\nYou already occupied a "+user.getArcher()+ "\nDo you want to sell? \n");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    System.out.print("\nEnter the number of your preference: \n");
                    choice = scanner.nextInt();
                    switch (choice){
                        case 1:
                            user.setGoldCoins(user.getGoldCoins()+user.characterList.get("Archer").getPrice()*9/10);
                            user.characterList.replace("Archer", new Archer());
                            buyArcher(scanner,user);
                            break;
                        case 2:
                            break;
                    }
                }else {
                    printMessege("Hey!Let's look for an Archer!");
                    buyArcher(scanner,user);
                    break;
                }
            case 2:
                haveI = user.getKnight() != "Not yet bought!";
                if(haveI){
                    System.out.printf("\nYou already occupied a "+user.getKnight()+ "\nDo you want to sell? \n");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    System.out.print("\nEnter the number of your preference: \n");
                    choice = scanner.nextInt();
                    switch (choice){
                        case 1:
                            user.setGoldCoins(user.getGoldCoins()+user.characterList.get("Knight").getPrice()*9/10);
                            user.characterList.replace("Knight", new Knight());
                            buyKnight(scanner,user);
                            break;
                        case 2:
                            break;
                    }
                }else {
                    printMessege("Hey!Let's look for a Knight!");
                    buyKnight(scanner,user);
                    break;
                }
            case 3:
                haveI = user.getMage() != "Not yet bought!";
                if(haveI){
                    System.out.printf("\nYou already occupied a "+user.getMage()+ "\nDo you want to sell? \n");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    System.out.print("\nEnter the number of your preference: \n");
                    choice = scanner.nextInt();
                    switch (choice){
                        case 1:
                            user.setGoldCoins(user.getGoldCoins()+user.characterList.get("Mage").getPrice()*9/10);
                            user.characterList.replace("Mage", new Mage());
                            buyMage(scanner,user);
                            break;
                        case 2:
                            break;
                    }
                }else {
                    printMessege("Hey!Let's look for a Mage!");
                    buyMage(scanner,user);
                    break;
                }
            case 4:
                haveI = user.getHealer() != "Not yet bought!";
                if(haveI){
                    System.out.printf("\nYou already occupied a "+user.getHealer()+ "\nDo you want to sell? \n");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    System.out.print("\nEnter the number of your preference: \n");
                    choice = scanner.nextInt();
                    switch (choice){
                        case 1:
                            user.setGoldCoins(user.getGoldCoins()+user.characterList.get("Healer").getPrice()*9/10);
                            user.characterList.replace("Healer", new Healer());
                            buyHealer(scanner,user);
                            break;
                        case 2:
                            break;
                    }
                }else {
                    printMessege("Hey!Let's look for a Healer!");
                    buyHealer(scanner,user);
                    break;
                }
            case 5:
                haveI = user.getMythicalCreature() != "Not yet bought!";
                if(haveI){
                    System.out.printf("\nYou already occupied a "+user.getMythicalCreature()+ "\nDo you want to sell? \n");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    System.out.print("\nEnter the number of your preference: \n");
                    choice = scanner.nextInt();
                    switch (choice){
                        case 1:
                            user.setGoldCoins(user.getGoldCoins()+user.characterList.get("MythicalCreature").getPrice()*9/10);
                            user.characterList.replace("MythicalCreature", new MythicalCreature());
                            buyMythicalCreature(scanner,user);
                            break;
                        case 2:
                            break;
                    }
                }else {
                    printMessege("Hey!Let's look for a Mythical Creature!");
                    buyMythicalCreature(scanner,user);
                    break;
                }
            default:
                printMessege("You are in the Home!");
                break;
        }
    }

    //this method is used to upgrade characters with equipment
    private static void upgradeCharacters(Scanner scanner, Player user) {
        // Display the list of character types available for upgrading
        printMessege("Welcome to the Heroic Forge! Enhance and Upgrade Your Characters:");
        boolean armourUsing = (user.getArmoursUsed() == 1);
        boolean artefactUsing = (user.getArtefactsUsed() == 1);

        Character armourUser = new Archer();
        Armour usingArmour = new Armour();

        Character artefactUser = new Archer();
        Artefact usingArtefact = new Artefact();

        if (armourUsing || artefactUsing){
            for(String warr : user.characterList.keySet()){
                Character character = user.characterList.get(warr);
                if(user.characterList.get(warr).getArmour().getName() != ""){
                    armourUser = user.characterList.get(warr);
                    usingArmour = armourUser.getArmour();
                }else if(user.characterList.get(warr).getArtefact().getName() != ""){
                    artefactUser = user.characterList.get(warr);
                    usingArtefact = artefactUser.getArtefact();
                }
            }
        }
        System.out.printf("\n1. Armours - %s ", (armourUsing) ? armourUser.getName()+" currently using a armour" : "not using one");
        System.out.printf("\n2. Artefacts - %s ", (artefactUsing) ? artefactUser.getName()+" currently using a armour" : "not using one");


        // Prompt the user to enter their choice
        printMessege("\nEnter the number corresponding to the character type you want to upgrade: ");
        int choice = scanner.nextInt();
        HashMap<Integer, String> characters = new HashMap<Integer, String>();
        characters.put(1, "Archer");
        characters.put(2, "Knight");
        characters.put(3, "Mage");
        characters.put(4, "Healer");
        characters.put(5, "Mythical Creature");

        // Process the user's choice
        switch (choice) {
            case 1:
                System.out.printf("\nAre you sure? you want to %s buy a new armour?\n", (armourUsing) ? "discard "+usingArmour+" and " : "");
                System.out.println("1. Yes");
                System.out.println("2. No");
                System.out.print("\nEnter your preference: \n");
                choice = scanner.nextInt();
                if(choice == 1){
                    armourUser.discardArmour(usingArmour, user);
                    System.out.printf("\nSelect an armour\n");
                    System.out.println("1. Chainmail");
                    System.out.println("2. Regalia");
                    System.out.println("3. Fleece");
                    System.out.print("\nEnter your preference: \n");
                    int armourChoice = scanner.nextInt();
                    HashMap<Integer, Armour> armourList = new HashMap<Integer, Armour>();
                    armourList.put(1, new Chainmail());
                    armourList.put(2, new Regalia());
                    armourList.put(3, new Fleece());
                    int diff = user.getGoldCoins() - armourList.get(armourChoice).getPrice();
                    if(diff >= 0){

                        System.out.printf("\nTo whom do you want to equip armour?\n");
                        System.out.println("1. Archer");
                        System.out.println("2. Knight");
                        System.out.println("3. Mage");
                        System.out.println("4. Healer");
                        System.out.println("5. Mythical Creature");
                        System.out.print("\nEnter your preference: \n");
                        int characterChoice = scanner.nextInt();

                        String bought = user.characterList.get(characters.get(characterChoice)).getName();
                        if(bought != ""){
                            user.characterList.get(characters.get(characterChoice)).buyArmour(armourList.get(armourChoice), user);
                            user.setGoldCoins(diff);
                        }else {
                            System.out.println("character is invalid!");
                        }
                    }else {
                        System.out.println("You are out of Money!");
                    }
                }
                break;
            case 2:
                System.out.printf("\nAre you sure? you want to %s buy a new artefact?\n", (artefactUsing) ? "discard "+usingArtefact+" and " : "");
                System.out.println("1. Yes");
                System.out.println("2. No");
                System.out.print("\nEnter your preference: \n");
                choice = scanner.nextInt();
                if(choice == 1){
                    artefactUser.discardArtefact(usingArtefact, user);
                    System.out.printf("\nSelect an armour\n");
                    System.out.println("1. Excalibur");
                    System.out.println("2. Amulet");
                    System.out.println("3. Crystel");
                    System.out.print("\nEnter your preference: \n");
                    int artefactChoice = scanner.nextInt();
                    HashMap<Integer, Artefact> armourList = new HashMap<Integer, Artefact>();
                    armourList.put(1, new Excalibur());
                    armourList.put(2, new Amulet());
                    armourList.put(3, new Crystal());
                    int diff = user.getGoldCoins() - armourList.get(artefactChoice).getPrice();
                    if(diff >= 0){

                        System.out.printf("\nTo whom do you want to equip artefact?\n");
                        System.out.println("1. Archer");
                        System.out.println("2. Knight");
                        System.out.println("3. Mage");
                        System.out.println("4. Healer");
                        System.out.println("5. Mythical Creature");
                        System.out.print("\nEnter your preference: \n");
                        int characterChoice = scanner.nextInt();

                        String bought = user.characterList.get(characters.get(characterChoice)).getName();
                        if(bought != ""){
                            user.characterList.get(characters.get(characterChoice)).buyArtefact(armourList.get(artefactChoice), user);
                            user.setGoldCoins(diff);
                        }else {
                            System.out.println("character is invalid!");
                        }
                    }else {
                        System.out.println("You are out of Money!");
                    }
                }
                break;
            default:
                System.out.println("Your Chosen Equipment is not Recognized in the Store.");
        }
    }

    //this method is used to sell characters if the want to
    private static void sellCharacters(Scanner scanner, Player user) {
        // Display the list of character types available for sale
        System.out.println("Welcome to the Heroic Marketplace! Explore and Choose Characters to Sell:");
        System.out.println("1. Archers - Masters of Artemis's Craft");
        System.out.println("2. Knights - Defenders of Olympus");
        System.out.println("3. Mages - Bearers of Zeus's Arcane Wisdom");
        System.out.println("4. Healers - Blessed by Apollo's Healing Light");
        System.out.println("5. Mythical Creatures - Denizens of the Majestic Greek Beasts");


        // Prompt the user to enter their choice
        System.out.println("Enter the number corresponding to the character type you want to sell: ");
        int choice = scanner.nextInt();

        // Process the user's choice
        switch (choice) {
            case 1:
                System.out.printf("\nAre you sure! you want to sell %s\n?", user.getArcher());
                System.out.println("1. Yes");
                System.out.println("2. No");
                System.out.println("Enter your preference: ");
                choice = scanner.nextInt();
                if(choice == 1){
                    user.setGoldCoins(user.getGoldCoins()+user.characterList.get("Archer").getPrice()*9/10);
                    user.characterList.replace("Archer", new Archer());
                }
                break;
            case 2:
                System.out.printf("\nAre you sure! you want to sell %s\n?", user.getKnight());
                System.out.println("1. Yes");
                System.out.println("2. No");
                System.out.println("Enter your preference: ");
                choice = scanner.nextInt();
                if(choice == 1){
                    user.setGoldCoins(user.getGoldCoins()+user.characterList.get("Knight").getPrice()*9/10);
                    user.characterList.replace("Knight", new Knight());
                }
                break;
            case 3:
                System.out.printf("\nAre you sure! you want to sell %s\n?", user.getMage());
                System.out.println("1. Yes");
                System.out.println("2. No");
                System.out.println("Enter your preference: ");
                choice = scanner.nextInt();
                if(choice == 1){
                    user.setGoldCoins(user.getGoldCoins()+user.characterList.get("Mage").getPrice()*9/10);
                    user.characterList.replace("Mage", new Mage());
                }
                break;
            case 4:
                System.out.printf("\nAre you sure! you want to sell %s\n?", user.getHealer());
                System.out.println("1. Yes");
                System.out.println("2. No");
                System.out.println("Enter your preference: ");
                choice = scanner.nextInt();
                if(choice == 1) {
                    user.setGoldCoins(user.getGoldCoins() + user.characterList.get("Healer").getPrice() * 9 / 10);
                    user.characterList.replace("Healer", new Healer());
                }
                break;
            case 5:
                System.out.printf("\nAre you sure! you want to sell %s\n?", user.getMythicalCreature());
                System.out.println("1. Yes");
                System.out.println("2. No");
                System.out.println("Enter your preference: ");
                choice = scanner.nextInt();
                if(choice == 1){
                    user.setGoldCoins(user.getGoldCoins()+user.characterList.get("MythicalCreature").getPrice()*9/10);
                    user.characterList.replace("MythicalCreature", new MythicalCreature());
                }
                break;
            default:
                System.out.println("Your Chosen Essence is not Recognized in the Realm of Heroes");
        }
    }

    //this method is used to buy mythical creature as we call inside the buy characters this method
    public static void buyMythicalCreature(Scanner scanner, Player user){
        System.out.println("\nMythical Creatures");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.printf("|%-10s| %-15s | %-10s | %-8s | %-8s | %-8s | %-8s |%n", "Number", "Name", "Price", "Attack", "Defence", "Health", "Speed");
        System.out.println("----------------------------------------------------------------------------------------");

        // Display available mythical creature characters
        Character dragon = new Dragon();
        printCharacter(1,dragon.getName(), dragon.getPrice(), dragon.getAttack(), dragon.getDefence(), dragon.getHealth(), dragon.getSpeed());

        Character basilisk = new Basilisk();
        printCharacter(2,basilisk.getName(), basilisk.getPrice(), basilisk.getAttack(), basilisk.getDefence(), basilisk.getHealth(), basilisk.getSpeed());

        Character hydra = new Hydra();
        printCharacter(3,hydra.getName(), hydra.getPrice(), hydra.getAttack(), hydra.getDefence(), hydra.getHealth(), hydra.getSpeed());

        Character phoenix = new Phoenix();
        printCharacter(4,phoenix.getName(), phoenix.getPrice(), phoenix.getAttack(), phoenix.getDefence(), phoenix.getHealth(), phoenix.getSpeed());

        Character pegasus = new Pegasus();
        printCharacter(5,pegasus.getName(), pegasus.getPrice(), pegasus.getAttack(), pegasus.getDefence(), pegasus.getHealth(), pegasus.getSpeed());
        System.out.println("----------------------------------------------------------------------------------------");

        // Process the user's choice to buy a mythical creature
        System.out.println("Enter the number corresponding to your chosen mythical creature: ");
        int mythicalCreatureChoice = scanner.nextInt();
        int goldCoins = user.getGoldCoins();
        switch (mythicalCreatureChoice) {
            case 1:
                int diff = goldCoins - dragon.getPrice();
                if(diff >= 0){
                    user.characterList.replace("MythicalCreature", dragon);
                    user.setGoldCoins(diff);
                }else{
                    System.out.println("You are out of Gold Coins!");
                }
                break;
            case 2:
                diff = goldCoins - basilisk.getPrice();
                if(diff >= 0){
                    user.characterList.replace("MythicalCreature", basilisk);
                    user.setGoldCoins(diff);
                }else{
                    System.out.println("You are out of Gold Coins!");
                }
                break;
            case 3:
                diff = goldCoins - hydra.getPrice();
                if(diff >= 0){
                    user.characterList.replace("MythicalCreature", hydra);
                    user.setGoldCoins(diff);
                }else{
                    System.out.println("You are out of Gold Coins!");
                }
                break;
            case 4:
                diff = goldCoins - phoenix.getPrice();
                if(diff >= 0){
                    user.characterList.replace("MythicalCreature", phoenix);
                    user.setGoldCoins(diff);
                }else{
                    System.out.println("You are out of Gold Coins!");
                }
                break;
            case 5:
                diff = goldCoins - pegasus.getPrice();
                if(diff >= 0){
                    user.characterList.replace("MythicalCreature", pegasus);
                    user.setGoldCoins(diff);
                }else{
                    System.out.println("You are out of Gold Coins!");
                }
                break;
            default:
                System.out.println("Invalid archer choice");
        }
    }

    //this method is used to buy healers
    public static void buyHealer(Scanner scanner, Player user){
        System.out.println("\nHealers");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.printf("|%-10s| %-15s | %-10s | %-8s | %-8s | %-8s | %-8s |%n", "Number", "Name", "Price", "Attack", "Defence", "Health", "Speed");
        System.out.println("----------------------------------------------------------------------------------------");

        // Display available healer characters
        Character soother = new Soother();
        printCharacter(1,soother.getName(), soother.getPrice(), soother.getAttack(), soother.getDefence(), soother.getHealth(), soother.getSpeed());

        Character medic = new Medic();
        printCharacter(2,medic.getName(), medic.getPrice(), medic.getAttack(), medic.getDefence(), medic.getHealth(), medic.getSpeed());

        Character alchemist = new Alchemist();
        printCharacter(3,alchemist.getName(), alchemist.getPrice(), alchemist.getAttack(), alchemist.getDefence(), alchemist.getHealth(), alchemist.getSpeed());

        Character saint = new Saint();
        printCharacter(4,saint.getName(), saint.getPrice(), saint.getAttack(), saint.getDefence(), saint.getHealth(), saint.getSpeed());

        Character lightbringer = new Lightbringer();
        printCharacter(5,lightbringer.getName(), lightbringer.getPrice(), lightbringer.getAttack(), lightbringer.getDefence(), lightbringer.getHealth(), lightbringer.getSpeed());
        System.out.println("----------------------------------------------------------------------------------------");

        // Process the user's choice to buy a healer
        System.out.println("Enter the number corresponding to your chosen healer: ");
        int healerChoice = scanner.nextInt();
        int goldCoins = user.getGoldCoins();
        switch (healerChoice) {
            case 1:
                int diff = goldCoins - soother.getPrice();
                if(diff >= 0){
                    user.characterList.replace("Healer", soother);
                    user.setGoldCoins(diff);
                }else{
                    System.out.println("You are out of Gold Coins!");
                }
                break;
            case 2:
                diff = goldCoins - medic.getPrice();
                if(diff >= 0){
                    user.characterList.replace("Healer", medic);
                    user.setGoldCoins(diff);
                }else{
                    System.out.println("You are out of Gold Coins!");
                }
                break;
            case 3:
                diff = goldCoins - alchemist.getPrice();
                if(diff >= 0){
                    user.characterList.replace("Healer", alchemist);
                    user.setGoldCoins(diff);
                }else{
                    System.out.println("You are out of Gold Coins!");
                }
                break;
            case 4:
                diff = goldCoins - saint.getPrice();
                if(diff >= 0){
                    user.characterList.replace("Healer", saint);
                    user.setGoldCoins(diff);
                }else{
                    System.out.println("You are out of Gold Coins!");
                }
                break;
            case 5:
                diff = goldCoins - lightbringer.getPrice();
                if(diff >= 0){
                    user.characterList.replace("Healer", lightbringer);
                    user.setGoldCoins(diff);
                }else{
                    System.out.println("You are out of Gold Coins!");
                }
                break;
            default:
                System.out.println("Invalid archer choice");
        }
    }

    //this method is used to buy mage
    public static void buyMage(Scanner scanner, Player user){
        System.out.println("\nMages");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.printf("|%-10s| %-15s | %-10s | %-8s | %-8s | %-8s | %-8s |%n", "Number", "Name", "Price", "Attack", "Defence", "Health", "Speed");
        System.out.println("----------------------------------------------------------------------------------------");

        // Display available mage characters
        Character warlock = new Warlock();
        printCharacter(1,warlock.getName(), warlock.getPrice(), warlock.getAttack(), warlock.getDefence(), warlock.getHealth(), warlock.getSpeed());

        Character illusionist = new Illusionist();
        printCharacter(2,illusionist.getName(), illusionist.getPrice(), illusionist.getAttack(), illusionist.getDefence(), illusionist.getHealth(), illusionist.getSpeed());

        Character enchanter = new Enchanter();
        printCharacter(3,enchanter.getName(), enchanter.getPrice(), enchanter.getAttack(), enchanter.getDefence(), enchanter.getHealth(), enchanter.getSpeed());

        Character conjurer = new Conjurer();
        printCharacter(4,conjurer.getName(), conjurer.getPrice(), conjurer.getAttack(), conjurer.getDefence(), conjurer.getHealth(), conjurer.getSpeed());

        Character eldritch = new Eldritch();
        printCharacter(5,eldritch.getName(), eldritch.getPrice(), eldritch.getAttack(), eldritch.getDefence(), eldritch.getHealth(), eldritch.getSpeed());
        System.out.println("----------------------------------------------------------------------------------------");

        // Process the user's choice to buy a mage
        System.out.println("Enter the number corresponding to your chosen mage: ");
        int mageChoice = scanner.nextInt();
        int goldCoins = user.getGoldCoins();
        switch (mageChoice) {
            case 1:
                int diff = goldCoins - warlock.getPrice();
                if(diff >= 0){
                    user.characterList.replace("Mage", warlock);
                    user.setGoldCoins(diff);
                }else{
                    System.out.println("You are out of Gold Coins!");
                }
                break;
            case 2:
                diff = goldCoins - illusionist.getPrice();
                if(diff >= 0){
                    user.characterList.replace("Mage", illusionist);
                    user.setGoldCoins(diff);
                }else{
                    System.out.println("You are out of Gold Coins!");
                }
                break;
            case 3:
                diff = goldCoins - enchanter.getPrice();
                if(diff >= 0){
                    user.characterList.replace("Mage", enchanter);
                    user.setGoldCoins(diff);
                }else{
                    System.out.println("You are out of Gold Coins!");
                }
                break;
            case 4:
                diff = goldCoins - conjurer.getPrice();
                if(diff >= 0){
                    user.characterList.replace("Mage", conjurer);
                    user.setGoldCoins(diff);
                }else{
                    System.out.println("You are out of Gold Coins!");
                }
                break;
            case 5:
                diff = goldCoins - eldritch.getPrice();
                if(diff >= 0){
                    user.characterList.replace("Mage", eldritch);
                    user.setGoldCoins(diff);
                }else{
                    System.out.println("You are out of Gold Coins!");
                }
                break;
            default:
                System.out.println("Invalid archer choice");
        }
    }

    //this method is used to buy knights
    private static void buyKnight(Scanner scanner, Player user) {
        System.out.println("\nKnights");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.printf("|%-10s| %-15s | %-10s | %-8s | %-8s | %-8s | %-8s |%n", "Number", "Name", "Price", "Attack", "Defence", "Health", "Speed");
        System.out.println("----------------------------------------------------------------------------------------");

        // Display available knight characters
        Character squire = new Squire();
        printCharacter(1,squire.getName(), squire.getPrice(), squire.getAttack(), squire.getDefence(), squire.getHealth(), squire.getSpeed());

        Character cavalier = new Cavalier();
        printCharacter(2,cavalier.getName(), cavalier.getPrice(), cavalier.getAttack(), cavalier.getDefence(), cavalier.getHealth(), cavalier.getSpeed());

        Character templar = new Templar();
        printCharacter(3,templar.getName(), templar.getPrice(), templar.getAttack(), templar.getDefence(), templar.getHealth(), templar.getSpeed());

        Character zoro = new Zoro();
        printCharacter(4,zoro.getName(), zoro.getPrice(), zoro.getAttack(), zoro.getDefence(), zoro.getHealth(), zoro.getSpeed());

        Character swiftblade = new Swiftblade();
        printCharacter(5,swiftblade.getName(), swiftblade.getPrice(), swiftblade.getAttack(), swiftblade.getDefence(), swiftblade.getHealth(), swiftblade.getSpeed());
        System.out.println("----------------------------------------------------------------------------------------");

        // Process the user's choice to buy a knight
        System.out.println("Enter the number corresponding to your chosen knight: ");
        int knightChoice = scanner.nextInt();
        int goldCoins = user.getGoldCoins();
        switch (knightChoice) {
            case 1:
                int diff = goldCoins - squire.getPrice();
                if(diff >= 0){
                    user.characterList.replace("Knight", squire);
                    user.setGoldCoins(diff);
                }else{
                    System.out.println("You are out of Gold Coins!");
                }
                break;
            case 2:
                diff = goldCoins - cavalier.getPrice();
                if(diff >= 0){
                    user.characterList.replace("Knight", cavalier);
                    user.setGoldCoins(diff);
                }else{
                    System.out.println("You are out of Gold Coins!");
                }
                break;
            case 3:
                diff = goldCoins - templar.getPrice();
                if(diff >= 0){
                    user.characterList.replace("Knight", templar);
                    user.setGoldCoins(diff);
                }else{
                    System.out.println("You are out of Gold Coins!");
                }
                break;
            case 4:
                diff = goldCoins - zoro.getPrice();
                if(diff >= 0){
                    user.characterList.replace("Knight", zoro);
                    user.setGoldCoins(diff);
                }else{
                    System.out.println("You are out of Gold Coins!");
                }
                break;
            case 5:
                diff = goldCoins - swiftblade.getPrice();
                if(diff >= 0){
                    user.characterList.replace("Knight", swiftblade);
                    user.setGoldCoins(diff);
                }else{
                    System.out.println("You are out of Gold Coins!");
                }
                break;
            default:
                System.out.println("Invalid archer choice");
        }
    }

    //this method is used to buy archers
    public static void buyArcher(Scanner scanner, Player user){
        System.out.println("\nArchers");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.printf("|%-10s| %-15s | %-10s | %-8s | %-8s | %-8s | %-8s |%n", "Number", "Name", "Price", "Attack", "Defence", "Health", "Speed");
        System.out.println("----------------------------------------------------------------------------------------");

        // Display available archer characters
        Character shooter = new Shooter();
        printCharacter(1,shooter.getName(), shooter.getPrice(), shooter.getAttack(), shooter.getDefence(), shooter.getHealth(), shooter.getSpeed());

        Character ranger = new Ranger();
        printCharacter(2,ranger.getName(), ranger.getPrice(), ranger.getAttack(), ranger.getDefence(), ranger.getHealth(), ranger.getSpeed());

        Character sunfire = new Sunfire();
        printCharacter(3,sunfire.getName(), sunfire.getPrice(), sunfire.getAttack(), sunfire.getDefence(), sunfire.getHealth(), sunfire.getSpeed());

        Character zing = new Zing();
        printCharacter(4,zing.getName(), zing.getPrice(), zing.getAttack(), zing.getDefence(), zing.getHealth(), zing.getSpeed());

        Character saggitarius = new Saggitarius();
        printCharacter(5,saggitarius.getName(), saggitarius.getPrice(), saggitarius.getAttack(), saggitarius.getDefence(), saggitarius.getHealth(), saggitarius.getSpeed());
        System.out.println("----------------------------------------------------------------------------------------");

        // Process the user's choice to buy an archer
        System.out.println("Enter the number corresponding to your chosen archer: ");
        int archerChoice = scanner.nextInt();
        int goldCoins = user.getGoldCoins();
        switch (archerChoice) {
            case 1:
                int diff = goldCoins - shooter.getPrice();
                if(diff >= 0){
                    user.characterList.replace("Archer", shooter);
                    user.setGoldCoins(diff);
                }else{
                    System.out.println("You are out of Gold Coins!");
                }
                break;
            case 2:
                diff = goldCoins - ranger.getPrice();
                if(diff >= 0){
                    user.characterList.replace("Archer", ranger);
                    user.setGoldCoins(diff);
                }else{
                    System.out.println("You are out of Gold Coins!");
                }
                break;
            case 3:
                diff = goldCoins - sunfire.getPrice();
                if(diff >= 0){
                    user.characterList.replace("Archer", sunfire);
                    user.setGoldCoins(diff);
                }else{
                    System.out.println("You are out of Gold Coins!");
                }
                break;
            case 4:
                diff = goldCoins - zing.getPrice();
                if(diff >= 0){
                    user.characterList.replace("Archer", zing);
                    user.setGoldCoins(diff);
                }else{
                    System.out.println("You are out of Gold Coins!");
                }
                break;
            case 5:
                diff = goldCoins - saggitarius.getPrice();
                if(diff >= 0){
                    user.characterList.replace("Archer", saggitarius);
                    user.setGoldCoins(diff);
                }else{
                    System.out.println("You are out of Gold Coins!");
                }
                break;
            default:
                System.out.println("Invalid archer choice");
        }
    }

    //the challenge present in here by the corrospond player
    public static void challengePlayer(Player user, Player enemy, Scanner scanner) throws CloneNotSupportedException {
        String mission = String.format("Greetings, brave %s! Your journey begins now.\n", user.getName())+
                "Behold, noble "+enemy.getName()+", known as the "+enemy.getUserName()+"\n";

        printMessege(mission);
        printMessege("\nLet's fight for our pride!!!\n");

        for(String warr : user.characterList.keySet()) {
            if (user.characterList.get(warr).getName() == "") {
                printMessege("\nYour army is not ready to battle.\n");
                buyCharacters(scanner, user);
            }
        }
        printMessege("\nYou are ready to go!\n");
        beforeMatch(user);
        combat(user, enemy, enemy.getHomeGround());
    }
    
    public static void beforeMatch(Player user){

        army.put("Archer", new int[]{user.characterList.get("Archer").getAttack(), user.characterList.get("Archer").getDefence(), (int) user.characterList.get("Archer").getHealth(), (int)user.characterList.get("Archer").getSpeed()});
        army.put("Knight", new int[]{user.characterList.get("Knight").getAttack(), user.characterList.get("Knight").getDefence(), (int) user.characterList.get("Knight").getHealth(), (int)user.characterList.get("Knight").getSpeed()});
        army.put("Mage", new int[]{user.characterList.get("Mage").getAttack(), user.characterList.get("Mage").getDefence(), (int) user.characterList.get("Mage").getHealth(), (int)user.characterList.get("Mage").getSpeed()});
        army.put("MythicalCreature", new int[]{user.characterList.get("MythicalCreature").getAttack(), user.characterList.get("MythicalCreature").getDefence(), (int) user.characterList.get("MythicalCreature").getHealth(), (int)user.characterList.get("MythicalCreature").getSpeed()});
        army.put("Healer", new int[]{user.characterList.get("Healer").getAttack(), user.characterList.get("Healer").getDefence(), (int) user.characterList.get("Healer").getHealth(), (int)user.characterList.get("Healer").getSpeed()});
    }
    //this method is used to create each player if we want to make new one
    private static Player createPlayer(String name, String username, int xp, int goldCoins, String homeground,
                                       Archer archer, Armour armour, Knight knight, Mage mage,
                                       Artefact artefact, MythicalCreature mythicalCreature,Healer healer) {
        Player player = new Player();
        if(username == "whitewolf"){
            archer.buyArmour(armour, player);
            healer.buyArtefact(artefact, player);
        }else{
            List<Character> eqList = new ArrayList<Character>(Arrays.asList(archer, knight, mage, mythicalCreature, healer));
            // Shuffle the eqList
            Collections.shuffle(eqList);

            // Access the first two characters
            Character randomCharacter1 = eqList.get(0);
            Character randomCharacter2 = eqList.get(1);

            randomCharacter1.buyArmour(armour, player);
            randomCharacter2.buyArtefact(artefact, player);
        }
        HashMap<String, Character> characterList = new HashMap<>(Map.of(
                "Archer", archer,
                "Knight", knight,
                "Mage", mage,
                "Healer", healer,
                "MythicalCreature", mythicalCreature
        ));

        player = new Player(name, username, xp, goldCoins, homeground, characterList);
        return player;
    }

    //this method is used to battle with player
    public static void combat(Player user, Player enemy, String battleground) throws CloneNotSupportedException
    {
        List<Character> userCharacters = new ArrayList<>(user.getCharacterList().values());
        List<Character> enemyCharacters = new ArrayList<>(enemy.getCharacterList().values());

        // Apply buffs and debuffs based on battleground
        if (battleground.equalsIgnoreCase("Hillcrest")) {
            for (Character character : userCharacters) {
                if (character instanceof Highlander) {
                    ((Highlander) character).applyHillcrestBuff();
                }else if (character instanceof Marshlander) {
                    ((Marshlander) character).applyHillcrestDebuff();
                }else if(character instanceof Sunchild){
                    ((Sunchild) character).applyHillcrestDebuff();
                }
            }
            for (Character character : enemyCharacters) {
                if (character instanceof Highlander) {
                    ((Highlander) character).applyHillcrestBuff();
                }else if (character instanceof Marshlander) {
                    ((Marshlander) character).applyHillcrestDebuff();
                }else if(character instanceof Sunchild){
                    ((Sunchild) character).applyHillcrestDebuff();
                }
            }
        } else if (battleground.equalsIgnoreCase("Marshland")) {
            for (Character character : userCharacters) {
                if (character instanceof Marshlander) {
                    ((Marshlander) character).applyMarshlandBuff();
                }
                else if (character instanceof Sunchild) {
                    ((Sunchild) character).applyMarshlandDebuff();
                }
                else if (character instanceof Mystic) {
                    ((Mystic) character).applyMarshlandDebuff();
                }
            }
            for (Character character : enemyCharacters) {
                if (character instanceof Marshlander) {
                    ((Marshlander) character).applyMarshlandBuff();
                }
                else if (character instanceof Sunchild) {
                    ((Sunchild) character).applyMarshlandDebuff();
                }
                else if (character instanceof Mystic) {
                    ((Mystic) character).applyMarshlandDebuff();
                }
            }
        } else if (battleground.equalsIgnoreCase("Desert")) {
            for (Character character : userCharacters) {
                if (character instanceof Marshlander) {
                    ((Marshlander) character).applyDesertDebuff();
                }
                else if (character instanceof Sunchild) {
                    ((Sunchild) character).applyDesertBuff();
                }
            }
            for (Character character : enemyCharacters) {
                if (character instanceof Marshlander) {
                    ((Marshlander) character).applyDesertDebuff();
                }
                else if (character instanceof Sunchild) {
                    ((Sunchild) character).applyDesertBuff();
                }
            }
        } else if (battleground.equalsIgnoreCase("Arcane")) {
            for (Character character : userCharacters) {
                if (character instanceof Highlander) {
                    ((Highlander) character).applyArcaneDebuff();
                }
                if (character instanceof Marshlander) {
                    ((Marshlander) character).applyArcaneBuff();
                }
                if (character instanceof Mystic) {
                    ((Mystic) character).applyArcaneBuff();
                }
            }
            for (Character character : enemyCharacters) {
                if (character instanceof Highlander) {
                    ((Highlander) character).applyArcaneDebuff();
                }
                if (character instanceof Marshlander) {
                    ((Marshlander) character).applyArcaneBuff();
                }
                if (character instanceof Mystic) {
                    ((Mystic) character).applyArcaneBuff();
                }
            }
        }
        List<Character> userTurnOrder = getTurnOrder(user);
        List<Character> enemyTurnOrder = getTurnOrder(enemy);
        int userTurnsLeft = 10;
        int enemyTurnsLeft = 10;
        Character attacker;
        Character defender;
        int k1 = 0;
        int k2 = 0;
        System.out.println("\n"+user.getName() + " vs "+ enemy.getName()+"\n");
        for(int i= 0; i < 20; i++){
            System.out.println();
            String messege = "Turn " + (i+1)+": ";
            String name;
            if(i % 2 == 0){ // user's turn
                name = user.getName();
                if(userTurnsLeft == 0){
                    break;
                }
                printMessege(messege + name+"\n");
                attacker = userTurnOrder.get(((i)/2 + k1) % 5);
                if(attacker.getHealth() <= 0){
                    k1 += 1;
                    attacker = userTurnOrder.get((((i)/2 + k1) % 5));
                }
                defender = getDefender(enemy);
                if (attacker instanceof Healer) {
                    //String healed = getDefender(user).getName();
                    System.out.printf(attacker.getName()+" heals ");
                    heal(attacker, user);
                    /*System.out.printf(healed+"'s health is: ");
                    System.out.printf("%.1f\n", healingPower);*/
                }
                else {
                    attack(attacker, defender);
                    System.out.println(attacker.getName()+" attacks "+defender.getName());
                    if(defender.getHealth()  > 0){
                        System.out.printf(defender.getName()+"'s Health: ");
                        System.out.printf("%.1f\n",defender.getHealth());
                        if(attacker instanceof Mystic && battleground == "Arcane"){
                            mysticHeal(attacker);
                        }
                        System.out.printf(attacker.getName()+"'s Health: ");
                        System.out.printf("%.1f\n",attacker.getHealth());
                    }else {
                        System.out.println(defender.getName()+" Dead!");
                    }
                    if(attacker instanceof Highlander && battleground == "Hilcrist"){
                        bonusTurn(attacker, enemy);
                    }
                }

                userTurnsLeft--;
                if (enemyTurnOrder.isEmpty()) {
                    System.out.println("Boooyaaaaaah!");
                    System.out.println("You won!");
                    afterWin(user, enemy);
                    break;
                }
            }else{ // enemy's turn
                name = enemy.getName();
                if(enemyTurnsLeft == 0){
                    break;
                }
                printMessege(messege + name+"\n");
                attacker = enemyTurnOrder.get(((i-1)/2 +k2) % 5);
                if(attacker.getHealth() <= 0){
                    k2 += 1;
                    attacker = enemyTurnOrder.get((((i-1)/2 + k2) % 5));
                }
                defender = getDefender(user);
                if (attacker instanceof Healer) {
                    //String healed = getDefender(enemy).getName();
                    System.out.printf(attacker.getName()+" heals ");
                    heal(attacker, enemy);
                    //System.out.printf(healed+"'s health is: ");
                    //System.out.printf("%.1f\n", healingPower);

                } else {
                    attack(attacker, defender);
                    System.out.println(attacker.getName()+" attacks "+defender.getName());
                    if(defender.getHealth()  > 0){
                        System.out.printf(defender.getName()+"'s Health: ");
                        System.out.printf("%.1f\n",defender.getHealth());
                        if(attacker instanceof Mystic && battleground == "Arcane"){
                            mysticHeal(attacker);
                        }
                        System.out.printf(attacker.getName()+"'s Health: ");
                        System.out.printf("%.1f\n",attacker.getHealth());
                    }else {
                        System.out.println(defender.getName()+" Dead!");
                    }
                    if(attacker instanceof Highlander && battleground == "Hilcrist"){
                        bonusTurn(attacker, user);
                    }
                }
                enemyTurnsLeft--;
                if (userTurnOrder.isEmpty()) {
                    System.out.println(enemy.getUserName()+" won!");
                    afterWin(enemy, user);
                    break;
                }
            }

        }
        if (!enemyTurnOrder.isEmpty() && !userTurnOrder.isEmpty()){
            System.out.println("The Battle is a Draw!");

        }
        postMatch(user, enemy);
    }

    //getting the speed order to attack
    private static List<Character> getTurnOrder(Player player) {
        List<Character> characters = new ArrayList<>(player.getCharacterList().values());
        Collections.sort(characters, new Comparator<Character>() {
            @Override
            public int compare(Character c1, Character c2) {
                int speedComparison = Double.compare(c2.getSpeed(), c1.getSpeed());
                //     System.out.println(speedComparison);

                if (speedComparison != 0) {
                    return speedComparison;
                } else {
                    // If speeds are the same, apply additional precedence
                    List<String> precedenceOrder = Arrays.asList(
                            "Character.Archer", "Character.Knight","Character.MythicalCreature", "Character.Mage", "Character.Healer"  );

                    int index1 = precedenceOrder.indexOf(c1.getClass().getSuperclass().getName());
                    int index2 = precedenceOrder.indexOf(c2.getClass().getSuperclass().getName());
                    //  System.out.println(index1);
                    //  System.out.println(Integer.compare(index1, index2));

                    return Integer.compare(index1, index2);
                    //return 0;
                }
            }
        });
        return characters;
    }

    //retrieving the alive defender with lowest deefence value
    private static Character getDefender(Player player) {
        List<Character> userCharacters = new ArrayList<>(player.getCharacterList().values());
        Collections.sort(userCharacters, new Comparator<Character>() {
            @Override
            public int compare(Character c1, Character c2) {
                int defenceComparison = Double.compare(c2.getDefence(), c1.getDefence());

                if (defenceComparison != 0) {
                    return -1*defenceComparison;
                } else {
                    // If speeds are the same, apply additional precedence
                    List<String> precedenceOrder = Arrays.asList(
                            "Character.Mage", "Character.Knight","Character.Archer", "Character.MythicalCreature", "Character.Healer"  );

                    int index1 = precedenceOrder.indexOf(c1.getClass().getSuperclass().getName());
                    int index2 = precedenceOrder.indexOf(c2.getClass().getSuperclass().getName());

                    return -1*Integer.compare(index1, index2);
                }
            }
        });

        for(int i = 0;i < userCharacters.size(); i++){
            if(userCharacters.get(i).getHealth() > 0){
                return userCharacters.get(i);
            }
        }
        return null;
    }

    //Bonus turn that if characters of hilcrist play
    //we give bonus chance of 20% attack power
    private static void bonusTurn(Character attacker, Player player){
        Character bonusAttacker = attacker;
        bonusAttacker.setAttack(bonusAttacker.getAttack()/5);
        Character defender = getDefender(player);
        attack(bonusAttacker, defender);
        System.out.println("Bonus Turn :");
        System.out.println(attacker.getName()+" attacks "+defender.getName());
        if(defender.getHealth()  > 0){
            System.out.printf(defender.getName()+"'s Health: ");
            System.out.printf("%.1f\n",defender.getHealth());
        }else {
            System.out.println(defender.getName()+" died!");
        }
    }

    //for mystics we give 10% extra healing in Arcane
    private static void mysticHeal(Character attacker){
        attacker.setHealth(attacker.getHealth()*1.1);
    }

    //changes of the players after the match are done in here
    public static void afterWin(Player player, Player looser){
        player.setXP(player.getXP()+1);
        player.setGoldCoins(player.getGoldCoins()+(int) Math.floor(looser.getGoldCoins()/10));
        looser.setGoldCoins(looser.getGoldCoins()-(int) Math.floor(looser.getGoldCoins()/10));
    }

    //the post battle presentation is done by this
    private static void postMatch(Player player, Player enemy) throws CloneNotSupportedException{
        System.out.println("*********************************************");
        System.out.println(player.getName() + " XP: " + player.getXP());
        System.out.println(player.getName() + " Gold Coins: " + player.getGoldCoins());
        System.out.println(enemy.getName() + " XP: " + enemy.getXP());
        System.out.println(enemy.getName() + " Gold Coins: " + enemy.getGoldCoins());
        System.out.println("*********************************************");
        battled+=1;
        player = reset(player);
    }

    //restart of player after a match is done in here
    public static Player reset(Player user) {
        //this is the reset of the player
        Character archer=user.characterList.get("Archer");
        archer.setAttack(army.get("Archer")[0]);
        archer.setDefence(army.get("Archer")[1]);
        archer.setHealth(army.get("Archer")[2]);
        archer.setHealth(army.get("Archer")[3]);

        Character knight=user.characterList.get("Knight");
        knight.setAttack(army.get("Knight")[0]);
        knight.setDefence(army.get("Knight")[1]);
        knight.setHealth(army.get("Knight")[2]);
        knight.setHealth(army.get("Knight")[3]);

        Character mage=user.characterList.get("Mage");
        mage.setAttack(army.get("Mage")[0]);
        mage.setDefence(army.get("Mage")[1]);
        mage.setHealth(army.get("Mage")[2]);
        mage.setHealth(army.get("Mage")[3]);

        Character healer=user.characterList.get("Healer");
        healer.setAttack(army.get("Healer")[0]);
        healer.setDefence(army.get("Healer")[1]);
        healer.setHealth(army.get("Healer")[2]);
        healer.setHealth(army.get("Healer")[3]);

        Character mythicalCreature=user.characterList.get("MythicalCreature");
        mythicalCreature.setAttack(army.get("MythicalCreature")[0]);
        mythicalCreature.setDefence(army.get("MythicalCreature")[1]);
        mythicalCreature.setHealth(army.get("MythicalCreature")[2]);
        mythicalCreature.setHealth(army.get("MythicalCreature")[3]);

        return user;
    }

    //attack logic is implemented here
    private static void attack(Character attacker, Character defender) {
        double damage = 0.5 * attacker.getAttack() - 0.1 * defender.getDefence();
        defender.setHealth(defender.getHealth() - damage);
    }

    //healing logic is implemented here
    private static void heal(Character healer, Player player) {
        List<Character> characters = new ArrayList<>(player.getCharacterList().values());
        List<Character> userCharacter = new ArrayList<Character>();
        for(Character warrior : characters){
            if(warrior.getHealth() > 0){
                userCharacter.add(warrior);
            }
        }
        Character characterToHeal = Collections.min(userCharacter, new Comparator<Character>() {
            @Override
            public int compare(Character c1, Character c2) {
                return Double.compare(c1.getHealth(), c2.getHealth());
            }
        });
        double healingPower = healer.getAttack() * 0.1;
        //double val = Math.max(characterToHeal.getHealth() + healingPower, characterToHeal.getHealth());
        characterToHeal.setHealth(characterToHeal.getHealth() + healingPower);
        System.out.printf("%s", characterToHeal.getName());
        System.out.printf("\n"+characterToHeal.getName()+"'s health is: ");
        System.out.printf("%.1f\n", characterToHeal.getHealth() + healingPower);
    }

    //this is the common styling printer used
    public static void printMessege(String message){
        for (int i = 0; i < message.length(); i++) {
            System.out.print(message.charAt(i));
            try {
                Thread.sleep(100); // Add a small delay for visualization
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //method for print player stats
    private static void displayPlayerStats(Player user) {
        // Retrieve and display the player's stats
        System.out.println("Your Stats: ");
        String XP = String.valueOf(user.getXP());
        String goldCoins = String.valueOf(user.getGoldCoins());
        String Archer = user.getArcher();
        String Knight = user.getKnight();
        String Mage = user.getMage();
        String Healer = user.getHealer();
        String MythicalCreature = user.getMythicalCreature();
        String[] attributes = {
                "Name", "Username", "XP", "Gold coins", "Homeground", "Archer", "Knight", "Mage", "Healer", "Mythical Creature"
        };
        String[] details = {
                user.getName(), user.getUserName(), XP, goldCoins, user.getHomeGround(), Archer, Knight, Mage, Healer, MythicalCreature
        };
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < attributes.length; i++) {
            System.out.printf("| %-20s | %-20s |\n", attributes[i], details[i]);
            System.out.println("-------------------------------------------------");
        }
        return;
    }

    //print each store elements
    public static void printCharacter(int i, String name, int price, int attack, int defence, Double health, Double speed) {
        System.out.printf("|%-10d| %-15s | %-10d | %-8d | %-8d | %-8d | %-8d |%n", i, name, price, attack, defence, (int) Math.floor(health), (int) Math.floor(speed));
    }

    //method for display the store
    public static void displayStore(){
        System.out.println("Character Table");
        System.out.println("------------------------------------------------------------------------------");
        System.out.printf("|%-10s| %-15s | %-10s | %-8s | %-8s | %-8s | %-8s |%n", "Numbers", "Name", "Price", "Attack", "Defence", "Health", "Speed");
        System.out.println("------------------------------------------------------------------------------");

        System.out.printf("| %-57s %n", "Archer");
        System.out.println("------------------------------------------------------------------------------");
        Character shooter = new Shooter();
        printCharacter(1,shooter.getName(), shooter.getPrice(), shooter.getAttack(), shooter.getDefence(), shooter.getHealth(), shooter.getSpeed());

        Character ranger = new Ranger();
        printCharacter(2,ranger.getName(), ranger.getPrice(), ranger.getAttack(), ranger.getDefence(), ranger.getHealth(), ranger.getSpeed());

        Character sunfire = new Sunfire();
        printCharacter(3,sunfire.getName(), sunfire.getPrice(), sunfire.getAttack(), sunfire.getDefence(), sunfire.getHealth(), sunfire.getSpeed());

        Character zing = new Zing();
        printCharacter(4,zing.getName(), zing.getPrice(), zing.getAttack(), zing.getDefence(), zing.getHealth(), zing.getSpeed());

        Character saggitarius = new Saggitarius();
        printCharacter(5,saggitarius.getName(), saggitarius.getPrice(), saggitarius.getAttack(), saggitarius.getDefence(), saggitarius.getHealth(), saggitarius.getSpeed());
        System.out.println("------------------------------------------------------------------------------");
        System.out.printf("| %-57s %n", "Knights");
        System.out.println("------------------------------------------------------------------------------");
        Character squire = new Squire();
        printCharacter(1,squire.getName(), squire.getPrice(), squire.getAttack(), squire.getDefence(), squire.getHealth(), squire.getSpeed());

        Character cavalier = new Cavalier();
        printCharacter(2,cavalier.getName(), cavalier.getPrice(), cavalier.getAttack(), cavalier.getDefence(), cavalier.getHealth(), cavalier.getSpeed());

        Character templar = new Templar();
        printCharacter(3,templar.getName(), templar.getPrice(), templar.getAttack(), templar.getDefence(), templar.getHealth(), templar.getSpeed());

        Character zoro = new Zoro();
        printCharacter(4,zoro.getName(), zoro.getPrice(), zoro.getAttack(), zoro.getDefence(), zoro.getHealth(), zoro.getSpeed());

        Character swiftblade = new Swiftblade();
        printCharacter(5,swiftblade.getName(), swiftblade.getPrice(), swiftblade.getAttack(), swiftblade.getDefence(), swiftblade.getHealth(), swiftblade.getSpeed());
        System.out.println("------------------------------------------------------------------------------");
        // Print the header for the Mages section
                System.out.printf("| %-57s %n", "Mages");
                System.out.println("------------------------------------------------------------------------------");

                // Create and print details of different types of Mages
                Character warlock = new Warlock();
                printCharacter(1,warlock.getName(), warlock.getPrice(), warlock.getAttack(), warlock.getDefence(), warlock.getHealth(), warlock.getSpeed());

                Character illusionist = new Illusionist();
                printCharacter(2,illusionist.getName(), illusionist.getPrice(), illusionist.getAttack(), illusionist.getDefence(), illusionist.getHealth(), illusionist.getSpeed());

                Character enchanter = new Enchanter();
                printCharacter(3,enchanter.getName(), enchanter.getPrice(), enchanter.getAttack(), enchanter.getDefence(), enchanter.getHealth(), enchanter.getSpeed());

                Character conjurer = new Conjurer();
                printCharacter(4,conjurer.getName(), conjurer.getPrice(), conjurer.getAttack(), conjurer.getDefence(), conjurer.getHealth(), conjurer.getSpeed());

                Character eldritch = new Eldritch();
                printCharacter(5,eldritch.getName(), eldritch.getPrice(), eldritch.getAttack(), eldritch.getDefence(), eldritch.getHealth(), eldritch.getSpeed());

                // Print the header for the Healers section
                System.out.println("------------------------------------------------------------------------------");
                System.out.printf("| %-57s %n", "Healers");
                System.out.println("------------------------------------------------------------------------------");

                // Create and print details of different types of Healers
                Character soother = new Soother();
                printCharacter(1,soother.getName(), soother.getPrice(), soother.getAttack(), soother.getDefence(), soother.getHealth(), soother.getSpeed());

                Character medic = new Medic();
                printCharacter(2,medic.getName(), medic.getPrice(), medic.getAttack(), medic.getDefence(), medic.getHealth(), medic.getSpeed());

                Character alchemist = new Alchemist();
                printCharacter(3,alchemist.getName(), alchemist.getPrice(), alchemist.getAttack(), alchemist.getDefence(), alchemist.getHealth(), alchemist.getSpeed());

                Character saint = new Saint();
                printCharacter(4,saint.getName(), saint.getPrice(), saint.getAttack(), saint.getDefence(), saint.getHealth(), saint.getSpeed());

                Character lightbringer = new Lightbringer();
                printCharacter(5,lightbringer.getName(), lightbringer.getPrice(), lightbringer.getAttack(), lightbringer.getDefence(), lightbringer.getHealth(), lightbringer.getSpeed());

                // Print the header for the Mythical Creatures section
                System.out.println("------------------------------------------------------------------------------");
                System.out.printf("| %-57s %n", "Mythical Creatures");
                System.out.println("------------------------------------------------------------------------------");

                // Create and print details of different types of Mythical Creatures
                Character dragon = new Dragon();
                printCharacter(1,dragon.getName(), dragon.getPrice(), dragon.getAttack(), dragon.getDefence(), dragon.getHealth(), dragon.getSpeed());

                Character basilisk = new Basilisk();
                printCharacter(2,basilisk.getName(), basilisk.getPrice(), basilisk.getAttack(), basilisk.getDefence(), basilisk.getHealth(), basilisk.getSpeed());

                Character hydra = new Hydra();
                printCharacter(3,hydra.getName(), hydra.getPrice(), hydra.getAttack(), hydra.getDefence(), hydra.getHealth(), hydra.getSpeed());

                Character phoenix = new Phoenix();
                printCharacter(4,phoenix.getName(), phoenix.getPrice(), phoenix.getAttack(), phoenix.getDefence(), phoenix.getHealth(), phoenix.getSpeed());

                Character pegasus = new Pegasus();
                printCharacter(5,pegasus.getName(), pegasus.getPrice(), pegasus.getAttack(), pegasus.getDefence(), pegasus.getHealth(), pegasus.getSpeed());

                // Print the header for the Equipment Table section
                System.out.println("------------------------------------------------------------------------------");
                System.out.println();
                System.out.println("Equipment Table");
                System.out.println("------------------------------------------------------------------------------");
                System.out.printf("|%-10s| %-15s | %-10s | %-8s | %-8s | %-8s | %-8s |%n", "Number","Name", "Price", "Attack", "Defence", "Health", "Speed");
                System.out.println("------------------------------------------------------------------------------");

                // Print the header for the Armour section
                System.out.printf("| %-57s %n", "Armour");
                System.out.println("------------------------------------------------------------------------------");

                // Create and print details of different types of Armour
                Armour chainmail = new Chainmail();
                printCharacter(1,chainmail.getName(), chainmail.getPrice(), chainmail.getAttack(), chainmail.getDefence(), chainmail.getHealth(), chainmail.getSpeed());

                Armour regalia = new Regalia();
                printCharacter(2,regalia.getName(), regalia.getPrice(), regalia.getAttack(), regalia.getDefence(), regalia.getHealth(), regalia.getSpeed());

                Armour fleece = new Fleece();
                printCharacter(3,fleece.getName(), fleece.getPrice(), fleece.getAttack(), fleece.getDefence(), fleece.getHealth(), fleece.getSpeed());

                // Print the header for the Artefact section
                System.out.println("------------------------------------------------------------------------------");
                System.out.printf("| %-57s %n", "Artefact");
                System.out.println("------------------------------------------------------------------------------");

                // Create and print details of different types of Artefacts
                Artefact excalibur = new Excalibur();
                printCharacter(1,excalibur.getName(), excalibur.getPrice(), excalibur.getAttack(), excalibur.getDefence(), excalibur.getHealth(), excalibur.getSpeed());

                Artefact amulet = new Amulet();
                printCharacter(2,amulet.getName(), amulet.getPrice(), amulet.getAttack(), amulet.getDefence(), amulet.getHealth(), amulet.getSpeed());

                Artefact crystal = new Crystal();
                printCharacter(3,crystal.getName(), crystal.getPrice(), crystal.getAttack(), crystal.getDefence(), crystal.getHealth(), crystal.getSpeed());

                System.out.println("------------------------------------------------------------------------------");

            }

    // Method to select an enemy for the player to battle against
    public static Player selectEnemy(Player user,List<Player> enemyList,Scanner scanner) throws CloneNotSupportedException {
                // Player 2
                try{
                    // Select an enemy from the list
                    Player enemy = enemyList.get(battled);
                    System.out.println("You are challenging " + enemy.getUserName() + " to a battle!");

                    // Prepare the attributes and details for display
                    String[] attributes = {
                            "Name", "Username", "XP", "Archer", "Knight", "Mage", "Healer", "Mythical Creature"
                    };
                    String[] details = {
                            enemy.getName(), enemy.getUserName(), Integer.toString(enemy.getXP()), enemy.getArcher(), enemy.getKnight(), enemy.getMage(), enemy.getHealer(), enemy.getMythicalCreature()
                    };

                    // Output the message in a tabular format
                    System.out.println("\n-------------------------------------------------");
                    for (int i = 0; i < attributes.length; i++) {
                        System.out.printf("| %-20s | %-20s |\n", attributes[i], details[i]);
                        System.out.println("-------------------------------------------------");
                    }

                    // Ask the user if they want to battle or skip to the next player
                    System.out.println();
                    System.out.println("Do you want to battle or skip to the next player?");
                    System.out.println("1) Battle. ");
                    System.out.println("2) Skip ");
                    System.out.println("Enter the number corresponding to your prefer: ");
                    int battleChoice = scanner.nextInt();

                    // Process the user's choice
                    switch (battleChoice) {
                        case 1:
                            // If the user chooses to battle, return the selected enemy
                            return enemy;
                        case 2:
                            // If the user chooses to skip, select the next enemy
                            battled += 1;
                            enemy = selectEnemy(user, enemyList, scanner);
                            if(enemy != null){
                                challengePlayer(user, enemy, scanner);
                            }
                            break;
                        default:
                            // If the user enters an invalid choice, display an error message
                            System.out.println("Invalid choice");
                            break;
                    }

                }
                catch (IndexOutOfBoundsException e){
                    // If all enemies have been battled, congratulate the user
                    System.out.println("Congratulations!!!");
                    System.out.println("You Completed the All levels!");
                    return null;
                }
                return null;
            }

}
