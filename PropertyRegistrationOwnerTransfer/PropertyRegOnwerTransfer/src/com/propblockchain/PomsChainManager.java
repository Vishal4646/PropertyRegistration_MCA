package com.propblockchain;

import java.io.File;
import java.util.ArrayList;
import com.constant.ServerConstants;
import com.helper.FileObjectHelper;

public class PomsChainManager {
	public static void addNewBlock(PomBlockDataModel data) {
		ArrayList<Block> blockchain = getBlockChain();
		if (blockchain != null) {

			boolean validation = NoobChain.isChainValid(blockchain);
			Block currentBlock;
			Block previousBlock;
			if (validation) {
				System.out.println("BlockChain Validate Successfully.!!");
				Block newBlock = new Block(data, blockchain.get(blockchain
						.size() - 1).hash);
				newBlock.mineBlock(ServerConstants.difficulty);
				blockchain.add(newBlock);
				FileObjectHelper.saveObject(blockchain,
						ServerConstants.blockChainFileName);
				System.out.println("Block Added Successfully.!!");
			} else {
				System.out.println("Error.. BlockChain Invalid .??");
			}
		} else {
			createBlockChain(data);
		}
	}

	public static void createBlockChain(PomBlockDataModel data) {
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
			System.err.println("Create new blockchain.....");
			return null;
		}

	}

	public static ArrayList<Block> getProductWiseBlockList(String productid) {
		ArrayList<Block> blockChain = getBlockChain();
		ArrayList<Block> productBlockList=new ArrayList<>();
		for (int i = blockChain.size() - 1; i >= 0; i--) {
			Block currentBlock= blockChain.get(i);
			if ((i-1)<0) {
				if (currentBlock.data.pid.equalsIgnoreCase(productid)) {
					productBlockList.add(currentBlock);
				}else{
					continue;
				}
			}else{
				Block prevBlock= blockChain.get(i-1);
				if (prevBlock.hash.equalsIgnoreCase(currentBlock.previousHash)) {
					if (currentBlock.data.pid.equalsIgnoreCase(productid)) {
						productBlockList.add(currentBlock);
					}else{
						continue;
					}
				}else{
					System.err.println("Block chain not valid..??");
				}
			}
		}
		return productBlockList;
	}
	public static ArrayList<Block> getCIDWiseBlockList(String cid) {
		ArrayList<Block> blockChain = getBlockChain();
		ArrayList<Block> productBlockList=new ArrayList<>();
		for (int i = blockChain.size() - 1; i >= 0; i--) {
			Block currentBlock= blockChain.get(i);
			if ((i-1)<0) {
				if (currentBlock.data.cid.equalsIgnoreCase(cid)) {
					productBlockList.add(currentBlock);
				}else{
					continue;
				}
			}else{
				Block prevBlock= blockChain.get(i-1);
				if (prevBlock.hash.equalsIgnoreCase(currentBlock.previousHash)) {
					if (currentBlock.data.cid.equalsIgnoreCase(cid)) {
						productBlockList.add(currentBlock);
					}else{
						continue;
					}
				}else{
					System.err.println("Block chain not valid..??");
				}
			}
		}
		return productBlockList;
	}
	public static void main(String[] args) {
		// FileObjectHelper.
//		 PomBlockDataModel bdm = new PomBlockDataModel();
//		 bdm.setCid("100");
//		 bdm.setPid("10");
//		 bdm.setTimestamp();
//		 bdm.setMetadata("its goos product");
//		 addNewBlock(bdm);
		ArrayList<Block> blockChain = getBlockChain();
		Block b = blockChain.get(blockChain.size()-1);
//		ArrayList<Block> blockChain1 = getProductWiseBlockList("5");
		System.out.println(blockChain.size());
		System.out.println(b.hash);
		System.out.println(b.previousHash);
		PomBlockDataModel pm = b.data;
		System.out.println(pm.cid);
		System.out.println(pm.pid);
		System.out.println(pm.getTimestamp());
		System.out.println(pm.getMetadata());
	}
}
