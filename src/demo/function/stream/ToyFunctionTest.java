package demo.function.stream;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ToyFunctionTest {

    public static String dateFormat(LocalDateTime dateTime , DateTimeFormatter format){
        return dateTime.format( format );
    }

    public static void main(String[] args) {
        // 直接调用
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter isoDateTime = DateTimeFormatter.ISO_DATE_TIME;
        String format = ToyFunctionTest.dateFormat(now, isoDateTime);
        System.out.println( format );

        // 匿名内部类
        ToyFunction<String, LocalDateTime, DateTimeFormatter> toyFunction = new ToyFunction<String, LocalDateTime, DateTimeFormatter>() {

            @Override
            public String run(LocalDateTime dateTime, DateTimeFormatter dateTimeFormatter) {
                return ToyFunctionTest.dateFormat(dateTime, dateTimeFormatter);
            }
        };
        String res = toyFunction.run(now, isoDateTime);
        System.out.println( res );

        // 匿名内部类 --简化--> Lambda 表达式
        toyFunction = (LocalDateTime dateTime, DateTimeFormatter dateTimeFormatter) -> {
                return ToyFunctionTest.dateFormat(dateTime, dateTimeFormatter);
        };
        res = toyFunction.run(now, isoDateTime);
        System.out.println( res );

        // 函数方法引用
        ToyFunction<String, LocalDateTime, DateTimeFormatter> toyFunction2 = ToyFunctionTest::dateFormat;
        res = toyFunction.run(now, isoDateTime);
        System.out.println( res );

    }
}
