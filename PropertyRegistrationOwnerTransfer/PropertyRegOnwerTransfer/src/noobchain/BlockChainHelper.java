package noobchain;

import java.io.File;
import java.util.ArrayList;

import com.constant.ServerConstants;
import com.helper.FileObjectHelper;

public class BlockChainHelper {
	public static void addNewBlock(BlockDataModel data) {
		ArrayList<Block> blockchain = getBlockChain();
		if (blockchain != null) {
			System.out.println("blockchain.size = "+blockchain.size());
			boolean validation = NoobChain.isChainValid(blockchain);
			Block currentBlock;
			Block previousBlock;
			if (validation) {
				System.out.println("BlockChain Validate Successfully.!!");
				Block newBlock = new Block(data, blockchain.get(blockchain
						.size() - 1).hash);
				newBlock.mineBlock(ServerConstants.difficulty);
				blockchain.add(newBlock);
				FileObjectHelper.saveObject(blockchain, ServerConstants.blockChainFileName);
			} else {
				System.out.println("Error.. BlockChain Invalid .??");
			}
		}else{
			createBlockChain(data);
		}
	}

	public static void createBlockChain(BlockDataModel data) {
		Block firstBlock = new Block(data, "0");
		ArrayList<Block> blockchain = new ArrayList<Block>();
		File f = new File(ServerConstants.blockChainFileName);
		if (f.exists()) {
			System.err
					.println("BlockChain Allready Exist..?? you cant create new one..??");
		} else {
			FileObjectHelper.saveObject(
					NoobChain.addBlock(firstBlock, blockchain),
					ServerConstants.blockChainFileName);
			System.out.println("BlockChain Created Successfully..!!");
		}
	}

	public static ArrayList<Block> getBlockChain() {
		File f = new File(ServerConstants.blockChainFileName);
		if (f.exists()) {
			ArrayList<Block> list = (ArrayList<Block>) FileObjectHelper
					.readObject(ServerConstants.blockChainFileName);
			return list;

		} else {
			System.err.println("BlockChain doesn't Exist..??");
			return null;
		}

	}

	public static void main(String[] args) {
		// FileObjectHelper.
		BlockDataModel bdm = new BlockDataModel();
		bdm.setCid("10");
		bdm.setPid("10");
		bdm.setTimestamp();
		bdm.setMetadata("its good product");
		addNewBlock(bdm);
		//ArrayList<Block> blockChain = getBlockChain();
	}
}
