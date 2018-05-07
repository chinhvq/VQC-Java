package test.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

public class PathTest {

	public static void main(String[] args) {
		//absolute Path
//		Path filePath = Paths.get("D:\\LAP TRINH\\iMIC\\note.txt");
//		printFile(filePath);
//		System.out.println("==========");
//		Path path = FileSystems.getDefault().getPath("Java_Core_Home","src","test","nio","test.txt");
//		printFile(path);
//		//sub-folder accessing
//		System.out.println("==========");
//		path = Paths.get("Java_Core_Home","src","test","nio", "subfolder", "java.txt");
//		printFile(path);
//		//get current directory
//		Path currentWorkingDirectory = Paths.get(".");
//		System.out.println("\n" + currentWorkingDirectory.toAbsolutePath());
//		
//		// . is current directory && .. is parent directory
//		System.out.println("==========");
//		path = FileSystems.getDefault().getPath(".", "..", "..", "..", "..", "iMIC","note.txt");
//		System.out.println("Printing - " + path.normalize().toAbsolutePath());
//		printFile(path);
//		
//		Path failPath = Paths.get("E:\\abc.txt");
//		System.out.println("\nFile abc.txt exist or not ==>" + Files.exists(failPath));
//		
//		//copy file with Files.copy
		try {
//			Path sourceFile = FileSystems.getDefault().getPath("Java_Core_Home","src","test","nio","test.txt");
//			Path copyFile = FileSystems.getDefault().getPath("Java_Core_Home","src","test","nio","test_copy.txt");
//			//this command will throw Exception if the copyFile already exist
////			Files.copy(sourceFile, copyFile);
//			Files.copy(sourceFile, copyFile, StandardCopyOption.REPLACE_EXISTING);
//			
//			//copy folder only(not copy file inside folder
//			Path sourceFolder = FileSystems.getDefault().getPath("Java_Core_Home","src","test","nio","folder1");
//			Path copyFolder = FileSystems.getDefault().getPath("Java_Core_Home","src","test","nio","folder2");
//			Files.copy(sourceFolder, copyFolder, StandardCopyOption.REPLACE_EXISTING);
			
			//move and rename file at the same time
//			Path fileToMove = FileSystems.getDefault().getPath("Java_Core_Home","src","test","nio","folder1", "test.txt");
//			Path destionationToMove = FileSystems.getDefault().getPath("Java_Core_Home","src","test","nio","folder2", "test_moved.txt");
//			Files.move(fileToMove, destionationToMove);
			
			//delete file
//			Path fileToDelete = FileSystems.getDefault().getPath("Java_Core_Home","src","test","nio","folder2", "test_moved.txt");
//			Files.delete(fileToDelete);
//			//this command will not throw Exception if file not exist
//			Files.deleteIfExists(fileToDelete);
			
			//delete an empty folder
//			Path folderToDelete = FileSystems.getDefault().getPath("Java_Core_Home","src","test","nio","folder2");
//			Files.deleteIfExists(folderToDelete);
//			
//			//create multi nested directory 
//			Path dirCreate = FileSystems.getDefault().getPath("Java_Core_Home","src","test","nio","folder1", "folder2\\folder3");
//			Files.createDirectories(dirCreate);
			
			Path path = FileSystems.getDefault().getPath(".", "..", "..", "..", "..", "iMIC","note.txt");
			System.out.println("Size = " + Files.size(path) + " bytes");
			System.out.println("Last modified : " + Files.getLastModifiedTime(path));
			
			System.out.println("=====Get all basic attributes of file or folder=====");
			BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
			System.out.println("Size is " + attr.size());
			System.out.println("Last modified is " + attr.lastModifiedTime());
			System.out.println("Created time is " + attr.creationTime());
			System.out.println("Is directory " + attr.isDirectory());
			System.out.println("Is file " + attr.isRegularFile());
			
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		
		
	}

	private static void printFile(Path path) {
		try (BufferedReader buffer = Files.newBufferedReader(path)) {
			String line;
			while ((line = buffer.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
