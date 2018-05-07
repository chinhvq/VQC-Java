package test.nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryStreamDemo {

	public static void main(String[] args) {
		// get seperator of the system - windows -> \ ; unix/linux/mac -> /
		String seperator = File.separator;
		System.out.println(seperator);
		seperator = FileSystems.getDefault().getSeparator();
		System.out.println(seperator);

		// get directory contents at first level of contents - direct descendant
//		Path directory = FileSystems.getDefault().getPath("Java_Core_Home", "src", "test");
		Path directory = FileSystems.getDefault().getPath("Java_Core_Home" + seperator +  "src" + seperator + "test");
		// try (DirectoryStream<Path> contents = Files.newDirectoryStream(directory)) {
		// for (Path file : contents) {
		// System.out.println(file.getFileName());
		// }
		// } catch (IOException | DirectoryIteratorException e) {
		// e.printStackTrace();
		// System.out.println(e.getMessage());
		// }

		// get directory contents at first level of contents - direct descendant
		// get all binary (.dat) file only using global
		// try (DirectoryStream<Path> contents = Files.newDirectoryStream(directory,
		// "*.dat")) {
		// for (Path file : contents) {
		// System.out.println(file.getFileName());
		// }
		// } catch (IOException | DirectoryIteratorException e) {
		// e.printStackTrace();
		// System.out.println(e.getMessage());
		// }

		// show all file only inside folder using Filter
		// DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
		// @Override
		// public boolean accept(Path entry) throws IOException {
		// return (Files.isRegularFile(entry));
		// }
		// };

		// using lamba for filter with same result
//		DirectoryStream.Filter<Path> filter1 = p -> Files.isRegularFile(p);
//		try (DirectoryStream<Path> contents = Files.newDirectoryStream(directory, filter1)) {
//			for (Path file : contents) {
//				System.out.println(file.getFileName());
//			}
//		} catch (IOException | DirectoryIteratorException e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//		}
		
		//create a temporary file
//		try {
//			Path tempFile = Files.createTempFile("myPrefix", ".myAppend");
//			System.out.print("Location of temp file is \n\t" + tempFile.toAbsolutePath());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		//get FileStore of the file system
//		System.out.println("\nCurent our file system");
//		Iterable<FileStore> stores = FileSystems.getDefault().getFileStores();
//		for (FileStore store : stores) {
//			System.out.println("\t" + store + "\t" + store.name());
//		}
//		
//		System.out.println("Current root path");
//		Iterable<Path> rootPaths = FileSystems.getDefault().getRootDirectories();
//		for (Path root : rootPaths) {
//			System.out.println("\t" + root);
//		}
		
		// List all file and subfolder in a directory
//		System.out.println("Walking tree " + directory.toAbsolutePath());
//		try {
//			Files.walkFileTree(directory, new FileTraverse());
//		} catch (IOException e) {
//			System.out.println(e.getMessage());
//		}
		
		//copy all file and folder from sourceFolder to targeFolder
//		System.out.println("Copy from nio to nio_clone");
//		Path srcCopy = FileSystems.getDefault().getPath("Java_Core_Home" + seperator + "src" + seperator + "test" + seperator + "nio");
//		Path targetCopy = FileSystems.getDefault().getPath("Java_Core_Home" + seperator + "src" + seperator + "test" + seperator + "nio_clone");;
//		try {
//			Files.walkFileTree(srcCopy, new CopyFiles(srcCopy, targetCopy));
//		}catch (IOException e) {
//			e.printStackTrace();
//		}
	
		System.out.println(directory.toAbsolutePath());
		File currentWorkingDirectory = new File("").getAbsoluteFile();
		System.out.print("Current working directory \n\t" + currentWorkingDirectory);
		File file = new File("locations.txt");
		System.out.println(file);
		Path filePath = file.toPath();
		System.out.println("converted Path = " + filePath);
		
		System.out.print("using .toPath() of File class \n\t");
		File parent = new File(directory.toString());
		File resoverFile = new File(parent, "locations.txt");
		System.out.println(resoverFile.toPath());
		
		System.out.print("using .resovle() of Path class \n\t");
		Path parentPath = Paths.get(directory.toString());
		Path childRelativePath = Paths.get("locations.txt");
		System.out.println(parentPath.resolve(childRelativePath));
		
	}

}
