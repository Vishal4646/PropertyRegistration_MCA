//package com.test;
//
//import java.io.File;
//import java.io.OutputStream;
//
//import com.constant.ServerConstants;
//
//public class HadoopReaderWriter {
//	public static void readFile(String filename) {
//		if (ServerConstants.IS_HADOOP) {
//			OutputStream os = null;
//			System.out.println("Reading File From Hadoop Dir :"
//					+ new File(filename).getAbsolutePath());
//			HadoopHelper.downloadFile(filename, os);
//		}
//	}
//
//	public static void writeFile(String pathwithfilename) {
//		try {
//			if (ServerConstants.IS_HADOOP) {
//				System.out
//						.println("Save Data into Encrypted File path on Hadoop :"
//								+ new File(pathwithfilename).getAbsolutePath());
//				HadoopHelper.copyFileHDFS1(pathwithfilename,
//						ServerConstants.HDFS_PROJECT_DIR);
//			}
//
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
//
//	public static void deleteFile(String filename) {
//		try {
//			if (ServerConstants.IS_HADOOP) {
//				HadoopHelper.deleteOutputFile(filename);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
//}
