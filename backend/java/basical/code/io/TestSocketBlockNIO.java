import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

public class TestSocketBlockNIO {
    private static final String TEST_DATA_PATH = "resource/test-data.md";
    private static final String TEST_DATA_PATH_NEW = "resource/test-data-new.md";

    @Test
    public void client() throws IOException {
        // 1. Create the client Socket
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
        // 2. Read the local file into the Channel
        FileChannel inChannel = FileChannel.open(Paths.get(TEST_DATA_PATH), StandardOpenOption.READ);
        ByteBuffer buf = ByteBuffer.allocate(1024);
        while (inChannel.read(buf) != -1) {
            buf.flip();
            sChannel.write(buf);
            buf.clear();
        }
        // 3. Shutdown the connection for writing without closing the channel.
        sChannel.shutdownOutput();

        // 4. Receive feedback from the server
        int len = 0;
        while ((len = sChannel.read(buf)) != -1) {
            buf.flip();
            System.out.println(new String(buf.array(), 0, len));
            buf.clear();
        }

        // 5. Closes resource.
        inChannel.close();
        sChannel.close();
    }

    @Test
    public void server() throws IOException {
        // 1.Create a server Socket
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        // 2. Binds the channel's socket to a local address and configures the socket to listen for connections.
        ssChannel.bind(new InetSocketAddress(9898));
        // 3. Accept client Socket
        SocketChannel sChannel = ssChannel.accept();
        // 4. Writes a sequence of bytes to this channel from the given buffer.
        FileChannel outChannel = FileChannel.open(Paths.get(TEST_DATA_PATH_NEW), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        ByteBuffer buf = ByteBuffer.allocate(1024);
        while (sChannel.read(buf) != -1) {
            buf.flip();
            outChannel.write(buf);
            buf.clear();
        }

        // 5. Send feedback to the client
        buf.put("服务端接收数据成功".getBytes());
        buf.flip();
        sChannel.write(buf);

        // 6. Closes resource.
        sChannel.close();
        outChannel.close();
        ssChannel.close();
    }
}
