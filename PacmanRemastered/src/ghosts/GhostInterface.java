/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghosts;
/**
 *
 * @author csc190
 */
public interface GhostInterface{
    void move(); // Move around the screen
    void chase(int a); // Chase pacman if nearby
    void goBack(int a); // go back to spawn point   
    int levelUp(int a);
}
