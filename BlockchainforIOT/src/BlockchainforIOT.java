import java.util.ArrayList;
import java.util.Scanner;

public class BlockchainforIOT extends Exception{

    // List to store the IoT devices information as blocks
    public static ArrayList<Block> iotBlockchain
            = new ArrayList<Block>();

    public static void main(String[] args)
    {

        iotBlockchain.add(new Block("Device1", "This is the first Device in this chain",  "0"));
        iotBlockchain.add(new Block("Device2", "This is the second Device in this chain",
                iotBlockchain.get(iotBlockchain.size() - 1).getHash()));
        iotBlockchain.add(new Block("Device3", "This is the third Device in this chain",
                iotBlockchain.get(iotBlockchain.size() - 1).getHash()));
        iotBlockchain.add(new Block("Device4", "This is the forth Device in this chain",
                iotBlockchain.get(iotBlockchain.size() - 1).getHash()));
        iotBlockchain.add(new Block("Device5", "This is the fifth Device in this chain",
                iotBlockchain.get(iotBlockchain.size() - 1).getHash()));
        iotBlockchain.add(new Block("Device6", "This is the sixth Device in this chain",
                iotBlockchain.get(iotBlockchain.size() - 1).getHash()));
        iotBlockchain.add(new Block("Device7", "This is the seventh Device in this chain",
                iotBlockchain.get(iotBlockchain.size() - 1).getHash()));

        boolean run = true;

        try {
            if (args[0].equalsIgnoreCase("--initialize")){
                System.out.printf("\n\nArguments below: \n");
                System.out.println("--printDevicesAndData or --d : Prints all the chained IoT Devices and the information in it");
                System.out.println("--printAllDeviceName --n : Prints name of each device in the chain");
                System.out.println("--removeDevice or --r : Removes the devices the first occurance of device by name if it exist");
                System.out.println("--addDevice --a : Adds a new devices at the end of chain with provided name and data");
                System.out.println("--checkValid --v : Checks if the chain is valid by calculating and comparing hashes for each device");
                System.out.println("--exit --e : Exits the Program");
                System.out.printf("If the argument is not valid it displays Invalid Argument\n\n");
                while (run){
                    Scanner scan = new Scanner(System.in);
                    System.out.print("Enter an argument: ");
                    String argument = scan.next();
                    if (argument.equalsIgnoreCase("--addDevice") || argument.equalsIgnoreCase("--a")){
                        System.out.print("Enter name: ");
                        String name = scan.next();
                        System.out.print("Enter data: ");
                        String data = scan.next();
                        iotBlockchain.add(new Block(name, data,
                                iotBlockchain.get(iotBlockchain.size() - 1).getHash()));
                    }else if (argument.equalsIgnoreCase("--removeDevice") || argument.equalsIgnoreCase("--r")){
                        System.out.print("Enter name: ");
                        String name = scan.next();
                        removeDevice(name);
                    }else if (argument.equalsIgnoreCase("--printDevicesAndData") || argument.equalsIgnoreCase("--d")){
                        if (iotBlockchain.size() == 0 ){
                            System.out.println("There are currently no devices in the chain");
                        }else{
                            for (Block block : iotBlockchain) {
                                block.getInfo();
                            }
                        }
                    }else if (argument.equalsIgnoreCase("--printAllDeviceName") || argument.equalsIgnoreCase("--n")){
                        if (iotBlockchain.size() == 0 ){
                            System.out.println("There are currently no devices in the chain");
                        }else{
                            for (Block block : iotBlockchain) {
                                System.out.println(block.getName());
                            }
                        }
                    }else if (argument.equalsIgnoreCase("--checkValid") || argument.equalsIgnoreCase("--c")) {
                        if (checkValidityOfIOTChain()) {
                            System.out.println("The IoT chain is valid");
                        } else {
                            System.out.println("The IoT chain is not valid");
                        }
                    }else if (argument.equalsIgnoreCase("--exit") || argument.equalsIgnoreCase("--e")){
                        System.out.println("Exiting IoT Blockchain Demo.");
                        break;
                    }else{
                        System.out.printf("Invalid Argument\n\n");
                        System.out.println("Please include arguments:");
                        System.out.println("--printDevicesAndData or --d : Prints all the chained IoT Devices and the information in it");
                        System.out.println("--printAllDeviceName --n : Prints name of each device in the chain");
                        System.out.println("--removeDevice or --r : Removes the devices the first occurance of device by name if it exist");
                        System.out.println("--addDevice --a : Adds a new devices at the end of chain with provided name and data");
                        System.out.println("--checkValid --v : Checks if the chain is valid by calculating and comparing hashes for each device");
                        System.out.println("--exit --e : Exits the Program");
                        System.out.println();
                    }

                }
            }
        }catch(Exception e){
            System.out.printf("\n\nPlease include arguments:\n");
            System.out.println("--printDevicesAndData or --d : Prints all the chained IoT Devices and the information in it");
            System.out.println("--printAllDeviceName --n : Prints name of each device in the chain");
            System.out.println("--removeDevice or --r : Removes the devices the first occurance of device by name if it exist");
            System.out.println("--addDevice --a : Adds a new devices at the end of chain with provided name and data");
            System.out.println("--checkValid --v : Checks if the chain is valid by calculating and comparing hashes for each device");
            System.out.println("--exit --e : Exits the Program");
            System.out.printf("If the argument is not valid it displays Invalid argument\n\n");
        }

    }

    /*
    Function below removes the devices from the List while maintaining the validity of the list.
     */

    public static void removeDevice(String name){
        int count = 0;
        int index = 0;

        for (Block block: iotBlockchain){
            if (block.getName().equalsIgnoreCase(name)){
                index = count;
                break;
            }else{
                count++;
            }
        }

        if (count==index){


            iotBlockchain.remove(count);

            for (int i = count; i < iotBlockchain.size();i++){
                if(i==0){
                    iotBlockchain.get(0).setPreviousHash("0");
                }else{
                    iotBlockchain.get(i).setPreviousHash(iotBlockchain.get(i-1).getHash());
                }
                iotBlockchain.get(i).setHash(CreateHash.sha256(iotBlockchain.get(i).getPreviousHash() + iotBlockchain.get(i).getTimeStamp() + iotBlockchain.get(i).getData()));
            }
        }else{
            System.out.println("Device not found in the chain");
        }
    }



    /*
    Function below to checks if the chain is maintained by checking all the hashes stored.
    If all the hashes equals the hashes calculated by this function then it is valid.
     */

    public static Boolean checkValidityOfIOTChain() {
        Block currentBlock;
        Block previousBlock;

        for (int i = 1; i < iotBlockchain.size(); i++) {

            currentBlock = iotBlockchain.get(i);
            previousBlock = iotBlockchain.get(i - 1);

            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                System.out.println("Hashes are not equal");
                System.out.println(currentBlock.getName());
                System.out.println(currentBlock.getHash());
                System.out.println(currentBlock.calculateHash());
                return false;
            }


            if (!previousBlock.getHash().equals(currentBlock.getPreviousHash())) {
                System.out.println("Previous Hashes are not equal");
                return false;
            }
        }

        return true;
    }
}
