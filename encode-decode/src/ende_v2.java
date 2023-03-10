import java.io.*;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JFrame;
import java.awt.*;

public class ende_v2 {

    public static void main (String[] args) throws IOException {
        try (Scanner obj = new Scanner(System.in)) {
            String s="";
            String answer="";
            System.out.print("Do you have a string or a file? ");
            String choice = obj.nextLine();
            //force lowercase just to be reduce no. of possibilities
            choice=choice.toLowerCase();
            if(choice.contains("str") || choice.startsWith("a s")|| choice.startsWith("s") || choice.contains("1")){
                System.out.print("Enter the string: ");
                s=obj.nextLine();
            }
            else if(choice.contains("fi") || choice.startsWith("a f")|| choice.startsWith("f") || choice.contains("2")){
                s=readFile();
            }
            System.out.print("1.Encode\n2.Decode\nEnter your choice:");
            int n=obj.nextInt();
            switch(n){
                case 1 -> answer=encode(s);
                case 2 -> answer=decode(s);
                default -> System.out.println("Invalid");
            }
            System.out.println("");
            System.out.println(answer);
            String choice2;
            System.out.println("Do you want to save? ");{
            obj.nextLine();
            choice2=obj.nextLine().toLowerCase();
        }
            if(choice2.contains("y") || choice2.contains("1") || choice2.contains("s"))
                writeFile(answer);
        }
       System.exit(0);
    }
    static String readFile() throws IOException
    {
        String path = "";
        String s="";
        //creates a window with basic values
        FileDialog fd = new FileDialog(new JFrame());
        //visibility of the dialog box
        fd.setVisible(true);
        File[] f = fd.getFiles();//getfiles() returns an array
        if(f.length > 0){
        path = (fd.getFiles()[0].getAbsolutePath());
        }
        s= new String(Files.readAllBytes(Paths.get(path)));
        return s;
        
    }

    static void writeFile(String s)throws IOException{
        int i;
        //looping to create a file whtsoever is the case
        for(i=1;i<100;i++)//just change condition depending on your needs - making sure doesn't go into an infinite loop
        {
            File file = new File("C:\\Users\\samsumg\\Desktop\\output("+i+ ").txt");
            if(file.createNewFile())//returns bool value depend on cond of creation
            break;
        }
        try (FileWriter obj = new FileWriter("C:\\Users\\samsumg\\Desktop\\output("+i+").txt") // to write
        ) {
            obj.write(s);
        }
    }

    static String encode(String s){
        s=s.toUpperCase();
        String ans="";
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(c>='A' && c<='M'){
                c=(char)(c-32);
                ans=ans+c;
            }
            else if(c=='N')
            ans=ans+"{";
            else if(c=='O')
            ans=ans+"}";
            else if(c>='P' && c<='V'){
                c=(char)(c-22);
                ans=ans+c;
            }
            else if(c>='W' && c<='Z'){
                c=(char)(c+4);
                ans=ans+c;
            }
            else {
                ans=ans+c; 
            }
            
            
        }
        return ans;
    }

    static String decode(String s) throws IOException{
        
        String ans="";       
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(c>='!' && c<='-'){
                c=(char)(c+32);
                ans=ans+c;
            }
            else if(c=='{')
            ans=ans+"N";
            else if(c=='}')
            ans=ans+"O";
            else if(c>=':' && c<='@'){
                c=(char)(c+22);
                ans=ans+c;
            }
            else if(c>='[' && c<='^'){
                c=(char)(c-4);
                ans=ans+c;
            }
            else{
                ans=ans+c;
            }
            
            
        }
        return ans;
    }
}
