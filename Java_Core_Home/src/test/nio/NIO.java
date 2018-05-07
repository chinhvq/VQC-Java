package test.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class NIO {
	public static void main(String[] args) {

		// write a binary file with nio package
		try (FileOutputStream binFile = new FileOutputStream("data.dat");
				FileChannel binChannel = binFile.getChannel()) {

			// write a string to the buffer then write into the binary file
			byte[] outputBytes = "Hello World".getBytes();
			ByteBuffer buffer = ByteBuffer.wrap(outputBytes);
			int numBytes = binChannel.write(buffer);
			System.out.println("numBytes written was: " + numBytes);

			// write a int value to a binary file
			ByteBuffer intBuffer = ByteBuffer.allocate(3*Integer.BYTES);
			intBuffer.putInt(100);
			intBuffer.putInt(101);
			intBuffer.putInt(102);
			intBuffer.flip();
			numBytes = binChannel.write(intBuffer);
			System.out.println("numBytes written was: " + numBytes + ":" + intBuffer.getInt(0) +":" + intBuffer.getInt(4) + ":" + intBuffer.getInt(8));

			intBuffer.position(8);
			intBuffer.putInt(-103);
			intBuffer.position(8);
			numBytes = binChannel.write(intBuffer);
			System.out.println("numBytes written was: " + numBytes + ":" + intBuffer.getInt(0) +":" + intBuffer.getInt(4) + ":" + intBuffer.getInt(8));
			System.out.println("Write at position 8 successfully");

			// read String from file using NIO package
			RandomAccessFile ra = new RandomAccessFile("data.dat", "rwd");
			buffer.flip();
			FileChannel channel = ra.getChannel();
			// method 1 to read String (byte buffer) from binary file
			// numBytes = channel.read(buffer);
			// System.out.println(new String(outputBytes) + " and " + numBytes + " bytes");

			// method 2 use buffer.array()
			if (buffer.hasArray()) {
				System.out.println("Byte buffer: " + new String(buffer.array()));
			}
			
			//Absolute Read Integer from binary file using NIO package . 
			//NOTED - THIS NOT WORK AS VIDEO
//			intBuffer.flip();
			numBytes = channel.read(intBuffer);
			System.out.println(intBuffer.getInt(0) + " : " + numBytes + " bytes");
//			intBuffer.flip();
			numBytes = channel.read(intBuffer);
			System.out.println(intBuffer.getInt(4) + " : " + numBytes + " bytes");
//			intBuffer.flip();
			numBytes = channel.read(intBuffer);
			System.out.println(intBuffer.getInt(8) + " : " + numBytes + " bytes");
			
			//Relative Read Integer from binary file using NIO package
//			intBuffer.flip();
//			numBytes = channel.read(intBuffer);
//			intBuffer.flip();
//			System.out.println(intBuffer.getInt());
//			intBuffer.flip();
//			numBytes = channel.read(intBuffer);
//			intBuffer.flip();
//			System.out.println(intBuffer.getInt());
//			intBuffer.flip();
//			numBytes = channel.read(intBuffer);
//			intBuffer.flip();
//			System.out.println(intBuffer.getInt());
//			intBuffer.flip();
//			numBytes = channel.read(intBuffer);
//			intBuffer.flip();
//			System.out.println(intBuffer.getInt());	
			

			// read String from binary file into a byte[] using IO package
//			 RandomAccessFile ra = new RandomAccessFile("data.dat", "rwd");
			// byte[] b = new byte[outputBytes.length];
			// ra.read(b);
			// System.out.println(new String(b));

			//read String from binary file
//			 int a1 = ra.readInt();
//			 int a2 = ra.readInt();
//			 int a3 = ra.readInt();
//			 int a4 = ra.readInt();
//			 int a5 = ra.readInt();
//			 System.out.println(a1 + ":" + a2 + ":" + a3 + ":" + a4 + " : " + a5);

		} catch (IOException e) {
			e.printStackTrace();
		}

		// working with text file => read all File content Files.readAllLines()
		// try {
		// Path dataPath = FileSystems.getDefault().getPath("test.txt");
		// // Files.write(dataPath, "\nENJOY THE WEEKEND".getBytes("UTF-8"),
		// // StandardOpenOption.APPEND);
		// // List<String> lines = Files.readAllLines(dataPath);
		// List<String> lines = Files.readAllLines(dataPath, StandardCharsets.US_ASCII);
		// for (String line : lines) {
		// System.out.println(line);
		// }
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}

}
