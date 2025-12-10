package gh2;
import deque.ArrayDeque61B;
import deque.Deque61B;
import deque.LinkedListDeque61B;
// TODO: maybe more imports

//Note: This file will not compile until you complete the Deque61B implementations
//注意：在您完成 Deque61B 的实现之前，此文件无法编译。
public class GuitarString extends LinkedListDeque61B{
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    /** 常量。请勿修改。如果您感到好奇，关键字 final
     * 表示这些值在运行时无法更改。我们将在周五的课堂上讨论这个问题以及其他相关主题。*/
    private static final int SR = 44100;      // Sampling Rate采样率
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    // TODO: uncomment the following line once you're ready to start this portion
    /* 用于存储声音数据的缓冲区。*/
// TODO：准备好开始这部分工作后，请取消注释以下代码行
    private Deque61B<Double> buffer;
    long n;

    /* Create a guitar string of the given frequency.  */
    /* 创建一个指定频率的吉他弦。*/
    public GuitarString(double frequency) {
        // TODO: Initialize the buffer with capacity = SR / frequency. You'll need to
        //       cast the result of this division operation into an int. For
        //       better accuracy, use the Math.round() function before casting.
        //       Your should initially fill your buffer with zeros.
        // TODO：初始化缓冲区，容量为 SR / frequency。你需要将除法运算的结果转换为整数。
//       为了提高精度，请在转换之前使用 Math.round() 函数。
//       你应该用零填充缓冲区。
        long cnt=Math.round(frequency);
        n=SR/cnt;
    buffer=new LinkedListDeque61B<>();
    for(int i=1;i<=n-1;i++){
        buffer.addFirst(0.0);
    }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    /* 通过用白噪声替换缓冲区来模拟拨动吉他弦的声音。*/
    public void pluck() {
        // TODO: Dequeue everything in buffer, and replace with random numbers
        //       between -0.5 and 0.5. You can get such a number by using:
        //       double r = Math.random() - 0.5;
        //
        //       Make sure that your random numbers are different from each
        //       other. This does not mean that you need to check that the numbers
        //       are different from each other. It means you should repeatedly call
        //       Math.random() - 0.5 to generate new random numbers for each array index.
        // TODO: 清空缓冲区中的所有元素，并用介于 -0.5 和 0.5 之间的随机数替换。
//       您可以使用以下方法生成这样的随机数：
//       double r = Math.random() - 0.5;
//
//       请确保生成的随机数彼此不同。这并不意味着您需要检查这些数字是否彼此不同。
//       而是指您应该为数组中的每个索引重复调用 Math.random() - 0.5 来生成新的随机数。
        for(int i=1;i<=n-1;i++){
            buffer.removeFirst();
            buffer.addLast((double) 0);
        }
        for(int i=1;i<=n-1;i++){
            double r = Math.random() - 0.5;
            buffer.removeFirst();
            buffer.addLast(r);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    /* 通过执行一次 Karplus-Strong 算法迭代，使模拟向前推进一个时间步长。
     */
    public void tic() {
        // TODO: Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       **Do not call StdAudio.play().**
        // TODO: 将队列前端的样本出队，并将一个新的样本入队，新样本是前两个样本的平均值乘以衰减因子。
//       **不要调用 StdAudio.play() 方法。**
        for(int i=1;i<n;i++){
            double cnt= buffer.removeFirst();
            double newDouble=DECAY*0.5*(cnt+ buffer.get(1));
            buffer.addLast(newDouble);
        }
    }

    /* Return the double at the front of the buffer. */
    /* 返回缓冲区开头的双精度浮点数。*/
    public double sample() {
        // TODO: Return the correct thing.
        // TODO：返回正确的结果。
        return buffer.get(1);
    }
}
    // TODO: Remove all comments that say TODO when you're done.
// TODO：完成后请删除所有包含“TODO”字样的注释。
