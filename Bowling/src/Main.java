import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static List<Bowler> bowlerList = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        // User input for player number
        Scanner input = new Scanner(System.in);
        System.out.print("Enter number of players: ");
        int player = input.nextInt();

        for (int i = 0; i < player; i++){
            Bowler bowler = new Bowler();
            bowler.setPins(10);
            bowler.setScore(0);
            bowler.setTotalScore(0);

            bowlerList.add(bowler);
        }

        int frame = 10;
        int roll = 2;
        Random random = new Random();

        int frameScore = 0;
        int totalScore = 0;

        // Frames counter
        for (int i = 0; i < frame; i++) {
            System.out.println("\n********** Frame " + (i + 1) + " **********");

            // Player number
            for (int j = 0; j < player; j++) {
                System.out.println("\nPlayer " + (j + 1));

                // Rolls per frame
                for (int k = 1; k < roll; k++) {

                    // int firstRoll = 10; //Strike tester
                    int firstRoll = random.nextInt(bowlerList.get(j).getPins() + 1);
                    System.out.println("1st Roll: " + firstRoll);
                    int pinsRemaining = bowlerList.get(j).getPins() - firstRoll;
                    //Thread.sleep(3000);

                    // Strike
                    if (pinsRemaining == 0) {
                        System.out.print("STRIKE! 20 points awarded.\n");
                        frameScore = bowlerList.get(j).getScore() + 20;
                        bowlerList.get(j).setTotalScore(bowlerList.get(j).getTotalScore() + frameScore);
                        
                    } else { // Begin second roll
                        int secondRoll = random.nextInt(pinsRemaining + 1);
                        System.out.println("2nd Roll: " + secondRoll);
                        pinsRemaining = pinsRemaining - secondRoll;

                        // Strike on second roll
                        if (pinsRemaining == 0) {
                            System.out.print("SPARE! 15 points awarded.\n");
                            frameScore = bowlerList.get(j).getScore() + 15;
                            bowlerList.get(j).setTotalScore(bowlerList.get(j).getTotalScore() + frameScore);

                        } else { // Pins knocked as Score
                            frameScore = Math.abs(pinsRemaining - bowlerList.get(j).getPins()); //
                            bowlerList.get(j).setTotalScore(bowlerList.get(j).getTotalScore() + frameScore);
                        }
                
                    }

                    // print score per frame
                    System.out.println("Frame " + (i + 1) + " Score: " + frameScore);
                }

                // total score per player instance
                totalScore = bowlerList.get(j).getTotalScore();
                System.out.println("Player " + (j + 1) + "'s Total Score: " + totalScore);
            }
        }
        input.close();
    }
}
