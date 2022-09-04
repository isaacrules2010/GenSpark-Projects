import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import Entities.*;
import Equipment.*;
import World.*;

public class HVGR {
    private JFrame gameFrame;

    private JPanel topHalf;
    private JPanel bottomHalf;
    private JPanel fullDisplay;

    private JButton up;
    private JButton down;
    private JButton left;
    private JButton right;

    private JLabel statsLabel;
    private JTextArea stats;

    private JLabel logLabel;
    private JTextArea log;

    private JTextArea gameDisplay;

    private JFrame winFrame;
    private JFrame loseFrame;

    private JButton quitButton;
    private JButton leaveButton;
    private JButton playAgain;
    private JButton nextRound;

    Timer gameTimer;

    private Unit[][] worldMap;
    private final int mapSize = 20;
    private final int middle = (mapSize - 1) / 2;

    private ArrayList<Goblin> goblins;
    private ArrayList<Item> drops;
    private Human player;

    int roundCount;

    public HVGR(){
        prepareGUI();
        playGame();

       //winFrame.setVisible(true);
       //loseFrame.setVisible(true);
    }
    public void prepareGUI() {

        gameFrame = new JFrame();
        gameFrame.setTitle("Humans Vs. Goblins - REDUX");
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameFrame.setSize(410, 825);

        quitButton = new JButton("Quit");
        leaveButton = new JButton("Leave");
        playAgain = new JButton("Start the fight anew! (Start over)");
        nextRound = new JButton("Keep Fighting!!! (Rewards & more goblins await)");
        winFrame = new JFrame();
        winFrame.setTitle("YOU WIN!");
        winFrame.setLayout(new GridLayout(3, 1));
        winFrame.add(new JTextArea("You've defeated all the Goblins.\nWould you like to stay and fight... or leave the forest?"));
        winFrame.add(nextRound);
        winFrame.add(leaveButton);
        winFrame.pack();

        loseFrame = new JFrame();
        loseFrame.setTitle("YOU LOSE!");
        loseFrame.setLayout(new GridLayout(3, 1));
        loseFrame.add(new JTextArea("The Goblins defeated you and left you for dead...\n Would you like to start the fight anew or leave the forest?"));
        loseFrame.add(playAgain);
        loseFrame.add(quitButton);
        loseFrame.pack();

        topHalf = new JPanel();
        bottomHalf = new JPanel();
        fullDisplay = new JPanel();
        gameDisplay = new JTextArea();

        up = new JButton("Up");
        down = new JButton("Down");
        left = new JButton("Left");
        right = new JButton("Right");
        topHalf.setLayout(new BorderLayout());
        topHalf.add(up, BorderLayout.NORTH);
        topHalf.add(down, BorderLayout.SOUTH);
        topHalf.add(left, BorderLayout.WEST);
        topHalf.add(right, BorderLayout.EAST);
        topHalf.add(gameDisplay, BorderLayout.CENTER);
        topHalf.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topHalf.setPreferredSize(new Dimension(360, 360));
        topHalf.setMinimumSize(topHalf.getPreferredSize());
        topHalf.setMaximumSize(topHalf.getPreferredSize());

        statsLabel = new JLabel("Stats:");
        stats = new JTextArea();
        stats.setEditable(false);
        stats.setText("Stats:\n" +
                "Str: \n" +
                "Dex: \n" +
                "Con: \n");

        logLabel = new JLabel("Log:");
        log = new JTextArea();
        JScrollPane logPane = new JScrollPane(log);
        log.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        log.append("Log:");
        //logPane.add(logLabel);
        logLabel.setVisible(true);
        logLabel.setLabelFor(logPane);
        log.setEditable(false);


        bottomHalf.setLayout(new GridLayout());
        bottomHalf.add(stats);
        bottomHalf.add(logPane);
        bottomHalf.setAlignmentX(Component.CENTER_ALIGNMENT);

        fullDisplay.setLayout(new GridLayout(2, 1));
        fullDisplay.add(topHalf);
        fullDisplay.add(bottomHalf);

        gameFrame.add(fullDisplay);
        //gameFrame.pack();
        gameFrame.setVisible(true);

        //log.append("You did 10 damage\n");
        Font oldFont = gameDisplay.getFont();
        gameDisplay.setFont(new Font(oldFont.getName(), oldFont.getStyle(), oldFont.getSize()));
    }

    public void playGame(){
        // does nothing right now
        initGame();
        generateEmptyMap();
        spawnPlayer(new int[]{middle, middle});
        populateMap(5+roundCount);
        gameDisplay.setText(printMap());

        gameTimer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //...Update the progress bar...
                if(!goblins.isEmpty()){
                    try{
                        for(Goblin goblin:goblins){
                            pursue(goblin);
                            goblin.readyForCombat();
                        }
                    }
                    catch(Exception e){
                        //goblins don't move for a turn
                    }
                }
                else{
                    winFrame.setVisible(true);
                    gameTimer.stop();
                }
                if(player.getHealth() != 0){
                    player.heal();
                    stats.setText(player.toString());
                }
                else{
                    loseFrame.setVisible(true);
                    gameTimer.stop();
                }

            }
        });
        stats.setText(player.toString());
        gameTimer.start();

        up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveCreature(player, 'n');
                //log.append("Player moved up\n");
            }
        });

        down.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveCreature(player, 's');
                //log.append("Player moved down\n");
            }
        });

        left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveCreature(player, 'w');
                //log.append("Player moved left\n");
            }
        });

        right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveCreature(player, 'e');
                //log.append("Player moved right\n");
            }
        });

        leaveButton.addActionListener(event->{
            winFrame.setVisible(false);
            gameFrame.dispose();
            System.exit(0);
        });
        quitButton.addActionListener(event->{
            loseFrame.setVisible(false);
            gameFrame.dispose();
            System.exit(0);
        });
        nextRound.addActionListener(event->{
            spawnPrize();
            gameTimer.start();
            populateMap(5+(++roundCount));
            winFrame.setVisible(false);
        });
        playAgain.addActionListener(event->{
           generateEmptyMap();
           spawnPlayer(new int[]{middle, middle});
           roundCount = 0;
           populateMap(5);
           gameTimer.start();
        });
    }

    private void initGame(){
        worldMap = new Unit[mapSize][mapSize];

        goblins = new ArrayList<>();
        drops = new ArrayList<>();

        roundCount = 0;
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
                    log.append("You can't leave the forest yet. There are still goblin's about.\n");
                    throw new Exception("Out Of Bounds");
                }
                else if (worldMap[newPos[0]][newPos[1]] instanceof Goblin) {
                    combat(creature, (Creature)worldMap[newPos[0]][newPos[1]]);
                }
                else {
                    if(worldMap[newPos[0]][newPos[1]] instanceof Item){
                        log.append(creature.pickUp((Item)worldMap[newPos[0]][newPos[1]]));
                        stats.setText(player.toString());
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
                        log.append(creature.pickUp((Item)worldMap[newPos[0]][newPos[1]]));
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
        gameDisplay.setText(printMap());
    }

    public void swapTile(int[] from, int[] to){
        Unit temp;
        temp = worldMap[to[0]][to[1]];
        temp.setPosition(from);

        worldMap[to[0]][to[1]] = worldMap[from[0]][from[1]];
        worldMap[to[0]][to[1]].setPosition(to);
        worldMap[from[0]][from[1]] = temp;
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

    public void combat(Creature attacker, Creature target){
        if(target instanceof Goblin){
            log.append(attacker.attack(target));
            goblins.get(goblins.indexOf((Goblin)target)).setHealth(target.getHealth());
            if(target.getHealth() > 0){
                log.append(target.attack(attacker));
            }
            log.append("Goblin HP: "+target.getHealth()+"/"+target.getMaxHp()+"\n");
            if(target.getHealth() <= 0){
                log.append("You killed the Goblin!!!\n");
                removeGoblin((Goblin)target);
            }
        }
        if(target instanceof Human){
            log.append(attacker.attack(target));
            if(target.getHealth() > 0){
                log.append(target.attack(attacker));
                goblins.get(goblins.indexOf((Goblin)attacker)).setHealth(attacker.getHealth());
            }
            log.append("Goblin HP: "+attacker.getHealth()+"/"+attacker.getMaxHp()+"\n");
            if(attacker instanceof Goblin && attacker.getHealth() <= 0){
                log.append("You killed the Goblin!!!\n");
                removeGoblin((Goblin)attacker);
            }
        }
        stats.setText(player.toString());
    }

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

    public static void main(String[] args) {
        new HVGR();
    }
}