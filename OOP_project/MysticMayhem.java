import java.util.*;
import Character.*;
import Character.Character;
import Equipment.*;
import Player.Player;

public class MysticMayhem {

    public static void main(String [] args){
        try (Scanner scanner = new Scanner(System.in)){
            Player user = createPlayer(scanner);
            Player enemy = createEnemy();
            int flag = 1;
            while (flag != 0) {
                String input = scanner.nextLine();  // Read the input

                if (input.equalsIgnoreCase("x")) {
                    displayPlayerStats(user);
                }
                else if (input.equalsIgnoreCase("s")){
                    displayStore();
                }
                else if (input.equalsIgnoreCase("b")){
                    buyCharacters(scanner, user);
                }
                else if (input.equalsIgnoreCase("u")){
                    upgradeCharacters(scanner, user);
                }
                else if (input.equalsIgnoreCase("d")){
                    sellCharacters(scanner, user);
                }
                else if (input.equalsIgnoreCase("p")){
                    createPlayer(scanner);
                }
                else if (input.equalsIgnoreCase("w")){
                    challengePlayer(user, enemy, scanner);
                    combat(user, enemy, enemy.getHomeGround());
                }
                else if (input.equalsIgnoreCase("0")){
                    flag = 0;
                }
                else{
                    System.out.println();
                    System.out.println("You are in Home!");
                    System.out.println( "Press 'x' to display your stats.\n" +
                                        "Press 's' to display store.\n" +
                                        "Press 'b' to buy characters.\n" +
                                        "Press 'u' to upgrade characters.\n" +
                                        "Press 'd' to sell characters.\n" +
                                        "Press 'p' to create a player.\n" +
                                        "Press 'w' to challenge White Wolf.\n");
                }
            }
        }
    }
    public static void combat(Player user, Player enemy, String battleground) {
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
        List<Character> userTurnOrder = getTurnOrder(userCharacters);
        List<Character> enemyTurnOrder = getTurnOrder(enemyCharacters);
//        List<Character> userDefTurnOrder = userCharacters;
//        List<Character> enemyDefTurnOrder = enemyCharacters;
        //System.out.println(userTurnOrder);
        //System.out.println(userDefTurnOrder);
        //System.out.println(enemyTurnOrder);
        //System.out.println(enemyDefTurnOrder);
        int userCharAlive;
        int enemyCharAlive;
        int userTurnsLeft = 10;
        int enemyTurnsLeft = 10;
        Character attacker;
        Character defender;
        for(int i= 0; i < 20; i++){
            userCharAlive = userTurnOrder.size();
            enemyCharAlive = enemyTurnOrder.size();
            if(i % 2 == 0){ // user's turn
                if(userTurnsLeft == 0){
                    break;
                }
                attacker = userTurnOrder.get(i/2 % userCharAlive);
                defender = getDefender(enemyTurnOrder);
                System.out.println(attacker.getName()+" attacks "+defender.getName());
                if (defender.getHealth()==0.0){
                    enemyTurnOrder.remove(defender);
                }
                if (attacker instanceof Healer) {
                    heal(attacker, userCharacters);
                } else {
                    attack(attacker, defender, enemyCharacters);
                }
                userTurnsLeft--;
                if (enemyTurnOrder.isEmpty()) {
                    System.out.println("User wins!");
                    afterWin(user, enemy);
                    break;
                }
            }else{ // enemy's turn
                if(enemyTurnsLeft == 0){
                    break;
                }
                attacker = enemyTurnOrder.get((i-1)/2 % enemyCharAlive);
                defender = getDefender(userTurnOrder);
                System.out.println(attacker.getName()+" attacks "+defender.getName());
                if (defender.getHealth()==0.0){
                    userTurnOrder.remove(defender);
                }
                if (attacker instanceof Healer) {
                    heal(attacker, enemyCharacters);
                } else {
                    attack(attacker, defender, userCharacters);
                }
                enemyTurnsLeft--;
                if (userTurnOrder.isEmpty()) {
                    System.out.println("Enemy wins!");
                    afterWin(enemy, user);
                    break;
                }
            }
        }
        if (!enemyTurnOrder.isEmpty() && !userTurnOrder.isEmpty()){
            System.out.println("Draw!");
        }
        /*Character attacker;
        Character defender = new Archer();
        int userCharAlive;
        int enemyCharAlive;
        for(int i= 0; i <2*(userTurnOrder.size()+enemyTurnOrder.size()); i++){
            userCharAlive = userDefTurnOrder.size();
            enemyCharAlive = enemyDefTurnOrder.size();
            if(i%2 == 0){
                i = i/2;
                i = i/userCharAlive;
                attacker = userTurnOrder.get(i);
                System.out.println(attacker.getName());
                if (defender.getHealth()==0.0){
                    defender = enemyDefTurnOrder.remove(i);
                    System.out.println(defender.getName());
                }
                if (attacker instanceof Healer) {
                    heal(attacker, userCharacters);
                } else {
                    attack(attacker, defender, enemyCharacters);
                }
                if (enemyDefTurnOrder.isEmpty()) {
                    System.out.println("User wins!");
                    afterWin(user, enemy);
                    break;
                }
            }else{
                i = (i-1)/2;
                i = i/enemyCharAlive;
                attacker = enemyTurnOrder.get(i);
                System.out.println(attacker.getName());
                if (defender.getHealth()==0.0){
                    defender = enemyDefTurnOrder.remove(i);
                    System.out.println(defender.getName());
                }
                if (attacker instanceof Healer) {
                    heal(attacker, enemyCharacters);
                } else {
                    attack(attacker, defender, userCharacters);
                }
                if (userDefTurnOrder.isEmpty()) {
                    System.out.println("Enemy wins!");
                    afterWin(enemy, user);
                    break;
                }
            }
        }*/
        /*for (Character userChar : userTurnOrder) {
            if (userChar.getHealth() <= 0) continue;
            Character enemyChar = enemyTurnOrder.remove(enemyTurnOrder.size() - 1);
            if (enemyChar.getHealth() <= 0) continue;
            if (userChar instanceof Healer) {
                heal(userChar, userCharacters);
            } else {
                attack(userChar, enemyChar, enemyCharacters);
            }
            if (enemyChar.getHealth() <= 0) {
                enemyCharacters.remove(enemyChar);
            }
        }
        if (enemyCharacters.isEmpty()) {
            System.out.println("User wins!");
            afterWin(user, enemy);
            break;
        }
        Collections.reverse(enemyTurnOrder);
        for (Character enemyChar : enemyTurnOrder) {
            if (enemyChar.getHealth() <= 0) continue;
            Character userChar = userTurnOrder.remove(userTurnOrder.size() - 1);
            if (userChar.getHealth() <= 0) continue;
            if (enemyChar instanceof Healer) {
                heal(enemyChar, enemyCharacters);
            } else {
                attack(enemyChar, userChar, userCharacters);
            }
            if (userChar.getHealth() <= 0) {
                userCharacters.remove(userChar);
            }
        }
        if (userCharacters.isEmpty()) {
            System.out.println("Enemy wins!");
            afterWin(enemy, user);
            break;
        }
        turn++;*/

    }
    private static List<Character> getDefTurnOrder(List<Character> characters) {
        Collections.sort(characters, new Comparator<Character>() {
            @Override
            public int compare(Character c1, Character c2) {
                return Double.compare(c2.getDefence(), c1.getDefence());
            }
        });
        return characters;
    }
    private static List<Character> getTurnOrder(List<Character> characters) {
        Collections.sort(characters, new Comparator<Character>() {
            @Override
            public int compare(Character c1, Character c2) {
                return Double.compare(c2.getSpeed(), c1.getSpeed());
            }
        });
        return characters;
    }
    private static Character getDefender(List<Character> defenders) {
        return Collections.min(defenders, new Comparator<Character>() {
            @Override
            public int compare(Character c1, Character c2) {
                return Double.compare(c1.getDefence(), c2.getDefence());
            }
        });
    }
    public static void afterWin(Player player, Player looser){
        player.setXP(player.getXP()+1);
        player.setGoldCoins(player.getGoldCoins()+(int) Math.floor(looser.getGoldCoins()/10));
        looser.setGoldCoins(looser.getGoldCoins()-(int) Math.floor(looser.getGoldCoins()/10));
    }
    private static void attack(Character attacker, Character defender, List<Character> characters) {
        double damage = 0.5 * attacker.getAttack() - 0.1 * defender.getDefence();
        defender.setHealth(defender.getHealth() - damage);
        if (defender.getHealth() <= 0) {
            characters.remove(defender);
        }
    }
    private static void heal(Character healer, List<Character> characters) {
        if (characters.isEmpty()) {
            return;
        }
        Character characterToHeal = Collections.min(characters, new Comparator<Character>() {
            @Override
            public int compare(Character c1, Character c2) {
                return Double.compare(c1.getHealth(), c2.getHealth());
            }
        });
        double healingPower = healer.getAttack() * 0.1;
        characterToHeal.setHealth(Math.min(characterToHeal.getHealth() + healingPower, characterToHeal.getHealth()));
    }
    public static void challengePlayer(Player user, Player enemy, Scanner scanner){
        String mission = "Huzzah!!! \n      You are now a legendary warrior in the Mayhem of the Ancients\n"+
                String.format("Greetings, brave %s! Your journey begins now.\n", user.getUserName())+
                "Embark on a perilous quest to vanquish the White Wolf and forge your legendary army!\n" +
                "Behold the formidable stats of the White Wolf as you muster your own mystical forces.\n"+
                "Behold, noble "+enemy.getName()+", known as the "+enemy.getUserName()+"\n";

        for (int i = 0; i < mission.length(); i++) {
            System.out.print(mission.charAt(i));
            try {
                Thread.sleep(1); // Add a small delay for visualization
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String[] attributes = {
                "Name", "Username", "XP", "Archer", "Knight", "Mage", "Healer", "Mythical Creature"
        };
        String[] details = {
                "GeraltofRivia", "whitewolf", "32", "Ranger", "Squire", "Warlock", "Medic", "Dragon"
        };

        // Output the message in a tabular format
        System.out.println("\n-------------------------------------------------");
        for (int i = 0; i < attributes.length; i++) {
            System.out.printf("| %-20s | %-20s |\n", attributes[i], details[i]);
            System.out.println("-------------------------------------------------");
        }
        System.out.println();
        System.out.println("Let's fight for our pride!!!");

        for(String warr : user.characterList.keySet()) {
            if (user.characterList.get(warr).getName() == "") {
                System.out.println("Your army is not ready to battle.");
                buyCharacters(scanner, user);
            }
        }
        System.out.println("You are ready to go!");

    }
    public static Player createEnemy(){
        //Enemy creation
        Player enemy = new Player();
        Ranger eArcher = new Ranger();
        Chainmail eChainMail = new Chainmail();
        eArcher.buyArmour(eChainMail, enemy);

        Squire eKnight = new Squire();

        Warlock eMage = new Warlock();

        Medic eHealer = new Medic();
        Amulet eArtefact = new Amulet();
        eHealer.buyArtefact(eArtefact, enemy);

        Dragon eMythicalCreature = new Dragon();
        HashMap<String, Character> characterList = new HashMap<String, Character>(Map.of(
                "Archer", eArcher,
                "Knight", eKnight,
                "Healer", eHealer,
                "Mage", eMage,
                "MythicalCreature", eMythicalCreature
        ));
        enemy = new Player("Geralt_of_Rivia", "whitewolf", 32, 215, "Marshland", characterList);
        return enemy;
    }
    public static Player createPlayer(Scanner scanner){
        //Player.Player creation
        Player user = new Player();
        String messege = "Welcome to the Mystical Mayhem!\n";
        for (int i = 0; i < messege.length(); i++) {
            System.out.print(messege.charAt(i));
            try {
                Thread.sleep(1); // Add a small delay for visualization
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Hey! Warrior!!! \n       who are you?");
        String name = scanner.nextLine();
        user.setName(name);
        System.out.println("Hail, mighty " + name);

        System.out.println("Choose a Heroic Title: ");
        String userName = scanner.nextLine();
        while (Arrays.asList(Player.userNames).contains(userName)) {
            System.out.println("Heroic Title already claimed! \nChoose another Heroic Title: ");
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
        return user;
    }
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
    }
    public static void printCharacter(int i, String name, int price, int attack, int defence, Double health, Double speed) {
        System.out.printf("|%-10d| %-15s | %-10d | %-8d | %-8d | %-8d | %-8d |%n", i, name, price, attack, defence, (int) Math.floor(health), (int) Math.floor(speed));
    }
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
        System.out.printf("| %-57s %n", "Mages");
        System.out.println("------------------------------------------------------------------------------");
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
        System.out.println("------------------------------------------------------------------------------");
        System.out.printf("| %-57s %n", "Healers");
        System.out.println("------------------------------------------------------------------------------");
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
        System.out.println("------------------------------------------------------------------------------");
        System.out.printf("| %-57s %n", "Mythical Creatures");
        System.out.println("------------------------------------------------------------------------------");
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

        System.out.println("------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("Equipment Table");
        System.out.println("------------------------------------------------------------------------------");
        System.out.printf("|%-10s| %-15s | %-10s | %-8s | %-8s | %-8s | %-8s |%n", "Number","Name", "Price", "Attack", "Defence", "Health", "Speed");
        System.out.println("------------------------------------------------------------------------------");
        System.out.printf("| %-57s %n", "Armour");
        System.out.println("------------------------------------------------------------------------------");
        Armour chainmail = new Chainmail();
        printCharacter(1,chainmail.getName(), chainmail.getPrice(), chainmail.getAttack(), chainmail.getDefence(), chainmail.getHealth(), chainmail.getSpeed());


        Armour regalia = new Regalia();
        printCharacter(2,regalia.getName(), regalia.getPrice(), regalia.getAttack(), regalia.getDefence(), regalia.getHealth(), regalia.getSpeed());

        Armour fleece = new Fleece();
        printCharacter(3,fleece.getName(), fleece.getPrice(), fleece.getAttack(), fleece.getDefence(), fleece.getHealth(), fleece.getSpeed());
        System.out.println("------------------------------------------------------------------------------");
        System.out.printf("| %-57s %n", "Artefact");
        System.out.println("------------------------------------------------------------------------------");
        Artefact excalibur = new Excalibur();
        printCharacter(1,excalibur.getName(), excalibur.getPrice(), excalibur.getAttack(), excalibur.getDefence(), excalibur.getHealth(), excalibur.getSpeed());

        Artefact amulet = new Amulet();
        printCharacter(2,amulet.getName(), amulet.getPrice(), amulet.getAttack(), amulet.getDefence(), amulet.getHealth(), amulet.getSpeed());

        Artefact crystal = new Crystal();
        printCharacter(3,crystal.getName(), crystal.getPrice(), crystal.getAttack(), crystal.getDefence(), crystal.getHealth(), crystal.getSpeed());
        System.out.println("------------------------------------------------------------------------------");
    }
    private static void buyCharacters(Scanner scanner,Player user) {
        // Display the list of character types
        System.out.println("Welcome to the Heroic Emporium! Browse and Choose Your Essence: ");
        System.out.println("1. Archers - Masters of Artemis's Craft");
        System.out.println("2. Knights - Defenders of Olympus");
        System.out.println("3. Mages - Bearers of Zeus's Arcane Wisdom");
        System.out.println("4. Healers - Blessed by Apollo's Healing Light");
        System.out.println("5. Mythical Creatures - Denizens of the Majestic Greek Beasts");
        System.out.println("Press other key to go back!");

        // Prompt the user to enter their choice
        System.out.print("\nEnter the number corresponding to your chosen Essence: \n");
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
                    System.out.println("Hey!Let's look for an Archer!");
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
                    System.out.println("Hey!Let's look for an Archer!");
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
                    System.out.println("Hey!Let's look for an Archer!");
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
                    System.out.println("Hey!Let's look for an Archer!");
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
                    System.out.println("Hey!Let's look for an Archer!");
                    buyMythicalCreature(scanner,user);
                    break;
                }
            default:
                System.out.println("You are in the Home!");
        }
    }
    private static void upgradeCharacters(Scanner scanner, Player user) {
        // Display the list of character types available for upgrading
        System.out.println("Welcome to the Heroic Forge! Enhance and Upgrade Your Characters:");
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
        System.out.print("\nEnter the number corresponding to the character type you want to upgrade: ");
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
                System.out.println("Your Chosen Essence is not Recognized in the Realm of Heroes");
        }
    }
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
    public static void buyMythicalCreature(Scanner scanner, Player user){
        System.out.println("Mythical Creatures");
        System.out.println("------------------------------------------------------------------------------");
        System.out.printf("|%-10s| %-15s | %-10s | %-8s | %-8s | %-8s | %-8s |%n", "Number", "Name", "Price", "Attack", "Defence", "Health", "Speed");
        System.out.println("------------------------------------------------------------------------------");

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
        System.out.println("------------------------------------------------------------------------------");

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
    public static void buyHealer(Scanner scanner, Player user){
        System.out.println("Healers");
        System.out.println("------------------------------------------------------------------------------");
        System.out.printf("|%-10s| %-15s | %-10s | %-8s | %-8s | %-8s | %-8s |%n", "Number", "Name", "Price", "Attack", "Defence", "Health", "Speed");
        System.out.println("------------------------------------------------------------------------------");

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
        System.out.println("------------------------------------------------------------------------------");

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
    public static void buyMage(Scanner scanner, Player user){
        System.out.println("Mages");
        System.out.println("------------------------------------------------------------------------------");
        System.out.printf("|%-10s| %-15s | %-10s | %-8s | %-8s | %-8s | %-8s |%n", "Number", "Name", "Price", "Attack", "Defence", "Health", "Speed");
        System.out.println("------------------------------------------------------------------------------");

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
        System.out.println("------------------------------------------------------------------------------");

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
    private static void buyKnight(Scanner scanner, Player user) {
        System.out.println("Knights");
        System.out.println("------------------------------------------------------------------------------");
        System.out.printf("|%-10s| %-15s | %-10s | %-8s | %-8s | %-8s | %-8s |%n", "Number", "Name", "Price", "Attack", "Defence", "Health", "Speed");
        System.out.println("------------------------------------------------------------------------------");

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
        System.out.println("------------------------------------------------------------------------------");

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
    public static void buyArcher(Scanner scanner, Player user){
        System.out.println("Archers");
        System.out.println("------------------------------------------------------------------------------");
        System.out.printf("|%-10s| %-15s | %-10s | %-8s | %-8s | %-8s | %-8s |%n", "Number", "Name", "Price", "Attack", "Defence", "Health", "Speed");
        System.out.println("------------------------------------------------------------------------------");

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
        System.out.println("------------------------------------------------------------------------------");

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
}
