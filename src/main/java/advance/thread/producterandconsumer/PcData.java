package advance.thread.producterandconsumer;

/**
 * 容器数据类型
 *
 * @author ctk
 */
public class PcData {

    private final int intData;

    public PcData(int d) {
        intData = d;
    }

    public PcData(String d) {
        intData = Integer.valueOf(d);
    }

    public int getData() {
        return intData;
    }

    @Override
    public String toString() {
        return "data:" + intData;
    }
}