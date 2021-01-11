package bio;

import java.nio.IntBuffer;

/**
 * @ClassName NioBufferTest
 * @Description TODO
 * @Author hanjun
 * @Date 2021/1/11 14:52
 **/
public class NioBufferTest {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(5);
        for(int i=0; i<intBuffer.capacity();i++){
            intBuffer.put(i * 3);
        }

        intBuffer.flip(); //翻转指针
        while(intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }

    }
}
