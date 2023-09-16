//package com.test;
//
//
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.FileSystem;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.io.IOUtils;
//import org.apache.hadoop.util.Progressable;
//
//import com.constant.ServerConstants;
//
//
//public class HadoopHelper {
//public static void main(String[] args) {
////	listFiles("/library");
//	copyFileHDFS1("/home/cloudera/Cloudera-Udacity-Training-VM-4.1.1.c.zip", "/library");
////	downloadFile
//}
//    static {
//        HadoopHelper.createDir(ServerConstants.HDFS_PROJECT_DIR);
//    }
//
//    
////    public static void uploadFile(Part filePart, String fileId) {
////        FileOutputStream fos = null;
////        try {
////            InputStream inputStream = filePart.getInputStream();
////            File f = new File(ServerConstants.TEMP_HOME + "/" + fileId);
////            fos = new FileOutputStream(f);
////            while (true) {
////                byte[] a = new byte[1024 * 1024 * 1];
////                int j = inputStream.read(a);
////                if (j == -1) {
////                    break;
////                }
////                fos.write(a, 0, j);
////                fos.flush();
////
////            }
////            fos.close();
////            String folderName = fileId.substring(0, fileId.lastIndexOf("."));
////
////            HadoopHelper.copyFileHDFS1(f.getAbsolutePath(), fileId);
////            System.out.println("Hadoop OP Directory " + fileId);
////            System.out.println("Input File PAth " + f.getAbsolutePath());
////            f.delete();
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        } finally {
////            try {
////                fos.close();
////            } catch (IOException ex) {
////                Logger.getLogger(HadoopHelper.class.getName()).log(Level.SEVERE, null, ex);
////            }
////        }
////    }
//
//    public static void downloadFile(String file, OutputStream os) {
//        try {
//            copyFile2Local(ServerConstants.TEMP_HOME, file);
//            String newPath = ServerConstants.TEMP_HOME + "/" + file;
//            File f = new File(newPath);
//            System.out.println(f.getCanonicalPath());
//            FileInputStream fis = new FileInputStream(f);
//            while (true) {
//                byte[] a = new byte[1024 * 1024 * 1];
//                int j = fis.read(a);
//                if (j == -1) {
//                    break;
//                }
//                os.write(a, 0, j);
//                os.flush();
//
//            }
//            os.close();
//        } catch (IOException ex) {
//            ex.printStackTrace();;
//        }
//    }
//
//    public static void searchFile(String searchString) {
//
//    }
//
//    public static void deleteOutputFile(String file) {
//
//        /* Provides access to configuration parameters */
//        Configuration conf = new Configuration();
//        Path coreSitePath = new Path(ServerConstants.HADOOP_HOME );
//        conf.addResource(coreSitePath);
//
//        /* Creating Filesystem object with the configuration */
//        FileSystem fs;
//        try {
//            fs = FileSystem.get(conf);
//
//            /* Check if output path (args[1])exist or not */
//            if (fs.exists(new Path(ServerConstants.HDFS_URL + "/" + ServerConstants.HDFS_PROJECT_DIR + "/" + file))) {
//                /* If exist delete the output path */
//                fs.delete(new Path(ServerConstants.HDFS_URL + "/" + ServerConstants.HDFS_PROJECT_DIR + "/" + file), true);
//            }
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//    }
//
//   
//
//    public static void copyFile2Local(String localFileDir, String hadoop_file_path) {
//        FileSystem hdfs;
//        try {
//            Configuration conf = new Configuration();
//            Path coreSitePath = new Path(ServerConstants.HADOOP_HOME );
//            conf.addResource(coreSitePath);
//            hdfs = FileSystem.get(conf);
//
//            Path hdfsFilePath = new Path(ServerConstants.HDFS_URL + "/" + ServerConstants.HDFS_PROJECT_DIR + "/" + hadoop_file_path);
//
//            Path localFilePath = new Path(localFileDir + "/" + hdfsFilePath.getName());
//            hdfs.copyToLocalFile(hdfsFilePath, localFilePath);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    public static void createDir(String file) {
//
//        /* Provides access to configuration parameters */
//        Configuration conf = new Configuration();
//        Path coreSitePath = new Path(ServerConstants.HADOOP_HOME);
//        conf.addResource(coreSitePath);
//        /* Creating Filesystem object with the configuration */
//        FileSystem fs;
//        try {
//            fs = FileSystem.get(conf);
//
//            /* Check if output path (args[1])exist or not */
//            if (!fs.exists(new Path(ServerConstants.HDFS_URL + "/" + file))) {
//                /* If exist delete the output path */
//                fs.mkdirs(new Path(ServerConstants.HDFS_URL + "/" + file + "/"));
//            }
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//    }
//
//    public static void copyFileHDFS1(String localFile, String hadoop_op_dir) {
//        FileSystem hdfs;
//        try {
//            Configuration conf = new Configuration();
//            Path coreSitePath = new Path(ServerConstants.HADOOP_HOME );
//            conf.addResource(coreSitePath);
//            hdfs = FileSystem.get(conf);
//
//            Path localFilePath = new Path(localFile);
//            Path hdfsFilePath = new Path(ServerConstants.HDFS_URL + "/" + ServerConstants.HDFS_PROJECT_DIR + "/"
//                    + localFilePath.getName());
//            System.out.println(" Hadoop path " + hadoop_op_dir
//                    + "/" + localFilePath.getName());
//            hdfs.copyFromLocalFile(localFilePath, hdfsFilePath);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    public static void copyFileHDFS2(String localFile, String hadoop_op_dir) {
//        try {
//            // 1. Get the instance of COnfiguration
//            Configuration configuration = new Configuration();
//            // 2. Create an InputStream to read the data from local file
//            File f = new File(localFile);
//            InputStream inputStream;
//
//            inputStream = new BufferedInputStream(
//                    new FileInputStream(localFile));
//
//            // 3. Get the HDFS instance
//            FileSystem hdfs = FileSystem.get(configuration);
//            if (!hdfs.exists(new Path(hadoop_op_dir))) {
//                hdfs.mkdirs(new Path(hadoop_op_dir));
//            }
//            // 4. Open a OutputStream to write the data, this can be obtained
//            // from
//            // the FileSytem
//            OutputStream outputStream = hdfs.create(new Path(hadoop_op_dir
//                    + "/" + f.getName()), new Progressable() {
//                @Override
//                public void progress() {
//                    System.out.println("....");
//                }
//            });
//            try {
//                IOUtils.copyBytes(inputStream, outputStream, 4096, false);
//            } finally {
//                IOUtils.closeStream(inputStream);
//                IOUtils.closeStream(outputStream);
//            }
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
////    public static ArrayList<Path> listFiles(String file) {
////        ArrayList<Path> arr = new ArrayList<Path>();
////        /* Provides access to configuration parameters */
////        Configuration conf = new Configuration();
////        Path coreSitePath = new Path(ServerConstants.HADOOP_HOME );
////        conf.addResource(coreSitePath);
//////FileSystem hdfs = FileSystem.get(conf);
//////
//////		Configuration conf = new Configuration();
////        /* Creating Filesystem object with the configuration */
////        FileSystem fs;
////        try {
////            Path p = new Path(ServerConstants.HDFS_URL + "/" + file);
////            fs = FileSystem.get(conf);
////
////            /* Check if output path (args[1])exist or not */
////            if (fs.exists(p)) {
////                /* If exist delete the output path */
////                RemoteIterator<LocatedFileStatus> remote = fs.listFiles(p, true);
////                while (remote.hasNext()) {
////                    LocatedFileStatus ls = remote.next();
////                    System.out.println("Reading " + ls.getPath().getName());
////                    arr.add(ls.getPath());
////                }
////            }
////        } catch (IOException e) {
////            // TODO Auto-generated catch block
////            e.printStackTrace();
////        }
////        return arr;
////    }
//}
