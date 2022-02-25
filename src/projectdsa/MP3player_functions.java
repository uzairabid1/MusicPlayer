package projectdsa;

import java.io.File;
import javax.sound.sampled.*;
import java.util.*;
import javax.swing.JOptionPane;

public class MP3player_functions {   
    static MP3player_functions player = new MP3player_functions(); 
    static boolean flag=false; 
    static boolean flag2=false;
    public static MP3player_functions getInstance(){
    return player;
    }  
 
   static AudioInputStream audioInput;
   static Clip clip; 
   
   static ListNode head;
   static ListNode tail;
    static ListNode current2;
    static ListNode current3;
    private int length;    
    
    
    public int getLength(){
    return length;}    

    
    public void addNode(File data,String name) {
        ListNode newNode = new ListNode(data,name);
        if(head == null) {
            newNode.next = newNode.prev = newNode;
            head = newNode;
            length++;
            return;
        }        
        tail = head.prev;
        newNode.next = head;
        head.prev = newNode;
        newNode.prev = tail;
        tail.next = newNode;
        length++;
    }
   
    
  public ListNode deleteNode(ListNode head, String key)
    {       
        if (head == null){
          flag = true;          
            return null;  }     
      
        ListNode curr = head, prev_1 = null;
        while (!curr.name.equals(key)) {        
            if (curr.next == head) {              
                return head;
            } 
            prev_1 = curr;
            curr = curr.next;
        }
        
        if (curr.next == head && prev_1 == null) {
            head = null;
            flag = true;
            clip.stop();
            length--;
            return head;
        }
     
        if (curr == head) {            
            prev_1 = tail;         
            head = head.next;        
            prev_1.next = head;
            tail = prev_1;
        }
        
        else if (curr.next == head) {            
            prev_1.next = head;
            head.prev = prev_1;
        }
        else {          
            ListNode temp = curr.next;            
            prev_1.next = temp;
            temp.prev = prev_1;
        }
        flag = true;
        length--;
        return head;
    }        
       
  public ListNode searchNode(ListNode head, String key){
    ListNode current = head;    
    if(current == null){
        flag2=false;
        return null;
    }
    else
    {
        while(current.next != head)
        {                 
            if(current.name.equals(key)) {
                flag2 = true;   
                return current;              
            }                       
            current = current.next;
        }
 
        if(current.name.equals(key))
        {         
            flag2 = true;
            return current;
        }         
    }
    flag2 = false;
    return null;
    }       

    public void addMusic(String musicLocation,String name){
        try{
            File musicPath = new File(musicLocation);
            if(musicPath.exists()){
                addNode(musicPath,name);
            }
            else{
                System.out.println("Cant find file");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }        
    }

    public void playMusic(ListNode h){        
        try{
            audioInput = AudioSystem.getAudioInputStream(h.data);
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();          
        }catch (Exception e){
            System.out.println("Error");
        }
    }
    public void nextMusic(){         
       head = head.next;
        playMusic(head); 
    }
    public void stopMusic(){
        clip.close();
    }

    public void previousMusic(){       
        head = head.prev;
        playMusic(head);
    }
    
    
public static void main(String[] args){

}
}
