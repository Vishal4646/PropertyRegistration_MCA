package com.ipfsblockchain;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.cid.Cid;
import io.ipfs.multihash.Multihash;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;  
import java.util.Optional;

import com.ipfsblockchain.Block;
import com.ipfsblockchain.BlockChainRun;
import com.propblockchain.StringUtil;

public class TestIPFSBlockChain {
	// public static void main210122(String[] args) {
	// BlockChainRun run = new BlockChainRun();
	// System.out.println("Trying to Mine block 1... ");
	// Block b = new Block("Hi im the first block", "0");
	//
	// run.addBlock(b, run.blockchain);
	// run.addBlock(
	// new Block("Yo im the second block", run.blockchain
	// .get(run.blockchain.size() - 1).hash), run.blockchain);
	// String blockchainJson = StringUtil.getJson(run.blockchain);
	// System.out.println(blockchainJson);
	//
	// run.addBlock(b, run.blockchain);
	//
	// System.out.println("Trying to Mine block 2... ");
	// run.addBlock(
	// new Block("Yo im the second block", run.blockchain
	// .get(run.blockchain.size() - 1).hash), run.blockchain);
	//
	// System.out.println("Trying to Mine block 3... ");
	// run.addBlock(
	// new Block("Hey im the third block", run.blockchain
	// .get(run.blockchain.size() - 1).hash), run.blockchain);
	//
	// System.out.println("\nBlockchain is Valid: "
	// + run.isChainValid(run.blockchain));
	//
	// // String blockchainJson = StringUtil.getJson(run.blockchain);
	// // System.out.println("\nThe block chain: ");
	// // System.out.println(blockchainJson);
	// // System.out.println(run.blockchain);
	// }

	// public static void main222(String[] args) {
	// IPFS ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001");
	// try {
	// ipfs.refs.local();
	// // MerkleNode node = ipfs.object._new(Optional.empty());
	// MerkleNode node = ipfs.object.get(Cid
	// .decode("QmXc9raDM1M5G5fpBnVyQ71vR4gbnskwnB9iMEzBuLgvoZ"));
	// System.out.println(node.hash);
	// BlockChainRun run = new BlockChainRun();
	// Block b = new Block("Hi im the Second  block", "0");
	// String blockchainJson = StringUtil.getJson(b);
	// byte[] bytes = blockchainJson.getBytes();
	// MerkleNode lnikNode = ipfs.dag.put(bytes);
	// MerkleNode n = ipfs.object.patch2(node.hash, "add-link",
	// Optional.empty(),
	// Optional.of(System.currentTimeMillis() + ""),
	// Optional.of(lnikNode.hash));
	//
	// Block b2 = new Block("Yo im the second block", b.hash);
	// blockchainJson = StringUtil.getJson(b2);
	// bytes = blockchainJson.getBytes();
	//
	// MerkleNode lnikNode2 = ipfs.dag.put(bytes);
	//
	// n = ipfs.object.patch2(n.hash, "add-link", Optional.empty(),
	// Optional.of(System.currentTimeMillis() + ""),
	// Optional.of(lnikNode2.hash));
	//
	// System.out.println(n.hash);
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// }

	public static void main(String[] args) {
		BlockChainIPFS br = new BlockChainIPFS();
		
//		MerkleNode node=br.createBlockChain();
		br.reteriveData();
		//System.out.println(br.reteriveLastBlock());
		//br.verifyBlockChain();
//		
//		String lastBlockSHA = br.reteriveLastBlock();
//		System.out.println("lastBlockSHA = "+lastBlockSHA);
//		//Block b = new Block("Hi im the first block", "0", "");
//		//System.out.println("System.currentTimeMillis() = "+System.currentTimeMillis());
//		Block b = new Block("Hi im the " + System.currentTimeMillis()
//				+ " block", lastBlockSHA, br.root.hash.toBase58());
//		MerkleNode node2 = br.addBlock(b);
//		System.out.println(node2.hash);
//		//
//		lastBlockSHA = br.reteriveLastBlock();
//		System.out.println("lastBlockSHA " + lastBlockSHA + " " + b.hash);
//		for (int i = 0; i < 2000; i++) {
//
//			Block b2 = new Block("Hi im the " + System.currentTimeMillis()
//					+ " block", b.hash, br.root.hash.toBase58());
//			node2 = br.addBlock(b2);
//			System.out.println(node2.hash);
//			lastBlockSHA = br.reteriveLastBlock();
//			System.out.println("lastBlockSHA2 " + lastBlockSHA + " b2.hash "
//					+ b2.hash + " br.root.hash " + br.root.hash);
//		}
	}

	public static void main212121(String[] args) {
		IPFS ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001");
		try {
			ipfs.refs.local();
			MerkleNode node = ipfs.object._new(Optional.empty());
			System.out.println(node);
			Block b = new Block("Hi im the first block", "0", "");
			String blockchainJson = StringUtil.getJson(b);
			byte[] bytes = blockchainJson.getBytes();

			MerkleNode lnikNode = ipfs.dag.put(bytes);

			MerkleNode f = ipfs.object.patch(node.hash, "add-link",
					Optional.empty(), Optional.of("1stObject"),
					Optional.of(lnikNode.hash));
			System.out.println(f.hash);
			if (true) {
				return;
			}

			// Optional<String> a = Optional.ofNullable("JSON");

			// NamedStreamable.ByteArrayWrapper file = new
			// NamedStreamable.ByteArrayWrapper("hell2o.json", bytes);

			// MerkleNode addResult = ipfs.add(file).get(0);
			// System.out.println(addResult);

			MerkleNode addResul2 = ipfs.block.put(bytes, Optional.empty());
			System.out.println("addResul2 Node " + addResul2.toJSON());
			Multihash filePointer = Multihash.fromBase58(addResul2.hash
					.toBase58());
			String json = "{\"Name\": \"test\",\"Hash\": \""
					+ addResul2.hash.toBase58() + "\" }";
			System.out.println(json);
			HashMap map = new HashMap();
			map.put("Name", "test");
			map.put("Hash", addResul2.hash.toBase58());

			MerkleNode n = new MerkleNode(addResul2.hash.toBase58());

			//
			System.out.println("n " + n);
			// node.links.add(n);
			Cid cid = Cid.buildCidV0(addResul2.hash);
			ipfs.object.patch(Multihash
					.fromHex("QmdfTbBqBPQ7VNxZEYEj14VmRuZBkqFbiwReogJgS1zR1n"),
					"add-link", Optional.empty(), Optional.of("1stObject"),
					Optional.of(cid));

			System.out.println("Main Node" + node.toJSON());

			// Multihash filePointer =
			// Multihash.fromHex("zdpuApqsjXTQQ2BaBKiPV4NDRW3Qn5RJdQBgCX335u74jr3ca-");
			// Cid cid=Cid.buildCidV0(filePointer);
			//
			//
			// System.out.println(ipfs.dag.get(cid));
			// Multihash filePointer =
			// Multihash.fromBase58("zdpuAzi66MHTHpQDxwxFMFPAyhi7dCr2XsctN9CRCDQY2PhK5");
			// System.out.println(ipfs.object.get(filePointer).toJSON());
			// MerkleNode node= ipfs.object.get(filePointer);
			// System.out.println(node.toJSON());

			// NamedStreamable.FileWrapper file = new
			// NamedStreamable.FileWrapper(new File("hello.json"));
			// MerkleNode addResult = ipfs.add(file).get(0);
			// System.out.println(addResult.toJSON());

			// Multihash filePointer =
			// Multihash.fromBase58("Qmd4PTgkAXtKU64dScEFswk44fGdCjEfBCnHrC2kgaqXMw");
			// byte[] fileContents = ipfs.cat(filePointer);
			// System.out.println(new String(fileContents));
			//
			// NamedStreamable.ByteArrayWrapper file = new
			// NamedStreamable.ByteArrayWrapper("hello.txt",
			// "G'day world! IPFS rocks!".getBytes());
			// MerkleNode addResult = ipfs.add(file).get(0);
			// System.out.println(addResult.toJSON());

			// NamedStreamable.ByteArrayWrapper file2 = new
			// NamedStreamable.ByteArrayWrapper("hello.txt",
			// "G'day world! IPFS rocks!".getBytes());
			// MerkleNode addResult2 = ipfs.add(file2).get(0);
			// System.out.println(addResult2.toJSON());

			// Multihash filePointer =
			// Multihash.fromBase58("zdpuAzi66MHTHpQDxwxFMFPAyhi7dCr2XsctN9CRCDQY2PhK5");
			// byte[] fileContents = ipfs.cat(filePointer);
			// System.out.println(new String(fileContents));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
