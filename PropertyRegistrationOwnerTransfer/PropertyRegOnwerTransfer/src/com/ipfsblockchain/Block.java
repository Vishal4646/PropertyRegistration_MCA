package com.ipfsblockchain;



import java.text.SimpleDateFormat;
import java.util.Date;

import com.constant.ServerConstants;
import com.propblockchain.StringUtil;

public class Block {
	
	public String hash;
	public String previousHash; 
	private String data; //our data will be a simple message.
	//private long timeStamp; //as number of milliseconds since 1/1/1970.
	private String timeStamp;
	private int nonce;
	private String previousBlockCID; 
	//Block Constructor.  
	transient SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	transient SimpleDateFormat timeFormat = new SimpleDateFormat("hh-mm");
    
	public Block(String data,String previousHash,String previousBlockCID ) {
		this.data = data;
		this.previousHash = previousHash;
		//this.timeStamp = System.currentTimeMillis();
		this.timeStamp = dateFormat.format(new Date())+"_"+timeFormat.format(new Date());
		this.previousBlockCID=previousBlockCID;
		this.hash = calculateHash(); //Making sure we do this after we set the other values.
		
		mineBlock(ServerConstants.difficulty);
		
	}
	
	//Calculate new hash based on blocks contents
	public String calculateHash() {
		String calculatedhash = StringUtil.applySha256( 
				previousHash +
				
//				Long.toString(timeStamp) +
				Integer.toString(nonce) + 
				data 
				);
		return calculatedhash;
	}
	
	//Increases nonce value until hash target is reached.
	public void mineBlock(int difficulty) {
		String target = StringUtil.getDificultyString(difficulty); //Create a string with difficulty * "0" 
//		System.out.println("target :"+target);
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
		System.out.println("nonce :"+nonce);
	}
	
}
