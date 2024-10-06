package demo.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {
    public static void main(String[] args) {
        // a == 10.1 && b=='2' || ( xxx > '1.2.3' ) 对应的变量依次为a,b,xxx;逻辑符号位==,>;常量为10.1,2,1.2.3
//        String expression = "a == 10.1 && b=='2'"; // 定义一个关系表达式
        String expression = "a == 10.1 && b=='2' || ( xxx > '1.2.3' )"; // 定义一个关系表达式

        /**
         * 使用Java语言处理关系表达式，并分别输出表达式中的变量和常量和符号，尽量使用规则引擎
         *
         (\\w+)表示匹配一个或多个字母、数字或下划线，例如"abc"、“123"或”_xyz"。括号表示将匹配的内容记住以备后续使用。1
         \\s*表示匹配零个或多个空白字符，例如空格、制表符或换行符。1
         (==|!=|>|<|>=|<=)表示匹配其中一个关系运算符，例如"==“、”!=“或”>="。括号表示将匹配的内容记住以备后续使用。1
         */
        String rule = "(\\w+)\\s*(==|!=|>|<|>=|<=)\\s*(\\'*\\w+\\.*\\w*\\.*\\w*\\'*)"; // 定义一个规则模式，用来匹配变量、符号和常量
        Pattern pattern = Pattern.compile(rule); // 创建一个Pattern对象，用来编译规则模式
        Matcher matcher = pattern.matcher(expression); // 创建一个Matcher对象，用来匹配关系表达式
        while (matcher.find()) { // 如果匹配成功
            System.out.println("变量：" + matcher.group(1)); // 输出第一个捕获组，即变量
            System.out.println("符号：" + matcher.group(2)); // 输出第二个捕获组，即符号
            System.out.println("常量：" + matcher.group(3)); // 输出第三个捕获组，即常量
        }

//        else { // 如果匹配失败
//            System.out.println("无法匹配关系表达式"); // 输出提示信息
//        }
    }
}
