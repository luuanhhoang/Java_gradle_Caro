/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hoangdz.server;

/**
 *
 * @author hoangisdev
 */
public class PlayerHandler implements Runnable {

    private PlayerService player;        // ngý?i chõi hi?n t?i
    private PlayerService opponent;      // ð?i th?
    private boolean myTurn;              // có ph?i lý?t m?nh không

    public PlayerHandler(PlayerService player, PlayerService opponent, boolean myTurn) {
        this.player = player;
        this.opponent = opponent;
        this.myTurn = myTurn;
    }
    public void setTurn(boolean turn) {
        this.myTurn = turn;
    }
    @Override
    public void run() {
        try {
            player.sendMessage("B?n ð? k?t n?i thành công! K? hi?u: " + player.getSybom());
            player.sendMessage(myTurn ? "B?n ði trý?c!" : "Ch? ð?i th?...");

            while (true) {
                if (myTurn) {
                    String move = player.getInput().readLine(); // VD: "2,3"
                    if (move == null) break; // disconnect
                    System.out.println(player.getName() + " ðánh vào: " + move);
                    opponent.sendMessage("Ð?i th? ðánh: " + move);
                    myTurn = false;
                    opponent.setTurn(true);
                } else {
                    Thread.sleep(100); // ð?i lý?t
                }
            }

        } catch (Exception e) {
            System.out.println("Ngý?i chõi b? ng?t k?t n?i: " + player.getName());
        } finally {
            player.close();
            opponent.sendMessage("Ð?i th? ð? r?i kh?i ph?ng.");
        }
    }
}
