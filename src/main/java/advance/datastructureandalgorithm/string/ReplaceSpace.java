package advance.datastructureandalgorithm.string;

/**
 * description: 将一个字符串中的每个空格替换成“%20”
 *
 * @author : LIUTAO
 * create at : 2020/4/12 下午10:11
 * @see <a href="https://www.nowcoder.com/practice/4060ac7e3e404ad1a894ef3e17650423?tpId=13&tqId=11155&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking">牛客网题目 - 将一个字符串中的每个空格替换成 其他字符</a>
 **/
public class ReplaceSpace {

    public String replaceSpace(StringBuffer str) {
        if(str == null || str.length() == 0) {
            return "";
        }
        char[] array = str.toString().toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < array.length; i++) {
            if(array[i] == ' ') {
                sb.append("%20");
            } else {
                sb.append(array[i]);
            }
        }
        return sb.toString();
    }
}
