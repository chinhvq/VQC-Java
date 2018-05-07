package test.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class NioPipe {

	public static void main(String[] args) {
		try {
			//use Pipe to send data from one Thread to one another 
			Pipe pipe = Pipe.open();

			Runnable writer = new Runnable() {
				@Override
				public void run() {
					try {
						Pipe.SinkChannel sinkChannel = pipe.sink();
						ByteBuffer buffer = ByteBuffer.allocate(56);
						for (int i = 0; i < 10; i++) {
							String currentTime = "Current Time is " + System.currentTimeMillis();
							buffer.put(currentTime.getBytes());
							buffer.flip();

							while (buffer.hasRemaining()) {
								sinkChannel.write(buffer);
							}
							buffer.flip();
							// sleep 100ms for reading thread hava change to read data from sinkChannel
							Thread.sleep(100);
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};

			Runnable reader = new Runnable() {
				@Override
				public void run() {
					try {
						Pipe.SourceChannel sourceChannel = pipe.source();
						ByteBuffer buffer = ByteBuffer.allocate(56);
						for (int i = 0; i < 10; i++) {
							int byteRead = sourceChannel.read(buffer);
							byte[] timeString = new byte[byteRead];
							buffer.flip();
							buffer.get(timeString);
							System.out.println("Reader thread: " + new String(timeString));
							buffer.flip();
							Thread.sleep(100);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			
			new Thread(writer).start();
			new Thread(reader).start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
