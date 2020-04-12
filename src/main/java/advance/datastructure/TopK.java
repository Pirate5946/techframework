package advance.datastructure;

import cn.hutool.core.io.FileUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Random;

/**
 *   1. 通过多线程 随机生成 1000万个int到文件中
 *   2. 通过多线程 从1000万个int 中 找到最小的 10个int
 *   	核心代码： Arrays.parallelSort （对 int[] 进行多线程排序）
 *   运行程序发现   time1到time2 文件IO 花费了大部分时间
 */
public class TopK {
	public static void main(String args[]) throws IOException{
		
//		CreateFile();

		long startTime = System.currentTimeMillis();
		
		getTopK();

		long endTime = System.currentTimeMillis();
		System.out.println("程序运行时间(秒) : " + (endTime - startTime) / 1000.0);


	}

	public static void getTopK() throws IOException {
		int[] intArray = getIntArrayFromFile();

		long startTime = System.currentTimeMillis();
		//Java8 的API，采用多线程，  JDK8之前使用  Arrays.sort();
		Arrays.parallelSort(intArray);
		long endTime = System.currentTimeMillis();

		System.out.println("Arrays.parallelSort 排序时间(秒) : " + (endTime - startTime) / 1000.0);

		for (int i = 0; i < 10; i++) {
			System.out.println(intArray[i]);
		}

	}

	public static int[] getIntArrayFromFile() throws IOException {
		File file = FileUtil.file("TopK1.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = null;
		StringBuffer sb = new StringBuffer();

		// 读取到 string 后 ，应该转换成 int 数组
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		String string = sb.toString();

		String[] stringArray = string.split(",");
		int[] intArray = new int[stringArray.length];

		for (int i = 0; i < stringArray.length; i++) {
			intArray[i] = Integer.parseInt(stringArray[i]);
		}
		br.close();
		return intArray;
	}

	public static void CreateFile() throws IOException {
		File file = FileUtil.file("TopK1.txt");
		
		file.createNewFile();
		
		System.out.println(file.getAbsolutePath());
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		Random random = new Random();
		String sep = System.lineSeparator();
		
		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < 10000; j++) {
				int a = Math.abs(random.nextInt());
				bw.write(a + ",");
			}
			bw.write(sep);//bw.newLine();			
		}
		bw.flush();
		bw.close();
	}
	
	public long readByFis() throws IOException{
		File file = FileUtil.file("TopK1.txt");
        FileInputStream inputStream = new FileInputStream(file);

        byte[] buffer = new byte[40960];
        long counts = 0;
        int offset = 0;
        
        while ((offset = inputStream.read(buffer)) != -1) {
			counts = counts + offset;
		}
        inputStream.close();
        return counts;
	}
	
	public long readByChannel() throws IOException{
		File file = FileUtil.file("TopK1.txt");
        FileInputStream inputStream = new FileInputStream(file);
        FileChannel channel = inputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024 * 10);
        
        long counts = 0;
        int offset = 0;
        
        while ((offset = channel.read(byteBuffer)) != -1) {
			counts = counts + offset;
			byteBuffer.clear();
		}
        channel.close();
        inputStream.close();
        return counts;
	}
}
