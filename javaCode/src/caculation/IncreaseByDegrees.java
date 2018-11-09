package caculation;

import java.math.BigDecimal;

public class IncreaseByDegrees {

    /**
     * 根据第一年的价格，和每年递增的价格计算每年的收入以及总收入
     *
     * @param origin   第一年价格
     * @param modeNum  舍入方式
     * @param newScale 精度位数 4：四舍五入
     * @param degrees  递增百分比
     */
    public static void printIncreaseByDegrees(String origin, int modeNum, int newScale, String... degrees) {
        BigDecimal sum = BigDecimal.ZERO;
        int mode = getMode(modeNum);
        BigDecimal tempOrigin = new BigDecimal(origin).setScale(newScale, mode);
        sum = sum.add(tempOrigin);
        System.out.println("第1年：" + tempOrigin + "--->总共累计：" + sum);
        if (degrees!=null || degrees.length < 1) return;
        if (degrees.length ==1 && "".equals(degrees[0]) ) return;
        for (int i = 0; i < degrees.length; i++) {
            BigDecimal tempDegree = new BigDecimal(degrees[i]);
            tempOrigin = tempOrigin.add(tempOrigin.multiply(tempDegree).divide(new BigDecimal(100)).setScale(newScale, mode)).setScale(newScale, mode);
            sum = sum.add(tempOrigin);
            System.out.println("第" + (i + 2) + "年：" + tempOrigin + "--->总共累计：" + sum);
        }
    }

    public static int getMode(int modeNum) {
        switch (modeNum) {
            case 0:
                //（常量字段值0）远离零的舍入模式（向上舍入）。舍弃某部分时，若舍弃部分非零则对其前面的数字加1（此舍入模式始终不会减少计算值的大小）
                return BigDecimal.ROUND_UP;
            case 1:
                //（常量字段值1）接近零的舍入模式（向下舍入）。直接丢弃需舍弃部分（此舍入模式始终不会增加计算值的大小）
                return BigDecimal.ROUND_DOWN;
            case 2:
                //（常量字段值2）接近正无穷大的舍入模式。若BigDecimal为正，则舍入行为同ROUND_UP；若为负，则舍入行为同ROUND_DOWN（此舍入模式始终不会减少计算值大小）
                return BigDecimal.ROUND_CEILING;
            case 3:
                //（常量字段值3）接近负无穷大（不是无穷小哦）的舍入模式。其行为与ROUND_CEILING相反，若BigDecimal为负，则舍入行为同ROUND_UP；若为正，则舍入行为同ROUND_DOWN（此舍入模式始终不会增加计算值大小）
                return BigDecimal.ROUND_FLOOR;
            case 4:
                //（常量字段值4）向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则为向上舍入的舍入模式（四舍五入，即舍弃部分>=0.5则向上舍入，否则向下舍入）
                return BigDecimal.ROUND_HALF_UP;
            case 5:
                //（常量字段值5）向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则为向下舍入的舍入模式（舍弃部分<=0.5则向下舍入，否则向上舍入）
                return BigDecimal.ROUND_HALF_DOWN;
            case 6:
                //（常量字段值6）向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则向相邻的偶数舍入（在重复进行一系列计算时，此舍入模式可以将累加错误减到最小）
                return BigDecimal.ROUND_HALF_EVEN;
            case 7:
                //（常量字段值7）断言请求的操作具有精确的结枚，因此不需要舍入，若该操作无精确结果（如1/3）则抛出 ArithmeticException
                return BigDecimal.ROUND_UNNECESSARY;
            default:
                return BigDecimal.ROUND_HALF_UP;

        }
    }

    public static void printEverySquarePrice(String yearPrice,String square){
        BigDecimal tempYearPrice = new BigDecimal(yearPrice);
        BigDecimal tempSquare = new BigDecimal(square);
        System.out.println(tempYearPrice.divide(new BigDecimal(365),3,BigDecimal.ROUND_HALF_UP).divide(tempSquare,3,BigDecimal.ROUND_HALF_UP)+"元/每平方米/天");
    }

    public static void main(String[] args) {
        printEverySquarePrice("110000","45.1");
        printIncreaseByDegrees("110000", 4, 3, "3", "5", "7");
    }

}
