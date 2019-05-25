package test;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Session;
import org.junit.Test;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;

/**
email_template.properties文件:
    subject=\u6765\u81EA\u4F20\u667A\u64AD\u5BA2\u7F51\u4E0A\u4E66\u57CE\u7684\u6FC0\u6D3B\u90AE\u4EF6
    content=\u606D\u559C\uFF0C\u60A8\u5DF2\u6CE8\u518C\u6210\u529F\uFF0C\u8BF7nishdfbndfhbnsdkfjlbn;ekldvdjkfndvj\u5357\u65B9ID\u8985\u548C\u5927V\u623F\u95F4\u5185 \u75AF\u72C2\u7684\u51CF\u80A5\u975E\uFF1B\u5C31 \u5C31\u56E7 \u3002
    to=1035950489@qq.com
    from=1252068782@qq.com
    host=smtp.qq.com
    username=1252068782@qq.com
    password=qhtifkeiioadhife
 */
public class sendEmail {
    @Test
    public  void testSendEmail() {
        /*
         * 3. 发邮件
         */
        /*
         * 把配置文件内容加载到prop中
         */
        Properties prop = new Properties();
        try {
            prop.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));
        } catch (IOException e1) {
            throw new RuntimeException(e1);
        }
        /*
         * 登录邮件服务器，得到session
         */
        String host = prop.getProperty("host");//服务器主机名
        String name = prop.getProperty("username");//登录名
        String pass = prop.getProperty("password");//登录密码
        Session session = MailUtils.createSession(host, name, pass);

        /*
         * 创建Mail对象
         */
        String from = prop.getProperty("from");
        String to = prop.getProperty("to");
        String subject = prop.getProperty("subject");
        // MessageForm.format方法会把第一个参数中的{0},使用第二个参数来替换。
        // 例如MessageFormat.format("你好{0}, 你{1}!", "张三", "去死吧"); 返回“你好张三，你去死吧！”
        String content = MessageFormat.format(prop.getProperty("content"), "laisnhunjshu");
        Mail mail = new Mail(from, to, subject, content);
        /*
         * 发送邮件
         */
        try {
            MailUtils.send(session, mail);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public  void _forver() {
        for(int i=0;i<100;i++){
            testSendEmail();
        }
    }
}
