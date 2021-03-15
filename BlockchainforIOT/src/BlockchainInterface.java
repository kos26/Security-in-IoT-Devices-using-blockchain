public interface BlockchainInterface {

    public String getHash();
    public String getPreviousHash();
    public String getName();
    public long getTimeStamp();
    public String calculateHash();
    public void setName(String newName);
    public void setHash(String newHash);
    public void setPreviousHash(String newPreviousHash);
    public String getData();

}
