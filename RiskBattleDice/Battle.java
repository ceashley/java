import java.util.Random;
import java.util.Arrays;

public class Battle {
    static Random random = new Random();
    static final int DIE_SIDES = 6;
    
    public static int rollDie(int sides) {
	return random.nextInt(sides) + 1;
    }
    
    // doDice FUNCTION GOES HERE
    public static int[] doDice(int atkDice,int defDice)
    {
        Random rand = new Random();
        int totalAtkDice[] = new int[3];
        int totalDefDice[] = new int[2];
        int maxAtkDie = 0;
        int maxDefDie = 0;
        int armiesLost[] = new int[2];
        for(int i = 0;i<atkDice;i++)
        {
            totalAtkDice[i]= rand.nextInt(6) + 1;
            System.out.println("attack dice rolled " + totalAtkDice[i]);
            if(totalAtkDice[i] > maxAtkDie)
                maxAtkDie = totalAtkDice[i];
        }
        for(int i = 0;i<defDice;i++)
        {
            totalDefDice[i]= rand.nextInt(6) + 1;
            System.out.println("defence dice rolled " + totalDefDice[i]);
            if(totalDefDice[i] > maxDefDie)
                maxDefDie = totalDefDice[i];
        }
        if(maxDefDie > maxAtkDie)
        {
            armiesLost[0] = 1;
            armiesLost[1] = 0;
        }
        else if(maxDefDie < maxAtkDie)
        {
            armiesLost[0] = 0;
            armiesLost[1] = 1;
        }
        else
        {
            armiesLost[0] = 0;
            armiesLost[1] = 0;
        }
        if(atkDice >= 2 && defDice >= 2)
        {
            Arrays.sort(totalAtkDice);
            Arrays.sort(totalDefDice);
            if(totalAtkDice[1] > totalDefDice[1])
            {
                armiesLost[1] += 1;
            }
            else if(totalAtkDice[1] < totalDefDice[1])
            {
                armiesLost[0] += 1;
            }
        }        
        return armiesLost;      
    }
    public static int[] runBattle(int attackers, int defenders) {
	int aleft = attackers;
	int dleft = defenders;
	int adice, ddice;
	int[] diceResult;
	
	while (aleft > 1 && dleft > 0) {
	    adice = Math.min(aleft - 1, 3);
	    ddice = Math.min(dleft, 2);
	    diceResult = doDice(adice, ddice);
	    aleft -= diceResult[0];
	    dleft -= diceResult[1];
	}

	int[] battleResult = new int[2];
	battleResult[0] = aleft;
	battleResult[1] = dleft;
	return battleResult;
    }
    
    public static void main(String[] args) {
	int attacker = 5;
	int defender = 4;
	int[] battleResult;

	battleResult = runBattle(attacker, defender);
	int aleft = battleResult[0];
	int dleft = battleResult[1];
	if (aleft > 1 && dleft == 0) {
	    System.out.printf("Attacker won with %d armies left.\n", aleft);
	} else {
	    System.out.printf("Attack failed. "
			      + "%d attackers left and %d defenders left.\n",
			      aleft, dleft);
	}
    }
}
