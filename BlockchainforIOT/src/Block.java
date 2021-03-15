
import java.util.Date;

public class Block implements BlockchainInterface{

    // Every block contains
    // a hash, previous hash and
    // data of the transaction made
    private String name;
    private String hash;
    private String previousHash;

    private String data;

    private long timeStamp;

    // Constructor for the block
    public Block(String name, String data,
                 String previousHash)
    {
        this.name = name;
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String getHash(){
        return this.hash;
    }
    public String getPreviousHash(){
        return this.previousHash;
    }
    public long getTimeStamp(){
        return this.timeStamp;
    }
    public String getName(){
        return this.name;
    }

    public String getData(){
        return this.data;
    }

    public void setName(String newName){
        this.name = newName;
    }
    public void setHash(String newHash){
        this.hash = newHash;
    }
    public void setPreviousHash(String newPreviousHash){
        this.previousHash = newPreviousHash;
    }

    // Function to calculate the hash
    public String calculateHash()
    {
        String calculatedhash = CreateHash.sha256(previousHash + Long.toString(timeStamp) + data);

        return calculatedhash;
    }

    /*
    The function would not be applicable for real world application.
    It is made for the purpose of this project to print all the devices that are chained
     */
    public void getInfo() {
        System.out.println("Object name: "+ this.name);
        System.out.println("Object data: "+ this.data);
        System.out.println("Object time-stamp: "+ this.timeStamp);
        System.out.println("Object hash: "+ this.hash);
        System.out.println("Object previous hash: "+ this.previousHash);
        System.out.println();
    }
}