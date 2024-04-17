import java.util.ArrayList;
import java.util.Date;

// Define a block class for the blockchain
class Block {
    private int index;
    private Date timestamp;
    private String data;
    private String previousHash;
    private String hash;

    // Constructor
    public Block(int index, String data, String previousHash) {
        this.index = index;
        this.timestamp = new Date();
        this.data = data;
        this.previousHash = previousHash;
        this.hash = calculateHash();
    }

    // Calculate the hash of the block
    public String calculateHash() {
        String calculatedHash = StringUtil.applySha256(
                previousHash +
                Long.toString(timestamp.getTime()) +
                Integer.toString(index) +
                data
        );
        return calculatedHash;
    }

    // Getters
    public int getIndex() {
        return index;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getData() {
        return data;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getHash() {
        return hash;
    }
}

// Define the Blockchain class
class Blockchain {
    private ArrayList<Block> blockchain;

    // Constructor
    public Blockchain() {
        blockchain = new ArrayList<>();
        // Genesis block
        blockchain.add(new Block(0, "Genesis Block", "0"));
    }

    // Add a new block to the chain
    public void addBlock(String data) {
        Block latestBlock = blockchain.get(blockchain.size() - 1);
        blockchain.add(new Block(latestBlock.getIndex() + 1, data, latestBlock.getHash()));
    }

    // Check if the blockchain is valid
    public boolean isChainValid() {
        for (int i = 1; i < blockchain.size(); i++) {
            Block currentBlock = blockchain.get(i);
            Block previousBlock = blockchain.get(i - 1);
            // Check if current hash is valid
            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                return false;
            }
            // Check if previous hash matches
            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                return false;
            }
        }
        return true;
    }

    // Print the blockchain
    public void printChain() {
        for (Block block : blockchain) {
            System.out.println("Block #" + block.getIndex() +
                    " [Timestamp: " + block.getTimestamp() +
                    ", Data: " + block.getData() +
                    ", Previous Hash: " + block.getPreviousHash() +
                    ", Hash: " + block.getHash() + "]");
        }
    }
}

// Utility class for hashing
class StringUtil {
    public static String applySha256(String input) {
        // Implementation of SHA-256 hashing algorithm
        // This code is omitted for brevity
        return input;
    }
}

// Main class
public class SupplyChainManagement {
    public static void main(String[] args) {
        // Create a blockchain
        Blockchain blockchain = new Blockchain();

        // Add some blocks
        blockchain.addBlock("Transaction data 1");
        blockchain.addBlock("Transaction data 2");

        // Print the blockchain
        blockchain.printChain();

        // Check if the blockchain is valid
        System.out.println("Is blockchain valid? " + blockchain.isChainValid());
    }
}
