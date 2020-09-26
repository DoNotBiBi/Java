import com.sun.jdi.event.StepEvent;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.sql.*;
import java.util.Date;
import java.util.Objects;
import java.util.PrimitiveIterator;

public class Test {
    public void Test_BRead() throws IOException {
        char c;
        // 使用 System.in 创建 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter lines of text.");
        System.out.println("Enter 'end' to quit.");
        // 读取字符
        try {
            String str;
            str = br.readLine();
            while (!str.equals("end")) {
                System.out.println(str);
                str = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void ConnectToMysql() {
        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        //数据库的用户名与密码，需要根据自己的设置
        final String USER = "root";
        final String PASS = "newlife1994";
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection("jdbc:mysql://localhost/test?useSSL=FALSE&serverTimezone=UTC&allowPublicKeyRetrieval=true", USER, PASS);
            //打开链接
            System.out.println("连接数据库...");

            /*执行查询*/
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM user_info";
            ResultSet rs = stmt.executeQuery(sql);

            //展开结果集数据库
            while (rs.next()) {
                // 通过字段检索

                String name = rs.getString("name");
                int age = rs.getInt("age");

                /* 输出数据 */
                System.out.print("name : " + name);
                System.out.print(",age: " + age);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
                ;
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }

    // 测试读写JSON字符串
    public void ParseJSON_String() {
        //记得转义，因为最外层已经是"",里面的""要转义
        String jsonStr = "{\"request\":\"success\",\"age\":18,\"school\":\"清华大学\"}";
        JSONObject jsonObj = new JSONObject(jsonStr);
        int age = jsonObj.getInt("age");
        String request = (String) jsonObj.get("request");
        String school = (String) jsonObj.get("school");
        System.out.println(age);
        System.out.println(request);
        System.out.println(school);
    }

    // 将JSON文件转化为字符串
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuilder sb = new StringBuilder();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 测试解析JSON 文件
    public void ParseJSON_File() {
        String path = main.class.getClassLoader().getResource("resources/movies.json").getPath();
        String s = readJsonFile(path);
        JSONObject jobj = new JSONObject(s);
        JSONArray movies = jobj.getJSONArray("RECORDS");//构建JSONArray数组
        for (int i = 0; i < movies.length(); i++) {
            JSONObject key = (JSONObject) movies.get(i);
            String name = (String) key.get("name");
            String director = (String) key.get("director");
            String scenarist = ((String) key.get("scenarist"));
            String actors = (String) key.get("actors");
            String type = (String) key.get("type");
            String ratingNum = (String) key.get("ratingNum");
            String tags = (String) key.get("tags");
            System.out.println(name);
            System.out.println(director);
            System.out.println(scenarist);
            System.out.println(actors);
            System.out.println(type);
            System.out.println(director);
            System.out.println(ratingNum);
            System.out.println(tags);
        }
    }

    // 测试字符串
    public void Test_String() {
        // String greeting="wangkang";
        // 字符数组--首选声明的方法
        char[] helloArray = {'w', 'a', 'n', 'g', 'k', 'a', 'n', 'g'};
        for (int i = 0; i < helloArray.length; i++) {
            System.out.println(helloArray[i]);
        }
        String helloString = new String(helloArray);
        System.out.println(helloString);
        System.out.println("字符串的长度：" + helloString.length());
        System.out.println(helloString + "ustc"); // 连接字符串
        String fs;
        float floatVar = 2.3f;
        int intVar = 3;
        String stringVar = "hello wangkang";
        fs = String.format("浮点型变量的值为 " +
                "%f, 整型变量的值为 " +
                " %d, 字符串变量的值为 " +
                " %s", floatVar, intVar, stringVar);
        System.out.println(fs);
        System.out.println("--------------------------------------------");
        StringBuilder t_sb = new StringBuilder("hello");
        char[] str = {'1', '3', '5', '7', '9', 'a', 'b', 'y'};
        t_sb.append("ehhlo");
        t_sb.append(str, 2, 4);
        System.out.println(t_sb);
        System.out.println(t_sb.charAt(2));
        System.out.println();

    }

    public void Test_TimeAndDate() {
        Date date = new Date();
        System.out.println(date.toString());
    }

    // 读取文件
    public String Test_ReadFile() throws FileNotFoundException {
        File file = new File(main.class.getClassLoader().getResource("resources/hello.txt").getPath());
        if (file.isFile() && file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String text = null;
                while ((text = bufferedReader.readLine()) != null) {
                    sb.append(text);
                }
                return sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;

    }
}
