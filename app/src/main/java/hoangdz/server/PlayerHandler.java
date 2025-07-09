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

    private PlayerService player;        // ng�?i ch�i hi?n t?i
    private PlayerService opponent;      // �?i th?
    private boolean myTurn;              // c� ph?i l�?t m?nh kh�ng

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
            player.sendMessage("B?n �? k?t n?i th�nh c�ng! K? hi?u: " + player.getSybom());
            player.sendMessage(myTurn ? "B?n �i tr�?c!" : "Ch? �?i th?...");

            while (true) {
                if (myTurn) {
                    String move = player.getInput().readLine(); // VD: "2,3"
                    if (move == null) break; // disconnect
                    System.out.println(player.getName() + " ��nh v�o: " + move);
                    opponent.sendMessage("�?i th? ��nh: " + move);
                    myTurn = false;
                    opponent.setTurn(true);
                } else {
                    Thread.sleep(100); // �?i l�?t
                }
            }

        } catch (Exception e) {
            System.out.println("Ng�?i ch�i b? ng?t k?t n?i: " + player.getName());
        } finally {
            player.close();
            opponent.sendMessage("�?i th? �? r?i kh?i ph?ng.");
        }
    }
}
