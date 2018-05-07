package test.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIO2 {

	public static void main(String[] args) {

		try (FileOutputStream binFile = new FileOutputStream("data2.dat");
				FileChannel binChannel = binFile.getChannel()) {

			// //write a string to the buffer then write into the binary file. By change the
			// put()
			// ByteBuffer buffer = ByteBuffer.allocate(100);
			// byte[] outputByte = "Hello World".getBytes();
			// byte[] outputByte2 = "Nice to meet you!".getBytes();
			// buffer.put(outputByte).putInt(100).putInt(-101).put(outputByte2).putInt(102).putInt(-103);
			// buffer.flip();
			// binChannel.write(buffer);

			// write a string to the buffer then write into the binary file. Line by line
			ByteBuffer buffer = ByteBuffer.allocate(100);
			byte[] outputByte = "Hello World".getBytes();
			buffer.put(outputByte);
			int int1Pos = outputByte.length;
			buffer.putInt(100);
			int int2Pos = int1Pos + Integer.BYTES;
			buffer.putInt(-101);
			byte[] outputByte2 = "Nice to meet you!".getBytes();
			buffer.put(outputByte2);
			int int3Pos = int2Pos + Integer.BYTES + outputByte2.length;
			buffer.putInt(102);
			buffer.putInt(-103);
			buffer.flip();
			binChannel.write(buffer);

			// read Random by random index
			try (RandomAccessFile ra = new RandomAccessFile("data2.dat", "rwd");
					FileChannel channel = ra.getChannel()) {

				ByteBuffer readBuffer = ByteBuffer.allocate(Integer.BYTES);
				channel.position(int3Pos);
				channel.read(readBuffer);
				readBuffer.flip();

				System.out.println(readBuffer.getInt());
				readBuffer.flip();
				channel.position(int2Pos);
				channel.read(readBuffer);
				readBuffer.flip();

				System.out.println(readBuffer.getInt());
				readBuffer.flip();
				channel.position(int1Pos);
				channel.read(readBuffer);
				readBuffer.flip();

				System.out.println(readBuffer.getInt());

				//copy from data2.data to new File
				RandomAccessFile copyFile = new RandomAccessFile("data_copy_tranferTo.dat", "rwd");
				FileChannel copyChannel = copyFile.getChannel();
				channel.position(0);
				long numTransferred = channel.transferTo(0, channel.size(), copyChannel);
//				long numTransferred = copyChannel.transferFrom(channel, 0, channel.size());
				System.out.println("Number of Transfer is " + numTransferred);

				// ByteBuffer readBuffer = ByteBuffer.allocate(100);
				// channel.read(readBuffer);
				// readBuffer.flip();
				// byte[] inputString = new byte[outputByte.length];
				// readBuffer.get(inputString);
				// System.out.println("input String = " + new String(inputString));
				// int a1 = readBuffer.getInt();
				// int a2 = readBuffer.getInt();
				// System.out.println(a1 + " : " + a2);
				// byte[] inputString2 = new byte[outputByte2.length];
				// readBuffer.get(inputString2);
				// System.out.println("input String2 = " + new String(inputString2));
				// int a3 = readBuffer.getInt();
				// int a4 = readBuffer.getInt();
				// System.out.println(a3 + " : " + a4);

			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
