package com.ipfsblockchain;

import java.util.ArrayList;

import com.constant.ServerConstants;

public class BlockChainHelper {
	public static void addNewBlock(String data,ArrayList<Block> blockchain) {
		boolean validation= BlockChainRun.isChainValid(blockchain);
		Block currentBlock; 
		Block previousBlock;
		if (validation) {
			System.out.println("BlockChain Validate Successfully.!!");
			Block newBlock= new Block(data,blockchain.get(blockchain.size()-1).hash,"");
			newBlock.mineBlock(ServerConstants.difficulty);
			blockchain.add(newBlock);
		}else{
			System.out.println("Error.. BlockChain Invalid .??");
		}
	}
	public static ArrayList<Block> createBlockChain(String data) {
		Block firstBlock=new Block(data, "0","");
		ArrayList<Block> blockchain = new ArrayList<Block>();
		return BlockChainRun.addBlock(firstBlock,blockchain);
	}
	public static void main(String[] args) {
//		FileObjectHelper.
		ArrayList<Block> blockChain=createBlockChain("i am first");
		FileObjectHelper.saveObject(blockChain, "");
	}
}
