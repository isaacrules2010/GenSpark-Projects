package Game;

import Entities.Creature;
import Entities.Goblin;
import Entities.Human;
import Equipment.Item;
import Equipment.Potion;
import World.Land;
import World.Unit;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class HumansVsGoblins {

    private Unit[][] worldMap;
    private final int mapSize = 25;
    private final int middle = (mapSize-1)/2;

    private ArrayList<Goblin> goblins;
    private ArrayList<Item> drops;
    private Human player;

    Scanner userInput;

    int roundCount;

    public static void main(String[] args){
        System.out.println(
                "Welcome to my Humans vs Goblins game!\n" +
                "In this game you play a lone Human(\u0398) who is lost in a forest overrun with Goblins(\u029b).\n" +
                "This is a N/S/E/W game, meaning you move by choosing North('n') South('s') East('e') or West('w')\n" +
                "To win, all you need to do is defeat all the goblins! Be careful though, they're tough.\n" +
                "----------------\n" +
                "This Game is structured much like the famous game \"Dungeons and Dragons,\"\n" +
                "you have 3 stats:\n" +
                "- Strength: Determines how much damage you deal and how often you hit.\n" +
                "- Dexterity: Determines how difficult you are to hit.\n" +
                "- Constitution: Determines maximum HP.\n" +
                "----------------\n" +
                "You start at 10 HP and lose if you drop to 0, but don't fret.\n" +
                "You heal back to full if you defeat a goblin! You can also heal, just a bit slower, by staying still!\n" +
                "To do so, put any input that isn't N, S, E, or W.\n" +
                "To attack, all you have to do is try to move into a Goblin's space.\n" +
                "Goblins also have drops! If you walk over an item('D') and its better than what you're currently using, \n" +
                "You'll automatically pick it up! If it isn't, then you'll throw it away like the trash it is.\n" +
                "Be careful, though, if you don't pick up an item and a goblin stumbles across it, they'll also be able to pick it up.\n" +
                "Let's get going!!!\n"
        );
        HumansVsGoblins game = new HumansVsGoblins();
        game.initGame();
        game.startGame();
    }

    public void initGame(){
        worldMap = new Unit[mapSize][mapSize];

        goblins = new ArrayList<>();
        drops = new ArrayList<>();
        setScanner(System.in);

        roundCount = 0;
    }

    public void setScanner(InputStream in){
        userInput = new Scanner(in);
    }
    public void startGame(){
        generateEmptyMap();
        int[] pos = {middle, middle};
        spawnPlayer(pos);
        do{
            populateMap(5 + roundCount);
            System.out.println(printMap());
            playGame();
            spawnPrize();
            roundCount++;
        }
        while(getUserInput() == 'y');
    }

    public void playGame(){
        do{
            System.out.println(player.toString());
            System.out.println("Which direction would you like to go?");
            moveCreature(player, getUserInput());
            try{
                for(Goblin goblin:goblins){
                    pursue(goblin);
                    goblin.readyForCombat();
                }
            }
            catch(Exception e){
                //goblins don't move for a turn
            }
            System.out.println(printMap());
        }
        while(player.getHealth() > 0 && !goblins.isEmpty());
        if(player.getHealth() <= 0){
            System.out.println(
                    "|||||||||||||||||||||||||||\n" +
                    "|||   Y O U   L O S T   |||\n" +
                    "|||||||||||||||||||||||||||\n"
            );
        }
        else{
            System.out.println("Congratulations! You've defeated all the goblins!");
        }

        System.out.println("\nYou could escape now or you could continue into the forest... \n" +
                "Would you like to continue? (y to continue) Any other input will leave the forest and end the game.\n");
    }

    public void generateEmptyMap(){
        for(int y = mapSize - 1; y >= 0; y--){
            for(int x = 0; x < mapSize; x++){
                worldMap[x][y] = new Land(new int[]{x, y});
            }
        }
    }

    // populates the map with a specific number of Goblins
    public void populateMap(int count){
        Random pos = new Random();

        while(goblins.size() < count){
            spawnGoblin(new int[]{pos.nextInt(mapSize), pos.nextInt(mapSize)});
        }
    }

    public void spawnGoblin(int[] position){
        if(worldMap[position[0]][position[1]] instanceof Creature){
            //if there's already creature at this position, don't spawn
            return;
        }
        Goblin gobbo = new Goblin(position, roundCount);
        goblins.add(gobbo);
        worldMap[position[0]][position[1]] = gobbo;
    }

    public void spawnPlayer(int[] pos){
        player = new Human();
        player.setPosition(pos);
        worldMap[middle][middle] = player;
    }

    private void spawnPrize() {
        Potion potion = new Potion(1, 1, 1);
        int[] pos = {middle, middle};
        potion.setPosition(pos);
        potion.setSymbol('!');
        if(!(worldMap[middle][middle] instanceof Creature)){
            worldMap[middle][middle] = potion;
        }
        else
            worldMap[middle+1][middle] = potion;

    }
    public void removeGoblin(Goblin goblin){
        int[] pos = goblin.getPosition();
        worldMap[pos[0]][pos[1]] = goblin.getDrop();
        goblin.getDrop().setPosition(pos);
        drops.add(goblin.getDrop());
        goblins.remove(goblin);
    }

    public void removeDrop(Item drop){
        int[] pos = drop.getPosition();
        Land emptyLand = new Land(pos);
        worldMap[pos[0]][pos[1]] = emptyLand;
        drops.remove(drop);
    }

    public String printMap(){
        String output = "";
        for(int y = worldMap.length - 1; y >= 0; y--){
            for(int x = 0; x < worldMap.length; x++){
                output +=(worldMap[x][y].getSymbol() + "  ");
            }
            output += "\n";
        }
        output += "\n";
        return output;
    }

    public void moveCreature(Creature creature, char dir){
        int[] newPos = {creature.getPosition()[0], creature.getPosition()[1]};

        switch(dir){
            case 'n':
                newPos[1]+=1;
                break;
            case 's':
                newPos[1]-=1;
                break;
            case 'e':
                newPos[0]+=1;
                break;
            case 'w':
                newPos[0]-=1;
                break;
            default:
                //do nothing;
                break;
        }
        try {

            boolean outOfBounds = newPos[0] > mapSize - 1 || newPos[1] > mapSize - 1 || newPos[0] < 0 || newPos[1] < 0;
            if(creature instanceof Human){
                if(outOfBounds){
                    System.out.println("You can't leave the forest yet. There are still goblin's about.");
                    throw new Exception("Out Of Bounds");
                }
                else if (worldMap[newPos[0]][newPos[1]] instanceof Goblin) {
                    combat(creature, (Creature)worldMap[newPos[0]][newPos[1]]);
                }
                else if (Arrays.equals(newPos, creature.getPosition())) {
                    int oldHp = player.getHealth();
                    player.heal();
                    System.out.println("You've healed by " + (player.getHealth() - oldHp) + " points!");
                }
                else {
                    if(worldMap[newPos[0]][newPos[1]] instanceof Item){
                        creature.pickUp((Item)worldMap[newPos[0]][newPos[1]]);
                        removeDrop((Item)worldMap[newPos[0]][newPos[1]]);
                    }
                    int[] oldPos = creature.getPosition();
                    player.setPosition(newPos);
                    swapTile(oldPos, newPos);
                }
            }
            else if(creature instanceof Goblin){
                if(outOfBounds){
                    throw new Exception("Out Of Bounds");
                }
                else if (worldMap[newPos[0]][newPos[1]] instanceof Human) {
                    combat(creature, (Creature)worldMap[newPos[0]][newPos[1]]);
                }
                else if (Arrays.equals(newPos, creature.getPosition())) {
                    //goblins don't heal
                }
                else {
                    if(worldMap[newPos[0]][newPos[1]] instanceof Item){
                        creature.pickUp((Item)worldMap[newPos[0]][newPos[1]]);
                        removeDrop((Item)worldMap[newPos[0]][newPos[1]]);
                    }
                    int[] oldPos = creature.getPosition();
                    creature.setPosition(newPos);
                    swapTile(oldPos, newPos);
                }
            }
        }
        catch(Exception e){
            // something went wrong
        }

    }

    public void swapTile(int[] from, int[] to){
        Unit temp;
        temp = worldMap[to[0]][to[1]];
        temp.setPosition(from);

        worldMap[to[0]][to[1]] = worldMap[from[0]][from[1]];
        worldMap[to[0]][to[1]].setPosition(to);
        worldMap[from[0]][from[1]] = temp;
    }

    public void combat(Creature attacker, Creature target){
        if(target instanceof Goblin){
            attacker.attack(target);
            goblins.get(goblins.indexOf((Goblin)target)).setHealth(target.getHealth());
            if(target.getHealth() > 0){
                target.attack(attacker);
            }
            System.out.printf("Goblin HP: %d/%d\n", target.getHealth(), target.getMaxHp());
            if(target.getHealth() <= 0){
                System.out.println("You killed the Goblin!!!");
                removeGoblin((Goblin)target);
            }
        }
        if(target instanceof Human){
            attacker.attack(target);
            if(target.getHealth() > 0){
                target.attack(attacker);
                goblins.get(goblins.indexOf((Goblin)attacker)).setHealth(attacker.getHealth());
            }
            System.out.printf("Goblin HP: %d/%d\n", attacker.getHealth(), attacker.getMaxHp());
            if(attacker instanceof Goblin && attacker.getHealth() <= 0){
                System.out.println("You killed the Goblin!!!");
                removeGoblin((Goblin)attacker);
            }
        }



        System.out.println("\n");
    }

    //Goblin Pursuit Logic
    public void pursue(Goblin goblin){
        int goblinX = goblin.getPosition()[0];
        int goblinY = goblin.getPosition()[1];

        if((Math.abs(goblinX - player.getPosition()[0]) < 4) || (Math.abs(goblinY - player.getPosition()[1]) < 4)){
            //if player within 4 Tile radius
            if(goblinX != player.getPosition()[0]){
                // move e or w
                if(player.getPosition()[0] > goblinX)
                    moveCreature(goblin, 'e');
                else moveCreature(goblin, 'w');
            }
            else if(goblinY != player.getPosition()[1]){
                //move n or s
                if(player.getPosition()[1] > goblinY)
                    moveCreature(goblin, 'n');
                else moveCreature(goblin, 's');
            }
        }
    }

    public char getUserInput(){
        char output = '?';
        try{
            String temp = userInput.nextLine();
            if(temp.length() > 1){
                return output;
            }
            output = temp.charAt(0);
        }
        catch(Exception e){
            System.out.println("There was an issue reading your input.\n");
        }
        return output;
    }

    public ArrayList<Goblin> getGoblins(){
        return goblins;
    }

    public ArrayList<Item> getDrops() {
        return drops;
    }

    public Human getPlayer() {
        return player;
    }

    public Unit[][] getWorldMap() {
        return worldMap;
    }
}
